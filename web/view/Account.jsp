<%-- 
    Document   : Account
    Created on : Oct 5, 2021, 9:38:07 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <script>
            function changeurl(url) {
                var host = "${pageContext.request.contextPath}/";
                window.location.href = host + url;
            }
        </script>

        <style>
            section .row .col-md-5 {
                border: 3px solid black;
                border-radius: 25px;
                padding: 85px 25px;
                text-align: center;
                margin: 35px 40px;
                background-color: #E3E3E3;
                color: #31708f;
            }

            section .row .col-md-5 h1 {
                font-weight: bold;
                margin-bottom: 20px;
            }
        </style>

    </head>

    <body>
        <header>
            <h3>Vui lòng chọn để tiếp tục </h3>
        </header>

        <section>
            <div class="row">
                <div class="col-md-5" onclick="changeurl('statistic')" style="cursor: pointer;">
                    <h1>Giao hàng</h1>
                    <h4>
                        -Bán sỉ-
                    </h4>
                </div>
                <div class="col-md-5">
                    <h1>Bán hàng tại nhà</h1>
                    <h4>-Bán lẻ-</h4>
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
