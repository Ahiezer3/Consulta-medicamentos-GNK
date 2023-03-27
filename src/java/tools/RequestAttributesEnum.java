
package tools;

import javax.servlet.http.HttpServletRequest;

/**
 * Enum for indentify all the attributes added to a HttpServletRequest.
 * @author abi_h
 * @since 24/03/2023
 */
public enum RequestAttributesEnum {
    
    USER("message"),
    MEDICINES("medicines"),
    MEDICINE("medicine"),
    ADD_MEDICINE_MESSAGE("addMedicineMessage"),
    PAGE_NAME("pageName"),
    ORDER_ADDED("orderAdded"),
    ADD_ORDER_MESSAGE("addOrderMessage"),
    ENTRIES("entries"),
    ORDERS("orders"),
    ERROR("error")
    ;
    public String description;

    private RequestAttributesEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Clean attributes in HttpServletRequest.
     * @param request
     * @return 
     */
    public static HttpServletRequest cleanAttributes(HttpServletRequest request){
        
        if( request != null ){
            for( RequestAttributesEnum value : RequestAttributesEnum.values() ){

                if( request.getAttribute(value.getDescription()) != null ) {
                    request.setAttribute(value.getDescription(), null);
                }
            }
        }
        
        return request;
    }
}
