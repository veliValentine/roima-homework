package com.example.demo.service.XmlConverters;

import com.example.demo.models.order.Order;
import com.example.demo.models.order.OrderItem;

import java.util.ArrayList;

public class OrderBuilder extends ParseXml {

    protected static final String ID_START = "<id>";
    protected static final String ID_END = "</id>";

    protected static final String BATCH_START = "<batch>";
    protected static final String BATCH_END = "</batch>";

    protected static final String ROWS_START = "<rows>";
    protected static final String ROWS_END = "</rows>";

    public static Order getOrder(String input) throws IllegalArgumentException {
        String orderId = getOrderId(input);
        Order order = new Order(orderId);
        addOrderRows(order.getRows(), input);
        return order;
    }

    private static String getOrderId(String orderElement) throws IllegalArgumentException {
        String id = getElement(orderElement, ID_START, ID_END);
        String batch = getElement(orderElement, BATCH_START, BATCH_END);
        return id + "_" + batch;
    }

    private static void addOrderRows(ArrayList<OrderItem> rows, String orderElement) {
        String rowsElement = getElement(orderElement, ROWS_START, ROWS_END);
        if (rowsElement.isBlank()) {
            throw new IllegalArgumentException("There has to be at least one <row>-element");
        }
        while (elementContains(rowsElement, ROW_START, ROW_END)) {
            OrderItem orderItem = OrderItemBuilder.getOrderItem(rowsElement);
            rows.add(orderItem);
            rowsElement = getNextRowElement(rowsElement);
        }
    }
}
