package com.bank.kata.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.kata.model.TransactionRecord;
import com.bank.kata.service.TransactionRecordService;

@RestController
public class TransactionController {
	
	@Autowired
	TransactionRecordService transactionRecordService;
	
	@PostMapping("/saveTransaction/{payerId}/{payeeId}/{amount}")
	public TransactionRecord addTransaction(@PathVariable int payerId, @PathVariable int payeeId, @PathVariable int amount) {
		return transactionRecordService.addTransaction(payerId, payeeId, amount);
	}
	
	@GetMapping("/getTransactionRecord/{transactionRecordId}")
	public TransactionRecord getTransaction(@PathVariable int transactionRecordId) {
		return transactionRecordService.getTransaction(transactionRecordId);
	}
	@GetMapping("/getAllTransactionsFromPayer/{payerId}")
	public List<TransactionRecord> getAllTransactionsFromPayer(@PathVariable int payerId) {
		return transactionRecordService.getAllTransactionsFromPayer(payerId);
	}
	
	@GetMapping("/getAllTransactionsToPayee/{payeeId}")
	public List<TransactionRecord> getAllTransactionsToPayee(@PathVariable int payeeId) {
		return transactionRecordService.getAllTransactionsToPayee(payeeId);
	}
	
}
