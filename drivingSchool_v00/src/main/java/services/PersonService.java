package services;


import java.util.ArrayList;

import dao.ConnectionWithDataExam;
import dao.ConnectionWithDataPerson;
import dao.ConnectionWithDataSeance;
import date.MyDate;
import entities.Exam;
import entities.Person;
import entities.Seance;
import settings.Setting;


public class PersonService {
	public static void  add(String nameOfClass, Person person) {
		ConnectionWithDataPerson.add(person);
	}
	
	public static Person search(String nameOfClass, long cin) {
		return ConnectionWithDataPerson.search(cin, nameOfClass);
	}
	
	public static void update( long cin, Person p) {
		ConnectionWithDataPerson.updateByCin(cin, p);
	}
	public static void delete(String nameOfClass, long cin) {
		ConnectionWithDataPerson.delete(cin, nameOfClass);
	}
	public static ArrayList<Person> getAll(String nameOfClass) {
		 return ConnectionWithDataPerson.getAll(nameOfClass);
	}
	
	
	public static double totalAmountForCandidate(long cin, String category) {
		double totalCode = 0, totalDriving = 0;
		ArrayList<Seance> listSeanceCode= ConnectionWithDataSeance.getByIdCandidate(cin, "SeanceCode");
		if (category.equals("A"))
			totalCode = Setting.basePriceForCandidate + (Setting.basePriceForCandidate * 2 /(double) 100);
		else if (category.equals("B"))
			totalCode = Setting.basePriceForCandidate + (Setting.basePriceForCandidate * 3 /(double) 100);
		else 
			totalCode = Setting.basePriceForCandidate + (Setting.basePriceForCandidate * 5 /(double) 100);
		totalCode *= listSeanceCode.size();
		ArrayList<Seance> listSeanceDriving= ConnectionWithDataSeance.getByIdCandidate(cin, "SeanceDriving");
		if (category.equals("A"))
			totalDriving = Setting.basePriceForCandidate + (Setting.basePriceForCandidate * 4 /(double) 100);
		else if (category.equals("B"))
			totalDriving = Setting.basePriceForCandidate + (Setting.basePriceForCandidate * 6 /(double) 100);
		else 
			totalDriving = Setting.basePriceForCandidate + (Setting.basePriceForCandidate * 8 /(double) 100);
		totalDriving *= listSeanceDriving.size();
		
		//exam Driving
		if (ConnectionWithDataExam.search(cin, "ExamDriving")!= null)
			totalDriving += Setting.priceExamForCandidate;
		//exam Code
		if (ConnectionWithDataExam.search(cin, "ExamCode")!= null)
			totalCode += Setting.priceExamForCandidate;
		
		return totalCode  + totalDriving ;
	}

	public static double totalAmountForInstructor(long cin) {
		
		ArrayList<Seance> listSeanceCode= ConnectionWithDataSeance.getByIdInstructor(cin, "SeanceCode");
		
		double totalCode = Setting.priceSeanceCodeForInstructor * listSeanceCode.size();
		
		ArrayList<Seance> listSeanceDriving= ConnectionWithDataSeance.getByIdInstructor(cin, "SeanceDriving");
		ArrayList<Exam> exams = ConnectionWithDataExam.getByIdInstructor(cin, "ExamDriving");
		double totalDriving = Setting.priceSeanceDrivingForInstructor * (listSeanceDriving.size()+ exams.size());
		
		return totalCode  + totalDriving ;
	}
	
	public static ArrayList<Seance> consultAllSeanceCode(long cin) {
		 return ConnectionWithDataSeance.getByIdCandidate(cin, "SeanceCode"); 
	}

	
	public static ArrayList<Seance> consultNextSeanceCode(long cin) {
		ArrayList<Seance> listSeanceCode= ConnectionWithDataSeance.getByIdCandidate(cin, "SeanceCode");
		ArrayList<Seance> nextSeances = new ArrayList<Seance>();
		for(Seance sea : listSeanceCode)
			if (MyDate.testAfterOrBefore(sea.getDate()))
				nextSeances.add(sea);
		return nextSeances;
	}

	
	public static ArrayList<Seance> consultPreviousSeanceCode(long cin) {
		ArrayList<Seance> listSeanceCode= ConnectionWithDataSeance.getByIdCandidate(cin, "SeanceCode");
		ArrayList<Seance> nextSeances = new ArrayList<Seance>();
		for(Seance sea : listSeanceCode)
			if (! MyDate.testAfterOrBefore(sea.getDate()))
				nextSeances.add(sea);
		return nextSeances;
	}

	
	public static ArrayList<Seance> consultAllSeanceDriving(long cin) {
		return ConnectionWithDataSeance.getByIdCandidate(cin, "SeanceDriving"); 
		
	}

	
	public static ArrayList<Seance> consultNextSeanceDriving(long cin) {
		ArrayList<Seance> listSeanceDriving= ConnectionWithDataSeance.getByIdCandidate(cin, "SeanceDriving"); 
		ArrayList<Seance> nextSeances = new ArrayList<Seance>();

		for(Seance sea : listSeanceDriving)
			if ( MyDate.testAfterOrBefore(sea.getDate()))
				nextSeances.add(sea);
		return nextSeances;
		
	}

	
	public static ArrayList<Seance> consultPreviousSeanceDriving(long cin) {
		ArrayList<Seance> listSeanceDriving= ConnectionWithDataSeance.getByIdCandidate(cin, "SeanceDriving"); 
		ArrayList<Seance> nextSeances = new ArrayList<Seance>();

		for(Seance sea : listSeanceDriving)
			if (! MyDate.testAfterOrBefore(sea.getDate()))
				nextSeances.add(sea);
		return nextSeances;
		
	}

	
	public static Exam consultExamDriving(long cin) {
		return ConnectionWithDataExam.search(cin, "ExamDriving");
		
	}
	
	public static Exam consultExamCode(long cin) {
		return ConnectionWithDataExam.search(cin, "ExamCode");
		
	}
}
