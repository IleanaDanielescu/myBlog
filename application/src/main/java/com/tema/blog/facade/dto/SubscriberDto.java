package com.tema.blog.facade.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SubscriberDto {

	@Email(message = "Email should be valid")
	private String email;
	
	@Pattern(message = "Invalid phone number", 
			regexp = "^(\\+4|)?(07[0-8]{1}[0-9]{1}|02[0-9]{2}|03[0-9]{2}){1}?(\\s|\\.|\\-)?([0-9]{3}(\\s|\\.|\\-|)){2}$")
	private String phoneNumber;
	
	private boolean sendEmail;
	
	private boolean sendSMS;
}
