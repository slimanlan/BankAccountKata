package com.bank.kata;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
public class AccountControllerTest {
	
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
    public void test_deposit_Amount() throws Exception 
    {
		MvcResult result = mockMvc.perform(put("/depositAmount/1/1200")
			    .contentType("application/json"))
			    .andExpect(status().isOk())
			    .andReturn();

		Account account = ObjectMapperConversion(result); 
				
		assertTrue(account.getAmount() == 21200);
    }
	
	@Test
    public void test_withdraw_Amount() throws Exception 
    {
		MvcResult result = mockMvc.perform(put("/withdrawAmount/1/200")
			    .contentType("application/json"))
			    .andExpect(status().isOk())
			    .andReturn();
		
		Account account = ObjectMapperConversion(result); 

		assertTrue(account.getAmount() == 21000);
		
    }
	
	
	@Test
    public void test_withdraw_Amount_insufficient_funds_exception() throws Exception  
    {
		 String message = mockMvc.perform(put("/withdrawAmount/7/25000")
								   	.contentType("application/json"))
								    .andExpect(status().is5xxServerError())
								    .andReturn().getResolvedException().getMessage();
		
		String exceptionMessage = "Current balance 8500 is less than requested amount 25000";
		
		assertTrue(exceptionMessage.equals(message));
		
    }
	
	
	private Account ObjectMapperConversion(MvcResult result) throws Exception {
		String contentAsString = result.getResponse().getContentAsString();
		return objectMapper.readValue(contentAsString, Account.class);
	}
}
