/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quandh.details;

import quandh.order.OrderDTO;
import quandh.product.ProductDTO;

/**
 *
 * @author PC_HONGQUAN
 */
public class DetailsDTO {

    private int id;
    private ProductDTO product;
    private OrderDTO order;
    private int quantity;
    private float price;
    private float total;

    public DetailsDTO() {
    }

    public DetailsDTO(int id, ProductDTO product, OrderDTO order, int quantity, float price, float total) {
        this.id = id;
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the product
     */
    public ProductDTO getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    /**
     * @return the order
     */
    public OrderDTO getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(OrderDTO order) {
        this.order = order;
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
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "DetailsDTO{" + "id=" + id + ", product=" + product + ", order=" + order + ", quantity=" + quantity + ", price=" + price + ", total=" + total + '}';
    }
    
}
