package gui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import dao.ConnectionWithDataSeance;
import entities.Candidate;
import entities.Exam;
import entities.ExamCode;
import entities.ExamDriving;
import entities.Person;
import entities.Seance;
import entities.SeanceDriving;
import exceptions.NotDispoException;
import exceptions.NotFoundException;
import exceptions.PriorityException;
import exceptions.SaturdayException;
import services.ExamService;
import services.PersonService;

	public class ExamGUI {
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
			
		public static void displayOne(Exam exam) {
			
			System.out.println("\n\t\t\t\tDate                       : "+formatDate(exam.getDate())
			+"\n\t\t\t\texamination result         : "+exam.getValidation()
			+"\n\t\t\t\tCin Candidate              : "+exam.getCinCandidate());
								
			if(exam instanceof ExamDriving)
				System.out.println("\t\t\t\tCin Instructor             : "+((ExamDriving) exam).getCinInstructor()
						+"\n\t\t\t\tId Vehicle	 	   : "+ ((ExamDriving) exam).getIdVehicle()+"\n");
				
		}
		
		public static void displayAll(ArrayList<Exam> examList) {
			
			
			if(examList.isEmpty())
					System.out.println("\t\t\t\t/!\\_File Empty_/!\\");
			else
				for(Exam e : examList) 
					displayOne(e);
		}
		public static Exam inputExam(String nameOfClass) throws NotFoundException, SaturdayException, NotDispoException, PriorityException {
			System.out.println("\t\t\t-->Add New "+nameOfClass+" <--\n");
			//input CinC
			long cinC= SeanceGUI.inputCinTest("Candidate NOT FOUND", "Candidate");
			
			//input CinI and idVehicle
			long cinI = 0;
			long idVehicle = 0;
			if (nameOfClass.equals("ExamDriving")) {
				examPriority(cinC);
				cinI = SeanceGUI.inputCinTest("INSTRUCTOR NOT FOUND", "Instructor");
				//input idVehicle
				Person can = PersonService.search("Candidate", cinC);
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
		        return (nameOfClass.equals("ExamDriving"))? new ExamDriving(cinC, false, date1, cinI, idVehicle) : new ExamCode(cinC, false, date1);
					
		}
		public static Exam inputUpdateExam(String nameOfClass, Exam exam) throws NotFoundException, SaturdayException, NotDispoException, PriorityException{
			Scanner sc = new Scanner(System.in);
			System.out.println("\t\t\t-->Update "+nameOfClass+" <--\n");
				long cinC = exam.getCinCandidate();
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
									Person can = PersonService.search("Candidate", cinC);
									String category= ((Candidate)can).getCategory();
									if (category.equals("B"))
										newIdVehicle = SeanceGUI.inputVehicleTest("Car");
									else if(category.equals("A"))
										newIdVehicle = SeanceGUI.inputVehicleTest("Moto");
									else
										newIdVehicle = SeanceGUI.inputVehicleTest("Truck");
									//set
									((ExamDriving)exam).setIdVehicle(newIdVehicle);
								}
								else System.out.println("\t\t\t\t--|take 0 or 1 or 2 or 3|-- ");
								break;
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
				
				return exam;
			
		}
		public static String deleteMsg(String nameOfClass) {
			System.out.println("\t\t\t-->Delete "+nameOfClass+" <--\n");
			System.out.print("\t\t\t\tAre you sure to delete this exam Y/N -->");
			return PersonGUI.inputCheck();
		
		}
		//output not exist
		public static void notFound() {
			System.out.println("\t\t\t/!\\_EXAM NOT EXIST/!\\");
		}
		private static void examPriority(long cinCandidate) throws PriorityException{
			Exam examCode = ExamService.search(cinCandidate, "ExamCode");
			if( examCode == null)
				throw new PriorityException("Exam code not exist");
			else if (!examCode.getValidation())
				throw new PriorityException("Exam code not completed");
		}
}
