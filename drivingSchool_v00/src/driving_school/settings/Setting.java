package driving_school.settings;

import java.util.Scanner;

import driving_school.gui.SettingGUI;

public class Setting {
	
	public static double basePriceForCandidate = 15;//dt
	public static double priceExamForCandidate = 50;//dt

	public static double priceSeanceCodeForInstructor = 7;//dt
	public static double priceSeanceDrivingForInstructor = 10;//dt
	public static double seanceLength = 120; // min
	public static double mileageOfSeance= 40;
	public static double maximumMileage = 600;
	public static void managment() {
		Scanner sc = new Scanner(System.in);
		int take;
		
		do {
			System.out.println("\t\t1.set  basePriceForCandidate ");
			System.out.println("\t\t2.set priceSeanceCodeForInstructor");
			System.out.println("\t\t3.set priceSeanceDrivingForInstructor");
			System.out.println("\t\t4.set seanceLength");
			System.out.println("\t\t5.set mileageOfSeance");
			System.out.println("\t\t6.set maximumMileage");
			System.out.println("\t\t7.set priceExamForCandidate");
			System.out.println("\t\t0.RETOUR");
			System.out.print("\t\t--> ");
			take = sc.nextInt();
			sc.nextLine();
			
			switch(take) {
				case 1 : 
					//set  basePriceForCandidate
					System.out.print("\t\t\tbasePriceForCandidate -->");
					double d = SettingGUI.inputNumber();
					setBasePriceForCandidate(d);
					break;
				case 2 :
					//set  basePriceForCandidate
					System.out.print("\t\t\tpriceSeanceCodeForInstructor -->");
					double d1 = SettingGUI.inputNumber();
					setPriceSeanceCodeForInstructor(d1);
					break;
				case 3 :
					//set  basePriceForCandidate
					System.out.print("\t\t\tpriceSeanceDrivingForInstructor -->");
					double d2 = SettingGUI.inputNumber();
					setPriceSeanceDrivingForInstructor(d2);
					break;
				case 4 :
					//set  basePriceForCandidate
					System.out.println("\t\t\tseanceLength");
					double l = SettingGUI.inputNumber();
					setSeanceLength(l);
					break;
				case 5 :
					//set  basePriceForCandidate
					System.out.print("\t\t\tmileageOfSeance -->");
					double m = SettingGUI.inputNumber();
					setMileageOfSeance(m);
					break;
				case 6 :
					//set  basePriceForCandidate
					System.out.print("\t\t\tmaximumMileage -->");
					double m2 = SettingGUI.inputNumber();
					setMaximumMileage(m2);
					break;
				case 7 :
					System.out.print("\t\t\tpriceExamForCandidate -->");
					double l3 = SettingGUI.inputNumber();
					setPriceExamForCandidate(l3);
					break;
				case 0 :
					break;
				default : 
					System.out.println("\t\t--|take 0 or 1 or 2 or 3 or 4 or 5 or 6 |-- ");
			}
		}while(take != 0);
	}
	public static void setBasePriceForCandidate(double basePriceForCandidate) {
		Setting.basePriceForCandidate = basePriceForCandidate;
	}
	public static void setPriceSeanceCodeForInstructor(double priceSeanceCodeForInstructor) {
		Setting.priceSeanceCodeForInstructor = priceSeanceCodeForInstructor;
	}
	public static void setPriceSeanceDrivingForInstructor(double priceSeanceDrivingForInstructor) {
		Setting.priceSeanceDrivingForInstructor = priceSeanceDrivingForInstructor;
	}
	public static void setSeanceLength(double seanceLength) {
		Setting.seanceLength = seanceLength;
	}
	public static void setMileageOfSeance(double mileageOfSeance) {
		Setting.mileageOfSeance = mileageOfSeance;
	}
	public static void setMaximumMileage(double maximumMileage) {
		Setting.maximumMileage = maximumMileage;
	}
	public static void setPriceExamForCandidate(double priceExamForCandidate) {
		Setting.priceExamForCandidate = priceExamForCandidate;
	}
	
	
	
}
