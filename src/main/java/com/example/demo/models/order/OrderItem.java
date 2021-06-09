package com.example.demo.models.order;

import com.example.demo.models.XmlConverters.XmlRowItem;

public class OrderItem implements XmlRowItem {
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

    @Override
    public String toXmlRowItem(int rowNumber) {
        StringBuilder builder = new StringBuilder();
        builder.append("<orderRow>");
        builder.append("<rowNumber>");
        builder.append(rowNumber);
        builder.append("</rowNumber>");
        builder.append("<description>");
        builder.append(this.description);
        builder.append("</description>");
        builder.append("</orderRow>");
        return builder.toString();
    }
}
