package com.example.freqService.Services;

import com.example.freqService.Requests.StringRequest;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface StringService {

public Map<Character,Integer> solve(String string);
public String result(StringRequest stringRequest);
public boolean checkOnDigit(StringRequest string);
}
