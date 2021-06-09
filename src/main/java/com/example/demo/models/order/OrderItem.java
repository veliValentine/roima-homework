package com.example.demo.models.order;

public class OrderItem {
    private String description;

    public OrderItem(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
