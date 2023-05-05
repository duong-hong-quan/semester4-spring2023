<%-- 
    Document   : search
    Created on : Feb 19, 2023, 4:00:59 PM
    Author     : LAPTOP_HONGQUAN
--%>

<%@page import="java.util.List"%>
<%@page import="quandh.registration.RegistrationDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font style="color: red">Welcome, ${sessionScope.USER.lastname} </font>
        <br>
        <form action="DispatchServlet">
            <input type="text" name="${param.txtSearchValue}" />
            <input type="submit" value="Search" name="txtAction" />      
        </form>
        <c:set var="searchValue" value="${param.txtSearchValue}"></c:set>

        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"></c:set>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Last Name</th>
                            <th>Role</th>
                            <th>Delete</th>                  
                            <th>Update</th>


                        </tr>

                    </thead>
                    <tbody>
                        <c:set var="count" value="0"></c:set>
                        <c:forEach var="dto" items="${result}" >
                            <tr>
                                <td>${count = count +1}</td>
                                <td>${dto.username}</td>                        
                                <td>${dto.password}</td>
                                <td>${dto.lastname}</td>

                                <td>${dto.role}</td>


                                <td>Delete</td>
                                <td>Update</td>

                            </tr>
                        </c:forEach>


                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty result}">
                <h2>No record is matched !</h2>
            </c:if>

        </c:if>
        <%--    <%
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    String username = "";
                    for (Cookie c : cookies) {
                        String temp = c.getName();
                        if (!temp.equals("JSESSIONID")) {
                            username = temp;
                        }
                    }
            %>
            <p style="color: red">Welcome, <%= username%></p>
            <%
                }
            %>




        <h1>Search</h1>

        <%
            String searchValue = request.getParameter("txtSearchValue");

        %>
        <form action="DispatchServlet">
            <input type="text" name="txtSearchValue"  value="<%= searchValue%>"/>
            <input type="submit" value="Search" name="txtAction" />      
        </form>


        <%
            if (searchValue != null) {
                List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
                if (result != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Last Name</th>
                    <th>Role</th>
                    <th>Delete</th>                  
                    <th>Update</th>


                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String urlRewritting = "DispatchServlet"
                                + "?txtAction=Delete"
                                + "&pk=" + dto.getUsername()
                                + "&txtSearchValue=" + searchValue;
                %>

            <form action="DispatchServlet">
                <tr>
                    <td>
                        <%= ++count%>
                    </td>
                    <td>
                        <%= dto.getUsername()%>
                        <input type="hidden" name="txtUser" value="<%= dto.getUsername()%>" />

                    </td>
                    <td>
                        <input type="text" name="txtPass" value="<%= dto.getPassword()%>" />
                    </td>
                    <td>
                        <%= dto.getLastname()%>
                    </td>
                    <td>
                        <input type="checkbox" name="chkAdmin" value="ADMIN" 
                               <%
                                   if (dto.isRole()) {
                               %>
                               checked="checked"
                               <%
                                   }
                               %>
                               />
                    </td>
                    <td>
                        <a href="<%= urlRewritting%>">Delete</a>
                    </td>

                    <td>
                        <input type="hidden" name="searchValue" value="<%= searchValue%>" />
                        <input type="submit" value="Update" name="txtAction" /> 
                    </td>
                </tr> 
            </form>   

            <%
                }//End traverse
            %>
        </tbody>
    </table>

    <%
    } else {
    %>
    <h2 style="color: red">
        No result is matched!!
    </h2>
    <%
            }// end of no record is matched!
        }// end search value is not really 
%>
        --%>
    </body>
</html>