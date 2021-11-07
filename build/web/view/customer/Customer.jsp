<%-- 
    Document   : Customer
    Created on : Sep 29, 2021, 10:53:46 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Customer"%>
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
                window.location.href = "customer/update?id=" + id;
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
                        <p onclick="changeurl('customer')" class="nav">Customer</p>
                        <p onclick="changeurl('product')">Product</p>
                        <p onclick="changeurl('report')">Report</p>
                    </div>
                </div>

                <div class="col-md-9">
                    <div class="right">
                        <form action="customer" method="POST">
                            <input type="text" name="name" value="${requestScope.name}"/>
                            <input type="submit" value="Search"/>
                        </form>
                        <input id="insert" type="button" onclick="insert('customer')" value="Insert"/>
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
                                        Gender
                                    </div>
                                    <div class="cell th">
                                        DOB
                                    </div>
                                    <div class="cell th">
                                        Phone
                                    </div>
                                    <div class="cell th">
                                        Email
                                    </div>
                                    <div class="cell th wi">
                                        Address
                                    </div>
                                    <div class="cell th">
                                        Action
                                    </div>
                                </div>
                                
                                <c:forEach items="${requestScope.customers}" var="c">
                                    <div class="r"> 
                                        <div class="cell">${c.customerNo}</div>
                                        <div class="cell">${c.name}</div>
                                        <div class="cell">
                                            <i ${c.gender ? "class=\"fas fa-mars\"" : "class=\"fas fa-venus\""}></i>
                                        </div>
                                        <div class="cell">${c.dob}</div>
                                        <div class="cell">${c.phone}</div>
                                        <div class="cell">${c.email}</div>
                                        <div class="cell wi">${c.address}</div>
                                        <div class="cell">
                                            <abbr title="update"><i class="far fa-edit" onclick="doUpdate(${c.id})"></i></abbr>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        
                        <div id="paggerBottom">     
                        </div>
                        <script>
                            createPagger('customer', 'paggerBottom', ${requestScope.pageIndex-1}, ${requestScope.pageIndex}, ${requestScope.pageIndex+1}, 2, ${requestScope.totalPage});
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
