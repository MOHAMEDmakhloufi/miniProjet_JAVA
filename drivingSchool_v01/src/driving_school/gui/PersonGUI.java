package driving_school.gui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import driving_school.date.MyDate;

public class PersonGUI {
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
	//input Cin
	public static long inputCin() {
		Scanner sc = new Scanner(System.in);
		String cin;
		Exception e;
		do {
			System.out.print("\t\t\tCin	   --> ");
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
	//input phone number
	public static long inputPhoneNumber() {
		Scanner sc = new Scanner(System.in);
		String phone;
		Exception e;
		do {
			System.out.print("\t\t\tPhone Number	   --> ");
			phone= sc.nextLine();
	
			e = null;
			try {
				Long.parseLong(phone);
			}catch(NumberFormatException e1) {
				e = e1;
			}
			
			if (phone.length() != 8 || e != null)
				System.out.println("\t\t\t/!\\_INCORRECT PHONE NUMBER_/!\\");
			
		}while(phone.length() != 8 || e != null);
		
		return Long.parseLong(phone);
	}
	//input email 
	public static String inputEmail() {
		Scanner sc = new Scanner(System.in);
		String email;
		int index1, index2;
		do {
			System.out.print("\t\t\tEmail Address xxxXXX@xxx.XXX --> ");
			email= sc.nextLine();
			index1 = email.indexOf('@');
			index2 = email.lastIndexOf('.');
			if (index1 == -1 || email.indexOf('@', index1 + 1) != -1 || index2 == -1 || index1<2 || (index2 - index1)<3 || (index2 + 2) > email.length())
				System.out.println("\t\t\t/!\\_INCORRECT @EMAIL_/!\\");
		}while(index1 == -1 || email.indexOf('@', index1 + 1) != -1 || index2 == -1 || email.indexOf('.', index2 + 1) != -1 || index1<2 || (index2 - index1)<3 || (index2 + 2) > email.length());
		
		return email;
	}
	
	public static Date inputDateOfBirthday() {
		Scanner sc = new Scanner(System.in);
		boolean test = false;
		Date date1 = null;
		int day=0, month=0, year=0;
		Exception e;
		int currentYear;
		do {
			System.out.println("\t\t\tdateOfBirthday : ");
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
			if (!test || e !=null || (currentYear - year) < 16)
				System.out.println("\t\t\t/!\\_INCORRECT DATE OF BIRTHDAY_/!\\");
		}while(!test || e !=null || (currentYear - year) < 16);
		
		return date1;
        
	}
	//input paidAmount
	public static double inputPaidAmount() {
		Scanner sc = new Scanner(System.in);
		Exception e;
		double advencePayment= 0;
		do {
			e = null;
			System.out.print("\t\t\tAdvence Payment --> ");
			try {
				advencePayment= sc.nextDouble();
			}catch(Exception e1) {
				e = e1;
			}finally {
				sc.nextLine();
			}
			if (e !=null)
				System.out.println("\t\t\t/!\\_INCORRECT ADVENCE PAYMENT_/!\\");
		}while(e != null);
		
		return advencePayment;
	}
	
	//input type A: moto, B: car, C: truck
	public static String inputCategory() {
		Scanner sc = new Scanner(System.in);
		String category;
		do {
			System.out.print("\t\t\tCategory A: moto, B: car, C: truck	   --> ");
			category= sc.nextLine();
			category= category.toUpperCase();
			if (category.length() != 1 || (!category.startsWith("A") && !category.startsWith("B") && !category.startsWith("C")))
				System.out.println("\t\t\t/!\\_INCORRECT CATEGORY_/!\\");
		}while(category.length() != 1 || (!category.startsWith("A") && !category.startsWith("B") && !category.startsWith("C")));
		
		return category;
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
