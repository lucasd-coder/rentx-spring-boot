package com.lucas.rentx.services;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
import com.lucas.rentx.entities.UserToken;
import com.lucas.rentx.repositories.UserRepository;
import com.lucas.rentx.repositories.UserTokenRepository;
import com.lucas.rentx.services.exceptions.ObjectNotFoundException;

@Service
public class EtherealMailService {

	@Value("${default.sender}")
	private String sender;
	
	@Value("${url.forgot}")
	private String url;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserTokenRepository userTokenRepository;

	public void sendEmail(String email) {

		try {

			MimeMessage msg = prepareMimSendEmail(email);
			mailSender.send(msg);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private MimeMessage prepareMimSendEmail(String email) throws MessagingException {

		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		UUID token = UUID.randomUUID();
		
		Date expiresDate = addHoursToJavaUtilDate(new Date(), 3);
		
		UserToken userToken = new UserToken(null, token, expiresDate, null, user);
		
		userTokenRepository.save(userToken);
		
		String link = url + token;

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

		helper.setTo(user.getEmail());
		helper.setFrom(sender);
		helper.setSubject("Recuperação de senha");
		helper.setSentDate(new Date(System.currentTimeMillis()));
		helper.setText(htmlFromTemplateForgot(user, link), true);
		return mimeMessage;
	}

	private String htmlFromTemplateForgot(User user, String link) {
		Map<String, Object> variables = new HashMap<>();
		Context context = new Context();
		variables.put("user", user);
		variables.put("link", link);		
		context.setVariables(variables);
		return templateEngine.process("email/forgotpassword", context);
	}

	public Date addHoursToJavaUtilDate(Date date, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hours);
		return calendar.getTime();
	}

}
