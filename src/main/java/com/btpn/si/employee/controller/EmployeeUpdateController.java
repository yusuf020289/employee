package com.btpn.si.employee.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.btpn.si.core.constants.SystemConstant;
import com.btpn.si.core.controller.AbstractPostController;
import com.btpn.si.core.exception.ApplicationException;
import com.btpn.si.core.exception.ErrorHolder;
import com.btpn.si.core.model.Response;
import com.btpn.si.employee.constants.EmployeeRestConstant;
import com.btpn.si.employee.model.Employee;
import com.btpn.si.employee.model.EmployeeUpdateRequest;
import com.btpn.si.employee.service.IEmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(EmployeeRestConstant.EMP_UPDATE)
public class EmployeeUpdateController extends AbstractPostController<EmployeeUpdateRequest> {
	
private static Logger LOGGER = LoggerFactory.getLogger(EmployeeInquiryController.class);
	
	@Autowired
	private IEmployeeService employeeService;

	@Override
	public EmployeeUpdateRequest validatePost(EmployeeUpdateRequest request) throws ApplicationException {
		List<ErrorHolder> errors = new ArrayList<>();
		if (request.getNama() == null || String.valueOf(request.getNama()).isEmpty()) {
			errors.add(buildErrorHolder("NAMA is mandatory"));//TODO: need error definition
		}
		if (request.getTanggal() == null) {
			errors.add(buildErrorHolder("TANGGAL is mandatory"));//TODO: need error definition
		}
		if (request.getId() == null) {
			errors.add(buildErrorHolder("ID is mandatory"));//TODO: need error definition
		}
		try {
			if (errors.isEmpty()) {
				if (employeeService.isExistEmployeeByNamaAndTanggal(request.getNama(), request.getTanggal(), request.getId())) {
					errors.add(buildErrorHolder("Employee Already Exists"));//TODO: need error definition
				}
			}
		} catch (ApplicationException e) {
			LOGGER.error("Failed when validation : {} ", e);
			errors.addAll(e.getErrors());
		} catch (Exception e) {
			LOGGER.error("Unknown exception : {} ", e);
			errors.add(buildErrorHolder(SystemConstant.ErrorCode.GENERAL_ERROR));
		}
		if(!errors.isEmpty()) {
			throw new ApplicationException(errors);
		}
		return request;
	}
	
	@Override
	public ResponseEntity<Response> executePost(EmployeeUpdateRequest request) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		LOGGER.info("Request : {}", mapper.writeValueAsString(request));
		employeeService.saveOrUpdate(mapRequestToEntity(request));
		Response resp = new Response();
		resp.setCode(SystemConstant.ResponseCode.SUCCESS);
		resp.setMessage(SystemConstant.ResponseCode.SUCCESS_DESCR);
		resp.setResult(new ObjectMapper().writeValueAsString(request));
		LOGGER.info("Response : {}", mapper.writeValueAsString(resp));
		return ResponseEntity.accepted().body(resp);
	}
	
	private Employee mapRequestToEntity(EmployeeUpdateRequest request) throws Exception {
		Employee employee = new Employee();
		employee.setId(request.getId());
		employee.setNama(request.getNama());
		employee.setTanggal(request.getTanggal());
		employee.setAlamat(request.getAlamat());
		employee.setNoTelepon(request.getNoTelepon());
		employee.setDivisi(request.getDivisi());
		return employee;
	}

	@Override
	public void constructAuditSuccess(RequestMethod method,
			EmployeeUpdateRequest request, ResponseEntity<Response> response)
			throws Exception {
	}

	@Override
	public void constructAuditError(RequestMethod method,
			EmployeeUpdateRequest request, List<ErrorHolder> errors) throws Exception {
	}

}
