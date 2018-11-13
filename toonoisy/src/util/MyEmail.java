package util;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.sun.mail.util.MailSSLSocketFactory;
/**
 * 
 * @author 04161519 hxy
 *  
 */
public class MyEmail {
	private Session session;
	private Properties props;
	private MimeMessage mail;
	private String user;
	private String password;
	//private Transport transport;
	
	/**
	 * 
	 * @param user 用户账号
	 * @param password 用户密码，通常使用邮箱授权码
	 * @throws GeneralSecurityException
	 * @throws NoSuchProviderException
	 * 
	 * 默认的构造方法，拥有默认的配置
	 */
	public MyEmail(final String user, final String password) throws GeneralSecurityException, NoSuchProviderException {
		this.user = user;
		this.password = password;
		
		props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.qq.com");
		//设置协议
		props.setProperty("mail.transport.protocol", "smtp");
		//设置验证
		props.setProperty("mail.smtp.auth", "true");
		
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		
		props.setProperty("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.socketFactory", sf);
		
		session = Session.getInstance(props,   
                new Authenticator() {  

                    @Override  
                    protected PasswordAuthentication getPasswordAuthentication() {  
                        //匿名只能访问函数内容的final类型的变量，可以访问外部类的成员变量  
                        return new PasswordAuthentication(user, password);  
                    }  
              
                }  
        );  
		
		mail = new MimeMessage(session);
		//transport = session.getTransport();
	}
	
	/**
	 * 
	 * @param props 参数配置
	 * @param user 用户账号
	 * @param password 邮箱授权码
	 * @throws NoSuchProviderException
	 */
	public MyEmail(Properties props, final String user ,final String password) throws NoSuchProviderException {
		this.user = user;
		this.password = password;
		this.props = props;
		session = Session.getInstance(props,   
                new Authenticator() {  
                    @Override  
                    protected PasswordAuthentication getPasswordAuthentication() {  
                        //匿名只能访问函数内容的final类型的变量，可以访问外部类的成员变量  
                        return new PasswordAuthentication(user, password);  
                    }  
              
                }  
        );  
		mail = new MimeMessage(session);
		//transport = session.getTransport();
	}
	
	/**
	 * 
	 * @param from 发送人邮箱
	 * @throws AddressException
	 * @throws MessagingException
	 * 
	 * @设置邮件的单个发送人
	 */
	public void setFrom(String from) throws AddressException, MessagingException {
		this.mail.setFrom(new InternetAddress(from));
	}
	
	/**
	 * 设置邮件主题
	 * @throws MessagingException 
	 */
	public void setSubject(String subject) throws MessagingException {
		mail.setSubject(subject);
	}
	
	/**
	 * 
	 * @param to  设置接收方邮箱
	 * @throws AddressException
	 * @throws MessagingException
	 * 
	 * @将TO作为默认发送方式，设置接收方邮箱
	 */
	public void setTo(String to) throws AddressException, MessagingException {
		this.mail.setRecipient(RecipientType.TO, new InternetAddress(to));
	}
	
	/**
	 * 
	 * @param to 接收方邮箱数组，可指定多个接收方
	 * @throws AddressException
	 * @throws MessagingException
	 * 
	 * @将TO作为默认发送方式，设置多个接收方
	 */
	public void setTo(String[] to) throws AddressException, MessagingException {
		int length = to.length;
		InternetAddress[] address = new InternetAddress[length];
		for (int i=0; i< length; i++) {
			address[i] = new InternetAddress(to[i]);
		}
		this.mail.setRecipients(RecipientType.TO, address );
	}
    
	/**
	 * 
	 * @param text 邮件正文
	 * @throws MessagingException
	 * 
	 * @设置邮件正文
	 */
	public void setText(String text) throws MessagingException {
		this.mail.setText(text);
	}
	
	/**
	 * 
	 * @param text 邮件正文
	 * @param charset 邮件内容编码
	 * @throws MessagingException
	 * 
	 * @按指定编码设置邮件正文
	 */
	public void setText(String text, String charset) throws MessagingException {
		this.mail.setText(text, charset);
	}
	
	/**
	 * @发送邮件
	 * @throws MessagingException
	 */
	public void send() throws MessagingException {
		Transport.send(mail);
	}
	
	/**
	 * 
	 * @return Properties 返回配置文件
	 * @可用以修改默认的配置文件，修改完请调用saveProperties
	 */
	public Properties getProperties() {
		return props;
	}
    
	/**
	 * @修改完默认配置文件，需要调用此文件
	 */
	public void saveProperties() {
		session = Session.getInstance(props,   
                new Authenticator() {  

                    @Override  
                    protected PasswordAuthentication getPasswordAuthentication() {  
                        //匿名只能访问函数内容的final类型的变量，可以访问外部类的成员变量  
                        return new PasswordAuthentication(user, password);  
                    }  
              
                }  
        );  
		
		mail = new MimeMessage(session);
	}
    
}
