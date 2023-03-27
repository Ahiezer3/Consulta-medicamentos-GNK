
package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Class for Medicine model.
 * @author abi_h
 * @since 24/03/2023
 */
public class Medicine {
    
    private Long id;
    private String description;
    private String storage;
    private LocalDate dateExpiration;
    private LocalDateTime dateRegister;

    public Medicine() {
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getStorage() {
        return storage;
    }

    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public LocalDateTime getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDateTime dateRegister) {
        this.dateRegister = dateRegister;
    }
    
}
