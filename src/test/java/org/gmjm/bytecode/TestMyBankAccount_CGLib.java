package org.gmjm.bytecode;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.gmjm.example.MyBankAccount;
import org.gmjm.manipulators.CGLibClassEnhancer;
import org.junit.Test;

public class TestMyBankAccount_CGLib {
	@Test
	public void testAdjustBalanceWithTransactionLogging_Javassist()
	{
		MyBankAccount bankAccount = CGLibClassEnhancer.createEnhancedMyBankAccount("Andy");
		
		bankAccount.adjustAcountBalance(new BigDecimal("50.00"));
		bankAccount.adjustAcountBalance(new BigDecimal("12.00"));
		bankAccount.adjustAcountBalance(new BigDecimal("-13.00"));
		bankAccount.adjustAcountBalance(new BigDecimal("101.00"));
		bankAccount.adjustAcountBalance(new BigDecimal("30.45"));
		
		assertEquals(new BigDecimal("180.45"),bankAccount.getAccountBalance());
		
	}
}
