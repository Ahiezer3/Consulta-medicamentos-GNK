
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="MenuHead.jsp" %>
    

<div class="container-sm" style ="width:90%;">
    <div>
        <div>
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
               <div class="card-header text-bg-primary">Generar reporte por medicamento</div>
               <div class="card-body">
                   <form action = "ControllerServlet?action=GET_MEDICINE_REPORT" method="POST">
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
               <div class="card-header text-bg-primary">Reporte</div>
               <div class="card-body">
                   <div class="card-body">
                        <table class="table table-hover">
                            <thead>
                                 <th>Identificador</th>
                                 <th>Descripción</th>
                                 <th>Lote</th>
                                 <th>Fecha de caducidad</th>
                                 <th>Tipo</th>
                                 <th>Fecha de movimiento</th>
                                 <th>Registro</th>
                                 <th>Cantidad</th>
                                 <th>Sumatoria</th>
                            </thead>
                            <tbody>
                                <c:forEach var = "order" items = "${orders}">
                                     <tr>
                                        <td class="text-center">${order.inventory.medicine.id}</td>
                                        <td class="text-center">${order.inventory.medicine.description}</td>
                                        <td class="text-center">${order.inventory.medicine.storage}</td>
                                        <td class="text-center">${order.inventory.medicine.dateExpiration}</td>
                                        <td class="text-center">${order.typeOrder}</td>
                                        <td class="text-center">${order.registerDate.toString().replace("T"," ")}</td>
                                        <td class="text-center">${order.user.name}</td>
                                        <td class="text-center">${order.amount}</td>
                                        <td class="text-center">${order.summary}</td>
                                     </tr>
                                 </c:forEach>
                            </tbody>
                        </table>
                    </div>
               </div>
           </div>
        </div>
    </div>
</div>

<%@include file="MenuFooter.jsp" %>