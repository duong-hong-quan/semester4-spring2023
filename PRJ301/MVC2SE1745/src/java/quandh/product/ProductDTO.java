/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quandh.product;

import java.io.Serializable;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class ProductDTO implements Serializable {

    private String sku;
    private String name;
    private int quantity;
    private float price;
    private int status;

    public ProductDTO() {
    }

    public ProductDTO(String sku, String name, int quantity, float price, int status) {
        this.sku = sku;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }

    /**
     * @return the sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @param sku the sku to set
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProductDTO{" + "sku=" + sku + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ", status=" + status + '}';
    }

 
}
