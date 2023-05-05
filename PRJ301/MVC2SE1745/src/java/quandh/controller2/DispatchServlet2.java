/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quandh.controller2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class DispatchServlet2 extends HttpServlet {

    private final String CLOTHES_STORE_CONTROLLER = "clothesstore";
    private final String ADD_TO_CART_CONTROLLER = "AddItemToCart2";
    private final String VIEW_CART_PAGE = "viewcart2.jsp";
    private final String REMOVE_ITEM_CONTROLLER = "RemoveItem";
    private final String CHECK_OUT_CONTROLLER = "checkout";

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
        String button = request.getParameter("txtAction");
        String url = CLOTHES_STORE_CONTROLLER;
        try {
            if (button == null) {

            } else if (button.equals("clothesstore")) {
                url = CLOTHES_STORE_CONTROLLER;
            } else if (button.equals("Add To Cart")) {
                url = ADD_TO_CART_CONTROLLER;
            } else if (button.equals("View Cart")) {
                url = VIEW_CART_PAGE;
            } else if (button.equals("Remove selected items")) {
                url = REMOVE_ITEM_CONTROLLER;
            } else if (button.equals("Check out")) {
                url = CHECK_OUT_CONTROLLER;
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
