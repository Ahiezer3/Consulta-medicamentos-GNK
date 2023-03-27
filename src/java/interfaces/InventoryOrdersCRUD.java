
package interfaces;

import java.util.List;
import models.InventoryOrders;
import models.Medicine;

/**
 * Interface CRUD for InventoryOrders model.
 * @author abi_h
 * @since 24/03/2023
 */
public interface InventoryOrdersCRUD {
    
    public InventoryOrders addOrder(InventoryOrders order) throws Exception;
    public List<InventoryOrders> getOrders(Long id) throws Exception;
}
