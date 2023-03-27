
package models.dao;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;


import config.MyConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;
import interfaces.UserCRUD;

/**
 * Class for UserDAO.
 * @author abi_h
 * @since 24/03/2023
 */
public class UserDAO implements UserCRUD{
    
    //Data for connection with data base and model
    MyConnection myConnection;
    Connection connection;
    PreparedStatement prepareStatement;
    Statement statement;
    ResultSet result;
    User persona = new User();

    public UserDAO() throws Exception {
        this.myConnection = new MyConnection();
    }
    
    /**
     * Find a specific user.
     * @param name
     * @param password
     * @return
     * @throws Exception 
     */
    @Override
    public User findUser(String name, String password) throws Exception{
        
        User userFound = null;
        String query = "SELECT id, name, lastname FROM tb_users WHERE name = (?) AND password = (?) LIMIT 1";
        
        try{
            connection = myConnection.getConection();
            prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, name.trim());
            prepareStatement.setString(2,password.trim());
            result = prepareStatement.executeQuery();
            
            while( result != null && result.next() ){
                userFound = new User();
                userFound.setId(result.getLong("id"));
                userFound.setName(result.getString("name"));
                userFound.setLastname(result.getString("lastname"));
            }
        } catch(Exception e){

                System.out.println("Error: "+e);
                
                throw new Exception(e);

        } finally{
            
           myConnection.closeAConnection(connection, statement, result);
           
        }
        
        return userFound;
 
    }
    
    /**
     * Get users
     * @return
     * @throws Exception 
     */
    public List getUsers() throws Exception{
        
        List<User> users = new ArrayList<>();
        String query = "select * from tb_users";
        
        try{
            connection = myConnection.getConection();
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            
            while( result != null && result.next() ){
                
                User user = new User();
                user.setId(result.getLong("id"));
        
                users.add(persona);
            }
        } catch(Exception e){
        
                System.out.println("Error: "+e);
                
                throw new Exception(e);

        } finally{
            
           myConnection.closeAConnection(connection, statement, result);
           
        }
        
        return users;
    }

    /**
     * Find a specific user.
     * @return
     * @throws Exception 
     */
    @Override
    public User findUser(Long id) throws Exception {
        
        User userFound = null;
        String query = "SELECT id, name, lastname FROM tb_users WHERE id = (?) LIMIT 1";
        
        try{
            connection = myConnection.getConection();
            prepareStatement = connection.prepareStatement(query);
            prepareStatement.setLong(1, id);
            result = prepareStatement.executeQuery();
            
            while( result != null && result.next() ){
                userFound = new User();
                userFound.setId(result.getLong("id"));
                userFound.setName(result.getString("name"));
                userFound.setLastname(result.getString("lastname"));
            }
        } catch(Exception e){

                System.out.println("Error: "+e);
                
                throw new Exception(e);

        } finally{
            
           myConnection.closeAConnection(connection, statement, result);
           
        }
        
        return userFound;
 
    }
 
}
