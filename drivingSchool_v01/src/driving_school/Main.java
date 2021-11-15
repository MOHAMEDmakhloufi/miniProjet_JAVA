package driving_school;

import java.util.Scanner;

import driving_school.services.PersonService;
import driving_school.services.SeanceService;
import driving_school.services.VehicleService;
import driving_school.settings.Setting;

public class Main {
	public static void firstDisplay() {
		System.out.println("                ______________________________________________");
		System.out.println("               |                \\              /              |");
		System.out.println("               |                 DRIVING-SCHOOL               |");
		System.out.println("               |________________/______________\\______________|");
	}
	public static void main(String[] args) {
		firstDisplay();
		Scanner sc = new Scanner(System.in);
		int take;
		
		do {
			System.out.println("\n\t1.Candidate management");
			System.out.println("\t2.Instructor management");
			System.out.println("\t3.Car  management");
			System.out.println("\t4.Moto   management");
			System.out.println("\t5.Truck   management");
			System.out.println("\t6.Seance Code   managment");
			System.out.println("\t7.Seance Driving   managment");
			System.out.println("\t8.Settings");
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
					VehicleService.managment("Car");
					break;
				case 4 :
					VehicleService.managment("Moto");
					break;
				case 5 :
					VehicleService.managment("Truck");
					break;
				case 6 :
					SeanceService.managment("SeanceCode");
					break;
				case 7 :
					SeanceService.managment("SeanceDriving");
					break;	
				case 8 :
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
