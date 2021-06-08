package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import java.io.StringReader;
import java.io.StringWriter;

@Service
public class TransformXMLService {

    private static final Logger log = LoggerFactory.getLogger(TransformXMLService.class);

    public TransformXMLService(){}

    public String transformXML(String message) {
        String result = "";
        try {
            StringReader reader = new StringReader(message);
            StringWriter writer = new StringWriter();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(
                new javax.xml.transform.stream.StreamSource("src/main/resources/transform.xsl"));

            transformer.transform(
                new javax.xml.transform.stream.StreamSource(reader),
                new javax.xml.transform.stream.StreamResult(writer));

            result = writer.toString();
            log.info("Transformed message: " + result);

        } catch (Exception e) {
            log.error("error message: " + e.getMessage() + "\n" + "Message: " + message);
            e.printStackTrace();
        }
        return result;
    }
}
