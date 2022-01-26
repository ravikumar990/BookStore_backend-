package com.bridgelabz.bookstoreapp.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.bookstoreapp.utl.TokenUtil;

@Component
public class MailService {

	@Autowired
	TokenUtil tokenUtil;

	public static void send(String toEmail, String subject, String body) {
		// requires valid gmail id
		final String fromEmail = "bookstoremailapi99@gmail.com";
	
		// correct password for gmail id
		final String password = "Password@12345";
 
		Properties props = new Properties();
		// SMTP Host
		props.put("mail.smtp.host", "smtp.gmail.com");
		
		// TLS Port
		props.put("mail.smtp.port", "587");
		
		// enable authentication
		props.put("mail.smtp.auth", "true");
		
		// enable STARTTLS
		props.put("mail.smtp.starttls.enable", "true");
		
		// to check email sender credentials are valid or not
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);

			}
		};
		javax.mail.Session session = Session.getInstance(props, auth);
		try {
			//compose message
			MimeMessage msg = new MimeMessage(session);
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			msg.setFrom(new InternetAddress("no_reply@gmail.com", "NoReply"));
			msg.setReplyTo(InternetAddress.parse("bookstoremailapi99@gmail.com", false));
			msg.setSubject(subject, "UTF-8");
			msg.setText(body, "UTF-8");
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			Transport.send(msg);
			System.out.println("Email Sent Successfully.........");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getLink(String link) {
		return link;
	}
}
