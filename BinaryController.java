package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
            .andExpect(model().attribute("operand1", ""))
            .andExpect(model().attribute("operand1Focused", false));
    }

    @Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
            .andExpect(model().attribute("operand1", "111"))
            .andExpect(model().attribute("operand1Focused", true));
    }

    @Test
    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1110"))
            .andExpect(model().attribute("operand1", "111"));
    }

    @Test
    public void postParameterWithSubtractionOperator() throws Exception {
        this.mvc.perform(post("/").param("operand1","50").param("operator","-").param("operand2","30"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "20"))
            .andExpect(model().attribute("operand1", "50"));
    }

    @Test
    public void postParameterWithMultiplicationOperator() throws Exception {
        this.mvc.perform(post("/").param("operand1","5").param("operator","*").param("operand2","6"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "30"))
            .andExpect(model().attribute("operand1", "5"));
    }

    @Test
    public void postParameterWithDivisionOperator() throws Exception {
        this.mvc.perform(post("/").param("operand1","100").param("operator","/").param("operand2","25"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "4"))
            .andExpect(model().attribute("operand1", "100"));
    }
}
