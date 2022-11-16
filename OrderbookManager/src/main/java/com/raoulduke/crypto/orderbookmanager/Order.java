package com.raoulduke.crypto.orderbookmanager;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Document
public class Order {

    @Id
    private String id;
    private String currencyPair;
    private double price;
    private double quantity;

    private String type;

    ArrayList<String> trades = new ArrayList<>();


    private Date createdDateTime;

    private double filledQuantity;

    private String orderStatus;


    public ArrayList<String> getTrades() {
        return trades;
    }

    public void setTrades(ArrayList<String> trades) {
        this.trades = trades;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public double getFilledQuantity() {
        return filledQuantity;
    }

    public void setFilledQuantity(double filledQuantity) {
        this.filledQuantity = filledQuantity;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public double getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", currencyPair='" + currencyPair + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", type='" + type + '\'' +
                ", trades=" + trades +
                ", createdDateTime=" + createdDateTime +
                ", filledQuantity=" + filledQuantity +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
