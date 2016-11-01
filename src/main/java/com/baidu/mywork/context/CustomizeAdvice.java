package com.baidu.mywork.context;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.baidu.mywork.domain.Domain;


public class CustomizeAdvice {
	
	public Object aroundCache(ProceedingJoinPoint call) throws Throwable {
		System.out.println("CustomizeAdvice begin:--------------");       
        try {    
        	MethodSignature signature = (MethodSignature)call.getSignature();
        	boolean flag = signature.getReturnType().isAnnotationPresent(Domain.class);
    		System.out.println("method name:"+signature.getName());       
    		System.out.println(signature.getReturnType().toString()+": flag:"+flag);       
            return call.proceed();
        } finally {           
            System.out.println("CustomizeAdvice end:--------------");
        }
    }

}
