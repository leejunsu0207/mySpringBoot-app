package com.base.mySpring.runner;

import lombok.extern.log4j.Log4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.base.mySpring.property.MyBootProperties;

import org.slf4j.Logger;

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
	
	@Autowired
	MyBootProperties myBootProperties;
	private Logger logger = LoggerFactory.getLogger(MyRunner.class);
	@Autowired
	private String hello;


	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		logger.debug("---------------------------");


		logger.info("Hello Bean : " + hello);

		logger.info("Properties name : " + name);
		logger.info("Properties age : " + age);
//		System.out.println("Properties fullName : " + fullName);
		logger.info("Properties fullName : " + environment.getProperty("myboot.fullName"));


		logger.info("VM arguments : " + args.containsOption("foo"));
		logger.info("Application arguments : " + args.containsOption("bar"));

		logger.debug("Property Class name: " + myBootProperties.getName());
		logger.debug("Property Class age: " +myBootProperties.getAge());
		logger.debug("Property Class fullName: " +myBootProperties.getFullName());
	}
	
	

}
