package com.bank.kata.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.kata.model.Account;
import com.bank.kata.model.TransactionRecord;
import com.bank.kata.repository.AccountRepository;
import com.bank.kata.repository.TransactionRecordRepository;

@Service
public class TransactionRecordService {
	
	@Autowired
	TransactionRecordRepository transactionRecordRepository; 
	
	@Autowired
	AccountService accountService;
	
	public TransactionRecord addTransaction(int payerId, int payeeId, int amount) {
		
		TransactionRecord transactionRecord = new TransactionRecord();
		
		transactionRecord.setAmount(amount);
		transactionRecord.setDate(new Date());
		
		accountService.transfertAmount(payerId, payeeId, amount); 
		
		Account payer = accountService.findAccountById(payerId);
		Account payee = accountService.findAccountById(payeeId);
	
		transactionRecord.setPayer(payer);
		transactionRecord.setPayee(payee);
		
		return transactionRecordRepository.save(transactionRecord);
		
	}
	
	public TransactionRecord getTransaction(int transactionRecordId) {
		Optional<TransactionRecord> transactionRecord = transactionRecordRepository.findById(transactionRecordId);
		if (transactionRecord.isPresent()) return transactionRecord.get();
		return null;
	}
	
	public List<TransactionRecord> getAllTransactionsFromPayer(int payerId) {
		Account payer = accountService.findAccountById(payerId);
		return transactionRecordRepository.findTransactionRecordByPayer(payer);
	}
	
	public List<TransactionRecord> getAllTransactionsToPayee(int payeeId) {
		Account payee = accountService.findAccountById(payeeId);
		return transactionRecordRepository.findTransactionRecordByPayee(payee);

	}
}
