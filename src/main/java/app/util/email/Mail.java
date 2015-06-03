package app.util.email;

import java.io.File;
import java.util.Arrays;

public class Mail {
	
	private String subject;
	private String body;
	private String from;
	private String to;
	private String[] cc;
	private File[] attaches;
	
	public Mail from(String from) {
		this.from = from;
		return this;
	}
	public Mail to(String to) {
		this.to = to;
		return this;
	}
	
	public Mail cc(String[] cc) {
		this.cc = cc;
		return this;
	}
	
	public Mail subject(String subject) {
		this.subject = subject;
		return this;
	}
	
	public Mail body(String body) {
		this.body = body;
		return this;
	}
	
	public Mail attaches(File[] attaches) {
		this.attaches = attaches;
		return this;
	}

	// getters
	public String getSubject() {
		return subject;
	}
	
	public String getBody() {
		return body;
	}
	
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	
	public String[] getCc() {
		return cc;
	}
	
	public File[] getAttaches() {
		return attaches;
	}
	
	@Override
	public String toString() {
		return "Mail [subject=" + subject + ", body=" + body + ", from=" + from
				+ ", to=" + to + ", cc=" + Arrays.toString(cc) + ", attaches="
				+ Arrays.toString(attaches) + "]";
	}
	
	
	
}
