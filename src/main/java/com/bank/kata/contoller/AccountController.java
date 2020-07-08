package com.bank.kata.contoller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.kata.exception.InSufficientFundException;
import com.bank.kata.model.Account;
import com.bank.kata.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	AccountService accountService; 
	
	@PutMapping("/depositAmount/{id}/{amount}")
	public Account depositAmount(@PathVariable int id, @PathVariable int amount) {
		return accountService.depositAmount(id, amount);
	}
	
	@PutMapping("/withdrawAmount/{id}/{amount}")
	public Account withdrawAmount(@PathVariable int id, @PathVariable int amount) throws InSufficientFundException {
		return accountService.withdrawAmount(id, amount);
	}
	
}
