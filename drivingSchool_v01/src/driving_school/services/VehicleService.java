package driving_school.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import driving_school.connections_with_data_file.ConnectionWithDataPerson;
import driving_school.connections_with_data_file.ConnectionWithDataVehicle;
import driving_school.entites.Candidate;
import driving_school.entites.Car;
import driving_school.entites.Instructor;
import driving_school.entites.Moto;
import driving_school.entites.Person;
import driving_school.entites.Truck;
import driving_school.entites.Vehicle;

public class VehicleService {
	
	/**
	 * vehicle management (crud)
	 */
	public static void managment() throws Exception {
		Scanner sc = new Scanner(System.in);
		int take;
		
		do {
			System.out.println("\t\t1.Add vehicle");
			System.out.println("\t\t2.Update vehicle");
			System.out.println("\t\t3.Delete vehicle");
			System.out.println("\t\t4.Search vehicle");
			System.out.println("\t\t5.consult all the vehicles");
			System.out.println("\t\t0.RETOUR");
			System.out.print("\t\t--> ");
			take = Integer.parseInt(sc.nextLine());
			
			switch(take) {
				case 1 : 
					add();
					break;
				case 2 :
					update();
					break;
				case 3 :
					delete();
					break;
				case 4 :
					search();
					break;
				case 5 :
					getAll();
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
	public static void  add() throws Exception {
		String nameOfClass="";
		Scanner sc = new Scanner(System.in);
		int take;
		
		do {
			System.out.println("\t\t\t1.Add Car");
			System.out.println("\t\t\t2.Add Moto");
			System.out.println("\t\t\t3.Add Truck");
			System.out.print("\t\t\t--> ");
			take = Integer.parseInt(sc.nextLine());
			
			switch(take) {
			case 1 : 
				nameOfClass = "Car";
				
				break;
			case 2 :
				nameOfClass = "Moto";
				
				break;
			case 3 :
				nameOfClass = "Truck";
				break;
				default : 
					System.out.println("\t\t\t--|take 0 or 1 or 2 or 3 |-- ");
			}
		}while(take != 1 && take != 2 && take != 3);
		
		System.out.println("\t\t\t\t-->Add New "+nameOfClass+" <--\n");
		//input Id
		System.out.print("\t\t\t\tId --> ");
		int id= sc.nextInt();
		sc.nextLine();
		//input Name
		System.out.print("\t\t\t\tName 	   --> ");
		String name= sc.nextLine();
		//input theDateOfPurchase
		System.out.println("\t\t\t\ttheDateOfPurchase : ");
		
		System.out.print("\t\t\t\tDay        --> ");
		int day= sc.nextInt();
		sc.nextLine();
		System.out.print("\t\t\t\tMonth      --> ");
		int month= sc.nextInt();
		sc.nextLine();
		System.out.print("\t\t\t\tYear       --> ");
		int year= sc.nextInt();
		sc.nextLine();
		
		
		//creat date
		String sDate1=String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year);  
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        
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
	public static void update() throws Exception {
		String nameOfClass="";
		Scanner sc = new Scanner(System.in);
		int take;
		
		do {
			System.out.println("\t\t\t1.Update Car");
			System.out.println("\t\t\t2.Update Moto");
			System.out.println("\t\t\t3.Update Truck");
			System.out.print("\t\t\t--> ");
			take = Integer.parseInt(sc.nextLine());
			
			switch(take) {
			case 1 : 
				nameOfClass = "Car";
				take = 0;
				break;
			case 2 :
				nameOfClass = "Moto";
				take = 0;
				break;
			case 3 :
				nameOfClass = "Truck";
				take = 0;
				break;
				default : 
					System.out.println("\t\t\t--|take 0 or 1 or 2 or 3 |-- ");
			}
		}while(take != 0);
		System.out.println("\t\t\t\t-->Update "+nameOfClass+" <--\n");
		//input id
		System.out.print("\t\t\t\tId --> ");
		int id= sc.nextInt();
		sc.nextLine();
		Vehicle vehicle = ConnectionWithDataVehicle.search(id, nameOfClass);
		if(vehicle != null) {
			int take_;
			do {
				System.out.println("\t\t\t\t1.Set Id");
				System.out.println("\t\t\t\t2.Set Name");
				System.out.println("\t\t\t\t3.Set TheDateOfPurchase");
				System.out.println("\t\t\t\t0.FINISH");
				System.out.print("\t\t\t\t--> ");
				take_ = sc.nextInt();
				sc.nextLine();
				
				switch(take_) {
					case 1 : 
						//input Id
						System.out.print("\t\t\t\t\tId --> ");
						int newId= sc.nextInt();
						sc.nextLine();
						//set
						vehicle.setId(newId);
						break;
					case 2 :
						//input Name
						System.out.print("\t\t\t\t\tName 	   --> ");
						String name= sc.nextLine();
						//set
						vehicle.setName(name);
						break;
					case 3 :
						//input theDateOfPurchase
						System.out.println("\t\t\t\t\theDateOfPurchase : ");
						
						System.out.print("\t\t\t\t\tDay        --> ");
						int day= sc.nextInt();
						sc.nextLine();
						System.out.print("\t\t\t\t\tMonth      --> ");
						int month= sc.nextInt();
						sc.nextLine();
						System.out.print("\t\t\t\t\tYear       --> ");
						int year= sc.nextInt();
						sc.nextLine();
						//creat date
						String sDate1=String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year);  
				        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
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
			System.out.println("\t\t\t\t***notFound***");
	}
	
	/**
	 * Delete a Vehicle by keyboard input
	 */
	
	public static void delete() {
		String nameOfClass="";
		Scanner sc = new Scanner(System.in);
		int take;
		
		do {
			System.out.println("\t\t\t1.Delete Car");
			System.out.println("\t\t\t2.Delete Moto");
			System.out.println("\t\t\t3.Delete Truck");
			System.out.println("\t\t\t0.RETOUR");
			System.out.print("\t\t\t--> ");
			take = Integer.parseInt(sc.nextLine());
			
			switch(take) {
			case 1 : 
				nameOfClass = "Car";
				
				break;
			case 2 :
				nameOfClass = "Moto";
				
				break;
			case 3 :
				nameOfClass = "Truck";
				break;
				default : 
					System.out.println("\t\t\t--|take 0 or 1 or 2 or 3 |-- ");
			}
		}while(take != 1 && take != 2 && take != 3);
		
		System.out.println("\t\t\t\t-->Delete "+nameOfClass+" <--\n");
		//input Id
		System.out.print("\t\t\t\tId --> ");
		int id= sc.nextInt();
		sc.nextLine();
		//delete and test
		if( ConnectionWithDataVehicle.delete(id, nameOfClass) )
			System.out.println("\t\t\t\t--> delete successfully <--");
		else 
			System.out.println("\t\t\t\t***erreur***");
	}
	
	/**
	 * search a Vehicle by keyboard input
	 */
	public static void search() {
		String nameOfClass="";
		Scanner sc = new Scanner(System.in);
		int take;
		
		do {
			System.out.println("\t\t\t1.search Car");
			System.out.println("\t\t\t2.search Moto");
			System.out.println("\t\t\t3.search Truck");
			System.out.println("\t\t\t0.RETOUR");
			System.out.print("\t\t\t--> ");
			take = Integer.parseInt(sc.nextLine());
			
			switch(take) {
			case 1 : 
				nameOfClass = "Car";
				
				break;
			case 2 :
				nameOfClass = "Moto";
				
				break;
			case 3 :
				nameOfClass = "Truck";
				break;
				default : 
					System.out.println("\t\t\t--|take 0 or 1 or 2 or 3 |-- ");
			}
		}while(take != 1 && take != 2 && take != 3);
		
		System.out.println("\t\t\t\t-->Search "+nameOfClass+" <--\n");
		//input Id
		System.out.print("\t\t\t\tId --> ");
		int id= sc.nextInt();
		sc.nextLine();
		Vehicle vehicle = ConnectionWithDataVehicle.search(id, nameOfClass);
		if(vehicle != null)
			System.out.println(vehicle);
		else
			System.out.println("\t\t\t\t***notFound***");
	}
	
	/**
	 * consult all the vehicles
	 */
	public static void getAll() {
		String nameOfClass="";
		Scanner sc = new Scanner(System.in);
		int take;
		
		do {
			System.out.println("\t\t\t1.consult all Car");
			System.out.println("\t\t\t2.consult all Moto");
			System.out.println("\t\t\t3.consult all Truck");

			System.out.print("\t\t\t--> ");
			take = Integer.parseInt(sc.nextLine());
			
			switch(take) {
			case 1 : 
				nameOfClass = "Car";
				
				break;
			case 2 :
				nameOfClass = "Moto";
				
				break;
			case 3 :
				nameOfClass = "Truck";
				break;
				default : 
					System.out.println("\t\t\t--|take 0 or 1 or 2 or 3 |-- ");
			}
		}while(take != 1 && take != 2 && take != 3);
		System.out.println("\t\t\t\t-->consult all the "+nameOfClass+"s <--\n");
		
		ArrayList<Vehicle> vehicleList = ConnectionWithDataVehicle.getAll(nameOfClass);
		if(vehicleList != null)
			for (Vehicle veh : vehicleList)
				System.out.println(veh);
		else
			System.out.println("\t\t\t\t***File Empty***");
	}
}
