package spring.mai.imp;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SpringMailSender {
	 private JavaMailSenderImpl mailSender;
	 /**
	  *Ĭ�ϣ� "sngexchange.newchinalife.com"
	  */
	 private String host="sngexchange.newchinalife.com";
	 private String userName="sunlh_wb";
	 private String password="ncl@1234";
	 private String sentTo="zhongyinruaner@yahoo.com.cn";
	 
	 public JavaMailSenderImpl getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSentTo() {
		return sentTo;
	}

	public void setSentTo(String sentTo) {
		this.sentTo = sentTo;
	}

	public SpringMailSender() {

		    // ��ʼ��JavaMailSenderImpl����Ȼ�Ƽ���spring�����ļ������ã�������Ϊ�˼�

		    mailSender = new JavaMailSenderImpl();
		    mailSender.setDefaultEncoding("UTF-8");

		    // ���ò���

		    mailSender.setHost(this.host);
//"sunlh_wb"
		    mailSender.setUsername(this.userName);
//"ncl@1234"
		    mailSender.setPassword(this.password);
	 }
	 
	 public void attachedSend(String subject,String contentText,File file) throws MessagingException {

		    //ʹ��JavaMail��MimeMessage��֧�����Ӹ��ӵ��ʼ���ʽ������
			//props.put("mail.from", "sunlh_wb@newchinalife.com");
		    MimeMessage msg = mailSender.createMimeMessage();
		    msg.addHeader("X-Mailer", "Java Mailer");
		    //����MimeMessageHelper���󣬴���MimeMessage�ĸ�����

		    MimeMessageHelper helper = new MimeMessageHelper(msg, true);

		    //ʹ�ø�����MimeMessage�趨����

		    helper.setFrom(this.mailSender.getUsername()+"@newchinalife.com");
		   // helper.setFrom(this.mailSender.getUsername());
//"zhongyinruaner@yahoo.com.cn
		    helper.setTo(this.sentTo);

		    helper.setSubject(subject);

		    helper.setText(contentText);
		    helper.setSentDate(new Date());

		    //�����ļ���Դ����Ϊ����

		   // ClassPathResource file = new ClassPathResource("inputData.xml");
//		    File file =new File("D:/test/w_mengchong.jpg");
//		    FileSystemResource fs = new FileSystemResource(file);
		    

		    //���븽��

		    helper.addAttachment("attachment.xml", file);

		    //�����ʼ�
		  //  mailSender.setJavaMailProperties(props);
		    mailSender.getJavaMailProperties().setProperty("mail.smtp.auth", "true");
		    mailSender.send(msg);

		}
	 
	 public void richContentSend(String subject,String contentText) throws MessagingException {

		    MimeMessage msg = mailSender.createMimeMessage();
		    msg.addHeader("X-Mailer", "Java Mailer");
		 

		    MimeMessageHelper helper = new MimeMessageHelper(msg, true);

		 

		    helper.setFrom(this.mailSender.getUsername()+"@newchinalife.com");

		    helper.setTo(this.getSentTo());

		    helper.setSubject(subject);

		    //�ڶ�������true����ʾtext������Ϊhtml��Ȼ��ע��<img/>��ǩ��src='cid:file'��'cid'��contentId����д��'file'��һ����ǣ���Ҫ�ں���Ĵ����е���MimeMessageHelper��addInline����������ļ�

		    helper.setText(

		            "<body><b>"+this.getSentTo()+":</b><br>&nbsp;&nbsp;��ã�<br><p>&nbsp;&nbsp;"+contentText+"</p></body>",

		            true);

		 
helper.setSentDate(new Date());
//		    FileSystemResource file = new FileSystemResource(
//
//		            "C:\\Users\\Public\\Pictures\\Sample Pictures\\Chrysanthemum.jpg");
		 //   ClassPathResource file = new ClassPathResource("w_mengchong.jpg");

		   // helper.addInline("file", file);
		    mailSender.getJavaMailProperties().setProperty("mail.smtp.auth", "true");
		 

		    mailSender.send(msg);

		}
	 
}
