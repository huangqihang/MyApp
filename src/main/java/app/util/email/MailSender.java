package app.util.email;

import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.config.AppConfig;
import app.util.thread.MyThreadPoolFactory;

public class MailSender {
	
	private static final Logger logger = LoggerFactory.getLogger(MailSender.class);
	
	private static MailSender self = new MailSender();
	private MailSender() {}
	public static MailSender getInstance() {
		return self;
	}
	
	/**************************************************************
	 * 线程池执行任务
	 ***************************************************************/
	private static final ThreadPoolExecutor exec = MyThreadPoolFactory.newThreadPoolExecutor();
	//private static final ExecutorService exec = Executors.newFixedThreadPool(3);
	public void sendMail(Mail... mails) {
		for(Mail mail : mails) {
			exec.execute(new Task(mail));
		}
	}
	
	
	/**************************************************************
	 * 	从阻塞队列取待发送邮件，一个一个发送
	 ***************************************************************/
	
	private static final String HOST = AppConfig.getString("mail.host");
	private static final int PORT = AppConfig.getInteger("mail.port");
	private static final String USERNAME = AppConfig.getString("mail.username");
	private static final String PASSWORD = AppConfig.getString("mail.password");
	private static final String FROM = AppConfig.getString("mail.from");
	
	private class Task implements Runnable {
		private Mail mail;
		
		public Task(Mail mail) {
			this.mail = mail;
		}
		
		public void run() {
			Throwable t = null;
			try {
				send(mail);
			} catch (InterruptedException e) {
				t = e;
				Thread.currentThread().interrupt();
			} catch (Exception e) {
				t = e;
			}
			
			if(t != null) {
				logger.error("send mail fail", t);
				throw new RuntimeException(t);
			}
		}
		
	}
	
	private void send(Mail mail) throws Exception {
		logger.info("send mail to {} start", mail.getTo());
		Email email = createMail();
		email.addTo(mail.getTo());
		email.addCc(mail.getCc());
		email.setSubject(mail.getSubject());
		email.setMsg(mail.getBody());
		int i = 1/0;
		email.send();
		logger.info("send mail to {} success", mail.getTo());
	}
	
	private Email createMail() throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName(HOST);
		email.setSmtpPort(PORT);
		email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
		email.setSSLOnConnect(true);
		email.setFrom(FROM);
		return email;
	}
	
}
