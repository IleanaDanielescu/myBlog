package com.tema.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="SUBSCRIBERS")
@Getter @Setter
public class Subscriber {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String email;
	
	@Column
	private String phoneNumber;
	
	@Column
	private boolean sendEmail;
	
	@Column
	private boolean sendSMS;
}
