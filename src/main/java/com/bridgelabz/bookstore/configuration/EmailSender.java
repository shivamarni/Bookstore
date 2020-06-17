//package com.bridgelabz.bookstore.configuration;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.MailException;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.mail.javamail.MimeMessagePreparator;
//import org.springframework.stereotype.Component;
//
//import com.bridgelabz.bookstore.dto.EmailStatus;
//
//import java.io.InputStream;
//import java.util.Properties;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//@Component
//public class EmailSender {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSender.class);
//
//    private JavaMailSenderImpl javaMailSender=new JavaMailSenderImpl();
//    public EmailStatus sendPlainText(String to, String subject, String text) throws MessagingException {
//        return sendM(to, subject, text, false);
//    }
//
//    public EmailStatus sendHtml(String to, String subject, String htmlBody) throws MessagingException {
//        return sendM(to, subject, htmlBody, true);
//    }
//
//    private EmailStatus sendM(String to, String subject, String text, Boolean isHtml) throws MessagingException {
//        	 javaMailSender.setHost("smtp.gmail.com");
//        	 javaMailSender.setPort(465);
//        	 javaMailSender.setUsername(String.valueOf("bookstore.bridgelabz@gmail.com"));
//        	 javaMailSender.setPassword(String.valueOf("Bookstore123"));
//             Properties mailProp = javaMailSender.getJavaMailProperties();
//             mailProp.put("mail.transport.protocol", "smtp");
//             mailProp.put("mail.smtp.auth", "true");
//             mailProp.put("mail.smtp.starttls.enable", "true");
//             mailProp.put("mail.smtp.starttls.required", "true");
//             mailProp.put("mail.debug", "true");
//             mailProp.put("mail.smtp.ssl.enable", "true");
//
//            MimeMessage mail = javaMailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
//            helper.setTo(to);
//
//            helper.setSubject(subject);
//
//            helper.setText(text, isHtml);
//
//            javaMailSender.send(mail);
//            return new EmailStatus(to, subject, text).success();
//    }
//}
