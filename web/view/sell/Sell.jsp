<%-- 
    Document   : Sell
    Created on : Oct 6, 2021, 11:32:27 AM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
        <link href="css/insert_style.css" rel="stylesheet" type="text/css"/>
        <script>
            function changeurl(url) {
                var host = "http://localhost:8080/ASSIGNMENT/";
                window.location.href = host + url;
            }

            function getTemplateRow() {
                var x = document.getElementById("templateRow").cloneNode(true);
                x.style.display = "";
                return x;
            }

            function addRow() {
                var t = document.getElementById("theTable");
                var rows = t.getElementsByTagName("tr");
                var r = rows[rows.length - 1];
                r.parentNode.insertBefore(getTemplateRow(), r);
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
                        <p onclick="changeurl('product')">Product</p>
                        <p onclick="changeurl('report')">Report</p>
                    </div>
                </div>

                <div class="col-md-9">
                    <div class="right">
                        <form action="sell" method="POST">
                            <p style="color: green; font-weight: bold;">
                                ${requestScope.mess}</p>
                            <p style="color: red; font-weight: bold;">
                                <c:choose>
                                    <c:when test="${requestScope.err == 1}">
                                        Duplicate product. Check again!
                                    </c:when>
                                    <c:when test="${requestScope.err == 2}">
                                        Quantity sell > inventory. Check again!
                                    </c:when>
                                    <c:when test="${requestScope.err == 3}">
                                        Product empty. Check again!
                                    </c:when>
                                    <c:when test="${requestScope.err == 4}">
                                        Duplicate product, Quantity sell > inventory,
                                        Product empty. Check again!
                                    </c:when>
                                </c:choose>
                            </p>
                            <table id="theTable">
                                <caption>Order</caption>
                                <tr>
                                    <td>Employee <span style="color: red;">*</span></td>
                                    <td>
                                        <c:set var="e" value="${requestScope.employee}"/>
                                        <input type="hidden" name="emp" value="${e.id}"/>
                                        ${e.name} - ${e.phone}
                                    </td>
                                    <td>Customer <span style="color: red;">*</span></td>
                                    <td>
                                        <select name="cus" required>
                                            <c:forEach items="${requestScope.customers}" var="c">
                                                <option value="${c.id}"
                                                        >${c.name} - ${c.phone}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>

                                <tr>
                                    <td>Product <span style="color: red;">*</span></td>
                                    <td>
                                        <select name="pro" required>
                                            <option value="0" hidden>Product</option>
                                            <c:forEach items="${requestScope.products}" var="p">
                                                <option value="${p.id}"
                                                        >${p.name} - 
                                                    <fmt:formatNumber type = "number" 
                                                                      value = "${p.priceExport}"/>
                                                    - SL: ${p.quantity}</option>
                                                </c:forEach>
                                        </select>
                                    </td>
                                    <td>Quantity <span style="color: red;">*</span></td>
                                    <td>
                                        <input id="quantity" type="number" name="quantity" min="1" required/>
                                    </td>
                                </tr>

                                <tr id="templateRow" style="display:none">
                                    <td>Product <span style="color: red;">*</span></td>
                                    <td>
                                        <select name="pro">
                                            <option value="0" hidden>Product</option>
                                            <c:forEach items="${requestScope.products}" var="p">
                                                <option value="${p.id}"
                                                        >${p.name} - 
                                                    <fmt:formatNumber type = "number" 
                                                                      value = "${p.priceExport}"/>
                                                    - SL: ${p.quantity}</option>
                                                </c:forEach>
                                        </select>
                                    </td>
                                    <td>Quantity <span style="color: red;">*</span></td>
                                    <td>
                                        <input type="number" name="quantity" min="0" 
                                               value="0" required/>
                                    </td>
                                </tr>
                            </table>
                            <div>
                                <input type="button" onclick="addRow();" value="Add" 
                                       style="margin-right: 950px; padding: 7px 15px;"/>
                                <input type="submit" value="Sell"
                                       style="padding: 7px 15px;"/>
                            </div>
                        </form>
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
