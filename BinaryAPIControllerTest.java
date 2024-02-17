package com.ontariotechu.sofe3980;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BinaryAPIController.class)
@AutoConfigureMockMvc
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testAddition() throws Exception {
        this.mvc.perform(get("/calculate").param("operand1", "101").param("operator", "+").param("operand2", "110"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value("1011"));
    }

    @Test
    public void testSubtraction() throws Exception {
        this.mvc.perform(get("/calculate").param("operand1", "110").param("operator", "-").param("operand2", "101"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value("1"));
    }

    @Test
    public void testMultiplication() throws Exception {
        this.mvc.perform(get("/calculate").param("operand1", "101").param("operator", "*").param("operand2", "110"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value("11110"));
    }

    @Test
    public void testBitwiseAnd() throws Exception {
        this.mvc.perform(get("/calculate").param("operand1", "101").param("operator", "&").param("operand2", "110"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value("100"));
    }

    @Test
    public void testBitwiseOr() throws Exception {
        this.mvc.perform(get("/calculate").param("operand1", "101").param("operator", "|").param("operand2", "110"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value("111"));
    }

    @Test
    public void testBitwiseXor() throws Exception {
        this.mvc.perform(get("/calculate").param("operand1", "101").param("operator", "^").param("operand2", "110"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value("11"));
    }
}
