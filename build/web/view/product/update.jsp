<%-- 
    Document   : update
    Created on : Oct 2, 2021, 12:40:20 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SALE MANAGEMENT</title>
        <Link rel = "stylesheet" href = "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link href="../css/base_style.css" rel="stylesheet" type="text/css"/>
        <link href="../css/insert_style.css" rel="stylesheet" type="text/css"/>
        <script>
            function changeurl(url) {
                var host = "http://localhost:8080/ASSIGNMENT/";
                window.location.href = host + url;
            }
        </script>

        <c:set var="p" value="${requestScope.product}"/>
    </head>

    <body>
        <header>
            <div class="row">
                <div class="col-md-3">
                    <div class="hleft">
                        <h3>Management</h3>
                    </div>
                </div>

                <div class="col-md-9">
                    <div class="hright">
                        <input id="sell" type="button" onclick="changeurl('sell')" value="SELL"/>
                        <i class="fas fa-history fa-2x" 
                           onclick="changeurl('history')" style="color: white;"></i>
                        <div class="dropdown">
                            <button class="dropbtn">
                                <i class="far fa-user-circle fa-2x" style="color: white;"></i>
                            </button>
                            <div class="dropdown-content">
                                <h5 onclick="changeurl('account')">Account</h5>
                                <h5 onclick="changeurl('logout')">Log out</h5>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <section>
            <div class="row">
                <div class="col-md-3">
                    <div class="left">
                        <p onclick="changeurl('statistic')">Statistic</p>
                        <p onclick="changeurl('employee')">Employee</p>
                        <p onclick="changeurl('customer')">Customer</p>
                        <p onclick="changeurl('product')" class="nav">Product</p>
                        <p onclick="changeurl('report')">Report</p>
                    </div>
                </div>

                <div class="col-md-9">
                    <div class="right">
                        <form action="update" method="POST">
                            <table>
                                <input type="hidden" name="id" value="${p.id}"/>
                                <caption>Update product</caption>
                                <tr>
                                    <td>Name <span style="color: red;">*</span></td>
                                    <td>
                                        <input type="text" name="name" value="${p.name}" pattern="[a-zA-Z0-9]+[ a-zA-Z0-9]*" required/>
                                    </td>
                                    <td></td>
                                    <td>Quantity <span style="color: red;">*</span></td>
                                    <td>
                                        ${p.quantity}
                                    </td>
                                </tr>

                                <tr>
                                    <td>Price import <span style="color: red;">*</span></td>
                                    <td>
                                        <input type="number" name="priceImport" value="${p.priceImport}" min="1" required/>
                                    </td>
                                    <td></td>
                                    <td>Price export <span style="color: red;">*</span></td>
                                    <td>
                                        <input type="number" name="priceExport" value="${p.priceExport}" min="1" required/>
                                    </td>
                                </tr>
                            </table>
                            <input id="save" type="submit" style="margin-left: 42%;" value="Save"/>
                            <p style="color: red; margin-top: 15px; text-align: center; font-weight: bold;">
                                ${requestScope.err == 1 ? "Import price > Export price. Re-enter!" : ""}
                            </p>
                        </form>
                    </div>
                </div>
            </div>
        </section>

        <footer>
            <div class="row">
                <div class="col-md-6">
                    <div class="fleft">
                        <h5>&copy PRJ301</h5>
                        <h5>phuongloan517@gmail.com</h5>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="fright">
                        <i class="fab fa-instagram fa-3x"></i>
                        <i class="fab fa-twitter fa-3x"></i>
                        <i class="fab fa-facebook fa-3x"></i>
                    </div>
                </div>
            </div>
        </footer>
    </body>
</html>
