package com.example.freqService.Services;

import com.example.freqService.Requests.StringRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StringServiceImplTest {

    @InjectMocks
    StringServiceImpl service;



    @Test
    void solve_validMap(){
        //given
        Map<Character,Integer> correctMap = new HashMap<>();
        correctMap.put('a',5);
        correctMap.put('c',4);
        correctMap.put('b',1);
        String input = "aaaaaccccb";

        //when
        Map respMap = service.solve(input);

        //then
        assertNotNull(respMap);
        assertEquals(respMap,correctMap);
    }

    @Test
    void result_validString(){
        //given
        String correctString = "\"a\":5,\"c\":4,\"b\":1";
        StringRequest request = new StringRequest("aaaaabcccc");

        //when
        String resString = service.result(request);

        //then
        assertNotNull(resString);
        assertEquals(resString,correctString);
    }

    @Test
    void checkOnDigit_correctInput_True(){
        //given
        StringRequest request = new StringRequest("aaaaabcccc");

        //when
        boolean result = service.checkOnDigit(request);

        //then
        assertTrue(result);
    }

    @Test
    void checkOnDigit_incorrectInput_False(){
        //given
        StringRequest request = new StringRequest("aaaaa1bcccc");

        //when
        boolean result = service.checkOnDigit(request);

        //then
        assertFalse(result);
    }

}