package driving_school.gui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import driving_school.connections_with_data_file.ConnectionWithDataPerson;
import driving_school.connections_with_data_file.ConnectionWithDataVehicle;
import driving_school.date.MyDate;
import driving_school.entites.Person;
import driving_school.entites.Vehicle;
import driving_school.exception.NotFoundException;
import driving_school.exception.SaturdayException;

public class SeanceGUI {

	//input Cin
		public static long inputCin(String nameOfClass) {
			Scanner sc = new Scanner(System.in);
			String cin;
			Exception e;
			do {
				System.out.print("\t\t\tCin "+nameOfClass+" --> ");
				cin= sc.nextLine();
		
				e = null;
				try {
					Long.parseLong(cin);
				}catch(NumberFormatException e1) {
					e = e1;
				}
				
				if (cin.length() != 8 || e != null)
					System.out.println("\t\t\t/!\\_INCORRECT CIN_/!\\");
				
			}while(cin.length() != 8 || e != null);
			
			return Long.parseLong(cin);
		}
		
		/**
		 * input Cin and test if person exist or not
		 * @param exceptionMsg
		 * @param nameOfClass
		 * @return
		 * @throws NotFoundException
		 */
		public static long inputCinTest(String exceptionMsg, String nameOfClass) throws NotFoundException {
			Scanner sc = new Scanner(System.in);
			String cin;
			Exception e;
			do {
				System.out.print("\t\t\tCin "+nameOfClass+" --> ");
				cin= sc.nextLine();
		
				e = null;
				try {
					Long.parseLong(cin);
				}catch(NumberFormatException e1) {
					e = e1;
				}
				
				if (cin.length() != 8 || e != null)
					System.out.println("\t\t\t/!\\_INCORRECT CIN_/!\\");
				else {
					Person p = ConnectionWithDataPerson.search(Long.parseLong(cin), nameOfClass);
					if (p == null)
						throw new NotFoundException("\t\t\t/!\\_"+exceptionMsg+"_/!\\");
				}
			}while(cin.length() != 8 || e != null);
			
			return Long.parseLong(cin);
		}
		
		//input idVehicle and test if vehicle exist or not
		public static long inputVehicleTest(String nameOfClass) throws NotFoundException {
			Scanner sc = new Scanner(System.in);
			String vehicle;
			Exception e;
			do {
				System.out.print("\t\t\tidVehicle --> ");
				vehicle= sc.nextLine();
		
				e = null;
				try {
					Long.parseLong(vehicle);
				}catch(NumberFormatException e1) {
					e = e1;
				}
				
				if (vehicle.length() < 5 || e != null)
					System.out.println("\t\t\t/!\\_INCORRECT ID VEHICLE_/!\\");
				else {
					Vehicle v = ConnectionWithDataVehicle.search(Long.parseLong(vehicle), nameOfClass);
					if (v == null)
						throw new NotFoundException("\t\t\t/!\\_VEHICLE NOT FOUND_/!\\");
				}
			}while(vehicle.length() < 5 || e != null);
			
			return Long.parseLong(vehicle);
		}
		
		//input idVehicle
		public static long inputVehicle() {
			Scanner sc = new Scanner(System.in);
			String vehicle;
			Exception e;
			do {
				System.out.print("\t\t\tidVehicle --> ");
				vehicle= sc.nextLine();
		
				e = null;
				try {
					Long.parseLong(vehicle);
				}catch(NumberFormatException e1) {
					e = e1;
				}
				
				if (vehicle.length() < 5 || e != null)
					System.out.println("\t\t\t/!\\_INCORRECT ID VEHICLE_/!\\");
				
			}while(vehicle.length() < 5 || e != null);
			
			return Long.parseLong(vehicle);
		}
		
		//input DateDay
		public static Date inputDateDay() {
			Scanner sc = new Scanner(System.in);
			boolean test = false;
			Date date1 = null;
			int day=0, month=0, year=0;
			Exception e;
			int currentYear;
			do {
				System.out.println("\t\t\tdateDay : ");
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
					System.out.println("\t\t\t/!\\_INCORRECT DATE DAY_/!\\");
			}while(!test || e !=null );
			
			return date1;
	        
		}
		
		//input Date
		public static Date inputDate() {
			Scanner sc = new Scanner(System.in);
			boolean test = false;
			Date date1 = null;
			int day=0, month=0, year=0;
			String hours= null;
			Exception e;
			int currentYear;
			do {
				System.out.println("\t\t\tdate : ");
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
				System.out.print("\t\t\tHours HH:mm    --> ");
				hours= sc.nextLine();
				e = null;
				}catch(Exception e1) {
					e = e1;
					sc.nextLine();
				}
				//creat date
				String sDate1=String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year)+" "+hours;   
		        
				try {
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		            df.setLenient(false);
		            date1 =df.parse(sDate1);
					test = true;
				} catch (ParseException e2) {
					test = false;
				}
				//current year
				currentYear = MyDate.theCurrentYear();
				if (!test || e !=null )
					System.out.println("\t\t\t/!\\_INCORRECT DATE_/!\\");
			}while(!test || e !=null );
			
			return date1;
	        
		}
		
		/**
		 * test if date is Saturday or not
		 * @param date
		 * @throws SaturdayException 
		 */
		public static void testSaturday(Date date) throws SaturdayException {
			SimpleDateFormat sdf = new SimpleDateFormat("E");
			String day= sdf.format(date);
			if (day.equals("sam.") || day.equals("sat."))
				throw new SaturdayException("/!\\_SATURDAY EXCEPTION_//!\\");
		}
		

}
