<%-- 
    Document   : Product
    Created on : Sep 29, 2021, 10:53:51 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SALE MANAGEMENT</title>
        <link rel="icon" href="img/favicon.png" type="image/png" sizes="16x16">
        <Link rel = "stylesheet" href = "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link href="css/base_style.css" rel="stylesheet" type="text/css"/>
        <link href="css/list_style.css" rel="stylesheet" type="text/css"/>
        <script src="js/pagger.js" type="text/javascript"></script>
        <link href="css/pagger.css" rel="stylesheet" type="text/css"/>

        <script>
            function changeurl(url) {
                var host = "http://localhost:8080/ASSIGNMENT/";
                window.location.href = host + url;
            }

            function insert(url) {
                var host = "http://localhost:8080/ASSIGNMENT/";
                window.location.href = host + url + "/insert";
            }

            function doUpdate(id) {
                window.location.href = "product/update?id=" + id;
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
                        <p onclick="changeurl('customer')">Customer</p>
                        <p onclick="changeurl('product')" class="nav">Product</p>
                        <p onclick="changeurl('report')">Report</p>
                    </div>
                </div>

                <div class="col-md-9">
                    <div class="right">
                        <form action="product" method="POST">
                            <input type="text" name="name" value="${requestScope.name}"/>
                            <input type="submit" value="Search"/>
                        </form>
                        <input id="insert" type="button" onclick="insert('product')" value="Insert"/>
                        <div class="wrapper">
                            <div class="table">
                                <div class="r green">
                                    <div class="cell th">
                                        Code
                                    </div>
                                    <div class="cell th">
                                        Name
                                    </div>
                                    <div class="cell th">
                                        Quantity
                                    </div>
                                    <div class="cell th">
                                        Price Import
                                    </div>
                                    <div class="cell th">
                                        Price Export
                                    </div>
                                    <div class="cell th">
                                        Action
                                    </div>
                                </div>

                                <c:forEach items="${requestScope.products}" var="p">
                                    <div class="r"> 
                                        <div class="cell">${p.productNo}</div>
                                        <div class="cell">${p.name}</div>
                                        <div class="cell">${p.quantity}</div>
                                        <div class="cell">
                                            <fmt:formatNumber type = "number" 
                                                              value = "${p.priceImport}" />
                                        </div>
                                        <div class="cell">
                                            <fmt:formatNumber type = "number" 
                                                              value = "${p.priceExport}" />
                                        </div>
                                        <div class="cell">
                                            <abbr title="update"><i class="far fa-edit" onclick="doUpdate(${p.id})"></i></abbr>
                                        </div>

                                    </div>
                                </c:forEach>
                            </div>
                        </div>

                        <div id="paggerBottom">     
                        </div>
                        <script>
                            createPagger('product', 'paggerBottom', ${requestScope.pageIndex-1}, ${requestScope.pageIndex}, ${requestScope.pageIndex+1}, 2, ${requestScope.totalPage});
                        </script> 
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
