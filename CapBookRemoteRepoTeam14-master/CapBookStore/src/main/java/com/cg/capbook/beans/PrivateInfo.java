package com.cg.capbook.beans;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

//abcd
@Embeddable
public class PrivateInfo {
	private String DOB;
	private int mobileNo;
	@Embedded
	private Address address;
	public String getDOB() {
		return DOB;
	}
	public PrivateInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public int getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(int mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public PrivateInfo(String dOB, int mobileNo, Address address) {
		super();
		this.DOB = dOB;
		this.mobileNo = mobileNo;
		this.address = address;
	}
	public PrivateInfo(int mobileNo, String dOB) {
		this.DOB = dOB;
		this.mobileNo = mobileNo;
	}
	
	
}