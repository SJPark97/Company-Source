package com.jobtang.sourcecompany.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
@Component
public class ResponseHandler {
    public ResponseEntity response(Object data, String message) {
        HashMap result = new HashMap();
        result.put("data", data);
        result.put("message", message);
        result.put("status", 200);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity response(Object data) {
        HashMap result = new HashMap();
        result.put("data", data);
        result.put("message", "");
        result.put("status", 200);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
