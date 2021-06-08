package com.example.demo.controllers;

import com.example.demo.service.DemoTransformer;
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
class DemoController {

    private static final Logger log = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private DemoTransformer demoTransformer;

    @Autowired
    private ErrorService errorService;

    @PostMapping(
            value = "/transform",
            headers = "content-type=application/xml",
            produces="application/xml"
    )
    public ResponseEntity<String> transform(@RequestBody String content) {
        if (content.isBlank()){
            return errorService.http400("Request body is blank");
        }
        String result = demoTransformer.transform(content);
        if (result.isBlank()){
            return errorService.http500("Could not parse given XML file");
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}