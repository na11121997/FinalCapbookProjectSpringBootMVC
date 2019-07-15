package com.cg.capbook.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cg.capbook.beans.Account;
import com.cg.capbook.beans.Address;
import com.cg.capbook.beans.PrivateInfo;
import com.cg.capbook.beans.Relationship;
import com.cg.capbook.beans.Email;
import com.cg.capbook.beans.Friend;
import com.cg.capbook.beans.Post;
import com.cg.capbook.exceptions.EmailAlreadyRegisteredException;
import com.cg.capbook.exceptions.InsufficientDataEneteredException;
import com.cg.capbook.exceptions.InvalidEmailIdException;
import com.cg.capbook.exceptions.UserNotFriendException;
import com.cg.capbook.services.CapbookServices;
import com.cg.capbook.services.DislikesServices;
import com.cg.capbook.services.EmailServices;
import com.cg.capbook.services.FriendServices;
import com.cg.capbook.services.LikesServices;
import com.cg.capbook.services.PostServices;

@Controller
@SessionAttributes({"account","posts","friendaccount"})
public class CapbookServicesController {
	@Autowired
	CapbookServices capbookServices;
	@Autowired
	EmailServices emailServices;
	@Autowired
	PostServices postServices;
	@Autowired
	FriendServices friendServices;
	@Autowired
	LikesServices likeServices;
	@Autowired
	DislikesServices dislikeServices;
	PrivateInfo privateInfo;
	@RequestMapping("/register")
	public ModelAndView registerAccountwithout(@RequestParam String emailId,@RequestParam String password,@RequestParam String firstName,@RequestParam String lastName,@RequestParam String securityQuestion,@RequestParam int mobileNo,@RequestParam String DOB,@RequestParam int age,@RequestParam String gender) throws EmailAlreadyRegisteredException,InvalidEmailIdException,InsufficientDataEneteredException{
		Account account=capbookServices.registration(emailId,firstName,lastName,password,securityQuestion,age,gender,mobileNo,DOB);
		return new ModelAndView("registrationSuccess","account",account);	
	}
	@RequestMapping("/login")
	public ModelAndView loginAccount(@RequestParam String emailId,@RequestParam String password)throws InvalidEmailIdException {
		Account account=capbookServices.findAccountByEmailId(emailId);
		String decryptedpassword=account.getPassword().substring(3, (account.getPassword().length()-3));
		if(decryptedpassword.equalsIgnoreCase(password))
			return new ModelAndView("userProfilePage","account",account);
		else
			return new ModelAndView("errorPage","errorMessage","Invalid Username or Password ");
		
	}
	@RequestMapping("/forgotPassword1")
	public ModelAndView forgotPassword(@RequestParam String emailId,@RequestParam String question,@RequestParam String password)throws Exception {
		Account account=capbookServices.findAccountByEmailId(emailId);
		if(account.getSecurityQuestion().equalsIgnoreCase(question))
		{
			String saltpassword="abc"+password+"def";
			account.setPassword(saltpassword);
			account=capbookServices.update(account);
			return new ModelAndView("forgotPasswordSuccessPage","account",account);
			
		}
		else
			return new ModelAndView("errorPage","error",account);
		
	}
	@RequestMapping("/changePassword1")
	public ModelAndView changePassword(@RequestParam String emailId,@RequestParam String cpassword,@RequestParam String password)throws Exception {
		Account account=capbookServices.findAccountByEmailId(emailId);
		if(password.equalsIgnoreCase(cpassword))
		{
			String saltpassword="abc"+password+"def";
			account.setPassword(saltpassword);
			account=capbookServices.update(account);
			return new ModelAndView("forgotPasswordSuccessPage","account",account);
			
		}
		else
			return new ModelAndView("errorPage","error",account);
		
	}

