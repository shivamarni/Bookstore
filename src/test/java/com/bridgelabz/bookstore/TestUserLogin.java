package com.bridgelabz.bookstore;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.bridgelabz.bookstore.controller.UserController;
import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.serviceimpl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest

@RunWith(MockitoJUnitRunner.Silent.class)
public class TestUserLogin {
	
	
	private MockMvc mockmvc;
	
	@Mock
	private UserServiceImpl userImpl;
	
	@InjectMocks
	private UserController userController;
	
	ObjectMapper om=new ObjectMapper();
	
	@Before
	public void setup()
	{
		mockmvc=MockMvcBuilders.standaloneSetup(this.userController).build();
		 MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * email-""
	 * password-""
	 * @throws Exception
	 */
	@Test
	public void userLoginNullTest() throws Exception
	{
		LoginDto dto=new LoginDto();
		dto.setEmail("");
		dto.setPassword("");
		String jsonRequest=om.writeValueAsString(dto);
		
		
		mockmvc.perform(
				MockMvcRequestBuilders.post("/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest)
				)
				.andExpect(status().isBadRequest());
	}
	
	
	/**
	 * email-"tkoteswararao"
	 * password-"Nani908490@"
	 * @throws Exception
	 */
	@Test
	public void userLoginInvalidEmailTest() throws Exception
	{
		LoginDto dto=new LoginDto();
		dto.setEmail("tkoteswararao");
		dto.setPassword("Nani908490@");
		String jsonRequest=om.writeValueAsString(dto);
		
		
		mockmvc.perform(
				MockMvcRequestBuilders.post("/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest)
				)
				.andExpect(status().isBadRequest());
	}
	
	
	/**
	 * email-"tkoteswararao@gmail.com"
	 * password-"Nani9"
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void userLoginInvalidPasswordTest() throws Exception
	{
		LoginDto dto=new LoginDto();
		dto.setEmail("tkoteswararao@gmail.com");
		dto.setPassword("Nani9");
		String jsonRequest=om.writeValueAsString(dto);
		
		
		mockmvc.perform(
				MockMvcRequestBuilders.post("/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest)
				)
				.andExpect(status().isBadRequest());
	}
	
	/**
	 * email-"tkoteswararao"
	 * password-"Nani90"
	 * @throws Exception
	 */
	@Test
	public void userLoginInvalidDetailsTest() throws Exception
	{
		LoginDto dto=new LoginDto();
		dto.setEmail("tkoteswararao");
		dto.setPassword("Nani90");
		String jsonRequest=om.writeValueAsString(dto);
		
		
		mockmvc.perform(
				MockMvcRequestBuilders.post("/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest)
				)
				.andExpect(status().isBadRequest());
	}
	
	
	/**
	 * email-"tkoteswararao@gmail.com"
	 * password-"Nani908490@"
	 * @throws Exception
	 */
	@Test
	public void userLoginValidTest() throws Exception
	{
		LoginDto dto=new LoginDto();
		dto.setEmail("tkoteswararao@gmail.com");
		dto.setPassword("Nani908490@");
		String jsonRequest=om.writeValueAsString(dto);
		
		
		mockmvc.perform(
				MockMvcRequestBuilders.post("/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest)
				)
				.andExpect(status().isOk());
	}
	
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	private MockMvc mockmvc;
//	
//	@Autowired
//	private WebApplicationContext context;
//	
//	ObjectMapper om=new ObjectMapper();
//	
//	@Before
//	public void setup()
//	{
//		mockmvc=MockMvcBuilders.webAppContextSetup(context).build();
//	}
//	
//	@Test
//	public void testRegisterUser() throws Exception
//	{
//		
//		
//		
//		
//		
		
//		UserDto dto=new UserDto();
//		dto.setEmail("kevinnani77@gmail.com");
//		dto.setPassword("Nani908490@");
//		dto.setName("koti");
//		dto.setPhoneNumber(9515325731L);
//		String jsonRequest=om.writeValueAsString(dto);
//		
//		MvcResult result=mockmvc.perform(post("/user/register").content(jsonRequest).content(MediaType.APPLICATION_JSON_VALUE))
//				.andExpect(status().isOk()).andReturn();
//		
//		String resultContent=result.getResponse().getContentAsString();
//		Response response=om.readValue(resultContent, Response.class);
//		System.out.println(response+" "+resultContent);
		//Assert.assertTrue(response.getStatusCode()==status().isOk());
	//}
//}