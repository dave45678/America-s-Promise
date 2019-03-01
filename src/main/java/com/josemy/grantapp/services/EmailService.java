package com.josemy.grantapp.services;

import com.josemy.grantapp.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {


  private TemplateEngine templateEngine;

  @Autowired
  Environment env;

  @Autowired
  public EmailService(TemplateEngine templateEngine) {
    this.templateEngine = templateEngine;
  }

  private Properties GetProperties(){
    Properties props = new Properties();
    props.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
    props.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
    props.put("mail.smtp.host", env.getProperty("mail.smtp.host"));
    props.put("mail.smtp.port", env.getProperty("mail.smtp.port"));

    return  props;
  }

  private Session GetSession(){
    // Email account credentials
    final String username = "testemail22cd@gmail.com";
    final String password = "MyPassword55!";

    // Create session with username and password
    Session session = Session.getInstance(GetProperties(),
            new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
              }
            });

    return  session;
  }

  public String BuildTemplateWithContent(String message) {
    Context context = new Context();
    context.setVariable("message", message);
    return templateEngine.process("mailtemplate", context);
  }

  public void SendSimpleEmail(String email, String appMesg){
    try {

      Message mimeMessage = new MimeMessage(GetSession());
      String content = BuildTemplateWithContent(appMesg);

      // The email address you're sending from
      mimeMessage.setFrom(new InternetAddress("testemail22cd@gmail.com"));

      // The email address(es) you're sending the email to
      mimeMessage.setRecipients(Message.RecipientType.TO,
              InternetAddress.parse(email));

      // Email subject
      mimeMessage.setSubject("Test Template");

      // Email content
      mimeMessage.setContent(content, "text/html; charset=utf-8");

      Transport.send(mimeMessage);

    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }
}
