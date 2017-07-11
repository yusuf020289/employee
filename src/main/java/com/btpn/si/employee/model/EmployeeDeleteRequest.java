package com.btpn.si.employee.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeDeleteRequest {

	public EmployeeDeleteRequest() {
		super();
	}
	
	public EmployeeDeleteRequest(Long id) {
		super();
		this.id = id;
	}

	@JsonProperty("id")
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
