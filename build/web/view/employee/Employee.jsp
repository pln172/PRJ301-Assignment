<%-- 
    Document   : Employee
    Created on : Sep 30, 2021, 12:29:40 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SALE MANAGEMENT</title>
        <Link rel = "stylesheet" href = "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link href="css/base_style.css" rel="stylesheet" type="text/css"/>
        <link href="css/list_style.css" rel="stylesheet" type="text/css"/>
        <script>
            function changeurl(url) {
                var host = "http://localhost:8080/ASSIGNMENT/";
                window.location.href = host + url;
            }
            
            function insert() {
                window.location.href += "/insert";
            }

            function doUpdate(id) {
                window.location.href = "employee/update?id=" + id;
            }
        </script>
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
                        <p onclick="changeurl('employee')" class="nav">Employee</p>
                        <p onclick="changeurl('customer')">Customer</p>
                        <p onclick="changeurl('product')">Product</p>
                        <p onclick="changeurl('report')">Report</p>
                    </div>
                </div>

                <div class="col-md-9">
                    <div class="right">
                        <input id="insert" type="button" onclick="insert()" value="Insert"/>
                        <table border="2px">
                            <tr class="th">
                                <td>STT</td>
                                <td>Name</td>
                                <td>Gender</td>
                                <td>DOB</td>
                                <td>Phone</td>
                                <td>Email</td>
                                <td>Address</td>
                                <td>Active</td>
                                <td>Action</td>
                            </tr>
                            <c:set var="i" value="0"/>
                            <c:forEach items="${requestScope.employees}" var="e">
                                <c:set var="i" value="${i+1}"/>
                            <tr>
                                <td>${i}</td>
                                <td>${e.name}</td>
                                <td>
                                    <i ${e.gender ? "class=\"fas fa-mars\"" 
                                        : "class=\"fas fa-venus\""}</i>
                                </td>
                                <td>${e.dob}</td>
                                <td>${e.phone}</td>
                                <td>${e.email}</td>
                                <td>${e.address}</td>
                                <c:choose>
                                    <c:when test="${e.active}">
                                        <td>YES</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td style="color: red;">NO</td>
                                    </c:otherwise>
                                </c:choose>
                                <td>
                                    <abbr title="update"><i class="far fa-edit" onclick="doUpdate(${e.id})"></i></abbr>
                                </td>
                            </tr>
                            </c:forEach>
                        </table>
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
