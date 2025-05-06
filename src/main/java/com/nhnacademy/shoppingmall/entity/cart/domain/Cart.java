package com.nhnacademy.shoppingmall.entity.cart.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private final Map<Integer, CartItem> itemMap = new LinkedHashMap<>();

    public boolean addItem(int cartProductId, int cartQuantity) {
        if(itemMap.containsKey(cartProductId)) {
            return false;
        }
        itemMap.put(cartProductId, new CartItem(cartProductId, cartQuantity));
        return true;
    }

    public void updateItem(int cartProductId, int cartQuantity) {
        if (itemMap.containsKey(cartProductId)) {
            itemMap.get(cartProductId).setCartQuantity(cartQuantity);
        }
    }

    public void removeItem(int cartProductId) {
        itemMap.remove(cartProductId);
    }

    public List<CartItem> getCartItemList() {
        return new ArrayList<>(itemMap.values());
    }

    public boolean isEmpty() {
        return itemMap.isEmpty();
    }

    public void clear() {
        itemMap.clear();
    }
}
