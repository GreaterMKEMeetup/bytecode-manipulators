package org.gmjm.manipulators;

import java.math.BigDecimal;

import org.gmjm.example.MyBankAccount;

public class JavassistTransactionInterceptor {
	public static void after(MyBankAccount myBankAccount, BigDecimal adjustment, BigDecimal previousBalance)
	{
		System.out.println(String.format("Adusted account: {%s} by: {%s}. "
				+ "\n\tPrevious Balance: {%s} Current Balance: {%s}.",
				myBankAccount.getAccountIdentifier(),
				adjustment,
				previousBalance,
				myBankAccount.getAccountBalance()));
	} 
}
