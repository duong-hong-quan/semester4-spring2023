<%-- 
    Document   : viewcart2
    Created on : Mar 1, 2023, 8:14:39 AM
    Author     : LAPTOP_HONGQUAN
--%>

<%@page import="java.util.Map"%>
<%@page import="quandh.cart.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Cart</h1>
        <%
//            1.Customer goes to his her cart place
            if (session != null) {
//                2.Customer take his her cart
                CartObj cart = (CartObj) request.getSession().getAttribute("CART");
                if (cart != null) {
//                    3.Customer get all items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {

        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Name</th>                           
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            <form action="DispatchServlet2">
                <%                    int count = 0;
                    for (String id : items.keySet()) {
                %>

                <tr>
                    <td><%= ++count%></td>
                    <td><%= id%></td>                           
                    <td><%= items.get(id)%></td>
                    <td>
                        <input type="checkbox" name="chkItem" value="<%= id%>" />
                    </td>
                </tr>



                <%
                    }
                %> 
                <tr>

                    <td colspan="3">
                        <a href="DispatchServlet2?txtAction=clothesstore">Add more item to your cart</a>

                    </td>

                    <td>
                        <input type="submit" value="Remove selected items" name="txtAction" />

                    </td>
                </tr>
            </form>

        </tbody>
    </table>
    <a href="DispatchServlet2?txtAction=Check out">Check out</a>
    <%
                return;
            }

        }
    %>
    <h1 style="color: red;">Your cart is empty</h1>
    <a href="DispatchServlet2?txtAction=clothesstore">Back to shop</a>

    <%
        }
    %>

</body>
</html>
