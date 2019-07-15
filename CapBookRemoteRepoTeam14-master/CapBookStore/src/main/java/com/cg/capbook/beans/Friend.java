 package com.cg.capbook.beans;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
//abcd
@Entity
public class Friend {
	@Override
	public String toString() {
		return "Friend [friendId=" + friendId + ", friendEmailId=" + friendEmailId + ", account=" + account + "]";
	}
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int friendId;
	private String friendEmailId;
	@ManyToOne
	private Account account;
	public Friend () {}
	public String getFriendEmailId() {
		return friendEmailId;
	}
	public void setFriendEmailId(String friendEmailId) {
		this.friendEmailId = friendEmailId;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Friend(String friendEmailId, Account account) {
		super();
		this.friendEmailId = friendEmailId;
		this.account = account;
	}
}