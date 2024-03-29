<%-- 
    Document   : detail
    Created on : Oct 19, 2021, 9:26:56 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SALE MANAGEMENT</title>
        <link rel="icon" href="../img/favicon.png" type="image/png" sizes="16x16">
        <style>
            div table {
                margin: 0 auto;
            }

            td {
                padding: 5px;
                text-align: left;
            }
        </style>

        <c:set var="e" value="${requestScope.employee}"/>
    </head>

    <body>
        <div>
            <table>
                <tr>
                    <td><h3>Id:</h3></td>
                    <td>${e.id}</td>
                    <td>
                        <input type="text" max="50" style="border: none; background-color: white;" disabled/>
                    </td>
                    <td><h3>Tên</h3></td>
                    <td>${e.name}</td>
                </tr>

                <tr>
                    <td><h3>Giới tính:</h3></td>
                    <td>${e.gender ? "nam" : "nữ"}</td>
                    <td></td>
                    <td><h3>Ngày sinh:</h3></td>
                    <td>${e.dob}</td>
                </tr>

                <tr>
                    <td><h3>SĐT:</h3></td>
                    <td>${e.phone}</td>
                    <td></td>
                    <td><h3>Email:</h3></td>
                    <td>${e.email}</td>
                </tr>

                <tr>
                    <td><h3>Địa chỉ:</h3></td>
                    <td>${e.address}</td>
                    <td></td>
                    <td><h3>Ngày bắt đầu:</h3></td>
                    <td>${e.starting_date}</td>
                </tr>

                <tr>
                    <td><h3>Ngày nghỉ:</h3></td>
                    <td>${e.leaving_date}</td>
                    <td></td>
                    <td><h3>Trạng thái:</h3></td>
                    <td>${e.active ? "đang làm" : "đã nghỉ"}</td>
                </tr>
            </table>
        </div>
    </body>
</html>
