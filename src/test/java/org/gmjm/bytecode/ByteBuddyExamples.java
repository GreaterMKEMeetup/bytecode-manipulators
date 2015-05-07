package org.gmjm.bytecode;

import static org.junit.Assert.assertEquals;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.matcher.ElementMatchers;

import org.junit.Test;

public class ByteBuddyExamples {
	
	@Test
	public void basicClassGeneration() throws InstantiationException, IllegalAccessException
	{
		
		Class<?> dynamicType = new ByteBuddy()
		  .subclass(Object.class) 
		  .method(ElementMatchers.named("toString"))
		  .intercept(FixedValue.value("Hello World!"))
		  .make()
		  .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
		  .getLoaded();
		
		Object newObject = dynamicType.newInstance();
		
		assertEquals(newObject.toString(), "Hello World!");
	}
}