	@RequestMapping("/editProfile") public ModelAndView editProfile(@SessionAttribute("account") Account account) throws Exception {
		return new ModelAndView("editProfilePage","account",account);
	} 
	@RequestMapping("/logout") public ModelAndView logout(SessionStatus status) throws Exception {
		status.setComplete();
		return new ModelAndView("indexPage","","");
	}
	@RequestMapping("/updateInfoFirstName") public ModelAndView editupdateInfoFirstName(@SessionAttribute("account") Account account,@RequestParam String firstName) throws Exception {
		if(firstName.equalsIgnoreCase(""))
			return new ModelAndView("editProfilePage","account",account);
		else {
			account.setFirstName(firstName);
			account=capbookServices.update(account);
			return new ModelAndView("editProfilePage","account",account);
		}
	} 
	@RequestMapping("/updateInfoLastName") public ModelAndView editupdateInfoLastName(@SessionAttribute("account") Account account,@RequestParam String lastName) throws Exception {
		if(lastName.equalsIgnoreCase(""))
			return new ModelAndView("editProfilePage","account",account);
		else {
			account.setLastName(lastName);
			account=capbookServices.update(account);
			return new ModelAndView("editProfilePage","account",account);
		}
	} 
	
	@RequestMapping("/updateInfoMobileNo") public ModelAndView editupdateInfoMobileNo(@SessionAttribute("account") Account account,@RequestParam int mobileNo) throws Exception {
		if(mobileNo==0)
			return new ModelAndView("editProfilePage","account",account);
		else {
			PrivateInfo privateInfo1=account.getPrivateInfo();
			privateInfo1.setMobileNo(mobileNo);
			account.setPrivateInfo(privateInfo1);
			account=capbookServices.update(account);
			return new ModelAndView("editProfilePage","account",account);
		}
	} 
	@RequestMapping("/updateInfoDOB") public ModelAndView editupdateInfoDOB(@SessionAttribute("account") Account account,@RequestParam String DOB) throws Exception {
		if(DOB.equalsIgnoreCase(""))
			return new ModelAndView("editProfilePage","account",account);
		else {
			PrivateInfo privateInfo1=account.getPrivateInfo();
			privateInfo1.setDOB(DOB);
			account.setPrivateInfo(privateInfo1);
			account=capbookServices.update(account);
			return new ModelAndView("editProfilePage","account",account);
		}
	} 
	@RequestMapping("/updateInfoCity") public ModelAndView editupdateInfoCity(@SessionAttribute("account") Account account,@RequestParam String city) throws Exception {
		if(city.equalsIgnoreCase(""))
			return new ModelAndView("editProfilePage","account",account);
		else {
			PrivateInfo privateInfo1=account.getPrivateInfo();
			Address address=privateInfo1.getAddress();
			address.setCity(city);
			privateInfo1.setAddress(address);
			account.setPrivateInfo(privateInfo1);
			account=capbookServices.update(account);
			return new ModelAndView("editProfilePage","account",account);
		}
	} 
	@RequestMapping("/updateInfoState") public ModelAndView editupdateInfoState(@SessionAttribute("account") Account account,@RequestParam String state) throws Exception {
		if(state.equalsIgnoreCase(""))
			return new ModelAndView("editProfilePage","account",account);
		else {
			PrivateInfo privateInfo1=account.getPrivateInfo();
			Address address=privateInfo1.getAddress();
			address.setState(state);
			privateInfo1.setAddress(address);
			account.setPrivateInfo(privateInfo1);
			account=capbookServices.update(account);
			return new ModelAndView("editProfilePage","account",account);
		}
	} 
	@RequestMapping("/updateInfoCountry") public ModelAndView editupdateInfoCountry(@SessionAttribute("account") Account account,@RequestParam String country) throws Exception {
		if(country.equalsIgnoreCase(""))
			return new ModelAndView("editProfilePage","account",account);
		else {
			PrivateInfo privateInfo1=account.getPrivateInfo();
			Address address=privateInfo1.getAddress();
			address.setCountry(country);
			privateInfo1.setAddress(address);
			account.setPrivateInfo(privateInfo1);
			account=capbookServices.update(account);
			return new ModelAndView("editProfilePage","account",account);
		}
	} 
	@RequestMapping("/updateInfoZipCode") public ModelAndView editupdateInfoZipCode(@SessionAttribute("account") Account account,@RequestParam String zipCode) throws Exception {
		if(zipCode.equalsIgnoreCase(""))
			return new ModelAndView("editProfilePage","account",account);
		else {
			PrivateInfo privateInfo1=account.getPrivateInfo();
			Address address=privateInfo1.getAddress();
			address.setZipCode(zipCode);
			privateInfo1.setAddress(address);
			account.setPrivateInfo(privateInfo1);
			account=capbookServices.update(account);
			return new ModelAndView("editProfilePage","account",account);
		}
	} 
	@RequestMapping("/savefile") public ModelAndView editPic( @RequestParam MultipartFile file ,@SessionAttribute("account") Account account) throws InvalidEmailIdException {
		account=capbookServices.addProfilePic(account.getEmailId(), file);
		return new ModelAndView("editProfilePage","account",account);
	}
	@RequestMapping("/userProfileSession") public ModelAndView userProfileSession(@SessionAttribute("account") Account account) throws Exception {
		return new ModelAndView("userProfilePage","posts",account);
	}
	
