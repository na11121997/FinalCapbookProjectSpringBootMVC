package com.cg.capbook.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.capbook.beans.Account;
import com.cg.capbook.beans.Email;
import com.cg.capbook.daoservices.AccountDao;
import com.cg.capbook.daoservices.EmailDao;
import com.cg.capbook.daoservices.FriendsDao;
import com.cg.capbook.exceptions.InvalidEmailIdException;
import com.cg.capbook.exceptions.UserNotFriendException;
//abcd
@Component("emailServices")
public class EmailServicesImpl implements EmailServices{

	@Autowired
	private EmailDao emailDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private FriendsDao friendDao;


	@Override
	public List<Email> getAllEmailsOfUser(String emailId) {
		return emailDao.getAllEmailsOfUser(emailId);
	}

	@Override
	public List<Email> getAllSentEmailsOfUser(String emailId) {
		return emailDao.getAllSentEmailsOfUser(emailId);
	}

	@Override
	public Email saveEmail(String fromEmail, String toEmail, String subject, String message)
			throws InvalidEmailIdException,UserNotFriendException {
		accountDao.findById(toEmail).orElseThrow(()->new InvalidEmailIdException("User is not Registered!"));
		//friendDao.findById(toEmail).orElseThrow(()->new UserNotFriendException("Can't Send Mail! User is Not a Friend!")); 
	        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
	        String dateOfMail = LocalDateTime.now().format(format);  
		Email email=new Email(fromEmail, toEmail, subject, message, dateOfMail);
		return emailDao.save(email);
	}

	@Override
	public Email getEmailSent(String emailId, int Id) {
		return emailDao.getEmailSent(emailId, Id);
	}
	@Override
	public Email getEmailRecieved(String emailId, int Id) {
		return emailDao.getEmailRecieved(emailId, Id);
	}
}
