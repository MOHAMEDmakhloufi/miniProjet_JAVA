package driving_school;

import java.util.Scanner;

import driving_school.services.PersonService;
import driving_school.services.SeanceService;
import driving_school.services.VehicleService;
import driving_school.settings.Setting;

public class Main {

	public static void main(String[] args) throws Exception{
		
		Scanner sc = new Scanner(System.in);
		int take;
		
		do {
			System.out.println("\t1.Candidate management");
			System.out.println("\t2.Instructor management");
			System.out.println("\t3.Vehicle   management");
			System.out.println("\t4.Seance Code   managment");
			System.out.println("\t4.Seance Driving   managment");
			System.out.println("\t5.Settings");
			System.out.println("\t0.EXIT");
			System.out.print("\t--> ");
			
			take = sc.nextInt();
			sc.nextLine();
			
			switch(take) {
				case 1 : 
					PersonService.managment("Candidate");
					break;
				case 2 :
					PersonService.managment("Instructor");
					break;
				case 3 :
					VehicleService.managment();
					break;
				case 4 :
					SeanceService.managment("SeanceCode");
					break;
				case 5 :
					SeanceService.managment("SeanceDriving");
					break;	
				case 6 :
					Setting.managment();
					break;
				case 0 :
					break;
				default : 
					System.out.println("\t--|take 0 or 1 or 2 or 3 or 4 or 5 or 6 |-- ");
			}
		}while(take != 0);
		

	}

}
