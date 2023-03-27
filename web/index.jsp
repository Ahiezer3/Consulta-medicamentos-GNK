<%-- 
    Document   : Index
    Created on : 24 mar. 2023, 16:29:26
    Author     : abi_h
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Inicio de sesión</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
    </head>
    <body class = "m-0 vh-100 row justify-content-center align-items-center">
        
        <div class="container-sm" style ="width:50%;">
            <div>
                <div class="row">
                  <div class="col">
                      <img src="images/Logo.jpeg" alt="alt"/>
                  </div>
                  <div class="col">
                      <div class="container">
                          
                          <form action = "ControllerServlet?action=LOGIN" method="POST">
                              <% 
                                if(request.getAttribute("message") != null ){
                                
                                    String message = request.getAttribute("message").toString();
                              %>
                             <div class="mb-3">
                              <div class="alert alert-danger" role="alert">
                                  
                                  
                                  <p>${message}</p>
                              </div>
                            </div>
                            <%
                                }
                            %>
                            <div class="mb-3">
                              <label for="userInput" class="form-label">Usuario</label>
                              <input type="text" class="form-control" id="userInput" name="userInput" required>
                            </div>
                            <div class="mb-3">
                              <label for="passwordInput" class="form-label">Contraseña</label>
                              <input type="password" class="form-control" id="passwordInput" name="passwordInput" required>
                              <div id="formHelp" class="form-text">Sistema de inventario de medicamentos</div>
                            </div>
                            <button type="submit" class="btn btn-primary" style ="width:100%;">Iniciar sesión</button>
                          </form>
                      </div>
                  </div>
                </div>
          </div>
        </div>
    </body>
</html>
