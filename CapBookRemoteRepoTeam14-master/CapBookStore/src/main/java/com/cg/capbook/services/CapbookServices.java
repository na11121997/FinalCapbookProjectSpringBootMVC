package com.cg.capbook.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cg.capbook.beans.Account;
import com.cg.capbook.exceptions.EmailAlreadyRegisteredException;
import com.cg.capbook.exceptions.InsufficientDataEneteredException;
import com.cg.capbook.exceptions.InvalidEmailIdException;

public interface CapbookServices {
	Account registration(String emailId, String firstName, String lastName, String password, String securityQuestion,int age, String gender, int mobileNo, String dOB)throws EmailAlreadyRegisteredException,InsufficientDataEneteredException;
	Account update(Account account);
	Account findAccountByEmailId(String emailId) throws InvalidEmailIdException;
	Account findAccountByEmailIdForSearch(String emailId);
	Account addProfilePic(String emailId,MultipartFile file) throws InvalidEmailIdException;
	List<String> getFriendBirthdays(String emailId)throws InvalidEmailIdException;;
}
