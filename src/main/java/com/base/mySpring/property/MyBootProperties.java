package com.base.mySpring.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("myboot")
@Data
public class MyBootProperties {
	
	private String name;
	private String age;
	private String fullName;

	
}
