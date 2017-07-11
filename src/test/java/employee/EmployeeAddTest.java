package employee;

import java.util.ArrayList;
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
import com.btpn.si.core.model.Response;
import com.btpn.si.core.util.DateUtils;
import com.btpn.si.employee.constants.EmployeeRestConstant;
import com.btpn.si.employee.model.EmployeeAddRequest;
import com.btpn.si.employee.model.EmployeeModel;

@RunWith(SpringJUnit4ClassRunner.class)
@EmployeeTest
public class EmployeeAddTest extends BaseTest<EmployeeAddRequest> {
	
	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void testSuccess() throws Exception {
		try {
			List<EmployeeAddRequest> rList = new ArrayList<>();
			rList.add(EmployeeAddRequest.newInstance("Yusuf", DateUtils.stringToDate("02-02-1989", SystemConstant.DATE_MASK), "Pasar Minggu", "0812123123", "DEV"));
			rList.add(EmployeeAddRequest.newInstance("Surahman", DateUtils.stringToDate("21-01-1980", SystemConstant.DATE_MASK), "Minggu Pasar", "0812123123", "DEV"));
			for(EmployeeAddRequest r : rList) {
				performPost(r, new ResultMatcher() {
					@Override
					public void match(MvcResult result) throws Exception {
						Response response = mapper.readValue(result.getResponse().getContentAsString(), Response.class);
						Assert.assertEquals(SystemConstant.ResponseCode.SUCCESS, response.getCode());
						Assert.assertEquals(SystemConstant.ResponseCode.SUCCESS_DESCR, response.getMessage());
						EmployeeModel obj = mapper.readValue(response.getResult(), EmployeeModel.class);
						Assert.assertEquals(r.getNama(), obj.getNama());
						Assert.assertEquals(r.getTanggal(), obj.getTanggal());
						Assert.assertEquals(r.getAlamat(), obj.getAlamat());
						Assert.assertEquals(r.getNoTelepon(), obj.getNoTelepon());
						Assert.assertEquals(r.getDivisi(), obj.getDivisi());
					}
				}, EmployeeRestConstant.EMP_ADD);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}
