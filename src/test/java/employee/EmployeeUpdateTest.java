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
import com.btpn.si.core.util.DateUtils;
import com.btpn.si.employee.constants.EmployeeRestConstant;
import com.btpn.si.employee.model.EmployeeUpdateRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@EmployeeTest
public class EmployeeUpdateTest extends BaseTest<EmployeeUpdateRequest> {
	
	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void testSuccess() throws Exception {
		try {
			EmployeeUpdateRequest r = new EmployeeUpdateRequest(13L, "Yusuf Surahman", DateUtils.stringToDate("02-02-1989", SystemConstant.DATE_MASK), "Pasar Minggu", "0812123123", "DEV");
			performPost(r, new ResultMatcher() {
				@Override
				public void match(MvcResult result) throws Exception {
					Response response = mapper.readValue(result.getResponse().getContentAsString(), Response.class);
					Assert.assertEquals(SystemConstant.ResponseCode.SUCCESS, response.getCode());
					Assert.assertEquals(SystemConstant.ResponseCode.SUCCESS_DESCR, response.getMessage());
					EmployeeUpdateRequest obj = mapper.readValue(response.getResult(), EmployeeUpdateRequest.class);
					Assert.assertEquals(r.getId(), obj.getId());
					Assert.assertEquals(r.getNama(), obj.getNama());
					Assert.assertEquals(r.getTanggal(), obj.getTanggal());
					Assert.assertEquals(r.getAlamat(), obj.getAlamat());
					Assert.assertEquals(r.getNoTelepon(), obj.getNoTelepon());
					Assert.assertEquals(r.getDivisi(), obj.getDivisi());
				}
			}, EmployeeRestConstant.EMP_UPDATE);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}
