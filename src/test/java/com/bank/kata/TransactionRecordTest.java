package com.bank.kata;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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

import com.bank.kata.model.TransactionRecord;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TransactionRecordTest {
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
    public void test_save_transactionRecord_by_id() throws Exception 
    {
		MvcResult result = mockMvc.perform(post("/saveTransaction/3/4/500")
			    .contentType("application/json"))
			    .andExpect(status().isOk())
			    .andReturn();
		
	     TransactionRecord transactionRecord = ObjectMapperConversion(result);
	     
	     assertTrue(transactionRecord.getAmount() == 500);
	     assertTrue(transactionRecord.getPayer().getId() == 3);
	     assertTrue(transactionRecord.getPayee().getId() == 4);
	     
    }
	
	@Test
    public void test_get_transactionRecord_by_id() throws Exception 
    {
		MvcResult result = mockMvc.perform(get("/getTransactionRecord/100")
			    .contentType("application/json"))
			    .andExpect(status().isOk())
			    .andReturn();
		
		TransactionRecord transactionRecord = ObjectMapperConversion(result);
	     
		assertTrue(transactionRecord.getId() == 100);
	    assertTrue(transactionRecord.getAmount() == 20);
	    assertTrue(transactionRecord.getPayer().getId() == 2);
	    assertTrue(transactionRecord.getPayee().getId() == 1);
	     
    }
	
	@Test
    public void test_get_all_transactions_from_payer() throws Exception 
    {
		int payerId = 5; 

		MvcResult result = mockMvc.perform(get("/getAllTransactionsFromPayer/" + payerId)
			    .contentType("application/json"))
			    .andExpect(status().isOk())
			    .andReturn();
		
		List<TransactionRecord> alltransactionRecordToPayee = ObjectMapperConversionList(result);
	     
		/* checking all the payer id is equals to the searched payer in the request */		
		alltransactionRecordToPayee
				.stream()
				.forEach(transactionRecord  -> {
					assertTrue(transactionRecord.getPayer().getId() == payerId);
				  });
		
		assertTrue(alltransactionRecordToPayee.size() == 3);
	     
    }
	
	
	@Test
    public void test_get_all_transactions_to_payee() throws Exception 
    {
		int payeeId = 2; 
		
		MvcResult result = mockMvc.perform(get("/getAllTransactionsToPayee/" + 2)
			    .contentType("application/json"))
			    .andExpect(status().isOk())
			    .andReturn();
		
		List<TransactionRecord> alltransactionRecordToPayee = ObjectMapperConversionList(result);
	     
		/* checking all the payee id is equals to the searched payee in the request */		
		alltransactionRecordToPayee
				.stream()
				.forEach(transactionRecord  -> {
					assertTrue(transactionRecord.getPayee().getId() == payeeId);
				  });
		
		assertTrue(alltransactionRecordToPayee.size() == 5);
		
	     
    }
	
	private TransactionRecord ObjectMapperConversion(MvcResult result) throws Exception {
		String contentAsString = result.getResponse().getContentAsString();
		return objectMapper.readValue(contentAsString, TransactionRecord.class);
	}
	
	private List<TransactionRecord> ObjectMapperConversionList(MvcResult result) throws Exception {
		String contentAsString = result.getResponse().getContentAsString();
		return objectMapper.readValue(contentAsString,  new TypeReference<List<TransactionRecord>>(){});
	}
}
