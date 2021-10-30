package driving_school.entites;

import java.util.Date;

public class Instructor extends Person {


	
	public Instructor(long cin, String name, long phoneNumber, String emailAddress, Date dateOfBirthday, double paidAmount) {
		super(cin, name, phoneNumber, emailAddress, dateOfBirthday, paidAmount);
	}

	@Override
	public void listSeance() {
		
	}
}
