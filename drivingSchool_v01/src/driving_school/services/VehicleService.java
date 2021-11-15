package driving_school.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import driving_school.connections_with_data_file.ConnectionWithDataPerson;
import driving_school.connections_with_data_file.ConnectionWithDataVehicle;
import driving_school.entites.Buisiness;
import driving_school.entites.Candidate;
import driving_school.entites.Car;
import driving_school.entites.Instructor;
import driving_school.entites.Moto;
import driving_school.entites.Person;
import driving_school.entites.Truck;
import driving_school.entites.Vehicle;
import driving_school.gui.PersonGUI;
import driving_school.gui.VehicleGUI;

public class VehicleService {
	
	/**
	 * vehicle management (crud)
	 */
	public static void managment(String nameOfClass) {
		Scanner sc = new Scanner(System.in);
		int take;
		
		do {
			System.out.println("\t\t1.Add "+nameOfClass);
			System.out.println("\t\t2.Update "+nameOfClass);
			System.out.println("\t\t3.Delete "+nameOfClass);
			System.out.println("\t\t4.Search "+nameOfClass);
			System.out.println("\t\t5.consult all the "+nameOfClass+"s");
			System.out.println("\t\t0.RETOUR");
			System.out.print("\t\t--> ");
			take = Integer.parseInt(sc.nextLine());
			
			switch(take) {
				case 1 : 
					add(nameOfClass);
					break;
				case 2 :
					update(nameOfClass);
					break;
				case 3 :
					delete(nameOfClass);
					break;
				case 4 :
					search(nameOfClass);
					break;
				case 5 :
					getAll(nameOfClass);
					break;
				case 0 :
					break;
				default : 
					System.out.println("\t\t--|take 0 or 1 or 2 or 3 or 4 |-- ");
			}
		}while(take != 0);
	}
	/**
	 * add a Vehicle by keyboard input
	 * @throws ParseException 
	 */
	public static void  add(String nameOfClass) {
		
		Scanner sc = new Scanner(System.in);

		
		System.out.println("\t\t\t-->Add New "+nameOfClass+" <--\n");
		//input Id
		
		int id= VehicleGUI.inputId();
		//input Name
		
		String name= VehicleGUI.inputName();
		//input theDateOfPurchase

        Date date1= VehicleGUI.inputDateOfPurchase();
        
        //creat new person
        Vehicle vehicle;
        if(nameOfClass.equals("Car"))
        	vehicle = new Car(id, name, date1);
        else if(nameOfClass.equals("Moto"))
        	vehicle = new Moto(id, name, date1);
        else 
        	vehicle = new Truck(id, name, date1);
		ConnectionWithDataVehicle.add(vehicle);
	}
	
	/**
	 * update a Vehicle by keyboard input
	 * @throws Exception 
	 */
	public static void update(String nameOfClass) {
		
		Scanner sc = new Scanner(System.in);
		int take;
		

		System.out.println("\t\t\t-->Update "+nameOfClass+" <--\n");
		
		//input Id
		int id= VehicleGUI.inputId();
		
		Vehicle vehicle = ConnectionWithDataVehicle.search(id, nameOfClass);
		if(vehicle != null) {
			int take_;
			do {
				System.out.println("\t\t\t\t.Set Id");
				System.out.println("\t\t\t\t2.Set Name");
				System.out.println("\t\t\t\t3.Set TheDateOfPurchase");
				System.out.println("\t\t\t\t0.FINISH");
				System.out.print("\t\t\t\t--> ");
				take_ = sc.nextInt();
				sc.nextLine();
				
				switch(take_) {
					case 1 : 
						//input Id
						System.out.print("\t\t");
						int newId= VehicleGUI.inputId();
						//set
						vehicle.setId(newId);
						break;
					case 2 :
						//input Name
						System.out.print("\t\t");
						String name=  VehicleGUI.inputName();
						//set
						vehicle.setName(name);
						break;
					case 3 :
						//input theDateOfPurchase  
				        Date date1= VehicleGUI.inputDateOfPurchase();
				        //set
				        vehicle.setTheDateOfPurchase(date1);
						break;
					case 0 :
						break;
					default : 
						System.out.println("\t\t\t\t--|take 0 or 1 or 2 or 3 |-- ");
				}
			}while(take_ !=0);
			//save this update
			ConnectionWithDataVehicle.updateById(id, vehicle);
		}else
			System.out.println("\t\t\t\t/!\\_notFound_/!\\");
	}
	
	/**
	 * Delete a Vehicle by keyboard input
	 */
	
	public static void delete(String nameOfClass) {
		
		Scanner sc = new Scanner(System.in);

		
		System.out.println("\t\t\t-->Delete "+nameOfClass+" <--\n");
		//input Id
		int id= VehicleGUI.inputId();
		
        System.out.print("\t\t\tAre you sure to delete this Seance Y/N -->");
		String check =VehicleGUI.inputCheck();
		if (check.equals("Y")) {
			//delete and test
			if( ConnectionWithDataVehicle.delete(id, nameOfClass) )
				System.out.println("\t\t\t--> delete successfully <--");
			else 
				System.out.println("\t\t\t/!\\_DELETE erreur_/!\\");
		}
	}
	
	/**
	 * search a Vehicle by keyboard input
	 */
	public static void search(String nameOfClass) {
		
		Scanner sc = new Scanner(System.in);
		int take;
		
		System.out.println("\t\t\t-->Search "+nameOfClass+" <--\n");
		//input Id
		int id= VehicleGUI.inputId();
		
		Vehicle vehicle = ConnectionWithDataVehicle.search(id, nameOfClass);
		if(vehicle != null) {
			System.out.println(vehicle);
		
			int take_;
			do {
				System.out.println("\t\t\t\t\t1.Display All Seance Driving");
				System.out.println("\t\t\t\t\t2.Display Next SeanceDriving");
				System.out.println("\t\t\t\t\t3.Display Previous SeanceDriving");
				System.out.println("\t\t\t\t\t0.FINISH");
				System.out.print("\t\t\t\t--> ");
				take_ = sc.nextInt();
				sc.nextLine();
				
				switch(take_) {
					case 1 :
						vehicle.consultAllSeanceDriving();
						break;
					case 2 :
						vehicle.consultNextSeanceDriving();
						break;
					case 3 :
						vehicle.consultPreviousSeanceDriving();
					case 0 :
						break;
					default : 
						System.out.println("\t\t\t\t--|take 0 or 1 or 2 or 3 |-- ");
				}
			}while(take_ !=0);
		}else
			System.out.println("\t\t\t/!\\_notFound_/!\\");
	}
	
	/**
	 * consult all the vehicles
	 */
	public static void getAll(String nameOfClass) {
		
		Scanner sc = new Scanner(System.in);
		int take;

		System.out.println("\t\t\t-->consult all the "+nameOfClass+"s <--\n");
		
		ArrayList<Vehicle> vehicleList = ConnectionWithDataVehicle.getAll(nameOfClass);
		if(vehicleList != null)
			for (Vehicle veh : vehicleList)
				System.out.println(veh);
		else
			System.out.println("\t\t\t/!\\_File Empty_/!\\");
	}
	
	
}
