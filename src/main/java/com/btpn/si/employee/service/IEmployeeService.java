package com.btpn.si.employee.service;

import java.util.Date;
import java.util.List;

import com.btpn.si.employee.model.Employee;

public interface IEmployeeService {
	
	public List<Employee> findAll() throws Exception;
	
	public List<Employee> findAllByNamaOrDivisi(String nama, String divisi) throws Exception;
	
	public void saveOrUpdate(Employee employee) throws Exception;
	
	public Employee findById(Long id) throws Exception;
	
	public void delete(Employee employee) throws Exception;
	
	public void deleteById(Long id) throws Exception;
	
	public void delete(Long[] ids) throws Exception;
	
	public Boolean isExistEmployeeByNamaAndTanggal(String nama, Date tanggal, Long id) throws Exception;
	
	public void deleteByNamaAndTanggal(String nama, Date tanggal) throws Exception;
	
}
