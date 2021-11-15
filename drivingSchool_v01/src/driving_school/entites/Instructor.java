package driving_school.entites;

import java.util.ArrayList;
import java.util.Date;

import driving_school.connections_with_data_file.ConnectionWithDataSeance;
import driving_school.settings.Setting;

public class Instructor extends Person implements Buisiness {

	private double paidAmount;
	
	public Instructor(long cin, String name, long phoneNumber, String emailAddress, Date dateOfBirthday, double paidAmount) {
		super(cin, name, phoneNumber, emailAddress, dateOfBirthday, paidAmount);
	}

	@Override
	public String toString() {
		double total = this.totalAmount();
		return "\n"
				+super.toString()
				+"\n\t\t\t\tTotalAmount	 : "+ total
				+"\n\t\t\t\tRemainingAmount	 : "+ (total - this.paidAmount)+"\n\n";
	}
	@Override
	public double totalAmount() {
		
		ArrayList<Seance> listSeanceCode= ConnectionWithDataSeance.getByIdInstructor(cin, "SeanceCode");
		double totalCode = Setting.priceSeanceCodeForInstructor * listSeanceCode.size();
		
		ArrayList<Seance> listSeanceDriving= ConnectionWithDataSeance.getByIdInstructor(cin, "SeanceDriving");
		double totalDriving = Setting.priceSeanceDrivingForInstructor * listSeanceDriving.size();
		
		return totalCode  + totalDriving ;
	}


	@Override
	public void consultAllSeanceCode() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void consultNextSeanceCode() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void consultPreviousSeanceCode() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void consultAllSeanceDriving() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void consultNextSeanceDriving() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void consultPreviousSeanceDriving() {
		// TODO Auto-generated method stub
		
	}

}
