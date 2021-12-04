package controllers;

import java.util.Date;


import entities.Person;
import entities.Seance;
import exceptions.NotDispoException;
import exceptions.NotFoundException;
import exceptions.SaturdayException;
import gui.SeanceGUI;
import services.PersonService;
import services.SeanceService;

public class SeanceController {
public static void managment(String nameOfClass)  {
		
		int take;
		
		do {
			take = SeanceGUI.managmentChoices(nameOfClass);
			
			switch(take) {
				case 1 : 
					try {
						Seance s = SeanceGUI.inputSeance(nameOfClass);
						SeanceService.add(nameOfClass, s);
					}catch (NotDispoException e) {
						// TODO Auto-generated catch block
						System.out.println("\t\t\t"+e.getMessage());
					}catch (NotFoundException e1) {
						System.out.println("\t\t\t"+e1.getMessage());
					}catch (SaturdayException e2) {
						System.out.println("\t\t\t"+e2.getMessage());
					}
					break;
				case 2 :
					
					//input CinI
					long cinI= SeanceGUI.inputCin("Instructor");
					
					//input date
					Date date1 = SeanceGUI.inputDate();
					
					Seance seance = SeanceService.getOne(date1, cinI, nameOfClass);
					if ( seance != null) {
						try {
						seance = SeanceGUI.inputUpdateSeance(nameOfClass, seance);
						SeanceService.update(date1, cinI, seance);
						}catch (NotDispoException e) {
							// TODO Auto-generated catch block
							System.out.println("\t\t\t"+e.getMessage());
						}catch (NotFoundException e1) {
							System.out.println("\t\t\t"+e1.getMessage());
						}catch (SaturdayException e2) {
							System.out.println("\t\t\t"+e2.getMessage());
						}
					}else
						SeanceGUI.notFound();
					break;
				case 3 :
					//input CinI
					long cinI3= SeanceGUI.inputCin("Instructor");
					//input date
			        Date date3 = SeanceGUI.inputDate();
			        Seance s3 =SeanceService.getOne(date3, cinI3, nameOfClass);
					if ( s3 != null) {
						String check = SeanceGUI.deleteMsg(nameOfClass);
						if (check.equals("Y"))
							if(SeanceService.delete(date3, cinI3, nameOfClass))
								SeanceGUI.msgSuccessfully();
					}else
						SeanceGUI.notFound();
					break;
				case 4 :
					//input CinI
					long cinI4= SeanceGUI.inputCin("Instructor");
					//input date
			        Date date4 = SeanceGUI.inputDate();
			        Seance s4 =SeanceService.getOne(date4, cinI4, nameOfClass);
					if ( s4 != null) {
						//name Candidate
						Person can = PersonService.search("Candidate",s4.getCinC());
						String nameCandidate= (can != null)?can.getName(): "";
						//name instructor
						Person ins = PersonService.search("Instructor", s4.getCinI());
						
						String nameInstructor= (ins != null)?ins.getName(): "";
						SeanceGUI.displayOne(s4,nameInstructor, nameCandidate);
						}
					else
						SeanceGUI.notFound();
					break;
				case 5 :
					//input date
			        Date date = SeanceGUI.inputDateDay();
			        SeanceGUI.getByDateDay(SeanceService.getByDateDay(date, nameOfClass));
					break;
				case 6 :
					SeanceGUI.displayAll(SeanceService.getAll(nameOfClass));
					break;
				case 0 :
					break;
				default : 
					System.out.println("\t\t--|take 0 or 1 or 2 or 3 or 4 or 5 or 6 |-- ");
			}
		}while(take != 0);
	}
}
