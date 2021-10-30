package driving_school.entites;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Person {
	
	protected long cin;
	protected String name;
	protected long phoneNumber;
	protected String emailAddress;
	protected Date dateOfBirthday;
	protected double totalAmount;
	protected double paidAmount;
	protected double remainingAmount;
	
	
	public Person(long cin, String name, long phoneNumber, String emailAddress, Date dateOfBirthday, double paidAmount) {
		this.cin = cin;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.dateOfBirthday = dateOfBirthday;
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


	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	//format date dd/mm/yyyy
	private String formatDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.dateOfBirthday);
	}
	
	@Override 
	public String toString() {
		return     "\t\t\t\tCin              : "+this.cin
				+"\n\t\t\t\tName             : "+this.name
				+"\n\t\t\t\tDate Of Birthday : "+formatDate()
				+"\n\t\t\t\tPhone number     : "+this.phoneNumber
				+"\n\t\t\t\tEmail Address    : "+this.emailAddress;
	}

	/**
	 * consult All the Seances By person
	 */
	public abstract void listSeance();
	
	
}
