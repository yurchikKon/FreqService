package com.example.freqService.Controlleres;

import com.example.freqService.Requests.StringRequest;
import com.example.freqService.Services.StringServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base")
@RequiredArgsConstructor
public class BaseController {
    private final StringServiceImpl service;

    @PostMapping("/work")
    public ResponseEntity<?> stringSolving(@RequestBody StringRequest stringRequest){
        if(service.checkOnDigit(stringRequest)){
            return ResponseEntity.ok(service.result(stringRequest));
        }
        else return ResponseEntity.badRequest().body("Incorrect string, use only characters");
    }
}
