/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quandh.details;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import quandh.order.OrderDAO;
import quandh.order.OrderDTO;
import quandh.product.ProductDTO;
import quandh.util.DBHelper;

/**
 *
 * @author PC_HONGQUAN
 */
public class DetailsDAO implements Serializable {

    public String getDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String day = currentDate.format(formatter);
        return day;
    }


    public void checkout(Map<String, Integer> items) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement stm = null;
        float total = 0;
        try {
            conn = DBHelper.makeConnection();

            // create a new order
            String day = getDate();
            String username = "Quan"; // replace with actual username
            String insertOrderSql = "INSERT INTO [Order] (day, username) VALUES (?, ?)";
            ps = conn.prepareStatement(insertOrderSql);
            ps.setString(1, day);
            ps.setString(2, username);
            ps.executeUpdate();

            // get the id of the newly created order
            String getIdOrder = "SELECT MAX(id) FROM [order]";
            int orderId = -1;
            stm = conn.createStatement();
            rs = stm.executeQuery(getIdOrder);

            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            for (String sku : items.keySet()) {
                // get product information
                String selectProductSql = "SELECT * FROM PRODUCT WHERE SKU = ?";
                PreparedStatement productPs = conn.prepareStatement(selectProductSql);
                productPs.setString(1, sku.trim());
                ResultSet productRs = productPs.executeQuery();

                if (productRs.next()) {
                    String skuDTO = productRs.getString("sku");
                    String name = productRs.getString("name");
                    int quantityDTO = productRs.getInt("quantity");
                    float price = productRs.getFloat("price");
                    int status = productRs.getInt("status");

                    // insert details
                    int quantity = items.get(sku);
                    float subtotal = quantity * price;
//                     insert the details of each item in the order
                    String insertDetailsSql = "INSERT INTO Details (SkuID, OrderID, quantity, price, total) VALUES (?, ?, ?, ?, ?)";
                    ps = conn.prepareStatement(insertDetailsSql);

                    ps.setString(1, skuDTO);
                    ps.setInt(2, orderId);
                    ps.setInt(3, quantity);
                    ps.setFloat(4, price);
                    ps.setFloat(5, subtotal);
                    ps.executeUpdate();

                    // update product quantity
                    int newQuantity = quantityDTO - quantity;
                    String updateProductSql = "UPDATE PRODUCT SET quantity = ? WHERE SKU = ?";
                    PreparedStatement updatePs = conn.prepareStatement(updateProductSql);
                    updatePs.setInt(1, newQuantity);
                    updatePs.setString(2, skuDTO);
                    updatePs.executeUpdate();

                } else {
                    throw new SQLException("Product not found with SKU " + sku);
                }
            }
            // Cập nhật tổng số tiền của đơn hàng vào cơ sở dữ liệu
            String sql4 = "SELECT SUM(total) AS [Total] FROM [Details] WHERE OrderID = ?";
            ps = conn.prepareStatement(sql4);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getFloat("Total");
            }
            String sqlOrder3 = "UPDATE [Order] "
                    + "SET total = ? "
                    + "WHERE id = ?";
            try (PreparedStatement stm2 = conn.prepareStatement(sqlOrder3)) {
                stm2.setFloat(1, total);
                stm2.setInt(2, orderId);
                stm2.executeUpdate();
            }
            conn.commit(); // commit the transaction if all queries succeeded
        } catch (ClassNotFoundException | SQLException ex) {
            if (conn != null) {
                try {
                    conn.rollback(); // rollback the transaction if any query failed
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            ex.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
