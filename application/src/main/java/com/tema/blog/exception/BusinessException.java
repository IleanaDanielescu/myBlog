package com.tema.blog.exception;

public class BusinessException extends RuntimeException{
	
	private final ErrorCode errorCode;
	private final String[] parameters;
		
	public BusinessException() {
		this((String) null);
	}
	
	public BusinessException(String message, Throwable cause) {
		this(message, cause, ErrorCode.GENERAL);
	}
	public BusinessException(String message) {
		this(message, null, ErrorCode.GENERAL);
	}
	public BusinessException(Throwable cause) {
		this(cause.getMessage(), cause, ErrorCode.GENERAL);
	}
	
	public BusinessException(ErrorCode errorCode, String... parameters) {
		this(null, null, errorCode, parameters);
	}
	
	public BusinessException(String message, ErrorCode errorCode, String... parameters) {
		this(message, null, errorCode, parameters);
	}
	
	public BusinessException(Throwable cause, ErrorCode errorCode, String... parameters) {
		this(null, cause, errorCode, parameters);
	}
	
	public BusinessException(String exceptionMessage, Throwable cause, ErrorCode errorCode, String... parameters) {
		super(exceptionMessage, cause);
		this.errorCode = errorCode;
		this.parameters = parameters;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	public String[] getParameters() {
		return parameters;
	}
}
