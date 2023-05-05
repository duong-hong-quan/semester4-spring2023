/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quandh.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class CartObj implements Serializable {

    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }

    public boolean addItemToCart(String id, int quantity) {
        boolean result = false;
        //1. Check data validation
        if (id == null) {
            return result;
        }
        if (id.trim().isEmpty()) {
            return result;
        }
        if (quantity <= 0) {
            return result;
        }
        //2. Check exsted items
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //3. drops item to items
        if (this.items.containsKey(id)) {
            int quan = items.get(id);
            quantity = quantity + quan;
        }
        items.put(id, quantity);
        result = true;
        return result;
    }

    public boolean removeItemFromCart(String id, int quantity) {
        boolean result = false;
        //1. Check data validation
        if (id == null) {
            return result;
        }
        if (id.trim().isEmpty()) {
            return result;
        }
        if (quantity <= 0) {
            return result;
        }
        //2. Check existed items
        if (this.items == null) {
            return result;
        }
        //3. Check existed items
        if (this.items.containsKey(id)) {
            //4. compare quantity
            int quan = this.items.get(id);
            if (quan < quantity) {
                return result;
            }
            quantity = quan - quantity;
            if (quantity == 0) {
                items.remove(id);
                if (this.items.isEmpty()) {
                    this.items = null;
                }
            } else {
                items.put(id, quantity);//update cart items
            }
            result = true;
        }// 
        return result;

    }

    public void removeItemFromCart(String id) {
        if (items == null) {
            return;
        }
        if (items.containsKey(id)) {
            items.remove(id);
        }
        if (items.isEmpty()) {
            items = null;
        }
    }
}
