package com.raoulduke.com.api1_1;



import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order")
public class Order {

    @Id
    private Integer Id;

    @Column(name = "currencyPair")
    private String currencyPair;
    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "type")
    private String type;

    @OneToMany(targetEntity = Trade.class)
    @JoinColumns{
        @JoinColumn(name = "buyOrder")
        @JoinColumn(name = "sellOrder")
    }

    List<Trade> trades;

    private Date createdDateTime;

    private double filledQuantity;

    private String orderStatus = "OPEN";


    public List<Trade> getTrades() {
        return trades;
    }

    public void setTrades(List<Trade> trades) {
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
        if (filledQuantity == quantity) orderStatus= "CLOSED";

    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
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

    public double getUnfilledQuantity(){
        return quantity-filledQuantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + Id +
                ", currencyPair='" + currencyPair + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
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
