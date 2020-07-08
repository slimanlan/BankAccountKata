package com.bank.kata.repository;

import org.springframework.data.repository.CrudRepository;

import com.bank.kata.model.Account;

public interface AccountRepository extends CrudRepository<Account, Integer>{

}
