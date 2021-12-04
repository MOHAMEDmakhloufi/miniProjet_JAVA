package driving_school.gui;

import java.util.Scanner;

public class SettingGUI {
	//input number
	public static double inputNumber() {
		Scanner sc = new Scanner(System.in);
		Exception e;
		double number= 0;
		do {
			e = null;
			
			try {
				number= sc.nextDouble();
			}catch(Exception e1) {
				e = e1;
			}finally {
				sc.nextLine();
			}
			if (e !=null)
				System.out.println("\t\t\t/!\\_INCORRECT NUMBER_/!\\");
		}while(e != null);
		
		return number;
	}
}
