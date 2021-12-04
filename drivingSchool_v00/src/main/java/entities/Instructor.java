package entities;

import java.util.ArrayList;
import java.util.Date;


public class Instructor extends Person{

	private double paidAmount;
	
	public Instructor(long cin, String name, long phoneNumber, String emailAddress, Date dateOfBirthday, double paidAmount) {
		super(cin, name, phoneNumber, emailAddress, dateOfBirthday, paidAmount);
	}

}
