package com.btpn.si.employee.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btpn.si.employee.model.Employee;
import com.btpn.si.employee.repository.IEmployeeRepository;
import com.google.common.collect.Lists;

@Service
@Transactional
public class EmployeeService implements IEmployeeService {
	
	@Autowired
	private IEmployeeRepository employeeRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void saveOrUpdate(Employee employee) throws Exception {
		employeeRepository.save(employee);
	}
	
	@Override
	public void delete(Employee employee) throws Exception {
		employeeRepository.delete(employee);
	}
	
	@Override
	public void deleteById(Long id) throws Exception {
		employeeRepository.delete(id);
	}
	
	@Override
	public void delete(Long[] ids) throws Exception {
		if (ids.length > 0) {
			for (Long l : ids) {
				employeeRepository.delete(l);
			}
		}
	}

	@Override
	public List<Employee> findAll() throws Exception {
		return Lists.newArrayList(employeeRepository.findAll());
	}

	@Override
	public Employee findById(Long id) throws Exception {
		return employeeRepository.findOne(id);
	}

	@Override
	public List<Employee> findAllByNamaOrDivisi(String nama, String divisi)
			throws Exception {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
		Root<Employee> root = query.from(Employee.class);
		Predicate predicateNama = cb.like(root.get(Employee.NAMA), nama.concat("%"));
		Predicate predicateDivisi = cb.equal(root.get(Employee.DIVISI), divisi);
		query.where(cb.or(predicateNama, predicateDivisi));
		return em.createQuery(query.select(root)).getResultList();
	}

	@Override
	public Boolean isExistEmployeeByNamaAndTanggal(String nama, Date tanggal, Long id)
			throws Exception {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		countQuery.select(cb.count(countQuery.from(Employee.class)));
		Root<Employee> root = countQuery.from(Employee.class);
		Predicate predicateNama = cb.equal(root.get(Employee.NAMA), nama);
		Predicate predicateTanggal = cb.equal(root.get(Employee.TANGGAL), tanggal);
		em.createQuery(countQuery);
		if (id != null) {
			Predicate predicateId = cb.notEqual(root.get(Employee.ID), id);
			countQuery.where(cb.and(predicateNama, predicateTanggal, predicateId));
		} else {
			countQuery.where(cb.and(predicateNama, predicateTanggal));
		}
		Long rowCount = em.createQuery(countQuery).getSingleResult();
		return rowCount != null && rowCount > 0 ? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	public void deleteByNamaAndTanggal(String nama, Date tanggal)
			throws Exception {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
		Root<Employee> root = query.from(Employee.class);
		Predicate predicateNama = cb.equal(root.get(Employee.NAMA), nama);
		Predicate predicateTanggal = cb.equal(root.get(Employee.TANGGAL), tanggal);
		query.where(cb.and(predicateNama, predicateTanggal));
		Employee employee = null; 
		if(em.createQuery(query.select(root)).getResultList().size() >0){
			employee = em.createQuery(query.select(root)).getResultList().get(0);
		}else{
			employee = em.createQuery(query.select(root)).getSingleResult();
		}
		if (employee != null) {
			delete(employee);
		}
	}
}
