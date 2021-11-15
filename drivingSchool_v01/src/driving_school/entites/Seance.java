package driving_school.entites;

import java.text.SimpleDateFormat;
import java.util.Date;

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
		return     "\n\t\t\t\tDate              : "+formatDate()
				+"\n\t\t\t\tCin Instructor             : "+this.cinI
				+"\n\t\t\t\tCin Candidate              : "+this.cinC;
	}
	
}
