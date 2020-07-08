package com.bank.kata.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
@Data
@Entity
public class TransactionRecord {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private int id;
	
	@OneToOne
	private Account payer; 
	
	@OneToOne
	private Account payee;
	
	private int amount;
	
	private Date date;
}
