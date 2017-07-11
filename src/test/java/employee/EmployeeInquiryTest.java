package employee;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.btpn.si.core.constants.SystemConstant;
import com.btpn.si.core.model.InquiryResponse;
import com.btpn.si.employee.constants.EmployeeRestConstant;
import com.btpn.si.employee.model.EmployeeInquiryRequest;
import com.btpn.si.employee.model.EmployeeModel;
import com.fasterxml.jackson.core.type.TypeReference;

@RunWith(SpringJUnit4ClassRunner.class)
@EmployeeTest
public class EmployeeInquiryTest extends BaseTest<EmployeeInquiryRequest> {
	
	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void testSuccess() throws Exception {
		try {
			EmployeeInquiryRequest r = new EmployeeInquiryRequest("Yusuf", "DEV");
			performPost(r, new ResultMatcher() {
				@Override
				public void match(MvcResult result) throws Exception {
					InquiryResponse<EmployeeModel> inqResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<InquiryResponse<EmployeeModel>>() {});
					Assert.assertEquals(SystemConstant.ResponseCode.SUCCESS, inqResponse.getCode());
					Assert.assertEquals(SystemConstant.ResponseCode.SUCCESS_DESCR, inqResponse.getMessage());
					List<EmployeeModel> obj = inqResponse.getResult();
					for (EmployeeModel employeeModel : obj) {
						Assert.assertEquals(r.getNama(), employeeModel.getNama());
						Assert.assertEquals(r.getDivisi(), employeeModel.getDivisi());
					}
				}
			}, EmployeeRestConstant.EMP_INQ);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}
