package com.btpn.si.employee.model;

import java.util.Date;

import com.btpn.si.core.constants.SystemConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeModel {

	public EmployeeModel() {
		super();
	}

	public EmployeeModel(Long id, String nama, Date tanggal, String alamat,
			String noTelepon, String divisi) {
		super();
		this.id = id;
		this.nama = nama;
		this.tanggal = tanggal;
		this.alamat = alamat;
		this.noTelepon = noTelepon;
		this.divisi = divisi;
	}

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("nama")
	private String nama;
	
	@JsonProperty("tanggal")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SystemConstant.DATE_MASK, locale = "US", timezone = SystemConstant.DEFAULT_TIMEZONE)
	private Date tanggal;
	
	@JsonProperty("alamat")
	private String alamat;
	
	@JsonProperty("no_telepon")
	private String noTelepon;
	
	@JsonProperty("divisi")
	private String divisi;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getNoTelepon() {
		return noTelepon;
	}

	public void setNoTelepon(String noTelepon) {
		this.noTelepon = noTelepon;
	}

	public String getDivisi() {
		return divisi;
	}

	public void setDivisi(String divisi) {
		this.divisi = divisi;
	}

}
