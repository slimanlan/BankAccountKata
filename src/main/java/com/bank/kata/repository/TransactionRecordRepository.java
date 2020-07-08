package com.bank.kata.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bank.kata.model.Account;
import com.bank.kata.model.TransactionRecord;

public interface TransactionRecordRepository  extends CrudRepository<TransactionRecord, Integer> {
	public List<TransactionRecord> findTransactionRecordByPayer(Account payer);
	public List<TransactionRecord> findTransactionRecordByPayee(Account payee);
}
