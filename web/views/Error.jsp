
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
        <title>Error</title>
    </head>
    
    <body>
        
        <div class="container-sm" style ="width:80%;">
            <div>
                <div>
                   <% 
                       if(request.getAttribute("error") != null ){

                           String error = request.getAttribute("error").toString();
                     %>
                    <div class="mb-3">
                     <div class="alert alert-danger" role="alert">

                         <p>${error}</p>
                     </div>
                   </div>
                   <%
                       }
                   %>
                </div>
            </div>
        </div>
    </body>
</html>