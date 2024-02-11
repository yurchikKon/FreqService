package com.example.freqService.Controlleres;

import com.example.freqService.Services.StringServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class BaseControllerTestIT {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    StringServiceImpl service;

    @Test
    void stringSolving_correctInput_ValidResponseEntity() throws Exception{
        var reqBuilder = post("/base/work")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "string": "aaaaabcccc"
                        }
""");

        this.mockMvc.perform(reqBuilder)
                .andExpectAll(
                        status().isOk(),
                        content().string("\"a\":5,\"c\":4,\"b\":1")
                );
    }

    @Test
    void stringSolving_incorrectInput_invalidResponseEntity() throws Exception{
        var reqBuilder = post("/base/work")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "string": "aaaaa1bcccc"
                        }
""");

        this.mockMvc.perform(reqBuilder)
                .andExpectAll(
                        status().is(400),
                        content().string("Incorrect string, use only characters")
                );
    }
}