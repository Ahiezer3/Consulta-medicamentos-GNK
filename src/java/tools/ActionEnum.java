
package tools;

/**
 * Enum that defines all the actions in a view to a controller
 * @author abi_h
 * @since 24/03/2023
 */
public enum ActionEnum {
    
    //Pages
    LOGIN("LOGIN","index.jsp","Inicio de sesión"),
    LOGOUT("LOGOUT","index.jsp","Inicio de sesión"),

    MEDICINES("MEDICINES","views/Medicines.jsp","Medicamentos"),
    INVENTORY_ORDER("INVENTORY_ORDER","views/InventoryOrder.jsp","Inventario"),
    INVENTORY_REPORT("INVENTORY_REPORT","views/InventoryReport.jsp","Reporte"),
    
    //Actions
    ADD_MEDICINE("ADD_MEDICINE","views/Medicines.jsp","Medicamentos"),
    GET_MEDICINE("GET_MEDICINE","views/InventoryOrder.jsp","Inventario"),
    ADD_ORDER("ADD_ORDER","views/InventoryOrder.jsp","Inventario"),
    GET_MEDICINE_REPORT("GET_MEDICINE_REPORT","views/InventoryReport.jsp","Reporte"),
    ABOUT("ABOUT","views/About.jsp","Más información"),
    //Error
    ERROR("ERROR","views/Error.jsp","Error")
    ;
    public String description;
    public String url;
    public String namePage;

    private ActionEnum(String description, String url, String namePage) {
        this.description = description;
        this.url = url;
        this.namePage = namePage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNamePage() {
        return namePage;
    }

    public void setNamePage(String namePage) {
        this.namePage = namePage;
    }
    
    /**
     * This find and gets a specific string action.
     * @param action
     * @return 
     */
    public static ActionEnum getAction(String action){
        
         for( ActionEnum value : ActionEnum.values() ){

            if( value.getDescription().contains(action) ) {
                return value;
            }
        }
         
        return null;
    }
}
