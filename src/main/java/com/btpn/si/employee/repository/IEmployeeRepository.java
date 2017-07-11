package com.btpn.si.employee.repository;

import org.springframework.data.repository.CrudRepository;

import com.btpn.si.employee.model.Employee;

public interface IEmployeeRepository extends CrudRepository<Employee, Long> {

}
