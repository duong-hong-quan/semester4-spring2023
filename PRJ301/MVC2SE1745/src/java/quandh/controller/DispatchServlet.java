/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package quandh.controller;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import quandh.util.MyApplicationConstants;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

    private final String START_SERVLET = "StartServlet";
    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_CONTROLLER = "login";
    private final String SEARCH_CONTROLLER = "searchlastname";
    private final String DELETE_CONTROLLER = "delete";
    private final String UPDATE_CONTROLLER = "update";
    private final String ADD_TO_CART_CONTROLLER = "AddItemToCartServlet";
    private final String REMOVE_SELECTED_ITEMS_CONTROLLER = "RemoveSelectedItemsServlet";

    private final String VIEWCART_PAGE = "viewcart.jsp";
    private final String CREATE_ACCOUNT_CONTROLLER = "CreateAccountServlet";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        1.Which button user click?
        String button = request.getParameter("txtAction");
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.LOGIN_PAGE);

        try {
            if (button == null) {
//                url = START_SERVLET;
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.LOGIN_PAGE);
            } else if (button.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else if (button.equals("Search")) {
                url = SEARCH_CONTROLLER;
            } else if (button.equals("Delete")) {
                url = DELETE_CONTROLLER;
            } else if (button.equals("Update")) {
                url = UPDATE_CONTROLLER;
            } else if (button.equals("Add To Cart")) {
                url = ADD_TO_CART_CONTROLLER;
            } else if (button.equals("View Your Cart")) {
                url = VIEWCART_PAGE;
            } else if (button.equals("Remove Selected Items")) {
                url = REMOVE_SELECTED_ITEMS_CONTROLLER;
            } else if (button.equals("Create account")) {
                url = CREATE_ACCOUNT_CONTROLLER;
            }
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