	@RequestMapping("/myStatus") public ModelAndView editmMStatus(@SessionAttribute("account") Account account) throws Exception {
		return new ModelAndView("myStatusPage","account",account);
	} 
	@RequestMapping("/updateStatus") public ModelAndView updateStatus(@SessionAttribute("account") Account account,@RequestParam String myStatus) throws Exception {
		if(myStatus.equalsIgnoreCase(""))
			return new ModelAndView("myStatusPage","account",account);
		else {
			account.setMyStatus(myStatus);
			account=capbookServices.update(account);
			return new ModelAndView("myStatusPage","account",account);
		}
	} 
	@RequestMapping("/userProfile1") public ModelAndView backToHomePage(@SessionAttribute("account") Account account) throws Exception {
			return new ModelAndView("userProfilePage","account",account);
		}

	@RequestMapping("/checkMails")public ModelAndView checkEmail(@SessionAttribute("account") Account account) {	
		List<Email> emails=emailServices.getAllEmailsOfUser(account.getEmailId());
		return new ModelAndView("emailPage", "emails", emails);
	}

	@RequestMapping("/sendAnEmail")public ModelAndView sendAnEmail(@SessionAttribute("account") Account account) {
		return new ModelAndView("emailSendPage", "account", account);
	}
	@RequestMapping("/sendEmailURI")
	public ModelAndView sendEmail(@SessionAttribute("account") Account account, @RequestParam String toEmail, @RequestParam String subject, @RequestParam String message) throws InvalidEmailIdException,UserNotFriendException{
		emailServices.saveEmail(account.getEmailId(), toEmail, subject, message);
		return new ModelAndView("emailPage", "account",account);
	}
	@RequestMapping("/getAllRecievedEmails")
	public ModelAndView recievedEmail(@SessionAttribute("account") Account account) throws InvalidEmailIdException{
		List<Email> emails=emailServices.getAllEmailsOfUser(account.getEmailId());	
		return new ModelAndView("emailRecievedPage", "emails",emails);
	}
	@RequestMapping("/sentMails")
	public ModelAndView sentMails(@SessionAttribute("account") Account account) throws InvalidEmailIdException{
		List<Email> emails=emailServices.getAllSentEmailsOfUser(account.getEmailId());	
		return new ModelAndView("emailSentPage", "emails",emails);
	}
	@RequestMapping("/viewAMailRecieved")
	public ModelAndView viewAMailRecieved(@SessionAttribute("account") Account account,@RequestParam int mailId) {	
		Email emails=emailServices.getEmailRecieved(account.getEmailId(), mailId);
		System.out.println(emails);
		return new ModelAndView("CheckAnEmail", "emails",emails);
	}
	@RequestMapping("/viewAMailSent")
	public ModelAndView viewAMailSent(@SessionAttribute("account") Account account,@RequestParam int mailId) {	
		Email emails=emailServices.getEmailSent(account.getEmailId(), mailId);
		System.out.println(emails);
		return new ModelAndView("CheckAnEmail", "emails",emails);
	}
	
