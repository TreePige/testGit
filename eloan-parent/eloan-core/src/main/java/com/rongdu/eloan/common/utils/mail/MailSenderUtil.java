package com.rongdu.eloan.common.utils.mail;

import java.util.Date;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
/**邮件发送类*/
public class MailSenderUtil {
	private MailSender mailSender;
	private SimpleMailMessage mailMessage;

	public SimpleMailMessage getMailMessage() {
		return mailMessage;
	}

	public void setMailMessage(SimpleMailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**邮件发送
	 * mailAddress 接收邮件地址；
	 * subject 主题
	 * contextText 邮件内容**/
	public int sendMail(String mailAddress,String subject,String contextText) {
		int result = 1;
		SimpleMailMessage message = new SimpleMailMessage(mailMessage);
		message.setTo(mailAddress);	
		message.setSubject(subject);
		// 设置email内容,
		message.setText(contextText);
		message.setSentDate(new Date());
		try {
			mailSender.send(message);
		} catch (MailException e) {
			System.out.println("O. "+mailAddress+"发送Email失败了....");
			e.printStackTrace();
			return 2;
		}
		return result;
	}
}
