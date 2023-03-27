
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="MenuHead.jsp" %>
    

<div class="container-sm" style ="width:90%;">
    <div>
        <div>
            <% 
               
               String addOrderMessage = "";
               
               if(request.getAttribute("orderAdded") != null && request.getAttribute("addOrderMessage") != null ){

                    addOrderMessage = request.getAttribute("addOrderMessage").toString();
             %>
            <div class="mb-3">
             <div class="alert alert-success" role="alert">

                 <p>${addOrderMessage}</p>
             </div>
           </div>
           <%
               } else if( request.getAttribute("addOrderMessage") != null ){
               
                   addOrderMessage = request.getAttribute("addOrderMessage").toString();
             %>
            <div class="mb-3">
             <div class="alert alert-danger" role="alert">

                 <p>${addOrderMessage}</p>
             </div>
           </div>
           <%
               }
           %>
           
           
           <% 
               
               String message = "";
               
               if(request.getAttribute("medicine") != null && request.getAttribute("message") != null ){

                    message = request.getAttribute("message").toString();
             %>
            <div class="mb-3">
             <div class="alert alert-success" role="alert">

                 <p>${message}</p>
             </div>
           </div>
           <%
               } else if( request.getAttribute("message") != null ){
               
                   message = request.getAttribute("message").toString();
             %>
            <div class="mb-3">
             <div class="alert alert-danger" role="alert">

                 <p>${message}</p>
             </div>
           </div>
           <%
               }
           %>
           
           <div class="card border-info mb-3" >
               <div class="card-header text-bg-primary">Ingrese identificador de producto para generar orden de inventario</div>
               <div class="card-body">
                   <form action = "ControllerServlet?action=GET_MEDICINE" method="POST">
                       <div class="container-sm">
                           <div class="row">
                               <div class="col">
                                   <label for="productIdentifierInput" class="form-label">Identificador producto</label>
                               </div>
                                <div class="col">
                                  <input type="number" class="form-control" id="productIdentifierInput" name="productIdentifierInput" style="width:120px;" min ="1" required>
                                </div>
                               <div class ="col">
                                    <button type="submit" class="btn btn-primary">Buscar</button>
                               </div>
                           </div>
                       </div>
                   </form>
               </div>
           </div>
           
           <div class="card border-info mb-3" >
               <div class="card-header text-bg-primary">Registrar orden de inventario</div>
               <div class="card-body">
                   <form action = "ControllerServlet?action=ADD_ORDER" method="POST">
                      
                       <!-- Row 1 -->
                       <div class="row">
                           
                           <div class="col" style="width:30%;">
                               <div class="row">
                                    <div class="col">
                                        <label for="productIdentifierOrderInput" class="form-label">Identificador producto</label>
                                    </div>
                                     <div class="col">
                                       <input type="number" class="form-control" id="productIdentifierOrderInput" name="productIdentifierOrderInput" style="width:120px;" required readonly value="${medicine.id}">
                                     </div>
                               </div>
                           </div>
                           
                           <div class="col" style="width:70%;">
                               <div class="row"">
                                    <div class="col" style="width:20%;">
                                        <label for="productDescriptionInput" class="form-label">Descripción</label>
                                    </div>
                                     <div class="col" style="width:80%;">
                                       <input type="text" class="form-control" id="productDescriptionInput" name="productDescriptionInput" required readonly value="${medicine.description}">
                                     </div>
                               </div>
                           </div>
                           
                        </div>
                       <!-- End row 1 -->
                       
                       <!-- Row 2 -->
                       <div class="row">
                           
                           <div class="col" style="width:30%;">
                               <div class="row">
                                    <div class="col">
                                        <label for="typeOrderInput" class="form-label">Tipo orden</label>
                                    </div>
                                     <div class="col">
                                         <select class="form-control" id="typeOrderInput" name="typeOrderInput" required>
                                            <option value="${entries[0].name()}" selected>${entries[0].getDescription()}</option>
                                            <option value="${entries[1].name()}">${entries[1].getDescription()}</option>
                                          </select>
                                    
                                     </div>
                               </div>
                           </div>
                           
                           <div class="col" style="width:70%;">
                               <div class="row"">
                                    <div class="col" style="width:20%;">
                                        <label for="amountInput" class="form-label">Cantidad</label>
                                    </div>
                                     <div class="col" style="width:80%;">
                                       <input type="number" class="form-control" id="amountInput" name="amountInput" min ="1" required>
                                     </div>
                               </div>
                           </div>
                           
                        </div>
                       <!-- End row 2 -->
                       
                       <!-- Row 3 -->
                       <div class="row">
                           
                           <div class="col" style="width:30%;">
                               <div class="row">
                                    <div class="col">
                                        <label for="reasonInput" class="form-label">Razón</label>
                                    </div>
                                     <div class="col">
                                        
                                         <textarea type="textArea" class="form-control" id="reasonInput" name="reasonInput" required  rows="2" cols="50"></textarea>
                                     </div>
                               </div>
                           </div> 
                           
                           <div class="col" style="width:70%;">
                               
                           </div>
                        </div>
                       <!-- End row 3-->
                       
                       <!-- Row 4 -->
                       <div class="row">
                           <div class ="col">
                                <button type="submit" class="btn btn-primary">Registrar</button>
                           </div>
                       </div>
                       <!-- End row 4 -->
                   </form>
               </div>
           </div>
        </div>
    </div>
</div>

<%@include file="MenuFooter.jsp" %>