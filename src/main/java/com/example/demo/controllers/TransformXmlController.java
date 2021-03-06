package com.example.demo.controllers;

import com.example.demo.service.TransformXmlService;
import com.example.demo.service.ErrorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
class TransformXmlController {

    private static final Logger log = LoggerFactory.getLogger(TransformXmlController.class);

    @Autowired
    private TransformXmlService transformXMLService;

    @Autowired
    private ErrorService errorService;

    @PostMapping(
            value = "/transform",
            headers = "content-type=application/xml",
            produces = "application/xml"
    )
    public ResponseEntity<String> transform(@RequestBody String content) {
        try {
            String result = transformXMLService.parseInputXmlOrder(content);
            if (result.isBlank()) {
                throw new IllegalArgumentException("Can not parse given xml");
            }
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return errorService.http400(e.getMessage());
        }
    }
}
