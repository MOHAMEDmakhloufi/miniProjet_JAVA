package driving_school.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import driving_school.connections_with_data_file.ConnectionWithDataPerson;
import driving_school.entites.Buisiness;
import driving_school.entites.Candidate;
import driving_school.entites.Instructor;
import driving_school.entites.Person;
import driving_school.gui.PersonGUI;

public class PersonService {
	/**
	 * Candidate management (crud)
	 * @throws Exception 
	 */
	public static void managment(String nameOfClass)  {
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
	public static void  add(String nameOfClass) {

		System.out.println("\t\t\t-->Add New "+nameOfClass+" <--\n");
		
		//input Cin
		long cin= PersonGUI.inputCin();
		
		Person p = ConnectionWithDataPerson.search(cin, nameOfClass);
		if(p == null) {
			
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
	        Person person = (nameOfClass.equals("Candidate"))? new Candidate(cin, name, phoneNumber, email, date1, type, advencePayment) : 
	        													new Instructor(cin, name, phoneNumber, email, date1, advencePayment);
			
			ConnectionWithDataPerson.add(person);
		}else
			System.out.println("\t\t\t/!\\_"+nameOfClass+" < "+p.getName() +" > already exists_/!\\");
	}
	
	/**
	 * update a Person by keyboard input
	 * @throws Exception 
	 */
	public static void update(String nameOfClass) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t-->Update "+nameOfClass+" <--\n");
		//input Cin
		long cin= PersonGUI.inputCin();
		
		Person person = ConnectionWithDataPerson.search(cin, nameOfClass);
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
			//save this update
			ConnectionWithDataPerson.updateByCin(cin, person );
		}else
			System.out.println("\t\t\t\t/!\\_notFound_/!\\");
	}
	
	/**
	 * Delete a Person by keyboard input
	 */
	
	public static void delete(String nameOfClass) {
		System.out.println("\t\t\t-->Delete "+nameOfClass+" <--\n");
		
		//input Cin
		System.out.print("\t");
		long cin= PersonGUI.inputCin();
		
		Person person = ConnectionWithDataPerson.search(cin, nameOfClass);
		if(person != null) {
			System.out.print("\t\t\t\tAre you sure to delete <"+person.getName()+"> Y/N -->");
			String check =PersonGUI.inputCheck();
			if (check.equals("Y")) {
				if( ConnectionWithDataPerson.delete(cin, nameOfClass) )
					System.out.println("\t\t\t--> delete successfully <--");
				else 
					System.out.println("\t\t\t/!\\_DELETE ERREUR/!\\");
			}	
		}else
			System.out.println("\t\t\t/!\\_PERSON NOT EXIST/!\\");
	}
	
	/**
	 * search a Person by keyboard input
	 */
	public static void search(String nameOfClass) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t-->Search "+nameOfClass+" <--\n");
		//input Cin
		System.out.print("\t");
		long cin= PersonGUI.inputCin();
		
		Person person = ConnectionWithDataPerson.search(cin, nameOfClass);
		if(person != null) {
			System.out.println(person);
			
			int take_;
			do {
				System.out.println("\t\t\t\t\t1.Display All SeanceCode");
				System.out.println("\t\t\t\t\t2.Display Next SeanceCode");
				System.out.println("\t\t\t\t\t3.Display Previous SeanceCode");
				System.out.println("\t\t\t\t\t4.Display ExamCode");
				System.out.println("\t\t\t\t\t5.Display All Seance Driving");
				System.out.println("\t\t\t\t\t6.Display Next SeanceDriving");
				System.out.println("\t\t\t\t\t7.Display Previous SeanceDriving");
				System.out.println("\t\t\t\t\t8.Display ExamDriving");
				System.out.println("\t\t\t\t\t0.FINISH");
				System.out.print("\t\t\t\t--> ");
				take_ = sc.nextInt();
				sc.nextLine();
				
				switch(take_) {
					case 1 :
						((Buisiness)person).consultAllSeanceCode();
						break;
					case 2 :
						((Buisiness)person).consultNextSeanceCode();
						break;
					case 3 :
						((Buisiness)person).consultPreviousSeanceCode();
						break;
					case 4 :
						if(person instanceof Candidate)
							((Candidate)person).consultExamCode();
						else
							System.out.println("\t\t\t\t--|take 0 or 1 or 2 or 3  or 5 or 6 |-- ");
						break;
					case 5 :
						((Buisiness)person).consultAllSeanceDriving();
						break;
					case 6 :
						((Buisiness)person).consultNextSeanceDriving();
						break;
					case 7 :
						((Buisiness)person).consultPreviousSeanceDriving();
					case 8 :
						((Buisiness)person).consultExamDriving();
						break;
					case 0 :
						break;
					default : 
						System.out.println("\t\t\t\t--|take 0 or 1 or 2 or 3 or 4 or 5 or 6 |-- ");
				}
			}while(take_ !=0);
		}
		else
			System.out.println("\t\t\t\t/!\\_notFound_/!\\");
	}
	
	/**
	 * consult all the Persons
	 */
	public static void getAll(String nameOfClass) {
		System.out.println("\t\t\t-->consult all the "+nameOfClass+"s <--\n");
		
		ArrayList<Person> personList = ConnectionWithDataPerson.getAll(nameOfClass);
		if(personList != null)
			for (Person per : personList)
				System.out.println(per);
		else
		System.out.println("\t\t\t\t/!\\_File Empty_/!\\");
	}
}
