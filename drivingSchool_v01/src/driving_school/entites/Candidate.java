package driving_school.entites;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Candidate extends Person {
	
	private String category;



	public Candidate(long cin, String name, long phoneNumber, String emailAddress, Date dateOfBirthday, String category, double paidAmount) {
		super(cin, name, phoneNumber, emailAddress, dateOfBirthday, paidAmount);
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	

	@Override
	public String toString() {
		return super.toString()+"\n\t\t\t\tCategory	 : "+ this.category+"\n";
	}
	
	@Override
	public void listSeance() {
		
	}
	
}
