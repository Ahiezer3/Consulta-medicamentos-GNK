
package config;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.dao.UserDAO;
import tools.DatabaseConectionConfigEnum;

/**
 * This class permits create a new connection with the database.
 * @author abi_h
 * @since 24/03/2023
 */
public class MyConnection {
    
    Connection conection;

    public MyConnection() throws Exception {
        
        try{
            //Create connection with parameters.
            Class.forName("com.mysql.cj.jdbc.Driver");
//            conection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto_gnk?autoReconnect=true&useSSL=false","root","root");
            String server = DatabaseConectionConfigEnum.SERVER.getDescription();
            String port = DatabaseConectionConfigEnum.PORT.getDescription();
            String database = DatabaseConectionConfigEnum.DATABASE.getDescription();
            String user = DatabaseConectionConfigEnum.USER.getDescription();
            String password = DatabaseConectionConfigEnum.PASSWORD.getDescription();

            String url = "jdbc:mysql://"+server+":"+port+"/"+database+"?autoReconnect=true&useSSL=false";
            
            conection = DriverManager.getConnection(url,user,password);
        } catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
            throw  new Exception(e);
        }
    }

    public Connection getConection() {
        return this.conection;
    }
    
    /**
     * Method to close any connection sent.
     * @param connection
     * @param statement
     * @param result 
     */
    public void closeAConnection(Connection connection,Statement statement,ResultSet result){
        
        try {
            if( result != null && !result.isClosed() ){
                result.close();
            }

            result = null;

            if( statement != null && !statement.isClosed() ){
                statement.close();
            }

            connection = null;

            if( connection != null && !connection.isClosed() ){
                connection.close();
            }

            connection = null;

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
    
}
