package com.nhnacademy.shoppingmall.entity.cart.domain;

public class CartItem {
    private int cartProductId;
    private int cartQuantity;

    public CartItem(int cartProductId, int cartQuantity) {
        this.cartProductId = cartProductId;
        this.cartQuantity = cartQuantity;
    }

    public int getCartProductId() {
        return cartProductId;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public void increaseQuantity(int amount) {
        this.cartQuantity += amount;
    }
}
