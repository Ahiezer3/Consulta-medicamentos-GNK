
package models.dao;

import config.MyConnection;
import interfaces.InventoryOrdersCRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Inventory;
import models.InventoryOrders;
import models.Medicine;
import models.User;
import tools.TypeOrder;

/**
 * Class for InventoryOrdersDAO.
 * @author abi_h
 * @since 24/03/2023
 */
public class InventoryOrdersDAO implements InventoryOrdersCRUD{
    
    //Data for connection with data base and model.
    MyConnection myConnection;
    Connection connection;
    PreparedStatement prepareStatement;
    Statement statement;
    ResultSet result;
    Medicine medicine = new Medicine();

    public InventoryOrdersDAO() throws Exception {
        this.myConnection = new MyConnection();
    }

    /***
     * It permits to add a new order to data base.
     * @param order
     * @return
     * @throws Exception 
     */
    @Override
    public InventoryOrders addOrder(InventoryOrders order) throws Exception{
        
        String query = "INSERT INTO tb_inventory_orders(id_user, id_inventory, type_order, amount, summary, reason, register_date) VALUES (?,?,?,?,?,?,?)";
        String queryUpdateInventory = "UPDATE tb_inventory SET amount = (?) WHERE id = (?)";
        
        try{

            if( order == null ||
                    order.getUser() == null ||
                    order.getInventory() == null ){
                return null;
            }
            
            //Insert new order.
            Long newSummary = order.getInventory().getAmount()+order.getAmount();
            
            LocalDateTime dateRegister = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(dateRegister);
            
            connection = myConnection.getConection();
            prepareStatement = connection.prepareStatement(query);
            
            prepareStatement.setLong(1,order.getUser().getId());
            prepareStatement.setLong(2,order.getInventory().getId());
            prepareStatement.setString(3,order.getTypeOrder());
            prepareStatement.setLong(4,order.getAmount());
            prepareStatement.setLong(5,newSummary);
            prepareStatement.setString(6,order.getReason());
            prepareStatement.setTimestamp(7,timestamp);
            
            //Validate if exist affected rows in data base for the action before.
            int affectedRows = prepareStatement.executeUpdate();
            if (affectedRows == 0) {
                    throw new Exception("No se pudo guardar la orden");
            } else {
                
                //Update amount from summary amount with this action for IN or OUT in inventory from last order inserted.
                PreparedStatement prepareStatementInventory = connection.prepareStatement(queryUpdateInventory);
            
                prepareStatementInventory.setLong(1,newSummary);
                prepareStatementInventory.setLong(2,order.getInventory().getId());

                int affectedRowsInventory = prepareStatementInventory.executeUpdate();
                if (affectedRowsInventory == 0) {
                        throw new Exception("No se pudo guardar el inventario");
                }
            }
            
        } catch(Exception e){
            System.out.println(e);
 
            throw new Exception(e);

        } finally{
            
           myConnection.closeAConnection(connection, statement, result);
           
        }
        
        return order;
    }
    
    /**
     * Get order inserted with medicine, inventory and user.
     * @param id
     * @return
     * @throws Exception 
     */
    @Override
    public List<InventoryOrders> getOrders(Long id) throws Exception{
        
        List<InventoryOrders> inventories = new ArrayList<>();
        
        String query = "SELECT \n" +
            "orders.id AS id, \n" +
            "orders.id_user AS id_user, \n" +
            "orders.id_inventory AS id_inventory, \n" +
            "orders.type_order AS type_order, \n" +
            "orders.amount AS amount, \n" +
            "orders.summary AS summary, \n" +
            "orders.reason AS reason, \n" +
            "orders.register_date AS register_date,\n" +
            "\n" +
            "inventory.id_medicine AS id_medicine,\n" +
            "\n" +
            "medicines.description AS description_medicine,\n" +
            "medicines.storage AS storage_medicine,\n" +
            "medicines.expiration_date AS expiration_date_medicine,\n" +
            "\n" +
            "users.name AS name_user,\n" +
            "users.lastname AS lastname_user\n" +
            "\n" +
            "FROM tb_inventory_orders AS orders \n" +
            "INNER JOIN tb_inventory AS inventory ON inventory.id = orders.id_inventory\n" +
            "INNER JOIN tb_users AS users ON users.id = orders.id_user\n" +
            "INNER JOIN tb_medicines AS medicines ON medicines.id = inventory.id_medicine\n" +
            "WHERE inventory.id_medicine = (?)\n" +
            "ORDER BY orders.id ASC";

        try{

            connection = myConnection.getConection();
            prepareStatement = connection.prepareStatement(query);
            
            prepareStatement.setLong(1,id);
        
            result = prepareStatement.executeQuery();
            
            while( result != null && result.next() ){
                
                String typeOrder = TypeOrder.getType(result.getString("type_order")).getDescription();
                
                User user = new User();
                user.setId(result.getLong("id_user"));
                user.setName(result.getString("name_user"));
                user.setLastname(result.getString("lastname_user"));
                
                Medicine medicine = new Medicine();
                medicine.setId(id);
                medicine.setDescription(result.getString("description_medicine"));
                medicine.setStorage(result.getString("storage_medicine"));
                medicine.setDateExpiration(result.getDate("expiration_date_medicine").toLocalDate());
                
                Inventory inventory = new Inventory();
                inventory.setId(result.getLong("id_inventory"));
                inventory.setMedicine(medicine);

                InventoryOrders inventoryOrders = new InventoryOrders();
                inventoryOrders.setId(result.getLong("id"));
                inventoryOrders.setAmount(result.getLong("amount"));
                inventoryOrders.setReason(result.getString("reason"));
                inventoryOrders.setRegisterDate(result.getTimestamp("register_date").toLocalDateTime());
                inventoryOrders.setSummary(result.getLong("summary"));
                inventoryOrders.setTypeOrder(typeOrder);
                inventoryOrders.setInventory(inventory);
                inventoryOrders.setUser(user);
                
                inventories.add(inventoryOrders);
                
            }
            
        } catch(Exception e){
            System.out.println(e);
           
             throw new Exception(e);
            

        } finally{
            
           myConnection.closeAConnection(connection, statement, result);
           
        }
        
        return inventories;
    }
}
