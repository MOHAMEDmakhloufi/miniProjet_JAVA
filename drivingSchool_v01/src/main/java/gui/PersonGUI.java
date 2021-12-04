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
import services.PersonService;

public class PersonGUI {
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
	public static int displayChoice(String nameOfClass) {
		Scanner sc = new Scanner(System.in);
		int take;
		System.out.println("\t\t\t\t\t1.Display All SeanceCode");
		System.out.println("\t\t\t\t\t2.Display Next SeanceCode");
		System.out.println("\t\t\t\t\t3.Display Previous SeanceCode");
		if(nameOfClass.equals("Candidate"))
			System.out.println("\t\t\t\t\t4.Display ExamCode");
		System.out.println("\t\t\t\t\t5.Display All Seance Driving");
		System.out.println("\t\t\t\t\t6.Display Next SeanceDriving");
		System.out.println("\t\t\t\t\t7.Display Previous SeanceDriving");
		System.out.println("\t\t\t\t\t8.Display ExamDriving");
		System.out.println("\t\t\t\t\t0.FINISH");
		System.out.print("\t\t\t\t--> ");
		take = sc.nextInt();
		sc.nextLine();
		return take;
	}	
	public static void displayOne(Person p) {
		DecimalFormat df = new DecimalFormat("0.00");
		if (p instanceof Candidate)
			System.out.println("\t\t\t\tCategory	 : "+ ((Candidate) p).getCategory());
		System.out.println( "\t\t\t\tCin              : "+p.getCin()
				+"\n\t\t\t\tName             : "+p.getName()
				+"\n\t\t\t\tDate Of Birthday : "+formatDate(p.getDateOfBirthday())
				+"\n\t\t\t\tPhone number     : "+p.getPhoneNumber()
				+"\n\t\t\t\tEmail Address    : "+p.getEmailAddress()
				+"\n\t\t\t\tPaidAmount	 : "+  df.format(p.getPaidAmount())+" dt");
		double total = (p instanceof Candidate)? PersonService.totalAmountForCandidate(p.getCin(), ((Candidate) p).getCategory()) : PersonService.totalAmountForInstructor(p.getCin());
		System.out.println("\n\t\t\t\tTotalAmount	 : "+ df.format(total)+" dt"
				+"\n\t\t\t\tRemainingAmount	 : "+ df.format((total - p.getPaidAmount()))+" dt\n\n");
		
	}
	public static void displayAll(ArrayList<Person> listPerson) {
		DecimalFormat df = new DecimalFormat("0.00");
		if(listPerson.isEmpty())
				System.out.println("\t\t\t\t/!\\_File Empty_/!\\");
		else
			for(Person p : listPerson) {
				if (p instanceof Candidate)
					System.out.println("\t\t\t\tCategory	 : "+ ((Candidate) p).getCategory());
				System.out.println( "\t\t\t\tCin              : "+p.getCin()
						+"\n\t\t\t\tName             : "+p.getName()
						+"\n\t\t\t\tDate Of Birthday : "+formatDate(p.getDateOfBirthday())
						+"\n\t\t\t\tPhone number     : "+p.getPhoneNumber()
						+"\n\t\t\t\tEmail Address    : "+p.getEmailAddress()
						+"\n\t\t\t\tPaidAmount	 : "+  df.format(p.getPaidAmount())+" dt");
			}
	}
	
	public static Person inputPerson(String nameOfClass) {
		System.out.println("\t\t\t-->Add New "+nameOfClass+" <--\n");
		
		//input Cin
		long cin= inputCin();
		//input Name
		String name= PersonGUI.inputName();
		
		//input Phone
		long phoneNumber= PersonGUI.inputPhoneNumber();

		//input email

		String email= PersonGUI.inputEmail();
		
		//input type A: moto, B: car, C: truck
		String type="";
		if(nameOfClass.equals("Candidate")) {
			type = PersonGUI.inputCategory();
			}
		
		//input paidAmount
		double advencePayment= PersonGUI.inputPaidAmount();

		
		//creat DateOfBirthday
        Date date1 = PersonGUI.inputDateOfBirthday();

        
        //creat new person
        return (nameOfClass.equals("Candidate"))? new Candidate(cin, name, phoneNumber, email, date1, type, advencePayment) : 
        													new Instructor(cin, name, phoneNumber, email, date1, advencePayment);
	}
	public static Person inputUpdatePerson(String nameOfClass, Person person) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t-->Update "+nameOfClass+" <--\n");
		if(person != null) {
			int take_;
			do {
				System.out.println("\t\t\t\t1.Set Name");
				System.out.println("\t\t\t\t2.Set PhoneNumer");
				System.out.println("\t\t\t\t3.Set EmailAddress");
				System.out.println("\t\t\t\t4.Set DateOfBirthday");
				if(nameOfClass.equals("Candidate"))
					System.out.println("\t\t\t\t5.Set Category");
				System.out.println("\t\t\t\t6.Set PaidAmount");
				System.out.println("\t\t\t\t0.FINISH");
				System.out.print("\t\t\t\t--> ");
				take_ = sc.nextInt();
				sc.nextLine();
				
				switch(take_) {
					case 1 :
						//input Name
						System.out.print("\t\t");
						String name= PersonGUI.inputName();
						//set
						person.setName(name);
						break;
					case 2 :
						//input Phone
						System.out.print("\t\t");
						long phoneNumber= PersonGUI.inputPhoneNumber();
						//set
						person.setPhoneNumber(phoneNumber);
						break;
					case 3 :
						//input email
						System.out.print("\t\t");
						String email= PersonGUI.inputEmail();
						//set
						person.setEmailAddress(email);
						break;
					case 4 :
						//creat DateOfBirthday
						System.out.print("\t\t");
				        Date date1 = PersonGUI.inputDateOfBirthday();
				        //set
				        person.setDateOfBirthday(date1);
						break;
					case 5 :
						if(nameOfClass.equals("Candidate")) {
							//input type A: moto, B: car, C: truck
							String type="";
							System.out.print("\t\t");
							type = PersonGUI.inputCategory();
							//set
							((Candidate)person).setCategory(type);
						}
						break;
					case 6 :
						//input paidAmount
						System.out.print("\t\t");
						double advencePayment= PersonGUI.inputPaidAmount();
						//set
						person.setPaidAmount(advencePayment + person.getPaidAmount());
						break;
					case 0 :
						break;
					default : 
						System.out.println("\t\t\t\t--|take 0 or 1 or 2 or 3 or 4 or 5 or 6 or 7|-- ");
				}
			}while(take_ !=0);
		}
		return person;
	}
	public static String deleteMsg(String nameOfClass, Person person) {
		System.out.println("\t\t\t-->Delete "+nameOfClass+" <--\n");
		System.out.print("\t\t\t\tAre you sure to delete <"+person.getName()+"> Y/N -->");
		return PersonGUI.inputCheck();
	
	}
	//output not exist
	public static void notFound() {
		System.out.println("\t\t\t/!\\_PERSON NOT EXIST/!\\");
	}
	public static void alreadyExist(String nameOfClass, Person p) {
		System.out.println("\t\t\t/!\\_"+nameOfClass+" < "+p.getName() +" > already exists_/!\\");
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
