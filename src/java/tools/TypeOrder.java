
package tools;

/**
 * Enum for indentify all the type orders.
 * @author abi_h
 * @since 24/03/2023
 */
public enum TypeOrder {
    
    IN("Entrada"),
    OUT("Salida");
    
    public String description;

    private TypeOrder(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Method to find and get a type order.
     * @param action
     * @return 
     */
    public static TypeOrder getType(String action){
        
         for( TypeOrder value : TypeOrder.values() ){

            if( value.name().contains(action) ) {
                return value;
            }
        }
         
        return null;
    }
}
