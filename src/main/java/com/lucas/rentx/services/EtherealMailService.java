package com.lucas.rentx.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.lucas.rentx.entities.User;
import com.lucas.rentx.repositories.UserRepository;
import com.lucas.rentx.services.exceptions.ObjectNotFoundException;

@Service
public class EtherealMailService {

	@Value("${default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private UserRepository userRepository;
	
		
	public void sendEmail(String email) {
		
		try {
			
			MimeMessage msg = prepareMimSendEmail(email);			
			mailSender.send(msg);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	protected MimeMessage prepareMimSendEmail(String email) throws MessagingException {

		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

		helper.setTo(user.getEmail());
		helper.setFrom(sender);
		helper.setSubject("Recuperação de senha");
		helper.setSentDate(new Date(System.currentTimeMillis()));
		helper.setText(htmlFromTemplateForgot(user), true);
		return  mimeMessage;
	}

	protected String htmlFromTemplateForgot(User user) {
		Context context = new Context();
		context.setVariable("user", user);
		return templateEngine.process("email/forgotpassword", context);
	}

}
