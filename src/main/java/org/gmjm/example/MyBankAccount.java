package org.gmjm.example;

import java.math.BigDecimal;

import org.gmjm.manipulators.LogTransaction;


public class MyBankAccount {
	
	private final String accountIdentifier;
	private BigDecimal accountBalance; 
	
	public MyBankAccount(String accountIdentifier)
	{
		this.accountIdentifier = accountIdentifier;
		this.accountBalance = new BigDecimal("0");
	}
	
	@LogTransaction
	public void adjustAcountBalance(BigDecimal amount)
	{
		accountBalance = accountBalance.add(amount);
	}
	
	public BigDecimal getAccountBalance()
	{
		return accountBalance;
	}
	
	public String getAccountIdentifier()
	{
		return accountIdentifier;
	}

	@Override
	public String toString() {
		return "MyBankAccount [accountIdentifier=" + accountIdentifier
				+ ", accountBalance=" + accountBalance + "]";
	}
	
	
	
}
