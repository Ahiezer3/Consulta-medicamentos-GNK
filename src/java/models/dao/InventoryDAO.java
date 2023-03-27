
package models.dao;

import config.MyConnection;
import interfaces.InventoryCRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Inventory;
import models.Medicine;
import models.User;

/**
 * Class for InventoryDAO.
 * @author abi_h
 * @since 24/03/2023
 */
public class InventoryDAO implements InventoryCRUD{
    
    //Data for connection with database.
    MyConnection myConnection;
    Connection connection;
    PreparedStatement prepareStatement;
    Statement statement;
    ResultSet result;
    Medicine medicine = new Medicine();

    public InventoryDAO() throws Exception {
        this.myConnection = new MyConnection();
    }
    
    /**
     * Method to get an specific inventory.
     * @param medicine
     * @return 
     */
    @Override
    public Inventory getInventory(Medicine medicine) {
        
        Inventory inventory = null;
        String query = "SELECT id, id_medicine, amount FROM tb_inventory WHERE id_medicine = (?) LIMIT 1";
        
        try{
            connection = myConnection.getConection();
            prepareStatement = connection.prepareStatement(query);
            prepareStatement.setLong(1, medicine.getId());
            result = prepareStatement.executeQuery();
            
            while( result != null && result.next() ){
                inventory = new Inventory();
                inventory.setId(result.getLong("id"));
                inventory.setMedicine(medicine);
                inventory.setAmount(result.getLong("amount"));
            }
        } catch(Exception e){

                System.out.println("Error: "+e);
                
            try {
                throw new Exception(e);
            } catch (Exception ex) {
                Logger.getLogger(InventoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } finally{
            
           myConnection.closeAConnection(connection, statement, result);
           
        }
        
        return inventory;
    }
    
}
