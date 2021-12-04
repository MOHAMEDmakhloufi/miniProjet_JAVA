package drivingSchool;

import java.util.Scanner;


import gui.FirstInterface;
import controllers.PersonController;
import controllers.VehicleController;
import controllers.SeanceController;
import controllers.ExamController;
public class Main {

	
	public static void main(String[] args) {
		FirstInterface.firstDisplay();
		
		int take;
		
		do {
			
			take = FirstInterface.choices();
			
			switch(take) {
				case 1 : 
					PersonController.managment("Candidate");
					break;
				case 2 :
					PersonController.managment("Instructor");
					break;
				case 3 :
					VehicleController.managment("Car");
					break;
				case 4 :
					VehicleController.managment("Moto");
					break;
				case 5 :
					VehicleController.managment("Truck");
					break;
				case 6 :
					SeanceController.managment("SeanceCode");
					break;
				case 7 :
					SeanceController.managment("SeanceDriving");
					break;	
				case 8 :
					ExamController.managment("ExamCode");
					break;
				case 9 :
					ExamController.managment("ExamDriving");
					break;	
				case 10 :
					//Setting.managment();
					break;
				case 0 :
					break;
				default : 
					System.out.println("\t--|take 0 or 1 or 2 or 3 or 4 or 5 or 6 |-- ");
			}
		}while(take != 0);
		

	}

}
