package services;

import java.util.ArrayList;

import dao.ConnectionWithDataExam;
import dao.ConnectionWithDataSeance;
import dao.ConnectionWithDataVehicle;
import date.MyDate;
import entities.Exam;
import entities.Seance;
import entities.Vehicle;
import settings.Setting;


public class VehicleService {
	public static void  add(String nameOfClass, Vehicle vehicle) {
		ConnectionWithDataVehicle.add(vehicle);
	}
	
	public static Vehicle search(String nameOfClass, long cin) {
		return ConnectionWithDataVehicle.search(cin, nameOfClass);
	}
	
	public static void update( long cin, Vehicle v) {
		ConnectionWithDataVehicle.updateById(cin, v);
	}
	public static void delete(String nameOfClass, long cin) {
		ConnectionWithDataVehicle.delete(cin, nameOfClass);
	}
	public static ArrayList<Vehicle> getAll(String nameOfClass) {
		 return ConnectionWithDataVehicle.getAll(nameOfClass);
	}
	
	
	public static double totalMileage(long id) {
		ArrayList<Seance> listSeanceDriving= ConnectionWithDataSeance.getByIdVehicle(id);
		return listSeanceDriving.size() * Setting.mileageOfSeance ;
	}
	
	public static double mileageRemains( double totalMileage) {
		return Setting.maximumMileage - (totalMileage % Setting.maximumMileage) ;
	}
	
	
	public static ArrayList<Seance> consultAllSeanceDriving(long id) {
		return ConnectionWithDataSeance.getByIdVehicle(id);

		
	}

	
	public static ArrayList<Seance> consultNextSeanceDriving(long id) {
		ArrayList<Seance> listSeanceDriving= ConnectionWithDataSeance.getByIdVehicle(id);
		ArrayList<Seance> nextSeances = new ArrayList<Seance>();

		for(Seance sea : listSeanceDriving)
			if ( MyDate.testAfterOrBefore(sea.getDate()))
				nextSeances.add(sea);
		return nextSeances;
	}

	
	public static ArrayList<Seance> consultPreviousSeanceDriving(long id) {
		ArrayList<Seance> listSeanceDriving= ConnectionWithDataSeance.getByIdVehicle(id);
		ArrayList<Seance> nextSeances = new ArrayList<Seance>();

		for(Seance sea : listSeanceDriving)
			if ( !MyDate.testAfterOrBefore(sea.getDate()))
				nextSeances.add(sea);
		return nextSeances; 

		
	}
	
	public static ArrayList<Exam> consultExamDriving(long id) {
		return ConnectionWithDataExam.getByIdVehicle(id);
	}
}
