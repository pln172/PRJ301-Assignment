<%-- 
    Document   : newjsplogin
    Created on : Sep 29, 2021, 3:13:10 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SALE MANAGEMENT</title>
        <link href="css/login_style.css" rel="stylesheet" type="text/css"/>
    </head>
    
    <body>
        <form action="login" method="POST">
            <table>
                <tr>
                    <td id="caption">SALE MANAGEMENT</td>
                </tr>
                
                <tr>
                    <td>Username <span>*</span></td>
                </tr>
                
                <tr>
                    <td><input type="text" name="user" required/></td>
                </tr>

                <tr>
                    <td>Password <span>*</span></td>
                </tr>
                
                <tr>
                    <td><input type="password" name="pass" required/></td>
                </tr>

                <tr>
                    <td><input id="login" type="submit" value="LOGIN"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
