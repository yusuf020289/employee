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
import com.btpn.si.employee.model.EmployeeDeleteRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@EmployeeTest
public class EmployeeDeleteTest extends BaseTest<EmployeeDeleteRequest> {
	
	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void testSuccess() throws Exception {
		try {
			performDelete(new EmployeeDeleteRequest(14L), new ResultMatcher() {
				@Override
				public void match(MvcResult result) throws Exception {
					Response response = mapper.readValue(result.getResponse().getContentAsString(), Response.class);
					Assert.assertEquals(SystemConstant.ResponseCode.SUCCESS, response.getCode());
					Assert.assertEquals(SystemConstant.ResponseCode.SUCCESS_DESCR, response.getMessage());
					if (response.getResult().isEmpty()) {
						throw new Exception("Employee not found");
					}
					EmployeeDeleteRequest empDeleteReq = mapper.readValue(response.getResult(), EmployeeDeleteRequest.class);
					if (empDeleteReq == null) {
						throw new Exception("Employee not found");
					} 
				}
			}, EmployeeRestConstant.EMP_DELETE);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}
