package driving_school.services;

import java.util.Scanner;

public class InstructorService {
	/**
	 * Instructor management (crud)
	 */
	public static void managment() {
		Scanner sc = new Scanner(System.in);
		int take;
		
		do {
			System.out.println("\t\t1.Add Instructor");
			System.out.println("\t\t2.Update Instructor");
			System.out.println("\t\t3.Delete Instructor");
			System.out.println("\t\t4.Search Instructor");
			System.out.println("\t\t5.consult all the Instructors");
			System.out.println("\t\t0.RETOUR");
			System.out.print("\t\t--> ");
			take = Integer.parseInt(sc.nextLine());
			
			switch(take) {
				case 1 : 
					add();
					break;
				case 2 :
					update();
					break;
				case 3 :
					delete();
					break;
				case 4 :
					search();
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
	 * add a Instructor by keyboard input
	 */
	public static void  add() {
		
	}
	
	/**
	 * update a Instructor by keyboard input
	 */
	public static void update() {
		
	}
	
	/**
	 * Delete a Instructor by keyboard input
	 */
	
	public static void delete() {
		
	}
	
	/**
	 * search a Instructor by keyboard input
	 */
	public static void search() {
		
	}
	
	/**
	 * consult all the Instructors
	 */
	public static void getAll() {
		
	}
}
