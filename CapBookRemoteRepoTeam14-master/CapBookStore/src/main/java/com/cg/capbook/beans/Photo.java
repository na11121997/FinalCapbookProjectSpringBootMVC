package com.cg.capbook.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
//abcd
@Entity
public class Photo {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int photoId;

	private String type;
	@ManyToOne
	Account account;
	//@ManyToOne
	//Album album;
	public int getPhotoId() {
		return photoId;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Account getaccount() {
		return account;
	}
	public void setaccount(Account account) {
		this.account = account;
	}
	public Photo(int photoId, String type, Account account) {
		super();
		this.photoId = photoId;
		this.type = type;
		this.account = account;
	}
	public Photo() {
		super();
	}
}
