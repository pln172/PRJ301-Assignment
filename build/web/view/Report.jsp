<%-- 
    Document   : Report
    Created on : Sep 29, 2021, 10:54:06 PM
    Author     : ASUS
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SALE MANAGEMENT</title>
        <Link rel = "stylesheet" href = "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link href="css/base_style.css" rel="stylesheet" type="text/css"/>
        <link href="css/report_style.css" rel="stylesheet" type="text/css"/>
        <script>
            function changeurl(url) {
                var host = "http://localhost:8080/ASSIGNMENT/";
                window.location.href = host + url;
            }
        </script>

        <style>
            td {
                padding: 5px 5px;
            }
        </style>
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
                        <p onclick="changeurl('product')">Product</p>
                        <p onclick="changeurl('report')" class="nav">Report</p>
                    </div>
                </div>

                <div class="col-md-9">
                    <div class="right">
                        <form action="report" method="POST">
                            <table>
                                <tr>
                                    <td>From</td>
                                    </td>
                                    <td>
                                        <input type ="date" name="dateFrom"
                                               value="${requestScope.dateFrom}"
                                               max="${requestScope.today}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>To</td> 
                                    <td>
                                        <input type ="date" name="dateTo"
                                               value="${requestScope.dateTo}"
                                               max="${requestScope.today}"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <input type="submit" value="Search"/>
                                    </td>
                                    <td style="color: red;">
                                        ${requestScope.err}
                                    </td>
                                </tr>
                            </table>
                        </form>
                        <div class="row">
                            <div class="col-md-2">
                                <h4>Revenue</h4>
                                <h5>
                                    <fmt:formatNumber type = "number" 
                                                      value = "${requestScope.revenue}" />
                                </h5>
                            </div>
                            <div class="col-md-2">
                                <h4>Invoice</h4>
                                <h5>${requestScope.invoice}</5>
                            </div>
                            <div class="col-md-2">
                                <h4>Capital</h4> 
                                <h5>
                                    <fmt:formatNumber type = "number" 
                                                      value = "${requestScope.capital}"/>
                                </h5>
                            </div>
                            <div class="col-md-2">
                                <h4>Interest</h4>
                                <h5>
                                    <fmt:formatNumber type = "number" 
                                                      value = "${requestScope.interest}"/>
                                </h5>         
                            </div>
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
