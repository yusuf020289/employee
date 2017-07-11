package com.btpn.si.employee.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btpn.si.core.constants.SystemConstant;
import com.btpn.si.core.controller.AbstractGetController;
import com.btpn.si.core.exception.ApplicationException;
import com.btpn.si.core.exception.ErrorHolder;
import com.btpn.si.core.model.Response;
import com.btpn.si.employee.constants.EmployeeRestConstant;
import com.btpn.si.employee.model.Employee;
import com.btpn.si.employee.model.EmployeeModel;
import com.btpn.si.employee.service.IEmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(EmployeeRestConstant.EMP_DETAIL)
public class EmployeeDetailController extends AbstractGetController<Long> {
	
	private static Logger LOGGER = LoggerFactory.getLogger(EmployeeDetailController.class);
	
	@Autowired
	private IEmployeeService employeeService;

	@Override
	public Long validateGet(Long id) throws ApplicationException {
		List<ErrorHolder> errors = new ArrayList<>();
		if (id == null) {
			errors.add(buildErrorHolder("ID is mandatory"));
		}
		if(!errors.isEmpty()) {
			throw new ApplicationException(errors);
		}
		return id;
	}
	
	@Override
	public ResponseEntity<Response> executeGet(Long id) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		LOGGER.info("Request : {}", mapper.writeValueAsString(id));
		Employee karyawan = employeeService.findById(id);
		EmployeeModel result = new EmployeeModel(
				karyawan.getId(), karyawan.getNama(),
				karyawan.getTanggal(), karyawan.getAlamat(),
				karyawan.getNoTelepon(), karyawan.getDivisi());
		Response response = new Response();
		response.setCode(SystemConstant.ResponseCode.SUCCESS);
		response.setMessage(SystemConstant.ResponseCode.SUCCESS_DESCR);
		response.setResult(new ObjectMapper().writeValueAsString(result));
		LOGGER.info("Response : {}", mapper.writeValueAsString(response));
		return ResponseEntity.accepted().body(response);
	}

}