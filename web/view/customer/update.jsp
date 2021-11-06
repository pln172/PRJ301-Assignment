<%-- 
    Document   : update
    Created on : Oct 1, 2021, 12:30:01 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SALE MANAGEMENT</title>
        <link rel="icon" href="../img/favicon.png" type="image/png" sizes="16x16">
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
        <c:set var="c" value="${requestScope.customer}"/>
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
                        <input id="import" type="button" onclick="changeurl('import')" value="IMPORT"/>
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
                        <p onclick="changeurl('customer')" class="nav">Customer</p>
                        <p onclick="changeurl('product')">Product</p>
                        <p onclick="changeurl('report')">Report</p>
                    </div>
                </div>

                <div class="col-md-9">
                    <div class="right">
                        <form action="update" method="POST">
                            <table>
                                <input type="hidden" name="id" value="${c.id}"/>
                                <caption>Update customer</caption>
                                <tr>
                                    <td>Name <span style="color: red;">*</span></td>
                                    <td>
                                        <input type="text" name="name" value="${c.name}" pattern="[a-zA-Z0-9]+[ a-zA-Z0-9]*" required/>
                                    </td>
                                    <td></td>
                                    <td>Gender <span style="color: red;">*</span></td>
                                    <td>
                                        <input type="radio" 
                                               ${c.gender ? "checked=\"checked\"" : ""}
                                               name="gender" value="male"/> Male
                                        <input type="radio" 
                                               ${!c.gender ? "checked=\"checked\"" : ""}
                                               name="gender" value="female"/> Female
                                    </td>
                                </tr>

                                <tr>
                                    <td>Date of birth <span style="color: red;">*</span></td>
                                    <td>
                                        <input type="date" name="dob" value="${c.dob}"
                                               min="${requestScope.dateMin}" max="${requestScope.dateMax}" required/>
                                    </td>
                                    <td></td>
                                    <td>Phone <span style="color: red;">*</span></td>
                                    <td>
                                        <input type="tel" name="phone" value="${c.phone}" pattern="[0]{1}[0-9]{9}" required/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>Email</td>
                                    <td>
                                        <input type="email" name="email" value="${c.email}"/>
                                    </td>
                                    <td></td>
                                    <td>Address <span style="color: red;">*</span></td>
                                    <td>
                                        <input type="text" name="address" length="100" value="${c.address}" pattern="[a-zA-Z0-9]+[ a-zA-Z0-9]*" required/>
                                    </td>
                                </tr>
                            </table>
                            <input id="save" type="submit" style="margin-left: 42%;" value="Save"/>
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
