package no.twct.recipeheaven.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaxMail implements Mail {

	private final String SMTP_HOST_PROPERTY = "mail.smtp.host";

	private final String SMTP_PORT_PROPERTY = "mail.smtp.port";

	private Properties properties = new Properties();

	private String reciever;

	private String sender;

	private String subject;

	private String message;

	public JavaxMail(String reciever, String sender, String subject, String message) {
		this.reciever = reciever;
		this.sender = sender;
		this.subject = subject;
		this.message = message;

		this.setupDefaults();
	}

	private void setupDefaults() {
		this.setHost("127.0.0.1");
		this.setHost("25");
	}

	public void setHost(String host) {
		this.properties.put(SMTP_HOST_PROPERTY, host);
	}

	public void setPort(String port) {
		this.properties.put(SMTP_PORT_PROPERTY, port);
	}

	public void send() {
		Session session = Session.getInstance(this.properties);

		try {
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(sender));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.reciever));

			message.setSubject(this.subject);

			message.setContent(this.message, "text/html");

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
