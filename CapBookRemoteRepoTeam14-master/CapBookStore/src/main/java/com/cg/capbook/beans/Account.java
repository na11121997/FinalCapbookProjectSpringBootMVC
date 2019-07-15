package com.cg.capbook.beans;

import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//abcd
@Entity
public class Account {
	@Id
	private String emailId;
	private String password;
	private String securityQuestion;
	private String firstName;
	private String lastName;
	private int age;
	private String myStatus;
	private String profileImage;
	private String gender;
	@Embedded
	private PrivateInfo privateInfo;	
	@OneToMany(mappedBy="account",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Photo> photos;
	@OneToMany(mappedBy="account",cascade=CascadeType.ALL,orphanRemoval=true) 
	private List<Post> post;
	@OneToMany(mappedBy="account",fetch=FetchType.EAGER,cascade=CascadeType.ALL,orphanRemoval=true) 
	private List<Friend> friends;


	public String getProfileImage() {
		return profileImage;
	}
	public List<Friend> getFriends() {
		return friends;
	}
	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}
	public List<Post> getPost() {
		return post;
	}
	public void setPost(List<Post> post) {
		this.post = post;
	}
	public String getMyStatus() {
		return myStatus;
	}
	public void setMyStatus(String myStatus) {
		this.myStatus = myStatus;
	}
	
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	public PrivateInfo getPrivateInfo() {
		return privateInfo;
	}
	public void setPrivateInfo(PrivateInfo privateInfo) {
		this.privateInfo = privateInfo;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(String firstName, String lastName, String emailId, String password, int age,
			String gender, String securityQuestion, PrivateInfo privateInfo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
		this.age = age;
		this.gender = gender;
		this.securityQuestion = securityQuestion;
		this.privateInfo = privateInfo;

	}

	public Account(String emailId, String firstName, String lastName, String password, String securityQuestion,
			int age, String gender, PrivateInfo privateInfo) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
		this.age = age;
		this.gender = gender;
		this.securityQuestion = securityQuestion;
		this.privateInfo = privateInfo;
	}
	@Override
	public String toString() {
		return "Account [firstName=" + firstName + ", lastName=" + lastName + ", emailId="
				+ emailId + ", password=" + password + ", age=" + age + ", gender=" + gender + ", securityQuestion="
				+ securityQuestion + ", privateInfo=" + privateInfo +"]";
	}
	

}
