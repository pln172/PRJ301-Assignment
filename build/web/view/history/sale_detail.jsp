<%-- 
    Document   : sale_detail
    Created on : Oct 12, 2021, 10:03:53 PM
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
        <link rel="icon" href="../../img/favicon.png" type="image/png" sizes="16x16">

        <style>
            div table {
                margin: 0 auto;
            }

            td {
                padding: 5px;
                text-align: center;
            }
        </style>

        <c:set var="o" value="${requestScope.order}"/>
    </head>
    <body>
        <div>
            <table>
                <tr>
                    <td><h3>Mã HĐ:</h3></td>
                    <td>${o.orderNo}</td>
                </tr>

                <tr>
                    <td><h3>Thời gian:</h3></td>
                    <td>
                        <fmt:formatDate pattern = "yyyy-MM-dd HH:mm:ss" 
                                        value = "${o.date}" />
                    </td>
                </tr>

                <tr>
                    <td><h3>Nhân viên:</h3></td>
                    <td>${o.eid.name} - ${o.eid.phone}</td>
                </tr>

                <tr>
                    <td><h3>Khách hàng:</h3></td>
                    <td>${o.cid.name}</td>
                </tr>
            </table>

            <table border="1px;">
                <caption><h3>Sản phẩm</h3></caption>
                <tr>
                    <td>STT</td>
                    <td>Tên</td>
                    <td>Số lượng</td>
                    <td>Giá đơn vị</td>
                    <td>Tổng</td>
                </tr>
                <c:set var="a" value="0"/>
                <c:forEach items="${requestScope.order.orderDetails}" var="od">
                    <c:set var="a" value="${a=a+1}"/>
                    <tr>
                        <td>${a}</td>
                        <td>${od.pid.name}</td>
                        <td>${od.quantity}</td>
                        <td>
                            <fmt:formatNumber type = "number" 
                                              value = "${od.price.priceExport}"/>
                        </td>
                        <td>
                            <fmt:formatNumber type = "number" 
                                              value = "${od.total}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <h3 style="margin-left: 800px;">Tổng hoá đơn: 
                <fmt:formatNumber type = "number" 
                                  value = "${o.total}"/>
            </h3>
        </div>
    </body>
</html>
