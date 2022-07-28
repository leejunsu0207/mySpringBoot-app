package com.base.mySpring.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements ApplicationRunner{
	
	@Value("${myboot.name}")
	String name;
	
	@Value("${myboot.age}")
	int age;
	
//	@Value("${myboot.fullName}")
//	String fullName;
	
	// setter injection
	@Autowired
	Environment environment;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Properties name : " + name);
		System.out.println("Properties age : " + age);
//		System.out.println("Properties fullName : " + fullName);
		System.out.println("Properties fullName : " + environment.getProperty("myboot.fullName"));
		
		
		System.out.println("VM arguments : " + args.containsOption("foo"));
		System.out.println("Application arguments : " + args.containsOption("bar"));
	}
	
	

}
