package com.example.demo.service.XmlConverters;

import com.example.demo.models.order.OrderItem;

public class OrderItemBuilder extends ParseXml {

    private static final String DESCRIPTION_START = "<description>";
    private static final String DESCRIPTION_END = "</description>";

    public static OrderItem getOrderItem(String rowElement) {
        String description = getDescription(rowElement);
        return new OrderItem(description);
    }

    private static String getDescription(String element) {
        if (elementContains(element, DESCRIPTION_START, DESCRIPTION_END)) {
            return getElement(element, DESCRIPTION_START, DESCRIPTION_END);
        }
        return null;
    }
}
