package employee;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;


public abstract class BaseTest<T> {
	
	protected MockMvc mockMvc;
	
	@Autowired
	protected WebApplicationContext wac;
	
	protected ObjectMapper mapper = new ObjectMapper();
	
	protected void performPost(T request, ResultMatcher matcher, String urlTemplate) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(request);
		mockMvc.perform(post(urlTemplate)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonInString))
				.andExpect(matcher);
	}
	
	protected void performDelete(T request, ResultMatcher matcher, String urlTemplate) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(request);
		mockMvc.perform(delete(urlTemplate)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonInString))
				.andExpect(matcher);
	}
	
	protected void performGet(T request, ResultMatcher matcher, String urlTemplate) throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(urlTemplate.concat("/{id}"), request))
				.andExpect(matcher);
	}
	
}
