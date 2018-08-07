package com.cg.pwa.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.oracle.webservices.internal.api.EnvelopeStyle;

@Entity
@Table(name="Account")
public class Account1 {
	
	@Id
	private String mobileNo;
	private String customerName;
	private String emailId;
	private double balance;
	@Override
	public String toString() {
		return "Account1 [customerName=" + customerName + ", mobileNo="
				+ mobileNo + ", emailId=" + emailId + ", balance=" + balance
				+ "]";
	}
	public String getCustomerName() {
		return customerName;
	}
	public Account1(String customerName, String phoneNo, String emailId,
			double initialBalance) {
		super();
		this.customerName = customerName;
		this.mobileNo = phoneNo;
		this.emailId = emailId;
		this.balance = initialBalance;
	}
	public Account1() {
		
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String phoneNo) {
		this.mobileNo = phoneNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double initialBalance) {
		this.balance = initialBalance;
	}
}
