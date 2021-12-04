package driving_school.entites;

import java.text.SimpleDateFormat;
import java.util.Date;

import driving_school.connections_with_data_file.ConnectionWithDataPerson;

public abstract class Seance {
	

	protected long cinI;
	protected long cinC;
	protected Date date; // day, month, year and hours

	
	public Seance(long cinI, long cinC, Date date) {

		this.cinI = cinI;
		this.cinC = cinC;

		this.date = date;
	}

	public long getCinI() {
		return cinI;
	}
	public void setCinI(long cinI) {
		this.cinI = cinI;
	}
	public long getCinC() {
		return cinC;
	}
	public void setCinC(long cinC) {
		this.cinC = cinC;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	//format date dd/mm/yyyy HH:mm
	private String formatDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return sdf.format(this.date);
	}
	
	
	@Override 
	public String toString() {
		//name Candidate
		Person can = ConnectionWithDataPerson.search(cinC, "Candidate");
		String nameCandidate= (can != null)?can.getName(): "";
		//name instructor
		Person ins = ConnectionWithDataPerson.search(cinI, "Instructor");
		String nameInstructor= (ins != null)?ins.getName(): "";
		
		return     "\n\t\t\t\tDate              : "+formatDate()
				+"\n\t\t\t\t Instructor             : "+this.cinI+" "+nameInstructor
				+"\n\t\t\t\t Candidate              : "+this.cinC+" "+nameCandidate;
	}
	
}
