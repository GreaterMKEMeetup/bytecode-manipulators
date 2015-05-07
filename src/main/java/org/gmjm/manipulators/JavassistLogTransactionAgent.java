package org.gmjm.manipulators;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.Arrays;

import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.bytecode.AnnotationsAttribute;

public class JavassistLogTransactionAgent {
	public static void premain(String args, Instrumentation inst)
	{
		ClassPool cp = ClassPool.getDefault();
		
		inst.addTransformer(new ClassFileTransformer() {
			
			@Override
			public byte[] transform(ClassLoader loader, String className,
					Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
					byte[] classfileBuffer) throws IllegalClassFormatException {
				
				cp.insertClassPath(new ByteArrayClassPath(className, classfileBuffer));
				
				try {
					CtClass clazz = cp.get(className.replaceAll("/", "."));
					
					Arrays.stream(clazz.getMethods())
						.filter(ctMethod -> {
							AnnotationsAttribute aa = (AnnotationsAttribute) ctMethod.getMethodInfo().getAttribute(AnnotationsAttribute.visibleTag);
							boolean exists = aa != null && aa.getAnnotation("org.gmjm.manipulators.LogTransaction") != null;
							return exists;
						})
						.forEach(ctMethod -> {
							try {
								ctMethod.addLocalVariable("pVal", cp.get("java.math.BigDecimal"));
								ctMethod.insertBefore("pVal = accountBalance;");
								ctMethod.insertAfter("org.gmjm.manipulators.JavassistTransactionInterceptor.after(this,$1,pVal);");
							} catch (Exception e) {
								e.printStackTrace();
							}
						});
					
					return clazz.toBytecode();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return classfileBuffer;
			}
		});
	}
}
