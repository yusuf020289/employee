package com.btpn.si.employee.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btpn.si.core.controller.AbstractInquiryController;
import com.btpn.si.core.exception.ApplicationException;
import com.btpn.si.core.exception.ErrorHolder;
import com.btpn.si.core.model.InquiryResponse;
import com.btpn.si.employee.constants.EmployeeRestConstant;
import com.btpn.si.employee.model.Employee;
import com.btpn.si.employee.model.EmployeeInquiryRequest;
import com.btpn.si.employee.model.EmployeeModel;
import com.btpn.si.employee.service.IEmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(EmployeeRestConstant.EMP_INQ)
public class EmployeeInquiryController extends AbstractInquiryController<EmployeeInquiryRequest, EmployeeModel> {
	
	private static Logger LOGGER = LoggerFactory.getLogger(EmployeeInquiryController.class);
	
	@Autowired
	private IEmployeeService employeeService;

	@Override
	public EmployeeInquiryRequest validatePost(EmployeeInquiryRequest request) throws ApplicationException {
		List<ErrorHolder> errors = new ArrayList<>();
		if(!errors.isEmpty()) {
			throw new ApplicationException(errors);
		}
		return request;
	}
	
	@Override
	public ResponseEntity<InquiryResponse<EmployeeModel>> executePost(EmployeeInquiryRequest request) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		LOGGER.info("Request : {}", mapper.writeValueAsString(request));
		List<Employee> listKaryawan = employeeService.findAllByNamaOrDivisi(request.getNama(), request.getDivisi());
		List<EmployeeModel> result = new ArrayList<EmployeeModel>();
		for (Employee karyawan : listKaryawan) {
			EmployeeModel karyawanRequest = new EmployeeModel(
					karyawan.getId(), karyawan.getNama(),
					karyawan.getTanggal(), karyawan.getAlamat(),
					karyawan.getNoTelepon(), karyawan.getDivisi());
			result.add(karyawanRequest);
		}
		InquiryResponse<EmployeeModel> inquiryResponse = new InquiryResponse<EmployeeModel>(result);
		LOGGER.info("Response : {}", mapper.writeValueAsString(inquiryResponse));
		return ResponseEntity.accepted().body(inquiryResponse);
	}

}