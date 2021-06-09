package com.example.demo.models.order;

import com.example.demo.models.TimeStamp;

import java.util.ArrayList;
import java.util.Collection;

public class Order{

    private String orderId;
    private String timeStamp;
    private ArrayList<OrderItem> rows;

    public Order(String orderId) {
        this.orderId = orderId;
        timeStamp = TimeStamp.currentUTCTime();
        this.rows = new ArrayList<>();
    }

    public void addRow(OrderItem item) {
        rows.add(item);
    }

    public void addRows(Collection<OrderItem> items) {
        rows.addAll(items);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public ArrayList<OrderItem> getRows() {
        return rows;
    }

    public void setRows(ArrayList<OrderItem> rows) {
        this.rows = rows;
    }

}
