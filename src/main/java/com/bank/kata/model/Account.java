package com.bank.kata.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Account {
	
	@Id
	private int id; 
	
	private String name;
	
	private int amount;
	
	public void depositAmount(int amount) {
		this.amount += amount;
	}

	public void withdrawAmount(int amount) {
		this.amount -= amount;
	}
	
}
