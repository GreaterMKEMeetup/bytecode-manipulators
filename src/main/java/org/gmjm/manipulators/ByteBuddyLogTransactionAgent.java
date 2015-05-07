package org.gmjm.manipulators;

import java.lang.instrument.Instrumentation;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.matcher.ElementMatchers;

public class ByteBuddyLogTransactionAgent {
	public static void premain(String args, Instrumentation inst) {
	    new AgentBuilder.Default()
	      .rebase(ElementMatchers.any())
	      .transform( (builder , typeDescription) -> builder
	    		 .method(ElementMatchers.isAnnotatedWith(LogTransaction.class))
	    		 .intercept(MethodDelegation.to(LogTransactionIntereceptor.class).andThen(SuperMethodCall.INSTANCE)
	    				))
	      .installOn(inst);
	}
}
