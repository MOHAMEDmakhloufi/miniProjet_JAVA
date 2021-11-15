package driving_school.entites;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import driving_school.connections_with_data_file.ConnectionWithDataSeance;
import driving_school.date.MyDate;
import driving_school.settings.Setting;

public class Candidate extends Person implements Buisiness {
	
	private String category;
	private double paidAmount;


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
		double total = this.totalAmount();
        DecimalFormat df = new DecimalFormat("0.00"); 
        
		return "\t\t\t\tCategory	 : "+ this.category+"\n"
				+super.toString()
				+"\n\t\t\t\tTotalAmount	 : "+ df.format(total)+" dt"
				+"\n\t\t\t\tRemainingAmount	 : "+ df.format((total - super.paidAmount))+" dt\n\n";
	}
	
	@Override
	public double totalAmount() {
		double totalCode = 0, totalDriving = 0;
		ArrayList<Seance> listSeanceCode= ConnectionWithDataSeance.getByIdCandidate(cin, "SeanceCode");
		if (category.equals("a"))
			totalCode = Setting.basePriceForCandidate + (Setting.basePriceForCandidate * 2 /(double) 100);
		else if (category.equals("b"))
			totalCode = Setting.basePriceForCandidate + (Setting.basePriceForCandidate * 3 /(double) 100);
		else 
			totalCode = Setting.basePriceForCandidate + (Setting.basePriceForCandidate * 5 /(double) 100);
		totalCode *= listSeanceCode.size();
		ArrayList<Seance> listSeanceDriving= ConnectionWithDataSeance.getByIdCandidate(cin, "SeanceDriving");
		if (category.equals("a"))
			totalDriving = Setting.basePriceForCandidate + (Setting.basePriceForCandidate * 4 /(double) 100);
		else if (category.equals("b"))
			totalDriving = Setting.basePriceForCandidate + (Setting.basePriceForCandidate * 6 /(double) 100);
		else 
			totalDriving = Setting.basePriceForCandidate + (Setting.basePriceForCandidate * 8 /(double) 100);
		totalDriving *= listSeanceDriving.size();
		
		return totalCode  + totalDriving ;
	}

	@Override
	public void consultAllSeanceCode() {
		ArrayList<Seance> listSeanceCode= ConnectionWithDataSeance.getByIdCandidate(cin, "SeanceCode"); 
		
		for(Seance sea : listSeanceCode)
			System.out.println("\t\t\t\t"+sea);
		
	}

	@Override
	public void consultNextSeanceCode() {
		ArrayList<Seance> listSeanceCode= ConnectionWithDataSeance.getByIdCandidate(cin, "SeanceCode");
		
		for(Seance sea : listSeanceCode)
			if (MyDate.testAfterOrBefore(sea.getDate()))
				System.out.println("\t\t\t\t"+sea);
	}

	@Override
	public void consultPreviousSeanceCode() {
		ArrayList<Seance> listSeanceCode= ConnectionWithDataSeance.getByIdCandidate(cin, "SeanceCode");
		for(Seance sea : listSeanceCode)
			if ( !MyDate.testAfterOrBefore(sea.getDate()))
				System.out.println("\t\t\t\t"+sea);
		
	}

	@Override
	public void consultAllSeanceDriving() {
		ArrayList<Seance> listSeanceDriving= ConnectionWithDataSeance.getByIdCandidate(cin, "SeanceDriving"); 
		
		for(Seance sea : listSeanceDriving)
			System.out.println("\t\t\t\t"+sea);
		
	}

	@Override
	public void consultNextSeanceDriving() {
		ArrayList<Seance> listSeanceDriving= ConnectionWithDataSeance.getByIdCandidate(cin, "SeanceDriving"); 
		
		for(Seance sea : listSeanceDriving)
			if (MyDate.testAfterOrBefore(sea.getDate()))
			System.out.println("\t\t\t\t"+sea);
		
	}

	@Override
	public void consultPreviousSeanceDriving() {
		ArrayList<Seance> listSeanceDriving= ConnectionWithDataSeance.getByIdCandidate(cin, "SeanceDriving"); 
		
		for(Seance sea : listSeanceDriving)
			if ( !MyDate.testAfterOrBefore(sea.getDate()))
			System.out.println("\t\t\t\t"+sea);
		
	}
}
