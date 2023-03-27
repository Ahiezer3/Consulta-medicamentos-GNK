/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Inventory;
import models.InventoryOrders;
import models.Medicine;
import models.User;
import models.dao.InventoryDAO;
import models.dao.InventoryOrdersDAO;
import models.dao.MedicineDAO;
import models.dao.UserDAO;
import tools.ActionEnum;
import tools.RequestAttributesEnum;
import tools.SessionAttributesEnum;
import tools.TypeOrder;

/**
 * This is the principal servlet controller to manipulate the models and views.
 * @author abi_h
 * @since 24/03/2023
 */
@WebServlet(name = "ControllerServlet", urlPatterns = {"/ControllerServlet"})
public class ControllerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        
        response.setContentType("text/html;charset=UTF-8");
        
        try{
            
            //Validate the action sent.
            if( request.getParameter("action") == null ) {
                
                redirectPage(request, response, ActionEnum.ERROR, "Ocurrió un error, la acción no se encontró.");
                
                return;
            }
            
            String action = request.getParameter("action");
            
            //Get the action from ENUMERAL.
            ActionEnum actionEnum = ActionEnum.getAction(action);
            
            if( actionEnum == null ){
                
                redirectPage(request, response, ActionEnum.ERROR, "Ocurrió un error, la acción no se encontró.");

                return;
            }
            
            //Validate existence for user from session.
            if( !actionEnum.equals(ActionEnum.LOGIN) && 
                    !actionEnum.equals(ActionEnum.LOGOUT) && 
                    !verifyUser(request, response)){
                
                request.setAttribute("message", "Error de credenciales, el usuario no existe, no tiene permiso para acceder al sistema o su sesión expiró.");
                redirectPage(request, response, ActionEnum.LOGIN, "");
            }
            
            //Evaluate each of actions from view and do each own action.
            switch( actionEnum ){

                case LOGIN :
                    loginUser(request,response);
                    break;

                case LOGOUT :
                    logoutUser(request,response);
                    break;
                
                case MEDICINES :
                    redirectPage(request, response, ActionEnum.MEDICINES, "");
                    break;

                case INVENTORY_ORDER :
                        redirectPage(request, response, ActionEnum.INVENTORY_ORDER, "");
                        break;
                
                case INVENTORY_REPORT :
                    redirectPage(request, response, ActionEnum.INVENTORY_REPORT, "");
                    break;
                
                case ADD_MEDICINE :
                    addMedicine(request,response);
                    break;
                
                case GET_MEDICINE :
                    getMedicineInventory(request,response,ActionEnum.GET_MEDICINE);
                    break;
                
                case ADD_ORDER :
                    addOrder(request,response);
                    break;
                
                case GET_MEDICINE_REPORT :
                    getMedicineInventory(request,response,ActionEnum.GET_MEDICINE_REPORT);
                    break;
                
                case ABOUT :
                    redirectPage(request, response, ActionEnum.ABOUT, "");
                    break;
                default : 
                        
                    request = RequestAttributesEnum.cleanAttributes(request);

                    redirectPage(request, response, ActionEnum.ERROR, "No se encontró la dirección");
                    
                    break;
                }

        } catch(Exception e){
            System.out.println(e);
            redirectPage(request, response, ActionEnum.ERROR, "Ocurrió un error: "+e.getMessage());
        }

    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    /**
     * Method to log user and create a session for it.
     * @param request
     * @param response
     * @throws Exception 
     */
    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        try{
            HttpSession session;
            String nameUser = request.getParameter("userInput");
            String passwordUser = request.getParameter("passwordInput");

            User userFound = null;
            
            try{
                UserDAO userDao = new UserDAO();
                userFound = userDao.findUser(nameUser, passwordUser);
            }catch(Exception e){
                System.out.println(e);
                throw new Exception(e);
            }
    
            //Clean request and session attributes.
            request = SessionAttributesEnum.cleanAttributes(RequestAttributesEnum.cleanAttributes(request));
            
            //If exists the user, then create session for it.
            if( userFound != null ) {
                
                session = request.getSession();
                session.setAttribute("idUser", userFound.getId());
                session.setAttribute("user", userFound);
                session.setAttribute("message", "Inicio de sesión satisfactorio");

                redirectPage(request, response, ActionEnum.MEDICINES, "");
                
            } else{
                //Return login.
                request.setAttribute("message", "Error en las credenciales, intente de nuevo");
                
                redirectPage(request, response, ActionEnum.LOGIN, "");
                
            }
             
        }catch(Exception e){
            System.out.println(e);
            throw new Exception(e);
        } 
    }
    
    /**
     * Method to verify the user from session.
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    private boolean verifyUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
       
        boolean valid = false;
        
        try{

            if( request == null || 
                    request.getSession() == null || 
                    request.getSession().getAttribute("user") == null ||
                    request.getSession().getAttribute("idUser") == null){
                return false;
            }
            
            HttpSession session = request.getSession();
            Long idUser = Long.parseLong(session.getAttribute("idUser").toString());
            User userFound = null;
            
            try{
                UserDAO userDao = new UserDAO();
                userFound = userDao.findUser(idUser);
            }catch(Exception e){
                System.out.println(e);
                throw new Exception(e);
            }
    
            if( userFound != null ) {
                valid = true;
            }
            
        }catch(Exception e){
            System.out.println(e);
            throw new Exception(e);
        } finally{
            return valid;
        }
    }
    
    /**
     * Log out session for current user.
     * @param request
     * @param response
     * @throws ServletException
     * @throws Exception 
     */
    private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {

        try {
            
            //Clean request and session attributes.
            request = SessionAttributesEnum.cleanAttributes(RequestAttributesEnum.cleanAttributes(request));
            
            HttpSession session = request.getSession();
            session.invalidate();
            
            redirectPage(request, response, ActionEnum.LOGIN, "");
        } catch (Exception ex) {
            System.out.println(ex);
            throw new Exception(ex);
        }
    }
    
    /**
     * Add medicine to data base.
     * @param request
     * @param response
     * @throws Exception 
     */
    private void addMedicine(HttpServletRequest request, HttpServletResponse response) throws Exception {
         
        try{
            
            //Clean request and session attributes.
            request = RequestAttributesEnum.cleanAttributes(request);
              
            //Validate parameters from view.
            if( request.getParameter("descriptionInput") == null ||
                    request.getParameter("descriptionInput").trim().equals("") ||
                    request.getParameter("storageInput") == null ||
                    request.getParameter("storageInput").trim().equals("") ||
                    request.getParameter("expirationInput") == null ||
                    request.getParameter("expirationInput").trim().equals("") ){
                 
                 request.setAttribute("addMedicineMessage", "Los parámetros no son válidos.");
                 redirectPage(request, response, ActionEnum.MEDICINES, "");
                 
                 return;
            }
            
            //Get parameters.
            String descriptionInput = request.getParameter("descriptionInput");
            String storageInput = request.getParameter("storageInput");
            String expirationInput = request.getParameter("expirationInput");
            
            Medicine medicine = null;
            
            try{
                MedicineDAO medicineDAO = new MedicineDAO();
                medicine = medicineDAO.addMedicine(descriptionInput, storageInput, expirationInput);
             }catch(Exception e){
                System.out.println(e);
                throw new Exception(e);
            }
                
            //If exists medicine, set into attributes.
            if( medicine != null ) {
              
                request.setAttribute("medicine", medicine);
                request.setAttribute("addMedicineMessage", "El medicamento fue registrado con éxito");
                
            } else{
                request.setAttribute("addMedicineMessage", "El medicamento no fue registrado con éxito, intente de nuevo");
   
            }
            
            redirectPage(request, response, ActionEnum.MEDICINES, "");
          
        }catch(ServletException | IOException e){
            System.out.println(e);
            throw new Exception(e);
        }
    }
    
    /**
     * Find and get a specific medicine.
     * @param request
     * @param response
     * @param action
     * @throws Exception 
     */
    private void getMedicineInventory(HttpServletRequest request, HttpServletResponse response, ActionEnum action) throws Exception {
        
        try{
                
            //Clean request and session attributes.
            request = RequestAttributesEnum.cleanAttributes(request);
            
            //Validate parameters from view.
            if( request.getParameter("productIdentifierInput") == null ||
                  request.getParameter("productIdentifierInput").trim().equals("") ||
                  Long.parseLong(request.getParameter("productIdentifierInput")) <= 0 ){
                
                 if( action.equals(ActionEnum.GET_MEDICINE) ){
                     request.setAttribute("addOrderMessage", "El identificador del producto es incorrecto");
                    redirectPage(request, response, ActionEnum.INVENTORY_ORDER, "");
                 }else if( action.equals(ActionEnum.GET_MEDICINE_REPORT) ){
                    request.setAttribute("message", "El identificador del producto es incorrecto");
                    redirectPage(request, response, ActionEnum.INVENTORY_REPORT, "");
                 }
                 
                return;
            }
            
            //Get parameters.
            Long productIdentifierInput = Long.parseLong(request.getParameter("productIdentifierInput"));
            Medicine foundMedicine = null;
            
            try{
                MedicineDAO medicine = new MedicineDAO();
                foundMedicine = medicine.getMedicine(productIdentifierInput);
            }catch(Exception e){
                System.out.println(e);
                throw new Exception(e);
            }

            if( foundMedicine != null ) {
                
                request.setAttribute("message", "Medicamento encontrado");
 
                request.setAttribute("medicine", foundMedicine);
            } else{
                
                request.setAttribute("message", "El medicamento no se encontró");
                
            }
            
            redirectPage(request, response, action, "");
            
        }catch(Exception e){
            System.out.println(e);
            throw new Exception(e);
        }
    }
    
    /**
     * Method to redirect to a specific view for each case.
     * @param request
     * @param response
     * @param action
     * @param message
     * @throws Exception 
     */
    private void redirectPage(HttpServletRequest request, HttpServletResponse response,ActionEnum action,String message) throws Exception {
        
        try{
            
            switch( action ){
                
                case MEDICINES :
                    try{
                        MedicineDAO medicineDAO = new MedicineDAO();
                        List<Medicine> medicines = medicineDAO.getMedicines();

                        request.setAttribute("medicines", medicines);
                    }catch(Exception e){
                        throw new Exception(e);
                    }
                    break;
                case INVENTORY_ORDER :
                    request.setAttribute("entries", TypeOrder.values());
                    break;
                    
                case GET_MEDICINE :
                    request.setAttribute("entries", TypeOrder.values());
                    break;
                    
                case GET_MEDICINE_REPORT :
                    try{
                        
                        Long productIdentifierInput = Long.parseLong(request.getParameter("productIdentifierInput"));
                        
                        InventoryOrdersDAO inventoryOrdersDAO = new InventoryOrdersDAO();
                        List<InventoryOrders> orders = inventoryOrdersDAO.getOrders(productIdentifierInput);

                        request.setAttribute("orders", orders);
                    }catch(Exception e){
                        throw new Exception(e);
                    }
                    break;
                    
                case ERROR :
                    request.setAttribute("error", message+". Comuniquese con el administrador o soporte técnico");
                    break;
            }
            
            request.setAttribute("pageName", action.getNamePage());

            RequestDispatcher view = request.getRequestDispatcher(action.url);
            view.forward(request, response);
          
        }catch(Exception e){
            System.out.println(e);
            
            request.setAttribute("error", e.getMessage());
            
            RequestDispatcher view = request.getRequestDispatcher(ActionEnum.ERROR.getUrl());
            view.forward(request, response);
        }
    }
    
    /**
     * This permits to add orders.
     * @param request
     * @param response
     * @throws Exception 
     */
    private void addOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
         
        try{
            
            //Clean request and session attributes.
            request = RequestAttributesEnum.cleanAttributes(request);
            
            //Validate parameters from view.
            if( request.getSession().getAttribute("idUser") == null ||
                    request.getSession().getAttribute("idUser").toString().trim().equals("") ||
                    Long.parseLong(request.getSession().getAttribute("idUser").toString()) <= 0 ||
                    request.getParameter("productIdentifierOrderInput") == null ||
                    request.getParameter("productIdentifierOrderInput").trim().equals("") ||
                    Long.parseLong(request.getParameter("productIdentifierOrderInput")) <= 0 ||
                    request.getParameter("typeOrderInput") == null ||
                    request.getParameter("typeOrderInput").trim().equals("") ||
                    request.getParameter("amountInput") == null ||
                    request.getParameter("amountInput").trim().equals("") ||
                    Long.parseLong(request.getParameter("amountInput")) <= 0 ||
                    request.getParameter("reasonInput") == null ||
                    request.getParameter("reasonInput").trim().equals("") ){
            
                request.setAttribute("addOrderMessage", "Los parámetros son inválidos");
                redirectPage(request, response, ActionEnum.INVENTORY_ORDER, "");
                
                return;
            }
            
            //Get parameters.
            Long idUser = Long.parseLong(request.getSession().getAttribute("idUser").toString());
       
            Long productIdentifierInput = Long.parseLong(request.getParameter("productIdentifierOrderInput"));
            String typeOrderInput = request.getParameter("typeOrderInput");
            Long amountInput = Long.parseLong(request.getParameter("amountInput"));
            String reasonInput = request.getParameter("reasonInput");
            
            TypeOrder typeOrderFound = TypeOrder.getType(typeOrderInput);
            
            amountInput = typeOrderFound.equals(TypeOrder.IN) ? amountInput : amountInput * -1;
            
            User userFound = null;
            Medicine medicine = null;
            Inventory inventory = null;
            
            InventoryOrders orderAdded = null;
            
            try{
                
                //Get each of models from data base.
                
                UserDAO userDao = new UserDAO();
                userFound = userDao.findUser(idUser);
                
                MedicineDAO medicineDAO = new MedicineDAO();
                medicine = medicineDAO.getMedicine(productIdentifierInput);
                
                InventoryDAO inventoryDAO = new InventoryDAO();
                inventory = inventoryDAO.getInventory(medicine);
                
                InventoryOrders order = new InventoryOrders();
                order.setUser(userFound);
                order.setInventory(inventory);
                order.setTypeOrder(typeOrderInput);
                order.setAmount(amountInput);
                order.setReason(reasonInput);
                
                InventoryOrdersDAO inventoryDao = new InventoryOrdersDAO();
                orderAdded = inventoryDao.addOrder(order);
                
             }catch(Exception e){
                System.out.println(e);
                throw new Exception(e);
            }
                
            if( orderAdded != null ) {
              
                request.setAttribute("orderAdded", orderAdded);
                request.setAttribute("addOrderMessage", "La orden fue registrada con éxito");
                
            } else{
                request.setAttribute("addOrderMessage", "La orden no fue registrada con éxito, intente de nuevo");
   
            }
            
            redirectPage(request, response, ActionEnum.INVENTORY_ORDER, "");
          
        }catch(ServletException | IOException e){
            System.out.println(e);
            throw new Exception(e);
        }
    }
    
}
