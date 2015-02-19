/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.mb;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author MdTasiddiqul
 */
@ManagedBean(name = "emailMB")
@Stateless
public class EmailMB {

    final String smptUserName = "masbha.ahmed01@gmail.com";
    final String smptUserPassword = "masbha123456";

    private String emailTo;
    private String emailToName;
    private String emailFrom;
    private String emailFromName;
    private String emailSubject;
    private String emailBody;

    public EmailMB() {
        this.emailFrom = "masbha.ahmed01@gmail.com";
        this.emailFromName = "Project Management Team";
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getEmailToName() {
        return emailToName;
    }

    public void setEmailToName(String emailToName) {
        this.emailToName = emailToName;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailFromName() {
        return emailFromName;
    }

    public void setEmailFromName(String emailFromName) {
        this.emailFromName = emailFromName;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public void sendEmail() throws UnsupportedEncodingException {

        try {

            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", true); // added this line
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.user", smptUserName);
            props.put("mail.smtp.password", smptUserPassword);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", true);

            Session session = Session.getInstance(props, null);
            MimeMessage message = new MimeMessage(session);

            System.out.println("Port: " + session.getProperty("mail.smtp.port"));

            message.setSubject(this.getEmailSubject());
            message.setFrom(new InternetAddress(this.getEmailFrom(), this.getEmailFromName()));
            //message.setFrom(new InternetAddress(this.getEmailFrom()));
            //message.setFrom(from);
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(this.getEmailTo()));

            // Create a multi-part to combine the parts
            Multipart multipart = new MimeMultipart("alternative");

            // Create your text message part
            BodyPart messageBodyPart = new MimeBodyPart();
            //messageBodyPart.setText("=========Dummy mail for testing==========");

            // Add the text part to the multipart
            //multipart.addBodyPart(messageBodyPart);
            // Create the html part
            messageBodyPart = new MimeBodyPart();
            String htmlMessage = this.getEmailBody();
            messageBodyPart.setContent(htmlMessage, "text/html");

            // Add html part to multi part
            multipart.addBodyPart(messageBodyPart);

            // Associate multi-part with message
            message.setContent(multipart);

            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", smptUserName, smptUserPassword);
            System.out.println("Transport: " + transport.toString());
            transport.sendMessage(message, message.getAllRecipients());

        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
