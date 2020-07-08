package com.bank.kata.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.kata.exception.InSufficientFundException;
import com.bank.kata.model.Account;
import com.bank.kata.repository.AccountRepository;

@Service
public class AccountService {
    
	@Autowired
    AccountRepository accountRepository;
	
	public Account findAccountById(int acountId) {
		return accountRepository.findById(acountId).get();
	}
		
	public Account depositAmount(int id, int amount) {

		Optional<Account> account = accountRepository.findById(id);
		
		if (account.isPresent()) {
			account.get().depositAmount(amount);
			return accountRepository.save(account.get());
		}
		
		return null;		
	}
	
	public Account withdrawAmount(int id, int amount) throws InSufficientFundException {

		Optional<Account> account = accountRepository.findById(id);
		
		if (account.isPresent()) {
			
			if (amount > account.get().getAmount()) {
		            throw new InSufficientFundException(String.format(
		                    "Current balance %d is less than requested amount %d",
		                    account.get().getAmount(), amount));
		     }
			account.get().withdrawAmount(amount);
			return accountRepository.save(account.get());
		}
		
		return null;		
	}

	public Map<String, Integer> transfertAmount(int payerId, int payeeId, int amount) {
		// TODO Auto-generated method stub

		Optional<Account> payer = accountRepository.findById(payerId);
		Optional<Account> payee = accountRepository.findById(payeeId);

		if (payer.isPresent() && payee.isPresent()) {
			
			Map<String, Integer> map = new HashMap<>();
			map.put("payerOldAmount", payer.get().getAmount());
			map.put("payeeOldAmount", payee.get().getAmount());
			
			
			payer.get().withdrawAmount(amount);
			payee.get().depositAmount(amount);
			accountRepository.save(payer.get());
			accountRepository.save(payee.get());
			
			payer = accountRepository.findById(payerId);
			payee = accountRepository.findById(payeeId);

			map.put("payerNewAmount", payer.get().getAmount());
			map.put("payeeNewAmount", payee.get().getAmount());
			
			return map;
		}
		
		return null;
	}

}
