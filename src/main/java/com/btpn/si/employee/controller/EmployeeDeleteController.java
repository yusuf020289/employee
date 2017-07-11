package com.btpn.si.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.btpn.si.core.constants.SystemConstant;
import com.btpn.si.core.controller.AbstractDeleteController;
import com.btpn.si.core.exception.ApplicationException;
import com.btpn.si.core.exception.ErrorHolder;
import com.btpn.si.core.model.Response;
import com.btpn.si.employee.constants.EmployeeRestConstant;
import com.btpn.si.employee.model.EmployeeDeleteRequest;
import com.btpn.si.employee.service.IEmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(EmployeeRestConstant.EMP_DELETE)
public class EmployeeDeleteController extends AbstractDeleteController<EmployeeDeleteRequest> {
	
	@Autowired
	private IEmployeeService employeeService;
	
	@Override
	public EmployeeDeleteRequest validateDelete(EmployeeDeleteRequest request) throws Exception {
		if (request.getId() == null) {
			throw new ApplicationException(buildErrorHolder("ID is mandatory"));
		}
		return request;
	}

	@Override
	public ResponseEntity<Response> executeDelete(EmployeeDeleteRequest request) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		LOGGER.info("Request : {}", mapper.writeValueAsString(request));
		employeeService.deleteById(request.getId());
		Response resp = new Response();
		resp.setCode(SystemConstant.ResponseCode.SUCCESS);
		resp.setMessage(SystemConstant.ResponseCode.SUCCESS_DESCR);
		resp.setResult(new ObjectMapper().writeValueAsString(request));
		LOGGER.info("Response : {}", mapper.writeValueAsString(resp));
		return ResponseEntity.accepted().body(resp);
	}
	
	@Override
	public void constructAuditSucccess(RequestMethod method,
			ResponseEntity<Response> response) throws Exception {
	}

	@Override
	public void constructAuditError(RequestMethod method,
			EmployeeDeleteRequest request, List<ErrorHolder> errors) throws Exception {
	}

}
