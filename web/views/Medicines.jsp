
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="MenuHead.jsp" %>
    

<div class="container-sm" style ="width:90%;">
    <div>
        <div>
            <% 
               if(request.getAttribute("medicine") != null && request.getAttribute("addMedicineMessage") != null ){

                   String addMedicineMessage = request.getAttribute("addMedicineMessage").toString();
             %>
            <div class="mb-3">
             <div class="alert alert-success" role="alert">

                 <p>${addMedicineMessage}</p>
             </div>
           </div>
           <%
               }
           %>
           
           <% 
               if(request.getAttribute("medicine") == null && request.getAttribute("addMedicineMessage") != null ){

                   String addMedicineMessage = request.getAttribute("addMedicineMessage").toString();
             %>
            <div class="mb-3">
             <div class="alert alert-danger" role="alert">

                 <p>${addMedicineMessage}</p>
             </div>
           </div>
           <%
               }
           %>

           <div class="card border-info mb-3" >
               <div class="card-header text-bg-primary">Registrar medicamento</div>
               <div class="card-body">
                   <form action = "ControllerServlet?action=ADD_MEDICINE" method="POST">
                       <div class="container-sm" style ="width:50%;">
                            <div class="mb-3">
                              <label for="descriptionInput" class="form-label">Descripción</label>
                              <input type="text" class="form-control" id="descriptionInput" name="descriptionInput" required>
                            </div>
                           <div class="mb-3">
                              <label for="storageInput" class="form-label">Lote</label>
                              <input type="text" class="form-control" id="storageInput" name="storageInput" required>
                           </div>
                           <div class="mb-3">
                              <label for="expirationInput">Fecha de caducidad</label>
                                <input type="date" id="expirationInput" name="expirationInput"
                                       min="2018-01-01" max="2050-12-31" required>
                            </div>

                           <button type="submit" class="btn btn-success">Registrar</button>
                       </div>
                   </form>
               </div>
           </div>
           
           <div class="card border-info mb-3">
               <div class="card-header text-bg-primary">Medicamentos existentes</div>
               <div class="card-body">
                   <table class="table table-hover table-fixed">
                       <thead>
                            <th>Identificador</th>
                            <th>Descripción</th>
                            <th>Lote</th>
                            <th>Fecha de caducidad</th>
                            <th>Fecha de registro</th>
                       </thead>
                       <tbody class="tbody">
                           <c:forEach var = "medicine" items = "${medicines}">
                                <tr>
                                    <td class="text-center">${medicine.id}</td>
                                    <td class="text-center">${medicine.description}</td>
                                    <td class="text-center">${medicine.storage}</td>
                                    <td class="text-center">${medicine.dateExpiration}</td>
                                    <td class="text-center">${medicine.dateRegister.toString().replace("T"," ")}</td>
                                </tr>
                            </c:forEach>
                       </tbody>
                   </table>
               </div>
           </div>
        </div>
    </div>
</div>

<%@include file="MenuFooter.jsp" %>