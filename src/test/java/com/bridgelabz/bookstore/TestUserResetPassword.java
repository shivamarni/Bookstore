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
import com.bridgelabz.bookstore.dto.ForgetPassword;
import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.serviceimpl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest

@RunWith(MockitoJUnitRunner.Silent.class)
public class TestUserResetPassword {
	
	
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
	 * name-""
	 * phomeNumber-0Ls
	 * @throws Exception
	 */
	@Test
	public void userResetPasswordNullTest() throws Exception
	{
		ForgetPassword dto=new ForgetPassword();
		dto.setPassword("Nani908490@");
		String jsonRequest=om.writeValueAsString(dto);
		mockmvc.perform(
				MockMvcRequestBuilders.put("/user/resetpassword/{email}","tkoteswararao13@gmail.com")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest)
				)
				.andExpect(status().isOk());
	}
	
	
//	/**
//	 * email-"tkoteswararao13"
//	 * password-"Nani908490@"
//	 * name-"nani"
//	 * phomeNumber-9999911111L
//	 * @throws Exception
//	 */
//	@Test
//	public void userRegisterEmailTest() throws Exception
//	{
//		UserDto dto=new UserDto();
//		dto.setEmail("tkoteswararao13");
//		dto.setPassword("Nani908490@");
//		dto.setName("nani");
//		dto.setPhoneNumber(9999911111L);
//		String jsonRequest=om.writeValueAsString(dto);
//		
//		
//		mockmvc.perform(
//				MockMvcRequestBuilders.post("/user/register")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(jsonRequest)
//				)
//				.andExpect(status().isBadRequest());
//	}
//	
//	
//	/**
//	 * email-"tkoteswararao13@gmail.com"
//	 * password-"Nani908490@"
//	 * name-"nani123"
//	 * phomeNumber-9999911111L
//	 * @throws Exception
//	 */
//	@Test
//	public void userRegisterNameTest() throws Exception
//	{
//		UserDto dto=new UserDto();
//		dto.setEmail("tkoteswararao13@gmail.com");
//		dto.setPassword("Nani908490@");
//		dto.setName("nani123");
//		dto.setPhoneNumber(9999911111L);
//		String jsonRequest=om.writeValueAsString(dto);
//		
//		
//		mockmvc.perform(
//				MockMvcRequestBuilders.post("/user/register")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(jsonRequest)
//				)
//				.andExpect(status().isBadRequest());
//	}
//	/**
//	 * email-"tkoteswararao13@gmail.com"
//	 * password-"Nani908490@"
//	 * name-"nani"
//	 * phomeNumber-9999911111L
//	 * @throws Exception
//	 */
//	@Test
//	public void userRegisterValidTest() throws Exception
//	{
//		UserDto dto=new UserDto();
//		dto.setEmail("tkoteswararao13@gmail.com");
//		dto.setPassword("Nani908490@");
//		dto.setName("nani");
//		dto.setPhoneNumber(9999911111L);
//		String jsonRequest=om.writeValueAsString(dto);
//		
//		
//		mockmvc.perform(
//				MockMvcRequestBuilders.post("/user/register")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(jsonRequest)
//				)
//				.andExpect(status().isCreated());
//	}
}
	