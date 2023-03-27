
package tools;

/**
 * Enum for add parameters to connection database configuration.
 * @author abi_h
 * @since 24/03/2023
 */
public enum DatabaseConectionConfigEnum {
    
    SERVER("localhost"),
    PORT("3306"),
    DATABASE("proyecto_gnk"),
    USER("root"),
    PASSWORD("root");
    
    public String description;

    private DatabaseConectionConfigEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
