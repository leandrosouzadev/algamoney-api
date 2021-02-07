package com.algamoney.api.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.Getter;

@ControllerAdvice
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String userMessage = messageSource.getMessage("invalid.message", null, LocaleContextHolder.getLocale());
		String developMessage = ex.getCause().toString();
		
		return super.handleExceptionInternal(ex, new Error(userMessage, developMessage), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Getter
	public static class Error {
		
		private String userMessage;
		private String developMessage;
		
		public Error(String userMessage, String developMessage) {			
			this.userMessage = userMessage;
			this.developMessage = developMessage;
		}			
	}

}
