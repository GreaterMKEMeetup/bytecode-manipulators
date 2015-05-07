package org.gmjm.bytecode;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import net.bytebuddy.agent.ByteBuddyAgent;

import org.gmjm.example.MyBankAccount;
import org.gmjm.manipulators.JavassistLogTransactionAgent;
import org.junit.Test;

public class TestMyBankAccount_Javassist {
	@Test
	public void testAdjustBalanceWithTransactionLogging_Javassist()
	{
		//Using ByteBuddy agent to avoid having to package as a Java Agent jar.
		JavassistLogTransactionAgent.premain(null, ByteBuddyAgent.installOnOpenJDK()); 
		
		MyBankAccount bankAccount = new MyBankAccount("Andy");
		
		bankAccount.adjustAcountBalance(new BigDecimal("50.00"));
		bankAccount.adjustAcountBalance(new BigDecimal("12.00"));
		bankAccount.adjustAcountBalance(new BigDecimal("-13.00"));
		bankAccount.adjustAcountBalance(new BigDecimal("101.00"));
		bankAccount.adjustAcountBalance(new BigDecimal("30.45"));
		
		assertEquals(new BigDecimal("180.45"),bankAccount.getAccountBalance());
		
	}
}
