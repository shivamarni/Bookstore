//package com.bridgelabz.bookstore.utility;
//
//import javax.mail.MessagingException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//
//import com.bridgelabz.bookstore.configuration.EmailSender;
//import com.bridgelabz.bookstore.dto.EmailStatus;
//
//@Component
//public class EmailHtmlSender {
//
//    private EmailSender emailSender=new EmailSender();
//    
//    private SpringTemplateEngine templateEngine=new SpringTemplateEngine();
//
//    public EmailStatus send(String to, String subject, String templateName, Context context) throws MessagingException {
//        String body = templateEngine.process("email/template-1", context);
//        return emailSender.sendHtml(to, subject, body);
//    }
//}
//
