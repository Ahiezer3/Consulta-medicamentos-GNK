
package models;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Class for InventoryOrders model.
 * @author abi_h
 * @since 24/03/2023
 */
public class InventoryOrders {
    
    private Long id;
    private User user;
    private Inventory inventory;
    private String typeOrder;
    private Long amount;
    private Long summary;
    private String reason;
    private LocalDateTime registerDate;

    public InventoryOrders() {
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getTypeOrder() {
        return typeOrder;
    }

    public Long getAmount() {
        return amount;
    }

    public Long getSummary() {
        return summary;
    }

    public String getReason() {
        return reason;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setTypeOrder(String typeOrder) {
        this.typeOrder = typeOrder;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setSummary(Long summary) {
        this.summary = summary;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }
    
}
