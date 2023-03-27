<%-- 
    Document   : Menu
    Created on : 24 mar. 2023, 16:26:44
    Author     : abi_h
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous"><!-- comment -->
        
        <style>
            
            tbody {
                width: 500px;
                overflow-x: auto;
            }
        </style>
         <%
            String title = "";

            if( session != null && session.getAttribute("pageName") != null ){
                 title = session.getAttribute("pageName").toString();
            }
        %>
                        
        <title>${title}</title>
    </head>
    <body>
        
        <div class="container-fluid">

            <div class="row flex-nowrap">
                <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
                    <div class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
                        <a href="Medicines.jsp" class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                            <span class="fs-5 d-none d-sm-inline">Menú</span>
                        </a>
                        <ul class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start" id="menu">
                            <li class="nav-item">
                                <a href="ControllerServlet?action=MEDICINES" class="nav-link align-middle px-0">
                                    <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline text-white">Medicamentos</span>
                                </a>
                            </li>
                            <li>
                                <a href="#submenu1" data-bs-toggle="collapse" class="nav-link px-0 align-middle">
                                    <i class="fs-4 bi-speedometer2"></i> <span class="ms-1 d-none d-sm-inline text-white">Inventario</span> </a>
                                <ul class="collapse show nav flex-column ms-1" id="submenu1" data-bs-parent="#menu">
                                    <li class="w-100">
                                        <a href="ControllerServlet?action=INVENTORY_ORDER" class="nav-link px-0"> <span class="d-none d-sm-inline text-white">Nueva orden</span></a>
                                    </li>
                                    <li>
                                        <a href="ControllerServlet?action=INVENTORY_REPORT" class="nav-link px-0"> <span class="d-none d-sm-inline text-white">Reporte por medicamento</span></a>
                                    </li>
                                </ul>
                            </li>
                            <li class="nav-item">
                                <a href="ControllerServlet?action=ABOUT" class="nav-link align-middle px-0">
                                    <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline text-white">Más información</span>
                                </a>
                            </li>
                        </ul>
                        <hr>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <nav class="col navbar navbar-expand-lg bg-body-tertiary">
                            <div class="container-fluid position-relative">
                              <div class="position-absolute top-50 start-25">
                                 <p>Autor: Abidan Ahiezer Carranza Talabera</p>
                              </div>
                              <div class="position-absolute top-50 end-0" id="navbarNavAltMarkup">
                                <div class="navbar-nav">
                                  <a class="nav-link text-right" href="ControllerServlet?action=LOGOUT">Cerrar sesión</a>
                                </div>
                              </div>
                            </div>
                        </nav>
                    </div>
                    <br>
                    <div class="row">
                        <div class="row">
                            <%
                                    String pageName = "";
                                    
                                if( session != null && session.getAttribute("pageName") != null ){
                                     pageName = session.getAttribute("pageName").toString();
                                }
                            %>
                            <h1>${pageName}</h1>
                            
                        </div>
                        <div class="row">
                            