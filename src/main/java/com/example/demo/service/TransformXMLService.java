package com.example.demo.service;

import com.example.demo.models.XMLConverters.XMLRowItem;
import com.example.demo.models.order.Order;
import com.example.demo.models.order.OrderItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TransformXMLService {

    private final String ROOT_START = "<root>";
    private final String ROOT_END = "</root>";

    private final String ID_START = "<id>";
    private final String ID_END = "</id>";

    private final String BATCH_START = "<batch>";
    private final String BATCH_END = "</batch>";

    private final String ROWS_START = "<rows>";
    private final String ROWS_END = "</rows>";

    private final String ROW_START = "<row>";
    private final String ROW_END = "</row>";

    private final String DESCRIPTION_START = "<description>";
    private final String DESCRIPTION_END = "</description>";

    public String parseInputXML(String inputXML) throws IllegalArgumentException {
        if (inputXML.isBlank()) throw new IllegalArgumentException("Input is blank");
        String input = parseInput(inputXML);
        String rootContent = getElement(input, ROOT_START, ROOT_END);
        Order order = getOrder(rootContent);
        return order.toXML();
    }

    private String parseInput(String input) {
        return input.replace("\n", "")
                .replace(" ", "");
    }

    private Order getOrder(String input) throws IllegalArgumentException {
        String orderId = getOrderId(input);
        Order order = new Order(orderId);
        addOrderRows(order.getRows(), input);
        return order;
    }

    private String getOrderId(String orderElement) throws IllegalArgumentException {
        String id = getElement(orderElement, ID_START, ID_END);
        String batch = getElement(orderElement, BATCH_START, BATCH_END);
        return id + "_" + batch;
    }

    private void addOrderRows(ArrayList<XMLRowItem> rows, String orderElement){
        String rowsElement = getElement(orderElement, ROWS_START, ROWS_END);
        if (rowsElement.isBlank()){
            throw new IllegalArgumentException("There has to be at least one <row>-element");
        }
        while (elementContains(rowsElement, ROW_START, ROW_END)) {
            OrderItem orderItem = getOrderItem(rowsElement);
            rows.add(orderItem);
            rowsElement = getNextElement(rowsElement, ROW_END);
        }
    }

    private OrderItem getOrderItem(String rowElement) {
        String description = getDescription(rowElement);
        return new OrderItem(description);
    }

    private String getDescription(String element) {
        if (elementContains(element, DESCRIPTION_START, DESCRIPTION_END)) {
            return getElement(element, DESCRIPTION_START, DESCRIPTION_END);
        }
        return null;
    }

    private String getElement(String input, String startElement, String endElement) throws IllegalArgumentException {
        int startIndex = input.indexOf(startElement);
        int endIndex = input.indexOf(endElement);
        if (startIndex == -1 || endIndex == -1) {
            throw new IllegalArgumentException("Missing " + startElement + "-element");
        }
        startIndex += startElement.length();
        return input.substring(startIndex, endIndex);
    }

    private boolean elementContains(String element, String startElement, String endElement) {
        int startIndex = element.indexOf(startElement);
        int endIndex = element.indexOf(endElement);
        if (startIndex == -1 || endIndex == -1) return false;
        return startIndex < endIndex;
    }

    private String getNextElement(String element, String endElement) {
        int indexOfEndElement = element.indexOf(endElement);
        int endIndexOfEndElement = indexOfEndElement + endElement.length();
        return element.substring(endIndexOfEndElement);
    }
}

