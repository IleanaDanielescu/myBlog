package com.tema.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SingleStringValue {

	private String value;

	public SingleStringValue() {
		
	}
	
	public SingleStringValue(String value) {
		this.value = value;
	}
	
	
}
