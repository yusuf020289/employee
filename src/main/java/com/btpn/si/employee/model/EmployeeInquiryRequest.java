package com.btpn.si.employee.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeInquiryRequest {

	public EmployeeInquiryRequest() {
		super();
	}

	public EmployeeInquiryRequest(String nama, String divisi) {
		super();
		this.nama = nama;
		this.divisi = divisi;
	}
	
	public static EmployeeInquiryRequest newInstance(String nama, String divisi) {
		return new EmployeeInquiryRequest(nama, divisi);
	}

	@JsonProperty("nama")
	private String nama;
	
	@JsonProperty("divisi")
	private String divisi;

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getDivisi() {
		return divisi;
	}

	public void setDivisi(String divisi) {
		this.divisi = divisi;
	}

}
