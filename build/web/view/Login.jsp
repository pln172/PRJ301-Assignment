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
        <link rel="icon" href="img/favicon.png" type="image/png" sizes="16x16">
        <Link rel = "stylesheet" href = "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
        <link href="css/login_style.css" rel="stylesheet" type="text/css"/>

        <script>
            function show() {
                var type = document.getElementById("pass").type;

                if (type === "text") {
                    document.getElementById("pass").type = "password";
                } else {
                    document.getElementById("pass").type = "text";
                }
            }
        </script>
    </head>

    <body>
        <form action="login" method="POST">
            <table>
                <caption id="caption">SALE MANAGEMENT</caption>

                <tr>
                    <td>Username <span>*</span></td>
                    <td><input type="text" name="user" pattern="[a-zA-Z0-9]+"
                               value="${requestScope.user}" required/></td>
                </tr>

                <tr>
                    <td>Password <span>*</span></td>
                    <td>
                        <input id="pass" type="password" name="pass" 
                               value="${requestScope.pass}" 
                               pattern="[a-zA-Z0-9]+" required/>
                        <span onclick="show()" style="cursor: pointer;">
                            <i class="fas fa-eye"></i></span>
                    </td>
                </tr>
            </table>
            <div>
                <input type="checkbox" name="remember" value="yes"
                       ${requestScope.remember == "yes" ? "checked=\"checked\"" : ""}/>Remember me<br/>
                <input id="login" type="submit" value="LOGIN"/><br/>
                <p"><a href="forgetPass">Forgot password?</a></p>
                <p style="color: red; font-weight: bold;">
                    ${requestScope.err}
                </p>
            </div>
        </form>
    </body>
</html>
