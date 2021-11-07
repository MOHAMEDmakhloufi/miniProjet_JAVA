package driving_school.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import driving_school.connections_with_data_file.ConnectionWithDataPerson;
import driving_school.connections_with_data_file.ConnectionWithDataSeance;
import driving_school.entites.Candidate;
import driving_school.entites.Instructor;
import driving_school.entites.Person;
import driving_school.entites.Seance;
import driving_school.entites.SeanceCode;
import driving_school.entites.SeanceDriving;

public class SeanceService {
	/**
	 * Seance management (crud)
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
	 * add a seance by keyboard input
	 */
	public static void  add(String nameOfClass) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t-->Add New "+nameOfClass+" <--\n");
		//input CinI
		System.out.print("\t\t\tCinInstructor --> ");
		long cinI= sc.nextLong();
		sc.nextLine();
		//input CinC
		System.out.print("\t\t\tCinCandidate --> ");
		long cinC= sc.nextLong();
		sc.nextLine();
		//input idVehicle
		long idVehicle = 0;
		if(nameOfClass.equals("SeanceDriving")) {
			
			System.out.print("\t\t\tidVehicle ");
			idVehicle = sc.nextLong();
			sc.nextLine();}
		//input date
		System.out.println("\t\t\tdate : ");
		
		System.out.print("\t\t\tDay        --> ");
		int day= sc.nextInt();
		sc.nextLine();
		System.out.print("\t\t\tMonth      --> ");
		int month= sc.nextInt();
		sc.nextLine();
		System.out.print("\t\t\tYear       --> ");
		int year= sc.nextInt();
		sc.nextLine();
		System.out.print("\t\t\tHours       --> ");
		String hours= sc.nextLine();
		
		
		//creat date
		String sDate1=String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year)+" "+hours;  
        Date date1 = null;
		try {
			date1 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(sDate1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //creat new Seance
        Seance seance;
        if(nameOfClass.equals("SeanceDriving"))
        	seance = new SeanceDriving(cinI, cinC, date1, idVehicle);
        else
        	seance = new SeanceCode(cinI, cinC, date1);
		
		ConnectionWithDataSeance.add(seance);
	}
	
	/**
	 * update a seance by keyboard input
	 */
	public static void update(String nameOfClass) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t-->Update "+nameOfClass+" <--\n");
		//input CinI
		System.out.print("\t\t\tCin Instructor --> ");
		long cinI= sc.nextLong();
		sc.nextLine();
		
		//input date
		System.out.println("\t\t\tdate : ");
		
		System.out.print("\t\t\tDay        --> ");
		int day= sc.nextInt();
		sc.nextLine();
		System.out.print("\t\t\tMonth      --> ");
		int month= sc.nextInt();
		sc.nextLine();
		System.out.print("\t\t\tYear       --> ");
		int year= sc.nextInt();
		sc.nextLine();
		System.out.print("\t\t\tHours       --> ");
		String hours= sc.nextLine();
		
		
		//creat date
		String sDate1=String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year)+" "+hours;  
        Date date1 = null;
		try {
			date1 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(sDate1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Seance seance = ConnectionWithDataSeance.getByDateAndCinI(date1, cinI, nameOfClass);
		
		if(seance != null) {
			int take_;
			do {
				System.out.println("\t\t\t\t1.Set CinI");
				System.out.println("\t\t\t\t2.Set CinC");
				System.out.println("\t\t\t\t3.Set Date");
				if(nameOfClass.equals("SeanceDriving"))
					System.out.println("\t\t\t\t4.Set IdVehicle");
				System.out.println("\t\t\t\t0.FINISH");
				System.out.print("\t\t\t\t--> ");
				take_ = sc.nextInt();
				sc.nextLine();
				
				switch(take_) {
					case 1 : 
						//input CinI
						System.out.print("\t\t\t\t\tCin Instructor --> ");
						long newCinI= sc.nextLong();
						sc.nextLine();
						//set
						seance.setCinI(newCinI);
						break;
					case 2 :
						//input CinI
						System.out.print("\t\t\t\t\tCin Candidate --> ");
						long newCinC= sc.nextLong();
						sc.nextLine();
						//set
						seance.setCinC(newCinC);
						break;
					case 3 :
						//input date
						System.out.println("\t\t\t\t\tdate : ");
						
						System.out.print("\t\t\t\t\tDay        --> ");
						int newday= sc.nextInt();
						sc.nextLine();
						System.out.print("\t\t\t\t\tMonth      --> ");
						int newmonth= sc.nextInt();
						sc.nextLine();
						System.out.print("\t\t\t\t\tYear       --> ");
						int newyear= sc.nextInt();
						sc.nextLine();
						System.out.print("\t\t\t\t\tHours       --> ");
						String newhours= sc.nextLine();
						
						
						//creat date
						String newsDate1=String.valueOf(newday)+"/"+String.valueOf(newmonth)+"/"+String.valueOf(newyear)+" "+newhours;  
				        Date newdate = null;
						try {
							newdate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(newsDate1);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//set
						seance.setDate(newdate);
						break;
					case 4 : 
						if(nameOfClass.equals("SeanceDriving")) {
							System.out.print("\t\t\t\t\tidVehicle ");
							long newIdVehicle = sc.nextLong();
							sc.nextLine();
							//set
							((SeanceDriving)seance).setIdVehicle(newIdVehicle);
						}
						else System.out.println("\t\t\t\t--|take 0 or 1 or 2 or 3|-- ");
					case 0 :
						break;
					default : 
						System.out.println("\t\t\t\t--|take 0 or 1 or 2 or 3|-- ");
				}
			}while(take_ !=0);
			//save this update
			
			ConnectionWithDataSeance.updateByDateAndIdInstructor(date1, cinI, seance );
		}else
			System.out.println("\t\t\t\t***notFound***");
	}
	
	/**
	 * Delete a seance by keyboard input
	 */
	
	public static void delete(String nameOfClass) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t-->Delete "+nameOfClass+" <--\n");
		//input CinI
		System.out.print("\t\t\tCin Instructor --> ");
		long cinI= sc.nextLong();
		sc.nextLine();
		
		//input date
		System.out.println("\t\t\tdate : ");
		
		System.out.print("\t\t\tDay        --> ");
		int day= sc.nextInt();
		sc.nextLine();
		System.out.print("\t\t\tMonth      --> ");
		int month= sc.nextInt();
		sc.nextLine();
		System.out.print("\t\t\tYear       --> ");
		int year= sc.nextInt();
		sc.nextLine();
		System.out.print("\t\t\tHours       --> ");
		String hours= sc.nextLine();
		
		
		//creat date
		String sDate1=String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year)+" "+hours;  
        Date date1 = null;
		try {
			date1 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(sDate1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//delete and test
		if( ConnectionWithDataSeance.delete(date1, cinI, nameOfClass) )
			System.out.println("\t\t\t--> delete successfully <--");
		else 
			System.out.println("\t\t\t***erreur***");
	}
	
	/**
	 * search a seance by keyboard input
	 */
	public static void search(String nameOfClass) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t-->Search "+nameOfClass+" <--\n");

		//input CinI
		System.out.print("\t\t\tCin Instructor --> ");
		long cinI= sc.nextLong();
		sc.nextLine();
		
		//input date
		System.out.println("\t\t\tdate : ");
		
		System.out.print("\t\t\tDay        --> ");
		int day= sc.nextInt();
		sc.nextLine();
		System.out.print("\t\t\tMonth      --> ");
		int month= sc.nextInt();
		sc.nextLine();
		System.out.print("\t\t\tYear       --> ");
		int year= sc.nextInt();
		sc.nextLine();
		System.out.print("\t\t\tHours       --> ");
		String hours= sc.nextLine();
		
		
		//creat date
		String sDate1=String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year)+" "+hours;  
        Date date1 = null;
		try {
			date1 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(sDate1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Seance seance = ConnectionWithDataSeance.getByDateAndCinI(date1, cinI, nameOfClass);
		if(seance != null)
			System.out.println(seance);
		else
			System.out.println("\t\t\t\t***notFound***");
	}
	
	/**
	 * consult all the seances
	 */
	public static void getAll() {
		
	}
}
