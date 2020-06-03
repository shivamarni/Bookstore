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
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.serviceimpl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest

@RunWith(MockitoJUnitRunner.Silent.class)
public class TestUserForgotPassword {
	
	
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
	 * @throws Exception
	 */
	@Test
	public void userForgotNullTest() throws Exception
	{
		
		mockmvc.perform(
				MockMvcRequestBuilders.post("/user/forgotpassword")
				.contentType(MediaType.APPLICATION_JSON)
				.header("email","")
				)
				.andExpect(status().isBadRequest());
	}
	/**
	 * email-"tkoteswararao13@gmail.com"
	 * @throws Exception
	 */
	@Test
	public void userForgotValidTest() throws Exception
	{
		mockmvc.perform(
				MockMvcRequestBuilders.post("/user/forgotpassword")
				.contentType(MediaType.APPLICATION_JSON)
				.header("email","tkoteswararao13@gmail.com")
				)
				.andExpect(status().isOk());
	}
}
	