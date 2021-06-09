package com.example.demo.models.XmlConverters;

import com.example.demo.models.order.Order;
import com.example.demo.models.order.OrderItem;

import java.util.List;

public class XmlBuilder {

    private static StringBuilder builder;

    public static String buildOrder(Order order) {
        builder = new StringBuilder();
        builder.append("<order>\n");
        addOrderId(order.getOrderId());
        addDocumentTimeDate(order.getTimeStamp());
        addOrderRows(order.getRows());
        builder.append("</order>");
        return builder.toString();
    }

    private static void addOrderId(String orderId) {
        builder.append("<orderId>");
        builder.append(orderId);
        builder.append("</orderId>");
    }

    private static void addDocumentTimeDate(String timeDate) {
        builder.append("<documentDateTime>");
        builder.append(timeDate);
        builder.append("</documentDateTime>");
    }

    private static void addOrderRows(List<OrderItem> orderItems) {
        if (orderItems.size() > 0) {
            builder.append("<orderRows>");
            builder.append("\n");
            for (int i = 0; i < orderItems.size(); i++) {
                OrderItem order = orderItems.get(i);
                addOrderRow(order, i + 1);
            }
            builder.append("</orderRows>");
        }
    }

    private static void addOrderRow(OrderItem order, int number) {
        builder.append("<orderRow>");
        addRowNumber(number);
        addDescription(order.getDescription());
        builder.append("</orderRow>");
    }

    private static void addRowNumber(int rowNumber) {
        builder.append("<rowNumber>");
        builder.append(rowNumber);
        builder.append("</rowNumber>");
    }

    private static void addDescription(String description) {
        builder.append("<description>");
        builder.append(description);
        builder.append("</description>");
    }

}
