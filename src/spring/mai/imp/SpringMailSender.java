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
	  *默认： "sngexchange.newchinalife.com"
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

		    // 初始化JavaMailSenderImpl，当然推荐在spring配置文件中配置，这里是为了简单

		    mailSender = new JavaMailSenderImpl();
		    mailSender.setDefaultEncoding("UTF-8");

		    // 设置参数

		    mailSender.setHost(this.host);
//"sunlh_wb"
		    mailSender.setUsername(this.userName);
//"ncl@1234"
		    mailSender.setPassword(this.password);
	 }
	 
	 public void attachedSend(String subject,String contentText,File file) throws MessagingException {

		    //使用JavaMail的MimeMessage，支付更加复杂的邮件格式和内容
			//props.put("mail.from", "sunlh_wb@newchinalife.com");
		    MimeMessage msg = mailSender.createMimeMessage();
		    msg.addHeader("X-Mailer", "Java Mailer");
		    //创建MimeMessageHelper对象，处理MimeMessage的辅助类

		    MimeMessageHelper helper = new MimeMessageHelper(msg, true);

		    //使用辅助类MimeMessage设定参数

		    helper.setFrom(this.mailSender.getUsername()+"@newchinalife.com");
		   // helper.setFrom(this.mailSender.getUsername());
//"zhongyinruaner@yahoo.com.cn
		    helper.setTo(this.sentTo);

		    helper.setSubject(subject);

		    helper.setText(contentText);
		    helper.setSentDate(new Date());

		    //加载文件资源，作为附件

		   // ClassPathResource file = new ClassPathResource("inputData.xml");
//		    File file =new File("D:/test/w_mengchong.jpg");
//		    FileSystemResource fs = new FileSystemResource(file);
		    

		    //加入附件

		    helper.addAttachment("attachment.xml", file);

		    //发送邮件
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

		    //第二个参数true，表示text的内容为html，然后注意<img/>标签，src='cid:file'，'cid'是contentId的缩写，'file'是一个标记，需要在后面的代码中调用MimeMessageHelper的addInline方法替代成文件

		    helper.setText(

		            "<body><b>"+this.getSentTo()+":</b><br>&nbsp;&nbsp;你好！<br><p>&nbsp;&nbsp;"+contentText+"</p></body>",

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
