
package interfaces;

import java.util.List;
import models.Medicine;

/**
 * Interface CRUD for Medicine model.
 * @author abi_h
 * @since 24/03/2023
 */
public interface MedicineCRUD {
    
    public Medicine addMedicine(String descriptionInput,String storageInput,String expirationInput) throws Exception;
    public List<Medicine> getMedicines() throws Exception;
    public Medicine getMedicine(Long id) throws Exception;
}
