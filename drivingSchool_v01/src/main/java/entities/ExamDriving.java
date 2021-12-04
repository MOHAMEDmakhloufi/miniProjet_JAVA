package entities;

import java.util.Date;

public class ExamDriving extends Exam {

	private long cinInstructor;
	private long idVehicle;
	public ExamDriving(long cinCandidate, boolean validation, Date date, long cinInstructor, long idVehicle) {
		super(cinCandidate, validation, date);
		this.cinInstructor = cinInstructor;
		this.idVehicle = idVehicle;
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

}
