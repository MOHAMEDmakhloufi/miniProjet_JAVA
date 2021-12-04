package gui;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import date.MyDate;
import entities.Candidate;
import entities.Instructor;
import entities.Person;
import entities.Vehicle;
import services.VehicleService;
import entities.Car;
import entities.Truck;
import entities.Moto;



public class VehicleGUI {
	
	public static int managmentChoices(String nameOfClass) {
		Scanner sc = new Scanner(System.in);
		int take;
		System.out.println("\t\t1.Add "+nameOfClass);
		System.out.println("\t\t2.Update "+nameOfClass);
		System.out.println("\t\t3.Delete "+nameOfClass);
		System.out.println("\t\t4.Search "+nameOfClass);
		System.out.println("\t\t5.consult all the "+nameOfClass+"s");
		System.out.println("\t\t0.RETOUR");
		System.out.print("\t\t--> ");
		take = sc.nextInt();
		sc.nextLine();
		return take;
	}
	
	//format date dd/mm/yyyy
	private static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}
	public static int displayChoice() {
		Scanner sc = new Scanner(System.in);
		int take;
		System.out.println("\n\t\t\t\t\t1.Display All Seance Driving");
		System.out.println("\t\t\t\t\t2.Display Next SeanceDriving");
		System.out.println("\t\t\t\t\t3.Display Previous SeanceDriving");
		System.out.println("\t\t\t\t\t4.Display ExamsDriving");
		System.out.println("\t\t\t\t\t0.FINISH");
		System.out.print("\t\t\t\t\t--> ");
		take = sc.nextInt();
		sc.nextLine();
		return take;
	}	
	public static void displayOne(Vehicle v) {
		DecimalFormat df = new DecimalFormat("0.00");
		System.out.println(  "\t\t\t\tId              : "+v.getId()
				+"\n\t\t\t\tName             : "+v.getName()
				+"\n\t\t\t\ttheDateOfPurchase : "+formatDate(v.getTheDateOfPurchase()));
		double totalMilege = VehicleService.totalMileage(v.getId());
		System.out.println("\n\t\t\t\ttotalMileage      : "+totalMilege+" Km"
				+"\n\t\t\t\tmileageRemains    : "+VehicleService.mileageRemains(totalMilege)+" Km");
	}
	public static void displayAll(ArrayList<Vehicle> listVehicle) {
		DecimalFormat df = new DecimalFormat("0.00");
		if(listVehicle.isEmpty())
				System.out.println("\t\t\t\t/!\\_File Empty_/!\\");
		else
			for(Vehicle v : listVehicle)
				System.out.println(  "\t\t\t\tId              : "+v.getId()
				+"\n\t\t\t\tName             : "+v.getName()
				+"\n\t\t\t\ttheDateOfPurchase : "+formatDate(v.getTheDateOfPurchase()));
	}
	
	public static Vehicle inputVehicle(String nameOfClass) {
		
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
        	return new Car(id, name, date1);
        else if(nameOfClass.equals("Moto"))
        	return new Moto(id, name, date1);
        else 
        	return new Truck(id, name, date1);
		
	}
	public static Vehicle inputUpdatePerson(String nameOfClass, Vehicle vehicle) {
		Scanner sc = new Scanner(System.in);
		int take;
		System.out.println("\t\t\t-->Update "+nameOfClass+" <--\n");

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
		return vehicle;
	}
	public static String deleteMsg(String nameOfClass, Vehicle vehicle) {
		System.out.println("\t\t\t-->Delete "+nameOfClass+" <--\n");
		System.out.print("\t\t\t\tAre you sure to delete <"+vehicle.getName()+"> Y/N -->");
		return PersonGUI.inputCheck();
	
	}
	//output not exist
	public static void notFound() {
		System.out.println("\t\t\t/!\\_VEHICLE NOT EXIST/!\\");
	}
	public static void alreadyExist(String nameOfClass, Vehicle v) {
		System.out.println("\t\t\t/!\\_"+nameOfClass+" < "+v.getName() +" > already exists_/!\\");
	}
	
	//input Id
	public static int inputId() {
		Scanner sc = new Scanner(System.in);
		String id;
		Exception e;
		do {
			System.out.print("\t\t\tId --> ");
			id= sc.nextLine();
	
			e = null;
			try {
				Integer.parseInt(id);
			}catch(NumberFormatException e1) {
				e = e1;
			}
			
			if (id.length() < 5 || e != null)
				System.out.println("\t\t\t/!\\_INCORRECT ID_/!\\");
			
		}while(id.length() < 5 || e != null);
		
		return Integer.parseInt(id);
	}
	
	//input Name
	public static String inputName() {
		Scanner sc = new Scanner(System.in);
		String name;
		do {
			System.out.print("\t\t\tName 	   --> ");
			name= sc.nextLine();
			if (name.length() < 2)
				System.out.println("\t\t\t/!\\_INCORRECT NAME_/!\\");
		}while(name.length() < 2);
		
		return name;
	}
	
	//input theDateOfPurchase
	public static Date inputDateOfPurchase() {
		Scanner sc = new Scanner(System.in);
		boolean test = false;
		Date date1 = null;
		int day=0, month=0, year=0;
		Exception e;
		int currentYear;
		do {
			System.out.println("\t\t\ttheDateOfPurchase : ");
			try {
			System.out.print("\t\t\t\t\tDay        --> ");
			day= sc.nextInt();
			sc.nextLine();
			System.out.print("\t\t\t\t\tMonth      --> ");
			month= sc.nextInt();
			sc.nextLine();
			System.out.print("\t\t\t\t\tYear       --> ");
			year= sc.nextInt();
			sc.nextLine();
			e = null;
			}catch(Exception e1) {
				e = e1;
				sc.nextLine();
			}
			//creat date
			String sDate1=String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year);  
	        
			try {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	            df.setLenient(false);
	            date1 =df.parse(sDate1);
				test = true;
			} catch (ParseException e2) {
				test = false;
			}
			//current year
			currentYear = MyDate.theCurrentYear();
			if (!test || e !=null )
				System.out.println("\t\t\t/!\\_INCORRECT theDateOfPurchase_/!\\");
		}while(!test || e !=null );
		
		return date1;
        
	}
	
	public static String inputCheck() {
		Scanner sc = new Scanner(System.in);
		String check;
		do {
			check= sc.nextLine();
			check= check.toUpperCase();
			if (check.length() != 1 || (!check.startsWith("Y") && !check.startsWith("N")))
				System.out.println("\t\t\t/!\\_INCORRECT CHECK_/!\\");
		}while(check.length() != 1 || (!check.startsWith("Y") && !check.startsWith("N")));
		
		return check;
	}
}