	@RequestMapping("/updateLikes")
	public ModelAndView updateLikes(@RequestParam int postId,@RequestParam String likedBy,@SessionAttribute("account") Account account,@SessionAttribute("posts") List<Post> posts) throws InvalidEmailIdException {
		likeServices.updateLikes(postId, likedBy);
		likeServices.getLikesCount(postId);
		posts=postServices.ShowAllPosts(account.getEmailId());
        return new ModelAndView("myWallPage","posts",posts);
	}
	@RequestMapping("/updateDislikes")
	public ModelAndView updateDisLikes(@RequestParam int postId,@RequestParam String dislikedBy,@SessionAttribute("account") Account account,@SessionAttribute("posts") List<Post> posts) throws InvalidEmailIdException {
		dislikeServices.updateDislikes(postId, dislikedBy);
		dislikeServices.getDislikesCount(postId);
		posts=postServices.ShowAllPosts(account.getEmailId());
        return new ModelAndView("myWallPage","posts",posts);
	}
	@RequestMapping("/deleteAPost1")
	public ModelAndView deleteAPost1(@RequestParam int postId,@SessionAttribute("account")  Account account)throws InvalidEmailIdException, UserNotFriendException{
		postServices.deletePost(postId);
		List<Post> posts=postServices.ShowAllPosts(account.getEmailId());
		return new ModelAndView("userProfilePage", "posts", posts);
	}
	@RequestMapping("/deleteAPost")
	public ModelAndView deleteAPost(@RequestParam int postId,@SessionAttribute("account")  Account account)throws InvalidEmailIdException, UserNotFriendException{
		postServices.deletePost(postId);
		List<Post> posts=postServices.ShowAllPosts(account.getEmailId());
		return new ModelAndView("myWallPage", "posts", posts);
	}
	
