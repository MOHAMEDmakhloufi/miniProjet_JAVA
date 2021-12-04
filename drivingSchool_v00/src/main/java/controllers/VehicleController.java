package controllers;

import entities.Vehicle;
import gui.ExamGUI;
import gui.PersonGUI;
import gui.SeanceGUI;
import gui.VehicleGUI;
import services.PersonService;
import services.VehicleService;

public class VehicleController {
	public static void managment(String nameOfClass)  {
		
		int take, take_;
		
		do {
			take = VehicleGUI.managmentChoices(nameOfClass);
			
			switch(take) {
				case 1 : 
					Vehicle v = VehicleGUI.inputVehicle(nameOfClass);
					if(VehicleService.search(nameOfClass, v.getId()) == null)
						VehicleService.add(nameOfClass, v);
					else
						VehicleGUI.alreadyExist(nameOfClass, v);
					break;
				case 2 :
					long id = VehicleGUI.inputId();
					Vehicle vehicle = VehicleService.search(nameOfClass, id);
					if ( vehicle != null) {
						vehicle = VehicleGUI.inputUpdatePerson(nameOfClass, vehicle);
						VehicleService.update(id, vehicle);
					}else
						VehicleGUI.notFound();
					break;
				case 3 :
					long id3 = VehicleGUI.inputId();
					Vehicle v3 =VehicleService.search(nameOfClass, id3);
					if ( v3 != null) {
						String check = VehicleGUI.deleteMsg(nameOfClass, v3);
						if (check.equals("Y"))
							VehicleService.delete(nameOfClass, id3);
					}else
						VehicleGUI.notFound();
					break;
				case 4 :
					long id4 = VehicleGUI.inputId();
					Vehicle v4 =VehicleService.search(nameOfClass, id4);
					if ( v4 != null) {
						VehicleGUI.displayOne(v4);
						do {
							take_ = VehicleGUI.displayChoice();
							
							switch(take_) {
							case 1 :
								SeanceGUI.displayAll(VehicleService.consultAllSeanceDriving(id4));
								break;
							case 2 :
								SeanceGUI.displayAll(VehicleService.consultNextSeanceDriving(id4));
								break;
							case 3 :
								SeanceGUI.displayAll(VehicleService.consultPreviousSeanceDriving(id4));
							case 4 :
								ExamGUI.displayAll(VehicleService.consultExamDriving(id4));
							case 0 :
								break;
							default : 
								System.out.println("\t\t\t\t--|take 0 or 1 or 2 or 3 |-- ");
						}
						}while(take_ !=0);
					}else
						VehicleGUI.notFound();
					break;
				case 5 :
					VehicleGUI.displayAll(VehicleService.getAll(nameOfClass));
					break;
				case 0 :
					break;
				default : 
					System.out.println("\t\t--|take 0 or 1 or 2 or 3 or 4 or 5 |-- ");
			}
		}while(take != 0);
	}
}
