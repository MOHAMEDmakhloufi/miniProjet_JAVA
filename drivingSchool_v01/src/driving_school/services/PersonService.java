package driving_school.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import driving_school.connections_with_data_file.ConnectionWithDataPerson;
import driving_school.entites.Candidate;
import driving_school.entites.Instructor;
import driving_school.entites.Person;


public class PersonService {
	/**
	 * Candidate management (crud)
	 * @throws Exception 
	 */
	public static void managment(String nameOfClass) throws Exception {
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
			take = sc.nextInt();
			sc.nextLine();
			
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
	 * add a Person by keyboard input
	 * @throws ParseException 
	 */
	public static void  add(String nameOfClass) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t-->Add New "+nameOfClass+" <--\n");
		//input Cin
		System.out.print("\t\t\tCin_number --> ");
		int cin= sc.nextInt();
		sc.nextLine();
		//input Name
		System.out.print("\t\t\tName 	   --> ");
		String name= sc.nextLine();
		//input Phone
		System.out.print("\t\t\tphone_Number	   --> ");
		long phoneNumber= sc.nextLong();
		sc.nextLine();
		//input email
		System.out.print("\t\t\tEmailAddress 	   --> ");
		String email= sc.nextLine();
		//input dateOfBirthday
		System.out.println("\t\t\tdateOfBirthday : ");
		
		System.out.print("\t\t\tDay        --> ");
		int day= sc.nextInt();
		sc.nextLine();
		System.out.print("\t\t\tMonth      --> ");
		int month= sc.nextInt();
		sc.nextLine();
		System.out.print("\t\t\tYear       --> ");
		int year= sc.nextInt();
		sc.nextLine();
		
		//input type A: moto, B: car, C: truck
		String type="";
		if(nameOfClass.equals("Candidate")) {
			//input type A: moto, B: car, C: truck
			System.out.print("\t\t\ttype  A: moto, B: car, C: truck  --> ");
			type = sc.nextLine();}
		
		//input paidAmount
		System.out.print("\t\t\tAdvence Payment --> ");
		double advencePayment= sc.nextDouble();
		sc.nextLine();
		
		//creat date
		String sDate1=String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year);  
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        
        //creat new person
        Person person;
        if(nameOfClass.equals("Candidate"))
        	person = new Candidate(cin, name, phoneNumber, email, date1, type, advencePayment);
        else
        	person = new Instructor(cin, name, phoneNumber, email, date1, advencePayment);
		
		ConnectionWithDataPerson.add(person);
	}
	
	/**
	 * update a Person by keyboard input
	 * @throws Exception 
	 */
	public static void update(String nameOfClass) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t-->Update "+nameOfClass+" <--\n");
		//input Cin
		System.out.print("\t\t\tCin_number --> ");
		int cin= sc.nextInt();
		sc.nextLine();
		Person person = ConnectionWithDataPerson.search(cin, nameOfClass);
		if(person != null) {
			int take_;
			do {
				System.out.println("\t\t\t\t1.Set Cin");
				System.out.println("\t\t\t\t2.Set Name");
				System.out.println("\t\t\t\t3.Set PhoneNumer");
				System.out.println("\t\t\t\t4.Set EmailAddress");
				System.out.println("\t\t\t\t5.Set DateOfBirthday");
				System.out.println("\t\t\t\t6.Set Category");
				System.out.println("\t\t\t\t7.Set PaidAmount");
				System.out.println("\t\t\t\t0.FINISH");
				System.out.print("\t\t--> ");
				take_ = sc.nextInt();
				sc.nextLine();
				
				switch(take_) {
					case 1 : 
						//input Cin
						System.out.print("\t\t\t\t\tCin_number --> ");
						int newCin= sc.nextInt();
						sc.nextLine();
						//set
						person.setCin(newCin);
						break;
					case 2 :
						//input Name
						System.out.print("\t\t\t\t\tName 	   --> ");
						String name= sc.nextLine();
						//set
						person.setName(name);
						break;
					case 3 :
						//input Phone
						System.out.print("\t\t\t\t\tphone_Number--> ");
						long phoneNumber= sc.nextLong();
						sc.nextLine();
						//set
						person.setPhoneNumber(phoneNumber);
						break;
					case 4 :
						//input email
						System.out.print("\t\t\t\t\tEmailAddress--> ");
						String email= sc.nextLine();
						//set
						person.setEmailAddress(email);
						break;
					case 5 :
						//input dateOfBirthday
						System.out.println("\t\t\t\t\tdateOfBirthday : ");
						
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
				        person.setDateOfBirthday(date1);
						break;
					case 6 :
						//input type A: moto, B: car, C: truck
						System.out.print("\t\t\t\t\ttype  A: moto, B: car, C: truck  --> ");
						String type = sc.nextLine();
						//set
						((Candidate)person).setCategory(type);
						break;
					case 7 :
						//input paidAmount
						System.out.print("\t\t\t\t\tpaidAmount --> ");
						double paidAmount= sc.nextDouble();
						sc.nextLine();
						//set
						person.setPaidAmount(paidAmount + person.getPaidAmount());
						break;
					case 0 :
						break;
					default : 
						System.out.println("\t\t\t\t--|take 0 or 1 or 2 or 3 or 4 or 5 or 6 or 7|-- ");
				}
			}while(take_ !=0);
			//save this update
			ConnectionWithDataPerson.updateByCin(cin, person );
		}else
			System.out.println("\t\t\t\t***notFound***");
	}
	
	/**
	 * Delete a Person by keyboard input
	 */
	
	public static void delete(String nameOfClass) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t-->Delete "+nameOfClass+" <--\n");
		//input Cin
		System.out.print("\t\t\tCin_number --> ");
		int cin= sc.nextInt();
		sc.nextLine();
		//delete and test
		if( ConnectionWithDataPerson.delete(cin, nameOfClass) )
			System.out.println("\t\t\t--> delete successfully <--");
		else 
			System.out.println("\t\t\t***erreur***");
	}
	
	/**
	 * search a Person by keyboard input
	 */
	public static void search(String nameOfClass) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t-->Search "+nameOfClass+" <--\n");
		//input Cin
		System.out.print("\t\t\tCin_number --> ");
		int cin= sc.nextInt();
		sc.nextLine();
		Person person = ConnectionWithDataPerson.search(cin, nameOfClass);
		if(person != null)
			System.out.println(person);
		else
			System.out.println("\t\t\t\t***notFound***");
	}
	
	/**
	 * consult all the Persons
	 */
	public static void getAll(String nameOfClass) {
		System.out.println("\t\t\t-->consult all the "+nameOfClass+"s <--\n");
		
		ArrayList<Person> personList = ConnectionWithDataPerson.getAll(nameOfClass);
		if(personList != null)
			for (Person can : personList)
				System.out.println(can);
		else
			System.out.println("\t\t\t\t***File Empty***");
	}
}
