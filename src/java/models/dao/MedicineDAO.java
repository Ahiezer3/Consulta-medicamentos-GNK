
package models.dao;

import config.MyConnection;
import interfaces.MedicineCRUD;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Medicine;
import models.User;

/**
 * Class for MedicineDAO.
 * @author abi_h
 * @since 24/03/2023
 */
public class MedicineDAO implements MedicineCRUD{
    
    //Data for connection with data base and model
    MyConnection myConnection;
    Connection connection;
    PreparedStatement prepareStatement;
    Statement statement;
    ResultSet result;
    Medicine medicine = new Medicine();

    public MedicineDAO() throws Exception {
        this.myConnection = new MyConnection();
    }
    
    /**
     * To add a new medicine.
     * @param descriptionInput
     * @param storageInput
     * @param expirationInput
     * @return
     * @throws Exception 
     */
    @Override
    public Medicine addMedicine(String descriptionInput,String storageInput,String expirationInput) throws Exception{
        
        Medicine medicineAdded = null;
        String query = "INSERT INTO tb_medicines (description,storage,expiration_date,register_date) VALUES (?,?,?,?)";
        String queryInventory = "INSERT INTO tb_inventory(id_medicine,amount) VALUES (?,?)";
        
        try{

            if( descriptionInput == null || 
                    storageInput == null ||
                    expirationInput == null ){
                return null;
            }
            
            String description = descriptionInput.trim();
            String storage = storageInput.trim();
            String expirationDateArray[] = expirationInput.split("-");
            int year = Integer.parseInt(expirationDateArray[0]);
            int month = Integer.parseInt(expirationDateArray[1]);
            int day = Integer.parseInt(expirationDateArray[2]);
            
            LocalDate expirationDate = LocalDate.of(year,month,day);
            
            LocalDateTime dateRegister = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(dateRegister);

            medicineAdded = new Medicine();
            medicineAdded.setDescription(description);
            medicineAdded.setStorage(storage);
            medicineAdded.setDateExpiration(expirationDate);
            medicineAdded.setDateRegister(dateRegister);
            
            java.sql.Date sqlExpirationDate = java.sql.Date.valueOf(expirationDate);
 
            connection = myConnection.getConection();
            
            //Add medicine
            prepareStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1,description);
            prepareStatement.setString(2,storage);
            prepareStatement.setDate(3,sqlExpirationDate);
            prepareStatement.setTimestamp(4,timestamp);
            
            //Know if it was successfully.
            int affectedRows = prepareStatement.executeUpdate();
            if (affectedRows == 0) {
                    throw new Exception("No se pudo guardar el medicamento");
            }

            ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
            Long idMedicineAdded = null;
            
            //Get ID KEY from last medicine inserted.
            if (generatedKeys.next()) {
                     
                    idMedicineAdded = Long.parseLong(String.valueOf(generatedKeys.getInt(1)));
                     
                     //Add inventory
                    PreparedStatement prepareStatementInventory = connection.prepareStatement(queryInventory,Statement.RETURN_GENERATED_KEYS);
                    prepareStatementInventory.setLong(1,idMedicineAdded);
                    prepareStatementInventory.setLong(2,new Long(0));

                    int affectedRowsInventory = prepareStatementInventory.executeUpdate();
                    if (affectedRowsInventory == 0) {
                            throw new Exception("No se pudo guardar el medicamento en el inventario");
                    }
            }
            
        } catch(Exception e){
            System.out.println(e);
            throw new Exception(e);

        } finally{
            
           myConnection.closeAConnection(connection, statement, result);
           
        }
        
        return medicineAdded;
    }

    /**
     * To get medicines.
     * @return
     * @throws Exception 
     */
    @Override
    public List<Medicine> getMedicines() throws Exception{
        List<Medicine> medicines = new ArrayList<>();
        
        String query = "SELECT id, description, storage, expiration_date, register_date FROM tb_medicines";
        
        try{

            connection = myConnection.getConection();
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            
            while( result != null && result.next() ){
                
                Medicine medicine = new Medicine();
                medicine.setId(result.getLong("id"));
                medicine.setDescription(result.getString("description"));
                medicine.setStorage(result.getString("storage"));
                medicine.setDateExpiration(result.getDate("expiration_date").toLocalDate());
                medicine.setDateRegister(result.getTimestamp("register_date").toLocalDateTime());
                
                medicines.add(medicine);
            }
            
        } catch(Exception e){
           
            System.out.println(e);
            throw new Exception(e);

        } finally{
            
           myConnection.closeAConnection(connection, statement, result);
           
        }
        
        return medicines;
    }
    
    /**
     * Get a specific medicine.
     * @param id
     * @return
     * @throws Exception 
     */
    @Override
    public Medicine getMedicine(Long id) throws Exception{
        
         Medicine medicine = null;
        
        String query = "SELECT id, description, storage, expiration_date, register_date FROM tb_medicines WHERE id = (?)";
        
        try{

            connection = myConnection.getConection();
            prepareStatement = connection.prepareStatement(query);
            prepareStatement.setLong(1,id);
            
            result = prepareStatement.executeQuery();
            
            while( result != null && result.next() ){
                
                medicine = new Medicine();
                medicine.setId(result.getLong("id"));
                medicine.setDescription(result.getString("description"));
                medicine.setStorage(result.getString("storage"));
                medicine.setDateExpiration(result.getDate("expiration_date").toLocalDate());
                medicine.setDateRegister(result.getTimestamp("register_date").toLocalDateTime());
            }
            
        } catch(Exception e){
          
                System.out.println(e);
                throw new Exception(e);

        } finally{
            
           myConnection.closeAConnection(connection, statement, result);
           
        }
        
        return medicine;
    }
    
}
