package com.bridgelabz.bookstore.serviceimpl;

import java.beans.Beans;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.bridgelabz.bookstore.dto.EmailStatus;
import com.bridgelabz.bookstore.entity.Address;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Cart;
import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.repository.QuantityRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.response.MailResponse;
//import com.bridgelabz.bookstore.utility.EmailHtmlSender;
import com.bridgelabz.bookstore.utility.JWTUtility;
import com.bridgelabz.bookstore.utility.JmsUtility;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
@Service
public class OrderServiceImpl {

	@Autowired
	private CartServiceImpl cartImpl;
	@Autowired
	private UserServiceImpl userImpl;
	@Autowired
	private AddressServiceImpl addressImpl;
	@Autowired
	private OrderRepository orderrepo;
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private CartRepository cartrepo;
	@Autowired
	private QuantityRepository quantrepo;
	
	private JavaMailSenderImpl javaMailSender=new JavaMailSenderImpl();
	
	@Autowired
	private Configuration config;
	
	@Transactional
	@Modifying
	public Order addOrder(String token, Long totalCartPrice, Long deliveryCharges, String type) throws BookStoreException, MessagingException, IOException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userImpl.getUserById(userId);
		Address address=addressImpl.getAddressForUser(token, type);
		Cart cart=user.getCart();
		List<Book> books=cart.getBooklist();
		Order order=new Order();
		order.setDeliveryCharges(deliveryCharges);
		order.setTotalCartPrice(totalCartPrice);
		order.setOrderedTime(LocalDateTime.now());
		for(Book book : books)
			{
				order.getOrderedBooks().add(book);
			}
//			orderrepo.save(order);
//			user.getOrderedBooks().add(order);
//			quantrepo.deleteCartBookList(cart.getCartId());
//			quantrepo.deleteQuantity(cart.getCartId());
//			userrepo.save(user);
		javaMailSender.setHost("smtp.gmail.com");
   	 javaMailSender.setPort(465);
   	 javaMailSender.setUsername(String.valueOf("bookstore.bridgelabz@gmail.com"));
   	 javaMailSender.setPassword(String.valueOf("Bookstore123"));
        Properties mailProp = javaMailSender.getJavaMailProperties();
        mailProp.put("mail.transport.protocol", "smtp");
        mailProp.put("mail.smtp.auth", "true");
        mailProp.put("mail.smtp.starttls.enable", "true");
        mailProp.put("mail.smtp.starttls.required", "true");
        mailProp.put("mail.debug", "true");
        mailProp.put("mail.smtp.ssl.enable", "true");
		MailResponse response = new MailResponse();
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			// set mediaType
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			// add attachment
//			helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
			EmailStatus request=new EmailStatus();
			request.setTo(user.getEmail());
			request.setName("bookstore");
			request.setSubject("order details");
			request.setFrom("bookstore.bridgelabz@gmail.coms");
			Map<String, Object> model = new HashMap<>();
			model.put("username", user.getName());
			model.put("orderdetails",order);
			model.put("cartPrice", totalCartPrice);
			model.put("books", books);
			model.put("delivaryCharges", deliveryCharges);
			double totalPrice=deliveryCharges+totalCartPrice;
			model.put("totalPrice", totalPrice);
			Template t = config.getTemplate("email-template.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

			helper.setTo(request.getTo());
			helper.setText(html, true);
			helper.setSubject(request.getSubject());
			helper.setFrom(request.getFrom());
			javaMailSender.send(message);

			response.setMessage("mail send to : " + request.getTo());
			response.setStatus(Boolean.TRUE);

		} catch (MessagingException | IOException | TemplateException e) {
			response.setMessage("Mail Sending failure : "+e.getMessage());
			response.setStatus(Boolean.FALSE);
		}
//		JmsUtility.sendEmail(user.getEmail(),"ordered details",
//				"user details-->"+address+"\n"+"order details--->"+
//				order.toString());
		
//		EmailHtmlSender emailHtmlSender=new EmailHtmlSender();
//		Context context = new Context();
//		context.setVariable("username", user.getName());
//		context.setVariable("orderdetails",order);
//		EmailStatus emailStatus = emailHtmlSender.send(user.getEmail(), "Title of email", "emaitemplate-1", context);
//
		return order;
	}
	
	

}
