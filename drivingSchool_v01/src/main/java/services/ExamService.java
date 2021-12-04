package services;

import java.util.ArrayList;
import java.util.Date;

import dao.ConnectionWithDataExam;
import entities.Exam;

public class ExamService {
	public static void add(Exam exam) {
		ConnectionWithDataExam.add(exam);
	}
	public static void update(long cinC, Exam exam) {
		ConnectionWithDataExam.updateByCin(cinC, exam);
	}
	public static boolean delete(long cinC, String nameOfClass) {
		return ConnectionWithDataExam.delete(cinC, nameOfClass);
	}
	public static Exam search(long cinC, String nameOfClass) {
		return ConnectionWithDataExam.search(cinC, nameOfClass);
	}
	public static ArrayList<Exam> getByDateDay(Date date, String nameOfClass){
		return ConnectionWithDataExam.getByDateDay(date, nameOfClass);
	}
	public static ArrayList<Exam> getAll(String nameOfClass){
		return ConnectionWithDataExam.getAll(nameOfClass);
	}
}
