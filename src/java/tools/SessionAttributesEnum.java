
package tools;

import javax.servlet.http.HttpServletRequest;

/**
 * Enum for indentify all the attributes added to a HttpServletSession.
 * @author abi_h
 * @since 24/03/2023
 */
public enum SessionAttributesEnum {
    ID_USER("idUser"),
    USER("user"),
    MESSAGE("message")
    ;
    
    public String description;

    private SessionAttributesEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Clean all the attributes saved in a specific session.
     * @param request
     * @return 
     */
    public static HttpServletRequest cleanAttributes(HttpServletRequest request){
        
        if( request != null ){
            for( SessionAttributesEnum value : SessionAttributesEnum.values() ){

                if( request.getAttribute(value.getDescription()) != null ) {
                    request.setAttribute(value.getDescription(), null);
                }
            }
        }
        
        return request;
    }
}
