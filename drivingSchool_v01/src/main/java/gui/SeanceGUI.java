package gui;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import dao.ConnectionWithDataSeance;
import dao.ConnectionWithDataVehicle;
import date.MyDate;

import entities.Candidate;
import entities.Person;
import entities.Seance;
import entities.SeanceCode;
import entities.SeanceDriving;
import entities.Vehicle;
import exceptions.NotDispoException;
import exceptions.NotFoundException;
import exceptions.SaturdayException;
import services.PersonService;

public class SeanceGUI {
	public static int managmentChoices(String nameOfClass) {
		Scanner sc = new Scanner(System.in);
		int take;
		System.out.println("\t\t1.Add "+nameOfClass);
		System.out.println("\t\t2.Update "+nameOfClass);
		System.out.println("\t\t3.Delete "+nameOfClass);
		System.out.println("\t\t4.Search "+nameOfClass);
		System.out.println("\t\t5.Display "+nameOfClass+" By date dd/yy/aaaa");
		System.out.println("\t\t6.consult all the "+nameOfClass+"s");
		System.out.println("\t\t0.RETOUR");
		System.out.print("\t\t--> ");
		take = sc.nextInt();
		sc.nextLine();
		return take;
	}
	
	//format date dd/mm/yyyy
	private static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return sdf.format(date);
	}
		
	public static void displayOne(Seance c, String nameInstructor, String nameCandidate) {
		System.out.println(  "\n\t\t\t\t Date                   : "+formatDate(c.getDate())
							+"\n\t\t\t\t Instructor             : "+c.getCinI()+" "+nameInstructor
							+"\n\t\t\t\t Candidate              : "+c.getCinC()+" "+nameCandidate);
							
		if(c instanceof SeanceDriving)
			System.out.println("\t\t\t\t Vehicle                : "+((SeanceDriving) c).getIdVehicle());
			
	}
	public static void getByDateDay( ArrayList<Seance> seanceList) {
		if(seanceList.isEmpty())
			System.out.println("\t\t\t\t/!\\_File Empty_/!\\");
		else
			for(Seance s : seanceList)
				displayOne(s, "", "");
	}
	public static void displayAll(ArrayList<Seance> seanceList) {
		
		
		if(seanceList.isEmpty())
				System.out.println("\t\t\t\t/!\\_File Empty_/!\\");
		else
			for(Seance s : seanceList)
				displayOne(s, "", "");
	}
	
	public static Seance inputSeance(String nameOfClass) throws NotFoundException, SaturdayException, NotDispoException {
		System.out.println("\t\t\t-->Add New "+nameOfClass+" <--\n");
		
			//input CinI
			long cinI;
			cinI = inputCinTest("INSTRUCTOR NOT FOUND", "Instructor");
	
			//input CinC
			long cinC= inputCinTest("Candidate NOT FOUND", "Candidate");
	
			//input idVehicle
			long idVehicle = 0;
			if(nameOfClass.equals("SeanceDriving")) {
				Person can = PersonService.search("Candidate", cinC);
				String category= ((Candidate)can).getCategory();
				if (category.equals("A"))
					idVehicle = SeanceGUI.inputVehicleTest("Moto");
				else if(category.equals("B"))
					idVehicle = SeanceGUI.inputVehicleTest("Car");
				else
					idVehicle = SeanceGUI.inputVehicleTest("Truck");
			}
			//input date
	        Date date1 = SeanceGUI.inputDate();
	        SeanceGUI.testSaturday(date1);
			ConnectionWithDataSeance.availability(date1, cinI, "cinI", "Instructor Not Available", nameOfClass);
			ConnectionWithDataSeance.availability(date1, cinC, "cinC", "Candidate Not Available", nameOfClass);
			ConnectionWithDataSeance.availability(date1, idVehicle, "idVehicle", "Vehicle Not Available", nameOfClass);
	        //creat new Seance
	        return (nameOfClass.equals("SeanceDriving"))? new SeanceDriving(cinI, cinC, date1, idVehicle) : new SeanceCode(cinI, cinC, date1);
				
	}
	public static Seance inputUpdateSeance(String nameOfClass, Seance seance) throws NotFoundException, SaturdayException, NotDispoException{
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t-->Update "+nameOfClass+" <--\n");
		
			int take_ = 0;
			do {
				
					System.out.println("\t\t\t\t1.Set CinI");
					System.out.println("\t\t\t\t2.Set CinC");
					System.out.println("\t\t\t\t3.Set Date");
					if(nameOfClass.equals("SeanceDriving"))
						System.out.println("\t\t\t\t4.Set IdVehicle ");
					System.out.println("\t\t\t\t0.FINISH");
					System.out.print("\t\t\t\t--> ");
					take_ = sc.nextInt();
					sc.nextLine();
					
					switch(take_) {
						case 1 : 
							//input CinI
							long newCinI= SeanceGUI.inputCinTest("INSTRUCTOR NOT FOUND", "Instructor");
							ConnectionWithDataSeance.availability(seance.getDate(), newCinI, "cinI", "Instructor Not Available", nameOfClass);
							//set
							seance.setCinI(newCinI);
							break;
						case 2 :
							//input CinC
							long newCinC= SeanceGUI.inputCinTest("Candidate NOT FOUND", "Candidate");
							ConnectionWithDataSeance.availability(seance.getDate(), newCinC, "cinC", "Candidate Not Available", nameOfClass);
							//set
							seance.setCinC(newCinC);
							break;
						case 3 :
							//input date 
					        Date newdate = SeanceGUI.inputDate();
					        SeanceGUI.testSaturday(newdate);
							ConnectionWithDataSeance.availability(newdate, seance.getCinI(), "cinI", "Instructor Not Available", nameOfClass);
							ConnectionWithDataSeance.availability(newdate, seance.getCinC(), "cinC", "Candidate Not Available", nameOfClass);
							if(nameOfClass.equals("SeanceDriving"))
								ConnectionWithDataSeance.availability(newdate, ((SeanceDriving)seance).getIdVehicle(), "idVehicle", "Vehicle Not Available", nameOfClass);
							//set
							seance.setDate(newdate);
							break;
						case 4 : 
							if(nameOfClass.equals("SeanceDriving")) {
								
								Person can = PersonService.search("Candidate", ((SeanceDriving)seance).getCinC());
								String category= ((Candidate)can).getCategory();
								long newIdVehicle;
								if (category.equals("A"))
									newIdVehicle = SeanceGUI.inputVehicleTest("Moto");
								else if(category.equals("B"))
									newIdVehicle = SeanceGUI.inputVehicleTest("Car");
								else
									newIdVehicle = SeanceGUI.inputVehicleTest("Truck");
								ConnectionWithDataSeance.availability(seance.getDate(), newIdVehicle, "idVehicle", "Vehicle Not Available", nameOfClass);
								//set
								((SeanceDriving)seance).setIdVehicle(newIdVehicle);
							}
							else System.out.println("\t\t\t\t--|take 0 or 1 or 2 or 3|-- ");
							break;
						case 0 :
							break;
						default : 
							System.out.println("\t\t\t\t--|take 0 or 1 or 2 or 3|-- ");
					}
					
			}while(take_ !=0);
			//save this update
			
			return seance;

	}
	public static String deleteMsg(String nameOfClass) {
		System.out.println("\t\t\t-->Delete "+nameOfClass+" <--\n");
		System.out.print("\t\t\t\tAre you sure to delete this seance Y/N -->");
		return PersonGUI.inputCheck();
	
	}
	//output not exist
	public static void notFound() {
		System.out.println("\t\t\t/!\\_SEANCE NOT EXIST/!\\");
	}
	
	//output successfully
	public static void msgSuccessfully() {
		System.out.println("\t\t\t-->perform the operation successfully <--");
	}
	
	
	
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
						Person p = PersonService.search(nameOfClass, Long.parseLong(cin));
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
