package com.raoulduke.com.api1_1;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "trade")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer Id;

    @Column(name = "buyOrder")
    int buyOrder;

    @Column(name = "sellOrder")
    int sellOrder;

    @Column(name = "timestamp")
    Date timestamp;
    @Column(name = "price")
    double price;

    public void setBuyOrder(int buyOrderId) {
        this.buyOrder = buyOrderId;
    }

    public void setSellOrder(int sellOrderId) {
        this.sellOrder = sellOrderId;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    double quantity;

    public int getBuyOrder() {
        return buyOrder;
    }

    public int getSellOrder() {
        return sellOrder;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public double getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }
}
