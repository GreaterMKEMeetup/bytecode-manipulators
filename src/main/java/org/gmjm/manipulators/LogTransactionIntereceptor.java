package org.gmjm.manipulators;

import java.math.BigDecimal;

import net.bytebuddy.implementation.bind.annotation.Argument;
import net.bytebuddy.implementation.bind.annotation.This;

import org.gmjm.example.MyBankAccount;

public class LogTransactionIntereceptor {
	public static void log(@Argument(0) BigDecimal amount, @This Object account) {
		MyBankAccount myBankAccount = (MyBankAccount)account;
		System.out.println(String.format("Adjusting balance of account: {%s} by: {%s}", myBankAccount.getAccountIdentifier(), amount));
	}
}
