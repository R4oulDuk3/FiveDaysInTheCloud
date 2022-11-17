package com.raoulduke.crypto.fivedaysincloud.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Trade {

    @Id
    Integer id;

    Integer buyOrderId;


    Integer sellOrderId;

    long timestamp;

    double price;

    public void setBuyOrderId(Integer buyOrderId) {
        this.buyOrderId = buyOrderId;
    }

    public void setSellOrderId(Integer sellOrderId) {
        this.sellOrderId = sellOrderId;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp.getTime();
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    double quantity;

    public Integer getBuyOrderId() {
        return buyOrderId;
    }

    public Integer getSellOrderId() {
        return sellOrderId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
