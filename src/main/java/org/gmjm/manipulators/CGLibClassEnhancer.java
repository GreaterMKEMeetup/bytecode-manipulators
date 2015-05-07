package org.gmjm.manipulators;

import java.lang.reflect.Method;
import java.math.BigDecimal;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.gmjm.example.MyBankAccount;

public class CGLibClassEnhancer {
	public static MyBankAccount createEnhancedMyBankAccount(String accountName)
	{
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(MyBankAccount.class);
		
		enhancer.setCallback(new MethodInterceptor() {
			
			@Override
			public Object intercept(Object obj, Method method, Object[] args,
					MethodProxy proxy) throws Throwable {
				if(method.isAnnotationPresent(LogTransaction.class))
				{
					MyBankAccount myBankAccount = (MyBankAccount)obj;
					BigDecimal previousBalance = myBankAccount.getAccountBalance();
					BigDecimal adjustment = (BigDecimal) args[0];
					
					proxy.invokeSuper(obj, args);
					
					BigDecimal currentBalance = myBankAccount.getAccountBalance();
					
					System.out.println(String.format("Adusted account: {%s} by: {%s}. "
							+ "\n\tPrevious Balance: {%s} Current Balance: {%s}.",
							myBankAccount.getAccountIdentifier(),
							adjustment,
							previousBalance,
							myBankAccount.getAccountBalance()));
				}
				else {
					return proxy.invokeSuper(obj, args);
				}
				return null;
			}
		});
		
		return (MyBankAccount) enhancer.create(new Class[]{String.class}, new Object[]{accountName});
	}
}
