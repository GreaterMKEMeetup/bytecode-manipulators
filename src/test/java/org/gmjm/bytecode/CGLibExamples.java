package org.gmjm.bytecode;

import static org.junit.Assert.*;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

import org.junit.Test;

public class CGLibExamples {
	
	public class SampleClass {
		public String test(String input)
		{
			return "Hello World";
		}
	}
	
	@Test
	public void testFixedValue() throws Exception {
	 Enhancer enhancer = new Enhancer();
	 enhancer.setSuperclass(SampleClass.class);
	 enhancer.setCallback(new FixedValue() {
	  @Override
	  public Object loadObject() throws Exception {
	   return "Hello cglib!";
	  }
	 });
	 SampleClass proxy = (SampleClass) enhancer.create();
	 assertEquals("Hello cglib!", proxy.test(null));
	}
	
	@Test
	public void testInvocationHandler() throws Exception {
		
	}
}
