package driving_school.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import driving_school.exception.NotDispoException;
import driving_school.exception.NotFoundException;
import driving_school.exception.SaturdayException;
import driving_school.gui.PersonGUI;
import driving_school.gui.SeanceGUI;

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
			System.out.println("\t\t5.Display "+nameOfClass+" By date dd/yy/aaaa");
			System.out.println("\t\t6.consult all the "+nameOfClass+"s");
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
					getByDateDay(nameOfClass);
					break;
				case 6 :
					getAll(nameOfClass);
					break;
				case 0 :
					break;
				default : 
					System.out.println("\t\t--|take 0 or 1 or 2 or 3 or 4 or 5 or 6 |-- ");
			}
		}while(take != 0);
	}
	/**
	 * add a seance by keyboard input
	 */
	public static void  add(String nameOfClass) {
		
		System.out.println("\t\t\t-->Add New "+nameOfClass+" <--\n");
		try {
			//input CinI
			long cinI;
			cinI = SeanceGUI.inputCinTest("INSTRUCTOR NOT FOUND", "Instructor");
	
			//input CinC
			long cinC= SeanceGUI.inputCinTest("Candidate NOT FOUND", "Candidate");
	
			//input idVehicle
			long idVehicle = 0;
			if(nameOfClass.equals("SeanceDriving")) {
				Person can = ConnectionWithDataPerson.search(cinC, "Candidate");
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
	        Seance seance = (nameOfClass.equals("SeanceDriving"))? new SeanceDriving(cinI, cinC, date1, idVehicle) : new SeanceCode(cinI, cinC, date1);
			
			ConnectionWithDataSeance.add(seance);
		} catch (NotDispoException e) {
			// TODO Auto-generated catch block
			System.out.println("\t\t\t"+e.getMessage());
		}catch (NotFoundException e1) {
			System.out.println("\t\t\t"+e1.getMessage());
		}catch (SaturdayException e2) {
			System.out.println("\t\t\t"+e2.getMessage());
		}

    
	}
	
	/**
	 * update a seance by keyboard input
	 */
	public static void update(String nameOfClass) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t-->Update "+nameOfClass+" <--\n");
		
		//input CinI
		long cinI= SeanceGUI.inputCin("Instructor");
		
		//input date
		Date date1 = SeanceGUI.inputDate();
		
		Seance seance = ConnectionWithDataSeance.getByDateAndCinI(date1, cinI, nameOfClass);
		
		if(seance != null) {
			int take_ = 0;
			do {
				try {
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
							ConnectionWithDataSeance.availability(date1, newCinI, "cinI", "Instructor Not Available", nameOfClass);
							//set
							seance.setCinI(newCinI);
							break;
						case 2 :
							//input CinC
							long newCinC= SeanceGUI.inputCinTest("Candidate NOT FOUND", "Candidate");
							ConnectionWithDataSeance.availability(date1, newCinC, "cinC", "Candidate Not Available", nameOfClass);
							//set
							seance.setCinC(newCinC);
							break;
						case 3 :
							//input date 
					        Date newdate = SeanceGUI.inputDate();
					        SeanceGUI.testSaturday(newdate);
							ConnectionWithDataSeance.availability(newdate, cinI, "cinI", "Instructor Not Available", nameOfClass);
							ConnectionWithDataSeance.availability(newdate, seance.getCinC(), "cinC", "Candidate Not Available", nameOfClass);
							if(nameOfClass.equals("SeanceDriving"))
								ConnectionWithDataSeance.availability(newdate, ((SeanceDriving)seance).getIdVehicle(), "idVehicle", "Vehicle Not Available", nameOfClass);
							//set
							seance.setDate(newdate);
							break;
						case 4 : 
							if(nameOfClass.equals("SeanceDriving")) {
								
								Person can = ConnectionWithDataPerson.search(((SeanceDriving)seance).getCinC(), "Candidate");
								String category= ((Candidate)can).getCategory();
								long newIdVehicle;
								if (category.equals("A"))
									newIdVehicle = SeanceGUI.inputVehicleTest("Moto");
								else if(category.equals("B"))
									newIdVehicle = SeanceGUI.inputVehicleTest("Car");
								else
									newIdVehicle = SeanceGUI.inputVehicleTest("Truck");
								ConnectionWithDataSeance.availability(date1, newIdVehicle, "idVehicle", "Vehicle Not Available", nameOfClass);
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
				} catch (NotDispoException e) {
					// TODO Auto-generated catch block
					System.out.println("\t\t\t"+e.getMessage());
				}catch (NotFoundException e1) {
					System.out.println("\t\t\t"+e1.getMessage());
				}catch (SaturdayException e2) {
					System.out.println("\t\t\t"+e2.getMessage());
				}	
			}while(take_ !=0);
			//save this update
			
			ConnectionWithDataSeance.updateByDateAndIdInstructor(date1, cinI, seance );
		}else
			System.out.println("\t\t\t\t/!\\_notFound_/!\\");
	}
	
	/**
	 * Delete a seance by keyboard input
	 */
	
	public static void delete(String nameOfClass) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t-->Delete "+nameOfClass+" <--\n");
		//input CinI
		long cinI= SeanceGUI.inputCin("Instructor");

		//input date
        Date date1 = SeanceGUI.inputDate();
        
        System.out.print("\t\t\t\tAre you sure to delete this Seance Y/N -->");
		String check =PersonGUI.inputCheck();
		if (check.equals("Y")) {
		//delete and test
		if( ConnectionWithDataSeance.delete(date1, cinI, nameOfClass) )
			System.out.println("\t\t\t--> delete successfully <--");
		else 
			System.out.println("\t\t\t/!\\_DELETE ERREUR_/!\\");
		}
	}
	
	/**
	 * search a seance by keyboard input
	 */
	public static void search(String nameOfClass) {
		System.out.println("\t\t\t-->Search "+nameOfClass+" <--\n");

		//input CinI
		long cinI= SeanceGUI.inputCin("Instructor");
		
		//input date
        Date date1 = SeanceGUI.inputDate();
		
		Seance seance = ConnectionWithDataSeance.getByDateAndCinI(date1, cinI, nameOfClass);
		if(seance != null)
			System.out.println(seance);
		else
			System.out.println("\t\t\t\t/!\\_notFound_/!\\");
	}
	public static void getByDateDay(String nameOfClass){
		System.out.println("\t\t\t-->Display "+nameOfClass+" By date dd/yy/aaaa <--\n");
		
		//input date
        Date date = SeanceGUI.inputDateDay();
        
		ArrayList<Seance> seanceList = ConnectionWithDataSeance.getByDateDay(date, nameOfClass);
		if(seanceList != null)
			for (Seance sea : seanceList)
				System.out.println(sea);
		else
			System.out.println("\t\t\t\t/!\\_Day Empty/!\\");
	}
	/**
	 * consult all the seances
	 */
	public static void getAll(String nameOfClass) {
		System.out.println("\t\t\t-->consult all the "+nameOfClass+"s <--\n");
		
		ArrayList<Seance> seanceList = ConnectionWithDataSeance.getAll(nameOfClass);
		if(seanceList != null)
			for (Seance sea : seanceList)
				System.out.println(sea);
		else
			System.out.println("\t\t\t\t/!\\_File Empty/!\\");
	}
}
