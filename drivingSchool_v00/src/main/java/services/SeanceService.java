package services;

import java.util.ArrayList;
import java.util.Date;

import dao.ConnectionWithDataPerson;
import dao.ConnectionWithDataSeance;
import entities.Person;
import entities.Seance;

public class SeanceService {
	
	public static void  add(String nameOfClass, Seance seance) {
		ConnectionWithDataSeance.add(seance);
	}
	public static void update(Date date, long cinI, Seance seance) {
		ConnectionWithDataSeance.updateByDateAndIdInstructor(date, cinI, seance );
	}
	public static boolean delete(Date date, long cinI, String nameOfClass) {
		return ConnectionWithDataSeance.delete(date, cinI, nameOfClass) ;
	}
	public static Seance getOne(Date date, long cinI, String nameOfClass) {
		 return  ConnectionWithDataSeance.getByDateAndCinI(date, cinI, nameOfClass);
	}
	public static ArrayList<Seance> getByDateDay(Date date, String nameOfClass) {
		 return ConnectionWithDataSeance.getByDateDay(date, nameOfClass);
	}
	public static ArrayList<Seance> getAll(String nameOfClass) {
		 return ConnectionWithDataSeance.getAll(nameOfClass);
	}
}
