package gui;

import java.util.Scanner;

public class FirstInterface {
	public static void firstDisplay() {
		System.out.println("                ___________________________________________________");
		System.out.println("               |                \\                  /               |");
		System.out.println("               |                 DRIVING-SCHOOL_V01                |");
		System.out.println("               |________________/__________________\\_______________|");
	}
	public static int choices() {
		Scanner sc = new Scanner(System.in);
		int take;
		System.out.println("\n\t1.Candidate management");
		System.out.println("\t2.Instructor management");
		System.out.println("\t3.Car  management");
		System.out.println("\t4.Moto   management");
		System.out.println("\t5.Truck   management");
		System.out.println("\t6.Seance Code   managment");
		System.out.println("\t7.Seance Driving   managment");
		System.out.println("\t8.Exam Code   managment");
		System.out.println("\t9.Exam Driving   managment");
		System.out.println("\t10.Settings");
		System.out.println("\t0.EXIT");
		System.out.print("\t--> ");
		
		take = sc.nextInt();
		sc.nextLine();
		return take;
	}
}
