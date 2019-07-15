package com.cg.capbook.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cg.capbook.beans.Account;
import com.cg.capbook.beans.Address;
import com.cg.capbook.beans.Friend;
import com.cg.capbook.beans.PrivateInfo;
import com.cg.capbook.daoservices.AccountDao;
import com.cg.capbook.exceptions.EmailAlreadyRegisteredException;
import com.cg.capbook.exceptions.InsufficientDataEneteredException;
import com.cg.capbook.exceptions.InvalidEmailIdException;
//abcd
@Component("capbookServices")
public class CapbookServicesImpl implements CapbookServices{
	@Autowired
	AccountDao accountDao;
	@Autowired
	FriendServices friendServices;
	
	PrivateInfo privateInfo;
	private static String UPLOADED_FOLDER = "C:\\Users\\ADM-IG-HWDLAB1D\\git\\CapbookLocalRepoTeam14\\CapbookStore\\src\\main\\resources\\static\\images\\";
	
	@Override
	public Account registration(String emailId, String firstName, String lastName, String password,
			String securityQuestion, int age, String gender, int mobileNo, String dOB)
			throws EmailAlreadyRegisteredException,InsufficientDataEneteredException {
		if(emailId==null || password==null || gender==null || firstName==null || lastName==null || mobileNo==0 || dOB==null || securityQuestion==null || age==0)
			throw new InsufficientDataEneteredException("Please fill the fields");

		Account account=accountDao.findById(emailId).orElse(null);
		if(account!=null)
			throw new EmailAlreadyRegisteredException("Email is already in use.");
		account=new Account(emailId,firstName,lastName,password,securityQuestion,age,gender,new PrivateInfo(mobileNo,dOB));
		String saltpassword="abc"+account.getPassword()+"def";
		account.setPassword(saltpassword);
		account.setProfileImage("http://ssl.gstatic.com/accounts/ui/avatar_2x.png");
		PrivateInfo privateInfo1=account.getPrivateInfo();
		Address address=privateInfo1.getAddress();
		address=new Address("Not Added", "Not Added", "Not Added", "Not Added");
		privateInfo1.setAddress(address);
		account.setPrivateInfo(privateInfo1);
		return accountDao.save(account);
	}
	public Account update(Account account) {
		return accountDao.save(account);
	}
	public Account findAccountByEmailId(String emailId) throws InvalidEmailIdException{
		return accountDao.findById(emailId).orElseThrow
				(()-> new InvalidEmailIdException("Account details not found : "+emailId));
	}
	
	
	@Override
	public Account addProfilePic(String emailId,MultipartFile file) throws InvalidEmailIdException  {
		Account account=findAccountByEmailId(emailId);
		try {
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			file.transferTo(path);
			account.setProfileImage("/images/" + file.getOriginalFilename());
			account=accountDao.save(account);
		} catch (IOException e) {
			e.printStackTrace();
		}
      return account;
	}
	@Override
	public List<String> getFriendBirthdays(String emailId) throws InvalidEmailIdException{
		
		List<String> birthdays=new ArrayList<String>();
		List<Friend> allfriends=friendServices.showAllFriends(emailId);
		

	String localDate=LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		for(Friend friend : allfriends) {
		Account account=findAccountByEmailId(friend.getFriendEmailId());
			if(account.getPrivateInfo().getDOB().substring(5,9).equalsIgnoreCase(localDate.substring(5, 9)))
			{
				birthdays.add(account.getFirstName()+" "+account.getLastName()+"\nEmail Id :"+account.getEmailId());
			}
		}
		return birthdays;
	}
	@Override
	public Account findAccountByEmailIdForSearch(String emailId) {
		return accountDao.findById(emailId).orElse(null);
	}
	
	

}
