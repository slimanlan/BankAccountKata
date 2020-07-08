# BankAccountKata
Bank Account Kata

# Requirements : 

Spring boot v2.

Java +1.8.

# Environments : 

Spring. 

Spring data jpa.

H2 database.

Lombok.

Tomcat.

Mavean. 

# User Stories :

Bank account Api that manages a bank account

● Write some code to create a bank application and to withdraw/deposit a valid
amount of money in/from the account

● Write some code to transfer a specified amount of money from one bank
account (the payer) to another (the payee)

● Write some code to keep a record of the transfer for both bank accounts in a
transaction history

● Write some code to query a bank account's transaction history for any bank
transfers to or from a specific account

# Presnetation of TDD Tests :

### AccountControllerTest  

Test the following endpoints exposed by The Account RestController : 

Depoist Amount into account. 
Withdraw Amount from account.
Insufficient funds exception when withdraw Amount from account. 

### TransfertControllerTest

Test the following endpoints exposed by The Transfert RestController : 

Transfert amount from acount to another account endpoint. 

### TransactionRecordTest

Test the following endpoints exposed by The TransactionRecord RestController :

Save Transaction Record into the  DB. 

Get a transaction Record from the DB. 

Get all transaction Record from a payer.

Get all transaction Record to a payee.


# Data.sql : 

Intialize the H2 database with data for TDD tests.
