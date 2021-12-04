package driving_school.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import driving_school.connections_with_data_file.ConnectionWithDataExam;
import driving_school.connections_with_data_file.ConnectionWithDataPerson;
import driving_school.connections_with_data_file.ConnectionWithDataSeance;
import driving_school.entites.Candidate;
import driving_school.entites.Exam;
import driving_school.entites.ExamCode;
import driving_school.entites.ExamDriving;
import driving_school.entites.Person;
import driving_school.entites.Seance;
import driving_school.entites.SeanceCode;
import driving_school.entites.SeanceDriving;
import driving_school.exception.NotDispoException;
import driving_school.exception.NotFoundException;
import driving_school.exception.PriorityException;
import driving_school.exception.SaturdayException;
import driving_school.gui.PersonGUI;
import driving_school.gui.SeanceGUI;

public class ExamService {
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
	 * add a Exam by keyboard input
	 */
	public static void  add(String nameOfClass) {
		
		System.out.println("\t\t\t-->Add New "+nameOfClass+" <--\n");
		try {
			//input CinC
			long cinC= SeanceGUI.inputCinTest("Candidate NOT FOUND", "Candidate");
			
			//input CinI and idVehicle
			long cinI = 0;
			long idVehicle = 0;
			if (nameOfClass.equals("ExamDriving")) {
				examPriority(cinC);
				cinI = SeanceGUI.inputCinTest("INSTRUCTOR NOT FOUND", "Instructor");
				//input idVehicle
				Person can = ConnectionWithDataPerson.search(cinC, "Candidate");
				String category= ((Candidate)can).getCategory();
				if (category.equals("A"))
					idVehicle = SeanceGUI.inputVehicleTest("Car");
				else if(category.equals("B"))
					idVehicle = SeanceGUI.inputVehicleTest("Moto");
				else
					idVehicle = SeanceGUI.inputVehicleTest("Truck");
				
			}

	
			//input date
	        Date date1 = SeanceGUI.inputDate();
	        SeanceGUI.testSaturday(date1);
			ConnectionWithDataSeance.availability(date1, cinI, "cinI", "Instructor Not Available", nameOfClass);
			ConnectionWithDataSeance.availability(date1, cinC, "cinC", "Candidate Not Available", nameOfClass);
			ConnectionWithDataSeance.availability(date1, idVehicle, "idVehicle", "Vehicle Not Available", nameOfClass);
	        //creat new Exam
	        Exam exam = (nameOfClass.equals("ExamDriving"))? new ExamDriving(cinC, false, date1, cinI) : new ExamCode(cinC, false, date1);
			
			ConnectionWithDataExam.add(exam);
		} catch (NotDispoException e) {
			// TODO Auto-generated catch block
			System.out.println("\t\t\t"+e.getMessage());
		}catch (NotFoundException e1) {
			System.out.println("\t\t\t"+e1.getMessage());
		}catch (SaturdayException e2) {
			System.out.println("\t\t\t"+e2.getMessage());
		}catch (PriorityException e3) {
			System.out.println("\t\t\t"+e3.getMessage());
		}

		
	}
	
	/**
	 * update a Exam by keyboard input
	 */
	public static void update(String nameOfClass) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t-->Update "+nameOfClass+" <--\n");
		
		//input CinC
		long cinC= SeanceGUI.inputCin("Candidate");
		
		Exam exam = ConnectionWithDataExam.search(cinC, nameOfClass);
		
