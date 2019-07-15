package com.cg.capbook.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

//abcd
@Entity
public class Email {
	
	@Id
	@SequenceGenerator(name="emaId",initialValue=101,allocationSize=50000,sequenceName="emaIdSequence")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="emaId")
	private int emId;
	public Email() {
		super();
		// TODO Auto-generated constructor stub
	}


	private String fromEmail;
	private String toEmail;
	private String message;
	private String dateOfMail;
	private String subject;


	public String getFromEmail() {
		return fromEmail;
	}
	public int getEmId() {
		return emId;
	}
	public void setEmId(int emId) {
		this.emId = emId;
	}
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}


	@Override
	public String toString() {
		return "Email [emId=" + emId + ", fromEmail=" + fromEmail + ", toEmail=" + toEmail + ", message=" + message
				+ ", dateOfMail=" + dateOfMail + ", subject=" + subject + "]";
	}
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getDateOfMail() {
		return dateOfMail;
	}


	public void setDateOfMail(String dateOfMail) {
		this.dateOfMail = dateOfMail;
	}


;


	public Email(String fromEmail, String toEmail, String subject, String message, String dateOfMail) {
				this.fromEmail=fromEmail;
				this.toEmail=toEmail;
				this.subject=subject;
				this.message=message;
				this.dateOfMail=dateOfMail;
	}
	
}