	@RequestMapping("/updateLikes1")
	public ModelAndView updateLikes1(@RequestParam int postId,@RequestParam String likedBy,@SessionAttribute("account") Account account,@SessionAttribute("posts") List<Post> posts) throws InvalidEmailIdException {
		likeServices.updateLikes(postId, likedBy);
		likeServices.getLikesCount(postId);
		List<Post> allposts=postServices.ShowAllFriendsPosts(account.getEmailId());	
		 Collections.sort(allposts);
        return new ModelAndView("userProfilePage","posts",allposts);
	}
	@RequestMapping("/updateDislikes1")
	public ModelAndView updateDisLikes1(@RequestParam int postId,@RequestParam String dislikedBy,@SessionAttribute("account") Account account,@SessionAttribute("posts") List<Post> posts) throws InvalidEmailIdException {
		dislikeServices.updateDislikes(postId, dislikedBy);
		dislikeServices.getDislikesCount(postId);
		List<Post> allposts=postServices.ShowAllFriendsPosts(account.getEmailId());	
		 Collections.sort(allposts);
        return new ModelAndView("userProfilePage","posts",allposts);
	}
	@RequestMapping("/friendtoUserHome")
	public ModelAndView friendtoUserHome(@SessionAttribute("account") Account account)throws InvalidEmailIdException, UserNotFriendException{
		return new ModelAndView("userProfilePage", "account",account);
	}
	@RequestMapping("/friendtoUserHomemyStatus")
	public ModelAndView friendtoUserHomemyStatus(@SessionAttribute("account") Account account)throws InvalidEmailIdException, UserNotFriendException{
		return new ModelAndView("myStatusPage", "account",account);
	}
	@RequestMapping("/friendtoUserHomecheckMails")
	public ModelAndView friendtoUserHomecheckMails(@SessionAttribute("account") Account account)throws InvalidEmailIdException, UserNotFriendException{
		return new ModelAndView("emailPage", "account",account);
	}
	@RequestMapping("/showAllFriendRequests")
	public ModelAndView showFriendRequests(@SessionAttribute("account") Account account)throws InvalidEmailIdException, UserNotFriendException{
		List<Relationship> allfriendRequests=friendServices.showAllFriendRequests(account.getEmailId());
		if(allfriendRequests==null)
			return new ModelAndView("allFriendRequestPage", "allfriendRequests","You have no friend requests");
		else
		return new ModelAndView("allFriendRequestPage", "allfriendRequests",allfriendRequests);
	}
	@RequestMapping("/getFriendProfile")
	public ModelAndView getFriendProfile(@SessionAttribute("account") Account account,@RequestParam String friendProfileId)throws InvalidEmailIdException, UserNotFriendException{
		Account friendProfileAccount=capbookServices.findAccountByEmailId(friendProfileId);
		return new ModelAndView("friendProfilePage", "friendaccount",friendProfileAccount);
	}
	@RequestMapping("/acceptFriendRequest")
	public ModelAndView acceptFriendRequest(@SessionAttribute("account") Account account,@RequestParam String senderId)throws InvalidEmailIdException, UserNotFriendException{
		friendServices.confirmFriendRequest(senderId, account.getEmailId());
		List<Relationship> allfriendRequests=friendServices.showAllFriendRequests(account.getEmailId());
		return new ModelAndView("allFriendRequestPage", "allfriendRequests",allfriendRequests);
	}
	@RequestMapping("/rejectFriendRequest")
	public ModelAndView rejectFriendRequest(@SessionAttribute("account") Account account,@RequestParam String senderId)throws InvalidEmailIdException, UserNotFriendException{
		friendServices.rejectFriendRequest(senderId, account.getEmailId());
		List<Relationship> allfriendRequests=friendServices.showAllFriendRequests(account.getEmailId());
		return new ModelAndView("allFriendRequestPage", "allfriendRequests",allfriendRequests);
	}
	@RequestMapping("/showAllFriends")
	public ModelAndView showAllFriends(@SessionAttribute("account") Account account)throws InvalidEmailIdException, UserNotFriendException{
		List<Friend> allfriends=friendServices.showAllFriends(account.getEmailId());
		return new ModelAndView("allFriendsPage", "allfriends",allfriends);
	}
	//@RequestMapping("/deleteFriend")
//	public ModelAndView deleteFriend(@SessionAttribute("account") Account account,@RequestParam String friendProfileId)throws InvalidEmailIdException, UserNotFriendException{
	//	friendServices.removeFriend(account.getEmailId(), friendProfileId);
	//	List<Friend> allfriends=friendServices.showAllFriends(account.getEmailId());
		//if(allfriends==null)
		//	return new ModelAndView("allFriendsPage", "allfriends",allfriends);
		//else
	//		return new ModelAndView("allFriendsPage", "allfriends",allfriends);
	//}
	@RequestMapping("/getFriendsPost")
	public ModelAndView getFriendsPost(@RequestParam String friendProfileId)throws InvalidEmailIdException, UserNotFriendException{
		List<Post> friendposts=postServices.ShowAllPosts(friendProfileId);
		return new ModelAndView("friendProfilePage", "friendposts", friendposts);
	}
	@RequestMapping("/updateLikesForFriends")
	public ModelAndView updateLikesForFriends(@RequestParam int postId,@RequestParam String likedBy,@SessionAttribute("account") Account account) throws InvalidEmailIdException {
		likeServices.updateLikes(postId,account.getEmailId());
		likeServices.getLikesCount(postId);
		List<Post> friendposts=postServices.ShowAllPosts(likedBy);
        return new ModelAndView("friendProfilePage","friendposts",friendposts);
	}
	@RequestMapping("/updateDislikesForFriends")
	public ModelAndView updateDislikesForFriends(@RequestParam int postId,@RequestParam String dislikedBy,@SessionAttribute("account") Account account) throws InvalidEmailIdException {
		dislikeServices.updateDislikes(postId,account.getEmailId());
		dislikeServices.getDislikesCount(postId);
		List<Post> friendposts=postServices.ShowAllPosts(dislikedBy);
        return new ModelAndView("friendProfilePage","friendposts",friendposts);
	}
	@RequestMapping("/myWallPosts")
	public ModelAndView allPosts(@SessionAttribute("account")  Account account)throws InvalidEmailIdException, UserNotFriendException{
		List<Post> posts=postServices.ShowAllPosts(account.getEmailId());
		return new ModelAndView("myWallPage", "posts", posts);
	}
	@RequestMapping("/getFriendsBirthday")
	public ModelAndView getFriendsBirthday(@SessionAttribute("account") Account account) throws InvalidEmailIdException {
		List<String>birthdays=capbookServices.getFriendBirthdays(account.getEmailId());
		return new ModelAndView("FriendsBirthdayPage","birthdays",birthdays);
	}
	@RequestMapping("/updatePost")
	public ModelAndView updatePost(@RequestParam String postMessage ,@SessionAttribute("account") Account account) throws InvalidEmailIdException, UserNotFriendException {
		postServices.savePost(account.getEmailId(), postMessage);
		List<Post> allposts=postServices.ShowAllFriendsPosts(account.getEmailId());	
		 Collections.sort(allposts);
		return new ModelAndView("userProfilePage", "posts", allposts);
	}
	@RequestMapping("/showAllPosts")
	public ModelAndView showAllPosts(@SessionAttribute("account") Account account) throws InvalidEmailIdException, UserNotFriendException {
		List<Post> allposts=postServices.ShowAllFriendsPosts(account.getEmailId());	
		 Collections.sort(allposts);
		return new ModelAndView("userProfilePage", "posts", allposts);
	}
	@RequestMapping("/sendFriendRequest")
	public ModelAndView sendFriendRequest(@RequestParam String receiverId,@SessionAttribute("account") Account account)throws InvalidEmailIdException, UserNotFriendException{	
		if(account.getEmailId().equalsIgnoreCase(receiverId)) {
			return new ModelAndView("SearchResultPage", "status", "Cant Send Friend Request to Yourself");
		}
			else {
			Relationship relation=friendServices.findFriendRequest(account.getEmailId(), receiverId);
			if(relation==null) {
			friendServices.sendFriendRequest(account.getEmailId(), receiverId);
			return new ModelAndView("SearchResultPage", "status", "Friend request Sent");
			}
			else {
					if(relation.getStatus().equalsIgnoreCase("pending"))
							return new ModelAndView("SearchResultPage", "status", "Friend Request has already been sent");
					if(relation.getStatus().equalsIgnoreCase("rejected"))
						return new ModelAndView("SearchResultPage", "status", "Sorry that user has rejected your request aldready.");
					else
						return new ModelAndView("SearchResultPage", "status", "User already a friend");
			}
		}
	}
		@RequestMapping("/getSearchResults")
		public ModelAndView getSearchResults(@RequestParam String receiverId,@SessionAttribute("account") Account account)throws InvalidEmailIdException, UserNotFriendException{	
			Account account1=capbookServices.findAccountByEmailIdForSearch(receiverId);	
			if(account1==null)
				return new ModelAndView("SearchResultPage", "searchResult","No User Found");
			else
				return new ModelAndView("SearchResultPage", "searchResult",receiverId);
				
		}	
		
	
}