		if(exam != null) {
			int take_ = 0;
			do {
				try {
					
					System.out.println("\t\t\t\t1.Set CinCandidate");
					System.out.println("\t\t\t\t2.Set Validation ");
					System.out.println("\t\t\t\t3.Set Date");
					if(nameOfClass.equals("ExamDriving")) {
						System.out.println("\t\t\t\t4.Set IdVehicle ");
						System.out.println("\t\t\t\t5.Set CinInstructor");
					}
						
					System.out.println("\t\t\t\t0.FINISH");
					System.out.print("\t\t\t\t--> ");
					take_ = sc.nextInt();
					sc.nextLine();
					
					switch(take_) {

						case 1 :
							//input CinC
							long newCinC= SeanceGUI.inputCinTest("Candidate NOT FOUND", "Candidate");
							ConnectionWithDataSeance.availability(exam.getDate(), newCinC, "cinC", "Candidate Not Available", nameOfClass);
							//set
							exam.setCinCandidate(newCinC);
							break;
						case 2 : 
							//input validation
							System.out.print("\t\t\t\t\tValidation      -->");
							Boolean newValidation= sc.nextBoolean();
							sc.nextLine();
							
							//set
							exam.setValidation(newValidation);
							break;
						case 3 :
							//input date 
					        Date newdate = SeanceGUI.inputDate();
					        examPriority(cinC);
					        SeanceGUI.testSaturday(newdate);
							ConnectionWithDataSeance.availability(newdate, cinC, "cinC", "Candidate Not Available", nameOfClass);
							if(nameOfClass.equals("ExamDriving")) {
								ConnectionWithDataSeance.availability(newdate, ((ExamDriving)exam).getIdVehicle(), "idVehicle", "Vehicle Not Available", nameOfClass);
								ConnectionWithDataSeance.availability(newdate, ((ExamDriving)exam).getCinInstructor(), "cinI", "Instructor Not Available", nameOfClass);

							}
							//set
							exam.setDate(newdate);
							break;
						case 4 : 
							long newIdVehicle = 0;
							if (nameOfClass.equals("ExamDriving")) {
								//input idVehicle
								Person can = ConnectionWithDataPerson.search(cinC, "Candidate");
								String category= ((Candidate)can).getCategory();
								if (category.equals("A"))
									newIdVehicle = SeanceGUI.inputVehicleTest("Car");
								else if(category.equals("B"))
									newIdVehicle = SeanceGUI.inputVehicleTest("Moto");
								else
									newIdVehicle = SeanceGUI.inputVehicleTest("Truck");
								//set
								((ExamDriving)exam).setIdVehicle(newIdVehicle);
							}
							else System.out.println("\t\t\t\t--|take 0 or 1 or 2 or 3|-- ");
						case 5 : 
							//input CinI
							long newCinI= 0;
							if (nameOfClass.equals("ExamDriving")) {
								newCinI= SeanceGUI.inputCinTest("INSTRUCTOR NOT FOUND", "Instructor");
								ConnectionWithDataSeance.availability(exam.getDate(), newCinI, "cinI", "Instructor Not Available", nameOfClass);
								//set
								((ExamDriving)exam).setCinInstructor(newCinI);
							}else System.out.println("\t\t\t\t--|take 0 or 1 or 2 or 3|-- ");
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
				}catch (PriorityException e3) {
					System.out.println("\t\t\t"+e3.getMessage());
				}catch(InputMismatchException e4) {
					sc.nextLine();
					System.out.println("\t\t\t"+e4.getMessage());
				}
			}while(take_ !=0);
			//save this update
			
			ConnectionWithDataExam.updateByCin(cinC, exam );
		}else
			System.out.println("\t\t\t\t/!\\_notFound_/!\\");
	}
	
	/**
	 * Delete a Exam by keyboard input
	 */
	
	public static void delete(String nameOfClass) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t-->Delete "+nameOfClass+" <--\n");
		//input CinC
		long cinC= SeanceGUI.inputCin("Candidate");
        
        System.out.print("\t\t\t\tAre you sure to delete this Exam Y/N -->");
		String check =PersonGUI.inputCheck();
		if (check.equals("Y")) {
		//delete and test
		if( ConnectionWithDataExam.delete(cinC, nameOfClass) )
			System.out.println("\t\t\t--> delete successfully <--");
		else 
			System.out.println("\t\t\t/!\\_DELETE ERREUR_/!\\");
		}
	}
	
	/**
	 * get by cin keyboard input
	 * @param nameOfClass
	 */
	public static void search(String nameOfClass) {
		System.out.println("\t\t\t-->Search "+nameOfClass+" <--\n");

		//input CinC
		long cinC= SeanceGUI.inputCin("Candidate");
		
		Exam exam = ConnectionWithDataExam.search(cinC, nameOfClass);
		if(exam != null)
			System.out.println(exam);
		else
			System.out.println("\t\t\t\t/!\\_notFound_/!\\");
	}
	public static void getByDateDay(String nameOfClass){
		System.out.println("\t\t\t-->Display "+nameOfClass+" By date dd/yy/aaaa <--\n");
		
		//input date
        Date date = SeanceGUI.inputDateDay();
        
		ArrayList<Exam> examList = ConnectionWithDataExam.getByDateDay(date, nameOfClass);
		if(examList != null)
			for (Exam exam : examList)
				System.out.println(exam);
		else
			System.out.println("\t\t\t\t/!\\_Day Empty/!\\");
	}
	/**
	 * display all Exams
	 * @param nameOfClass
	 */
	public static void getAll(String nameOfClass) {
		System.out.println("\t\t\t-->consult all the "+nameOfClass+"s <--\n");
		
		ArrayList<Exam> examList = ConnectionWithDataExam.getAll(nameOfClass);
		if(examList != null)
			for (Exam exam : examList)
				System.out.println(exam);
		else
			System.out.println("\t\t\t\t/!\\_File Empty/!\\");
	}
	
	private static void examPriority(long cinCandidate) throws PriorityException{
		Exam examCode = ConnectionWithDataExam.search(cinCandidate, "ExamCode");
		if( examCode == null)
			throw new PriorityException("Exam code not exist");
		else if (examCode.getValidation())
			throw new PriorityException("Exam code not completed");
	}
}
