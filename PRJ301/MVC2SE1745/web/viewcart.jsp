<%-- 
    Document   : viewcart
    Created on : Feb 27, 2023, 8:35:31 AM
    Author     : PHAMKHANG
--%>

<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Map"%>
<%@page import="quandh.cart.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Book Store</h1>
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

        <form action="DispatchServlet">
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
                        <td colspan="3">Add more items</td>
                        <td><input type="submit" value="Remove Selected Items" name="txtAction" /></td>                           

                    </tr>
                </tbody>
            </table>

        </form>
        <%
                return;
            }

        } else {
        %>
        <h1>Your cart is empty</h1>
        <%
                }

            }
        %>
    </body>
</html>
