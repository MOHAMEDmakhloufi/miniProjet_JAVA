package driving_school.gui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import driving_school.date.MyDate;

	public class VehicleGUI {
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
