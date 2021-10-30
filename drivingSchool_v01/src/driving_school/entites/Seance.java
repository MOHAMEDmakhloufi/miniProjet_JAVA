package driving_school.entites;

import java.sql.Date;

public abstract class Seance {
	
	protected long id;
	protected long cinI;
	protected long cinC;
	protected Date date; // day, month, year and hours
	protected double priceForInstructor;
	protected double priceForCandidate;
	
	public Seance(long id, long cinI, long cinC, Date date) {
		this.id = id;
		this.cinI = cinI;
		this.cinC = cinC;

		this.date = date;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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

	
	
}
