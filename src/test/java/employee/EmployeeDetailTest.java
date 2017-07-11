package employee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.btpn.si.core.constants.SystemConstant;
import com.btpn.si.core.model.Response;
import com.btpn.si.employee.constants.EmployeeRestConstant;
import com.btpn.si.employee.model.EmployeeModel;

@RunWith(SpringJUnit4ClassRunner.class)
@EmployeeTest
public class EmployeeDetailTest extends BaseTest<Long> {
	
	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void testSuccess() throws Exception {
		try {
			performGet(13L, new ResultMatcher() {
				@Override
				public void match(MvcResult result) throws Exception {
					Response response = mapper.readValue(result.getResponse().getContentAsString(), Response.class);
					Assert.assertEquals(SystemConstant.ResponseCode.SUCCESS, response.getCode());
					Assert.assertEquals(SystemConstant.ResponseCode.SUCCESS_DESCR, response.getMessage());
					if (response.getResult().isEmpty()) {
						throw new Exception("Employee not found");
					}
					EmployeeModel employeeModel = mapper.readValue(response.getResult(), EmployeeModel.class);
					if (employeeModel == null) {
						throw new Exception("Employee not found");
					}
				}
			}, EmployeeRestConstant.EMP_DETAIL);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}
