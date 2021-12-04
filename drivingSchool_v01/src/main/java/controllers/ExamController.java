package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;


import entities.Exam;
import entities.Seance;
import exceptions.NotDispoException;
import exceptions.NotFoundException;
import exceptions.PriorityException;
import exceptions.SaturdayException;
import gui.ExamGUI;
import gui.SeanceGUI;
import services.ExamService;
import services.SeanceService;

public class ExamController {
	public static void managment(String nameOfClass) {
		Scanner sc = new Scanner(System.in);
		int take;
		
		do {
			take = ExamGUI.managmentChoices(nameOfClass);
			
			switch(take) {
				case 1 : 
					try {
						Exam exam = ExamGUI.inputExam(nameOfClass);
						ExamService.add(exam);
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
					break;
				case 2 :
					try {
						//input CinC
						long cinC= SeanceGUI.inputCin("Candidate");
						
						Exam exam = ExamService.search(cinC, nameOfClass);
						if(exam != null) {
							exam = ExamGUI.inputUpdateExam(nameOfClass, exam);
							ExamService.update(cinC, exam);
						}else
							ExamGUI.notFound();
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
					break;
				case 3 :
					//input CinI
					long cinC3= SeanceGUI.inputCin("Candidate");
					
					Exam e3 = ExamService.search(cinC3, nameOfClass);
					if ( e3 != null) {
						String check = ExamGUI.deleteMsg(nameOfClass); 
						if (check.equals("Y"))
							if(ExamService.delete(cinC3, nameOfClass))
								SeanceGUI.msgSuccessfully();
					}else
						ExamGUI.notFound();
					break;
				case 4 :
					//input CinC
					long cinC= SeanceGUI.inputCin("Candidate");
					
					Exam exam = ExamService.search(cinC, nameOfClass);
					if(exam != null)
						ExamGUI.displayOne(exam);
					else
						ExamGUI.notFound();
					break;
				case 5 :
					//input date
			        Date date = SeanceGUI.inputDateDay();
			        
					ArrayList<Exam> examList = ExamService.getByDateDay(date, nameOfClass);
					ExamGUI.displayAll(examList);
					break;
				case 6 :
					ArrayList<Exam> AllExam = ExamService.getAll(nameOfClass);
					ExamGUI.displayAll(AllExam);
					break;
				case 0 :
					break;
				default : 
					System.out.println("\t\t--|take 0 or 1 or 2 or 3 or 4 or 5 or 6 |-- ");
			}
		}while(take != 0);
	}
}
