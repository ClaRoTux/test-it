package com.example.testit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.testit.service.BookService;

@SpringBootTest 
@AutoConfigureMockMvc 
public class BookControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean 
    private BookService bookService;

    @Test 
    void test1(){

    }
}
