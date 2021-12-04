package driving_school.entites;

import java.util.Date;

public class ExamDriving extends Exam {

	private long cinInstructor;
	private long idVehicle;
	public ExamDriving(long cinCandidate, boolean validation, Date date, long cinInstructor) {
		super(cinCandidate, validation, date);
		this.cinInstructor = cinInstructor;
	}

	public long getCinInstructor() {
		return cinInstructor;
	}

	public void setCinInstructor(long cinInstructor) {
		this.cinInstructor = cinInstructor;
	}
	
	public long getIdVehicle() {
		return idVehicle;
	}

	public void setIdVehicle(long idVehicle) {
		this.idVehicle = idVehicle;
	}

	@Override 
	public String toString() {
		return  super.toString()
				+"\n\t\t\t\tCin Instructor             : "+this.cinInstructor
				+"\n\t\t\t\tId Vehicle	 	           : "+ this.idVehicle+"\n";
	}
	


}
