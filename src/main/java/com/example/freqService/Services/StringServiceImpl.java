package com.example.freqService.Services;

import com.example.freqService.Requests.StringRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class StringServiceImpl implements StringService{

    @Override
    public Map<Character, Integer> solve(String string) {
        Map<Character,Integer> map = new HashMap<>();
        for(char c : string.toCharArray()){
            if (map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }
            else{
                map.put(c,1);
            }
        }
        return map;
    }

    @Override
    public String result(StringRequest stringRequest) {
        Map<Character,Integer> map = this.solve(stringRequest.getString());
        return map.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(entry -> "\""+entry.getKey()+"\""+":"+entry.getValue())
                .reduce((s1,s2) -> s1+","+s2).get();
    }

    public boolean checkOnDigit(StringRequest string){
        for(Character c : string.getString().toCharArray()){
            if(Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
}
