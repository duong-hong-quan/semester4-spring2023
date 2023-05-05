/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quandh.order;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author PC_HONGQUAN
 */
public class OrderDTO {

    private int id;
    private String day;
    private String username;
    private float total;

    public OrderDTO() {
    }

    public OrderDTO(int id, String day, String username) {
        this.id = id;
        this.day = day;
        this.username = username;
    }

    public OrderDTO(int id, String day, String username, float total) {
        this.id = id;
        this.day = day;
        this.username = username;
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
     * @return the day
     */
    public String getDay() {

        return day;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
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
        return "OrderDTO{" + "id=" + id + ", day=" + day + ", username=" + username + ", total=" + total + '}';
    }

   
}
