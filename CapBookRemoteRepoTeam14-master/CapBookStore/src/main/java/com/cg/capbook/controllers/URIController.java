package com.cg.capbook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cg.capbook.beans.Account;
import com.cg.capbook.beans.Comments;
import com.cg.capbook.beans.Notification;
import com.cg.capbook.beans.Post;
import com.cg.capbook.beans.PrivateInfo;
//abcd
@Controller
public class URIController {
	private Account account;
	private PrivateInfo privateInfo;
	private Post post;
	private Comments comments;
	private Notification notification;
	@RequestMapping(value= {"/","index"})
	public String getIndexPage(){
		return "indexPage";
	}
	@RequestMapping("/registration")
	public String getRegistrationPage(){
		return "registrationPage";
	}
	
	@RequestMapping("/forgotPassword")
	public String getforgotPassword(){
		return "forgotPasswordPage";
	}
	@RequestMapping("/changePassword")
	public String getchangePassword(){
		return "changePasswordPage";
	}
	

	@ModelAttribute
	public Account getAccount(){
		account=new Account();
		return account;
	}
	
	@ModelAttribute
	public PrivateInfo getPrivateInfo(){
		privateInfo=new PrivateInfo();
		return privateInfo;
	}
	@ModelAttribute
	public Post getPost() {
		post=new Post();
		return post;
		}

	@ModelAttribute
	public Comments getCommnets() {
		comments=new Comments();
		return comments;
		}

	@ModelAttribute
	public Notification getNotification() {
		notification=new Notification();
		return notification;
		}

	}

