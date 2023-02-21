<%-- 
    Document   : ForgetPass
    Created on : Nov 1, 2021, 11:39:57 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SALE MANAGEMENT</title>
        <link rel="icon" href="img/favicon.png" type="image/png" sizes="16x16">

        <style>
            body {
                background-image: url("https://t3.ftcdn.net/jpg/02/90/89/76/360_F_290897614_7RdAsk2GmumcGWZ2qklmV74hKlNmznSx.jpg");
                background-size: cover;
            }
            
            form {
                margin-left: 600px;
                margin-top: 300px;
            }

            img {
                width: 80px;
                height: 80px;
                margin-left: 90px;
                margin-bottom: 30px;
            }

            td {
                padding-right: 20px;
            }
        </style>
    </head>
    <body>
        <form action="forgetPass" method="POST">
            <img src="img/mail.png" alt="Email"/>

            <table>
                <tr>
                    <td style="color: white;">Email:</td>
                    <td>
                        <input type="email" name="email" value="${requestScope.email}"/>
                    </td>
                </tr>
            </table>
            <input style="margin: 20px 90px; padding: 10px 10px; border-radius: 5px; 
                   font-weight: bold; font-size: 16px; cursor: pointer;" 
                   type="submit" value="Send"/>
        </form>
        <p style="color: yellow; margin: 10px 650px; font-weight: bold;">
            ${requestScope.message}
        </p>
        
        <a href="login" style="color: white; margin-left: 700px; font-size: 17px;">Login</a>
    </body>
</html>
