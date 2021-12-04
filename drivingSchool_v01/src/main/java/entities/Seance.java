package entities;

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
	
	
}
