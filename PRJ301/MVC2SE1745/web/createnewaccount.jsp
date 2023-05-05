<%-- 
    Document   : createnewaccount
    Created on : Mar 2, 2023, 9:11:40 AM
    Author     : LAPTOP_HONGQUAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>Create new account</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div>Create new account</div>
        <form action="DispatchServlet">
            Username : <input type="text" name="txtUser" />         
            Password : <input type="password" name="txtPass" />
            Confirm : <input type="password" name="txtRePass" />
            Full name : <input type="text" name="txtFullname" />

            <input type="submit" value="Create account" />
            <input type="reset" value="reset" />
        </form>
    </body>
</html>
