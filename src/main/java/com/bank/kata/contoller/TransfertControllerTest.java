package com.bank.kata.contoller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.kata.service.AccountService;

@RestController
public class TransfertControllerTest {
	
	@Autowired
	AccountService accountService;
	
	@PutMapping("/transfert/{payerId}/{payeeId}/{amount}")
	public Map<String, Integer> tranfertAmount(@PathVariable int payerId, @PathVariable int payeeId, @PathVariable int amount) {
		 return accountService.transfertAmount(payerId, payeeId, amount);
	}
}
