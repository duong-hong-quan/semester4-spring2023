/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quandh.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import quandh.util.DBHelper;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class ProductDAO implements Serializable {

    private ArrayList<ProductDTO> products;

    public ArrayList<ProductDTO> getProducts() {
        return products;
    }

    public ArrayList<ProductDTO> getAll() {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT * FROM PRODUCT WHERE STATUS = 1 ";
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                String sku = rs.getString("sku");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                float price = rs.getFloat("price");
                int status = rs.getInt("status");
                ProductDTO product = new ProductDTO(sku, name, quantity, price, status);
                if (this.products == null) {
                    products = new ArrayList<>();
                }
                products.add(product);
            }
        } catch (Exception e) {
        }
        return products;
    }
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        System.out.println(dao.getAll());
    }
}
