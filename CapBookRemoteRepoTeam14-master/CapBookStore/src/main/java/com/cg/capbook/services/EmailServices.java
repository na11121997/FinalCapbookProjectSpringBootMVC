package com.cg.capbook.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.cg.capbook.beans.Email;
import com.cg.capbook.exceptions.InvalidEmailIdException;
import com.cg.capbook.exceptions.UserNotFriendException;
//abcd
public interface EmailServices {
	Email saveEmail(String fromEmail,String toEmail,String subject,String message)throws InvalidEmailIdException,UserNotFriendException;
	List<Email> getAllEmailsOfUser(String emailId);
	List<Email> getAllSentEmailsOfUser(String emailId);
	public Email getEmailRecieved(String emailId,int Id);
	public Email getEmailSent(String emailId,int Id);
	
}
