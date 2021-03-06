package com.example.demo.service;

import com.example.demo.service.XmlConverters.OrderBuilder;
import com.example.demo.service.XmlConverters.ParseXml;
import com.example.demo.service.XmlConverters.XmlBuilder;
import com.example.demo.models.order.Order;
import org.springframework.stereotype.Service;


@Service
public class TransformXmlService {


    public String parseInputXmlOrder(String inputXml) throws IllegalArgumentException {
        String input = validate(inputXml);
        String rootContent = ParseXml.getRootElement(input);
        Order order = OrderBuilder.getOrder(rootContent);
        return XmlBuilder.buildOrder(order);
    }

    private String validate(String input) throws IllegalArgumentException {
        input = input.replace("\n", "")
                .replace(" ", "");
        if (input.isBlank()) throw new IllegalArgumentException("Content is blank");
        return input;
    }

}

