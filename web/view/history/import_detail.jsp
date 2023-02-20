<%-- 
    Document   : import_detail
    Created on : Nov 5, 2021, 10:40:40 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

        <c:set var="i" value="${requestScope.import}"/>
    </head>

    <body>
        <div>
            <table>
                <tr>
                    <td><h3>Import no:</h3></td>
                    <td>${i.importNo}</td>
                </tr>

                <tr>
                    <td><h3>Thời gian:</h3></td>
                    <td>
                        <fmt:formatDate pattern = "yyyy-MM-dd HH:mm:ss" 
                                        value = "${i.date}" />
                    </td>
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
                <c:forEach items="${requestScope.import.importDetails}" var="id">
                    <c:set var="a" value="${a=a+1}"/>
                    <tr>
                        <td>${a}</td>
                        <td>${id.pid.name}</td>
                        <td>${id.quantity}</td>
                        <td>
                            <fmt:formatNumber type = "number" 
                                              value = "${id.pid.priceImport}"/>
                        </td>
                        <td>
                            <fmt:formatNumber type = "number" 
                                              value = "${id.total}"/>
                        </td>
                    </tr>
                    
                </c:forEach>
            </table>
            <h3 style="margin-left: 800px;">Tổng hoá đơn: 
                <fmt:formatNumber type = "number" 
                                      value = "${i.total}"/>
            </h3>
        </div>
    </body>
</html>
