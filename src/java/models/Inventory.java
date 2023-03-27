
package models;

/**
 * Class for Inventory model.
 * @author abi_h
 * @since 24/03/2023
 */
public class Inventory {
    
    private Long id;
    private Medicine medicine;
    private Long amount;

    public Inventory() {
    }

    public Long getId() {
        return id;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public Long getAmount() {
        return amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
    
}
