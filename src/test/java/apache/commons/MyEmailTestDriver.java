package apache.commons;

import app.common.AppBase;
import app.util.email.Mail;
import app.util.email.MailSender;

public class MyEmailTestDriver extends AppBase {
	
	public static void main(String[] args) {
		new MyEmailTestDriver().testSimpleEmail();
	}
	
	public void testSimpleEmail() {
		MailSender sender = MailSender.getInstance();
		Mail mail = new Mail();
		mail.from("schy_hqh@163.com")
			.to("sailhuang@126.com")
			.cc(new String[]{"sailhuang@126.com"})
			.subject("sleep")
			.body("take a bath");
		
		sender.sendMail(mail);
		
	}
}
