package controllers;

import java.util.ArrayList;


import java.util.Scanner;

import entities.Candidate;
import entities.Person;

import gui.PersonGUI;
import gui.SeanceGUI;
import gui.ExamGUI;
import services.PersonService;
public class PersonController {
	/**
	 * 
	 * @param nameOfClass
	 */
	public static void managment(String nameOfClass)  {
		
		int take, take_;
		
		do {
			take = PersonGUI.managmentChoices(nameOfClass);
			
			switch(take) {
				case 1 : 
					Person p = PersonGUI.inputPerson(nameOfClass);
					if(PersonService.search(nameOfClass, p.getCin()) == null)
						PersonService.add(nameOfClass, p);
					else
						PersonGUI.alreadyExist(nameOfClass, p);
					break;
				case 2 :
					long cin = PersonGUI.inputCin();
					Person person = PersonService.search(nameOfClass, cin);
					if ( person != null) {
						person = PersonGUI.inputUpdatePerson(nameOfClass, person);
						PersonService.update(cin, person);
					}else
						PersonGUI.notFound();
					break;
				case 3 :
					long cin3 = PersonGUI.inputCin();
					Person p3 =PersonService.search(nameOfClass, cin3);
					if ( p3 != null) {
						String check = PersonGUI.deleteMsg(nameOfClass, p3);
						if (check.equals("Y"))
							PersonService.delete(nameOfClass, cin3);
					}else
						PersonGUI.notFound();
					break;
				case 4 :
					long cin4 = PersonGUI.inputCin();
					Person p4 =PersonService.search(nameOfClass, cin4);
					if ( p4 != null) {
						PersonGUI.displayOne(p4);

						do {
							take_ = PersonGUI.displayChoice(nameOfClass);
							
							switch(take_) {
								case 1 :
									SeanceGUI.displayAll(PersonService.consultAllSeanceCode(cin4));
									break;
								case 2 :
									SeanceGUI.displayAll(PersonService.consultNextSeanceCode(cin4));
									break;
								case 3 :
									SeanceGUI.displayAll(PersonService.consultPreviousSeanceCode(cin4));
									break;
								case 4 :
									if(p4 instanceof Candidate)
										ExamGUI.displayOne(PersonService.consultExamCode(cin4));
									else
										System.out.println("\t\t\t\t--|take 0 or 1 or 2 or 3  or 5 or 6 |-- ");
									break;
								case 5 :
									SeanceGUI.displayAll(PersonService.consultAllSeanceDriving(cin4));
									break;
								case 6 :
									SeanceGUI.displayAll(PersonService.consultNextSeanceDriving(cin4));
									break;
								case 7 :
									SeanceGUI.displayAll(PersonService.consultPreviousSeanceDriving(cin4));
								case 8 :
									ExamGUI.displayOne(PersonService.consultExamDriving(cin4));
									break;
								case 0 :
									break;
								default : 
									System.out.println("\t\t\t\t--|take 0 or 1 or 2 or 3 or 4 or 5 or 6 |-- ");
							}
						}while(take_ !=0);
					}else
						PersonGUI.notFound();
					break;
				case 5 :
					PersonGUI.displayAll(PersonService.getAll(nameOfClass));
					break;
				case 0 :
					break;
				default : 
					System.out.println("\t\t--|take 0 or 1 or 2 or 3 or 4 or 5 |-- ");
			}
		}while(take != 0);
	}
}
