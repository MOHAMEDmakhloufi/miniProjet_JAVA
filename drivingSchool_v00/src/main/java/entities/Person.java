package entities;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Person {
	
	protected long cin;
	protected String name;
	protected long phoneNumber;
	protected String emailAddress;
	protected Date dateOfBirthday;

	protected double paidAmount;
	protected double remainingAmount;
	
	
	public Person(long cin, String name, long phoneNumber, String emailAddress, Date dateOfBirthday, double paidAmount) {
		this.cin = cin;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.dateOfBirthday = dateOfBirthday;
		this.paidAmount = paidAmount;
	}


	public long getCin() {
		return cin;
	}


	public void setCin(long cin) {
		this.cin = cin;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	public Date getDateOfBirthday() {
		return dateOfBirthday;
	}


	public void setDateOfBirthday(Date dateOfBirthday) {
		this.dateOfBirthday = dateOfBirthday;
	}


	public double getPaidAmount() {
		return paidAmount;
	}


	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	

}
