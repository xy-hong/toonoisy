package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.util.MailSSLSocketFactory;

public class PostTool 
{
	
	private String hostServicer;
	
	private String to;
	private String from;
	private String fromPsd;
	
	Properties properties;
	Session session;
	
	
	
	public PostTool() {
		properties = new Properties();
	}
	
	
	
	public void setTo(String toAddress) {
		// �ռ��˵�������
        this.to = toAddress;
	}
	
	public void setformImf(String formAddress,String fromPsd) {
		// �����˵�������
        this.from = formAddress;
		//����������
		this.fromPsd = fromPsd;
	}
	
	public void setEmailhost(String emailhost) {
		this.hostServicer = emailhost;
	}
	
	
	public void setProperties(String propertiesPath)  {
		
		try {
			properties.store(new FileOutputStream(propertiesPath), null);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		properties.setProperty("mail.smtp.host", this.hostServicer);
		properties.put("mail.smtp.auth", "true");
		MailSSLSocketFactory sf;
		try {
			sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			properties.put("mail.smtp.ssl.enable", "true");
			properties.put("mail.smtp.ssl.socketFactory", sf);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		
		session = Session.getDefaultInstance(properties,new Authenticator(){
        public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(from, fromPsd); //�������ʼ��û���������
            }
        });
		MailSSLSocketFactory sf1;
		try {
			sf1 = new MailSSLSocketFactory();
			sf1.setTrustAllHosts(true);
			properties.put("mail.smtp.ssl.enable", "true");
			properties.put("mail.smtp.ssl.socketFactory", sf1);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} 
        
	}
	
	
	public void readProperties(String propertiesPath) {
		try {
			properties.load(new FileInputStream(propertiesPath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session = Session.getDefaultInstance(properties,new Authenticator(){
	        public PasswordAuthentication getPasswordAuthentication()
	            {
	                return new PasswordAuthentication(from, fromPsd); //�������ʼ��û���������
	            }
	        });
		
		
	}
	
	
	public void setmessage(String head,String messageText) {
	
		 // ����Ĭ�ϵ� MimeMessage ����
        MimeMessage message = new MimeMessage(session);

        // Set From: ͷ��ͷ�ֶ�
        try {
			message.setFrom(new InternetAddress(from));// Set To: ͷ��ͷ�ֶ�
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	        // Set Subject: ͷ��ͷ�ֶ�
	        message.setSubject(head);

	        // ������Ϣ��
	        message.setText(messageText);

	        // ������Ϣ
	        Transport.send(message);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

        
	}
	
	
	
}
