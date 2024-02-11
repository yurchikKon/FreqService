package com.example.freqService.Controlleres;

import com.example.freqService.Requests.StringRequest;
import com.example.freqService.Services.StringServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BaseControllerTest {
    @Mock
    StringServiceImpl service;

    @InjectMocks
    BaseController controller;

    @Test
    void stringSolving_correctInput_ValidResponseEntity(){
        //given
            String validOutput = "\"a\":5,\"c\":4,\"b\":1";
        StringRequest stringRequest = new StringRequest("aaaaabcccc");
        Mockito.doReturn(validOutput).when(this.service).result(stringRequest);
        Mockito.doReturn(true).when(this.service).checkOnDigit(stringRequest);
        //when
        var responseEntity = this.controller.stringSolving(stringRequest);

        //then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(validOutput,responseEntity.getBody());
    }

    @Test
    void stringSolving_incorrectInput_BadStatusCode(){
        //given
        StringRequest stringRequest = new StringRequest("aaaaa1bccc");
        String validOutput = "Incorrect string, use only characters";
        Mockito.doReturn(false).when(service).checkOnDigit(stringRequest);
        //when
        var responseEntity = this.controller.stringSolving(stringRequest);

        //then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
        assertEquals(validOutput,responseEntity.getBody());
    }

}