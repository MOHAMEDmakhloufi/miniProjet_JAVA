package driving_school.entites;

import java.util.ArrayList;
import java.util.Date;

import driving_school.connections_with_data_file.ConnectionWithDataExam;
import driving_school.connections_with_data_file.ConnectionWithDataSeance;
import driving_school.date.MyDate;
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
		ArrayList<Exam> exams = ConnectionWithDataExam.getByIdInstructor(cin, "ExamDriving");
		double totalCode = Setting.priceSeanceCodeForInstructor * (listSeanceCode.size() + exams.size() );
		
		ArrayList<Seance> listSeanceDriving= ConnectionWithDataSeance.getByIdInstructor(cin, "SeanceDriving");
		double totalDriving = Setting.priceSeanceDrivingForInstructor * listSeanceDriving.size();
		
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
	
	@Override
	public void consultExamDriving() {
		ArrayList<Exam> exams = ConnectionWithDataExam.getByIdInstructor(cin, "ExamDriving");
		for(Exam exam : exams)
			System.out.println(exam);
	}
}
