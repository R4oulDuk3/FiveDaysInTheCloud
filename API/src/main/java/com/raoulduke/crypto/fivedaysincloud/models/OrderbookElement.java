package com.raoulduke.crypto.fivedaysincloud.models;

public class OrderbookElement {

    double quantity;
    double price;

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderbookElement{" +
                "quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
