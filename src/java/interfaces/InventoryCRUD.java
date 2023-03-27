/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import models.Inventory;
import models.Medicine;

/**
 * Interface CRUD for Inventory model.
 * @author abi_h
 * @since 24/03/2023
 */
public interface InventoryCRUD {
    public Inventory getInventory(Medicine medicine);
}
