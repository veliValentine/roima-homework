package com.example.demo.models.order;

import com.example.demo.models.TimeStamp;
import com.example.demo.models.XMLConverters.XMLItem;
import com.example.demo.models.XMLConverters.XMLRowItem;

import java.util.ArrayList;
import java.util.Collection;

public class Order implements XMLItem {

    private String orderId;
    private String timeStamp;
    private ArrayList<XMLRowItem> rows;

    public Order(String orderId) {
        this.orderId = orderId;
        timeStamp = TimeStamp.currentUTCTime();
        this.rows = new ArrayList<>();
    }

    public void addRow(XMLRowItem item) {
        rows.add(item);
    }

    public void addRows(Collection<XMLRowItem> items) {
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

    public ArrayList<XMLRowItem> getRows() {
        return rows;
    }

    public void setRows(ArrayList<XMLRowItem> rows) {
        this.rows = rows;
    }

    @Override
    public String toXML() {
        StringBuilder builder = new StringBuilder();
        builder.append("<order>");
        builder.append("\n");
        builder.append("<orderId>");
        builder.append(orderId);
        builder.append("</orderId>");
        builder.append("\n");
        builder.append("<documentDateTime>");
        builder.append(timeStamp);
        builder.append("</documentDateTime>");
        builder.append("\n");
        if(rows.size() > 0){
            builder.append("<orderRows>");
            for (int i = 0; i < rows.size(); i++) {
                XMLRowItem row = rows.get(i);
                builder.append(row.toXMLRowItem(i + 1));
                builder.append("\n");
            }
            builder.append("</orderRows>");
        }
        builder.append("</order>");
        return builder.toString();
    }
}
