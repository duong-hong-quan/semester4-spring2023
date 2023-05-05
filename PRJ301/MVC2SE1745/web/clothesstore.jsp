<%-- 
    Document   : clothesstore
    Created on : Mar 1, 2023, 7:13:56 AM
    Author     : LAPTOP_HONGQUAN
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="quandh.product.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clothes store</title>
    </head>
    <body>
        <h1>Clothes store</h1>
        <a href="DispatchServlet2?txtAction=View Cart">View cart</a>
        <table border="1">
            <thead>
                <tr>
                    <th>SKU</th>
                    <th>Name</th>               
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Status</th>
                    <th>Action</th>


                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<ProductDTO> products = (ArrayList) request.getAttribute("products");
                    for (ProductDTO p : products) {
                %>
            <form action="DispatchServlet2">

                <tr>
                    <td>
                        <%= p.getSku()%> 
                        <input type="hidden" name="sku" value="  <%= p.getSku()%> " />
                    </td>
                    <td><%= p.getName()%></td>
                    <td><%= p.getQuantity()%></td>
                    <td><%= p.getPrice()%></td>
                    <td><%= p.getStatus()%></td>
                    <td>
                        <input type="submit" name="txtAction" value="Add To Cart" />
                    </td>


                </tr>



            </form>
            <%
                }

            %>


        </tbody>
    </table>

</body>
</html>
