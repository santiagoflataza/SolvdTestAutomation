package com.solvd.dataBaseOnlineShop.model.comerce;

import com.solvd.dataBaseOnlineShop.model.AbstractEntity;

import java.util.Objects;

public class Order extends AbstractEntity {
    private int cartId;

    public Order(){}

    public Order(int id, int cartId) {
        super(id);
        this.cartId = cartId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "cartId=" + cartId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return getCartId() == order.getCartId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCartId());
    }
}
