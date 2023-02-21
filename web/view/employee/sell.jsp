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
        <link rel="icon" href="../img/favicon.png" type="image/png" sizes="16x16">
        <!--        <Link rel = "stylesheet" href = "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">-->
        <link href="../css/base_style.css" rel="stylesheet" type="text/css"/>
        <link href="../css/insert_style.css" rel="stylesheet" type="text/css"/>
        <!-- Font Awesome -->
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            rel="stylesheet"
            /><!--
         Google Fonts 
        <link
            href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
            rel="stylesheet"
            />
         MDB 
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.css"
            rel="stylesheet"
            />-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

        <style>
            footer {
                position: relative;
                bottom: 0;
                width: 100%;
                white-space: nowrap;
                line-height: 60px;
            }
        </style>
        <script>
            function changeurl(url) {
                var host = "${pageContext.request.contextPath}/";
                window.location.href = host + url;
            }
        </script>
    </head>

    <body>
        <header>
            <div class="row">
                <div class="col-md-3">
                    <div class="hleft">
                        <a href="${pageContext.request.contextPath}/emp/sell"><h3>Bán hàng</h3></a>
                    </div>
                </div>

                <div class="col-md-9">
                    <div class="hright">
                        <i class="fas fa-history fa-2x" 
                           onclick="changeurl('emp/sell-history')" style="color: white;"></i>
                        <div class="dropdown">
                            <button class="dropbtn">
                                <i class="far fa-user-circle fa-2x" style="color: white;"></i>
                            </button>
                            <div class="dropdown-content">
                                <!--<h5>Tài khoản</h5>-->
                                <h5 onclick="changeurl('logout')">Đăng xuất</h5>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <section style="background-color: #eee;">
            <div style="text-align: center;">
                <h4 style="color: green; font-weight: bold;" id="mess-sell">
                    ${requestScope.mess}</h4>
            </div>
            <form id="myForm" action="sell" method="POST">
                <table id="theTable" hidden>
                    <tr>
                        <td>Nhân viên <span style="color: red;">*</span></td>
                        <td>
                            <c:set var="e" value="${requestScope.employee}"/>
                            <input type="hidden" name="emp" value="${e.id}"/>
                            ${e.name} - ${e.phone}
                        </td>
                        <td>Khách hàng <span style="color: red;">*</span></td>
                        <td>
                            <select name="cus" required>
                                <c:forEach items="${requestScope.customers}" var="c">
                                    <option value="${c.id}"
                                            >${c.name} - ${c.phone}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>

                <div class="container py-5"> 
                    <p class="sticky-top" style="background-color: #eeeeee;">
                        <c:forEach items="${requestScope.groups}" var="group">
                            <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#g${group.id}" aria-expanded="false" aria-controls="collapseExample">
                                ${group.name}
                            </button>
                        </c:forEach>
                        <button type="button" class="btn btn-outline-dark ms-5" id="btn-total">Tổng tiền: 0 VND</button>
                        <button type="button" class="btn btn-danger ms-1" onclick="sell()">Bán hàng</button>
                    </p>
                    <c:forEach items="${requestScope.groups}" var="group">
                        <div class="collapse" id="g${group.id}">
                            <c:forEach items="${requestScope.products}" var="product" >
                                <c:choose>
                                    <c:when test="${product.group.id == group.id}">
                                        <div class="row justify-content-center mb-3">
                                            <div class="col-md-12 col-xl-10">
                                                <div class="card shadow-0 border rounded-3">
                                                    <div class="card-body">
                                                        <div class="row">
                                                            <div class="col-md-4 col-lg-4 col-xl-4">
                                                                <h5>${product.name}</h5>
                                                                <div class="d-flex flex-row">
                                                                    <div class="text-danger mb-1 me-2">
                                                                        Giá: 
                                                                    </div>
                                                                    <span id="p${product.id}" data-price="${product.priceExport}"><fmt:formatNumber type = "number" 
                                                                                                                                         value = "${product.priceExport}"/> VND</span>
                                                                    <div class="text-danger mb-1 me-2 ms-3">
                                                                        Tồn: 
                                                                    </div>
                                                                    <span><fmt:formatNumber type = "number" 
                                                                                      value = "${product.quantity}"/></span>
                                                                </div>

                                                            </div>
                                                            <div class="col-md-4 col-lg-4 col-xl-4">
                                                                <div class="input-group form-outline">
                                                                    <span class="input-group-text" id="inputGroupPrepend">Số lượng</span>
                                                                    <input type="number" class="form-control" aria-describedby="inputGroupPrepend" 
                                                                           placeholder="0" min="0" id="q${product.id}" name="quantity${product.id}"
                                                                           oninput="calMoney(${product.id})"/>
                                                                </div>

                                                            </div>
                                                            <div class="col-md-4 col-lg-4 col-xl-4 border-sm-start-none border-end">

                                                                <div class="d-flex flex-row align-items-center mb-1" style="text-align: right;">
                                                                    <h4 class="mb-1 me-1 money" id="money${product.id}">0</h4>
                                                                    <span class="text-danger"><s>VND</s></span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </div>
                    </c:forEach>
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
<!-- MDB -->
<!--<script
    type="text/javascript"
    src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.js"
></script>-->
<script>
    function calMoney(id) {
        document.getElementById("mess-sell").innerHTML = "";
        var str = "p" + id;
        var price = document.getElementById(str).getAttribute("data-price");
        str = "q" + id;
        var quantity = document.getElementById(str).value;
        var money = price * quantity;

        document.getElementById("money" + id).innerHTML = money.toLocaleString('vn-VN');

        var moneys = document.getElementsByClassName("money");
        var total = 0;
        for (var x of moneys) {
            total += Number(x.innerHTML);
        }

        document.getElementById("btn-total").innerHTML = "Tổng tiền: " + (total * 1000).toLocaleString('vn-VN') + " VND";
    }

    function sell() {
        if (confirm("Bạn chắc chắn với đơn hàng này?") == true) {
            document.getElementById("myForm").submit();
        }
    }
</script>
</body>
</html>
