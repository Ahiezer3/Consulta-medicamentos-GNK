
package interfaces;

import java.util.List;
import models.User;

/**
 * Interface CRUD for User model.
 * @author abi_h
 * @since 24/03/2023
 */
public interface UserCRUD {
    
    public User findUser(String name, String password) throws Exception;
    public User findUser(Long id) throws Exception;
    public List<User> getUsers() throws Exception;
    
}
