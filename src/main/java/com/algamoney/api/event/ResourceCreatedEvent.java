package com.algamoney.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

public class ResourceCreatedEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private final HttpServletResponse response;
	
	@Getter
	private final Long id;
	
	public ResourceCreatedEvent(Object source, HttpServletResponse response, Long id) {
		super(source);
		this.response = response;
		this.id = id;
	}


}
