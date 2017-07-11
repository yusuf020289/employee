package com.btpn.si.employee.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "karyawan")
public class Employee implements Serializable {

	private static final long serialVersionUID = -175374233063443961L;
	
	public static final String ID 			= "id";
	public static final String NAMA 		= "nama";
	public static final String TANGGAL 		= "tanggal";
	public static final String ALAMAT 		= "alamat";
	public static final String NO_TELEPON 	= "noTelepon";
	public static final String DIVISI 		= "divisi";

	@Id
	@SequenceGenerator(name="seq_karyawan", sequenceName="seq_karyawan", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_karyawan")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "nama")
	private String nama;
	
	@Column(name = "tanggal")
	private Date tanggal;
	
	@Column(name = "alamat")
	private String alamat;
	
	@Column(name = "no_telepon")
	private String noTelepon;
	
	@Column(name = "divisi")
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
