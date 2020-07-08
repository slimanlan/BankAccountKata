package com.bank.kata;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bank.kata.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TransfertControllerTest {
	
	private MockMvc mockMvc;

	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Autowired
	private ObjectMapper objectMapper;
	
    @Before
    public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	
	@Test
    public void test_transfert_Amount() throws Exception 
    {
		MvcResult result = mockMvc.perform(put("/transfert/5/6/500")
			    .contentType("application/json"))
			    .andExpect(status().isOk())
			    .andReturn();
		
		String contentAsString = result.getResponse().getContentAsString();
		
	     JSONObject jsonObject = new JSONObject(contentAsString);
	     
	     assertTrue("1000".equals(jsonObject.get("payerOldAmount").toString()));
	     assertTrue("500".equals(jsonObject.get("payerNewAmount").toString()));
	     assertTrue("1200".equals(jsonObject.get("payeeOldAmount").toString()));
	     assertTrue("1700".equals(jsonObject.get("payeeNewAmount").toString()));

    }
	
	
}
