package com.raoulduke.crypto.fivedaysincloud;

import java.util.List;

public class Orderbook {

    List<OrderbookElement> buyOrders;
    List<OrderbookElement> sellOrders;

    public List<OrderbookElement> getBuyOrders() {
        return buyOrders;
    }

    public void setBuyOrders(List<OrderbookElement> buyOrders) {
        this.buyOrders = buyOrders;
    }

    public List<OrderbookElement> getSellOrders() {
        return sellOrders;
    }

    public void setSellOrders(List<OrderbookElement> sellOrders) {
        this.sellOrders = sellOrders;
    }
}
