package driving_school.entites;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Exam {
	protected long cinCandidate;
	protected boolean validation;
	protected Date date;
	
	public Exam(long cinCandidate, boolean validation, Date date) {
		this.cinCandidate = cinCandidate;
		this.validation = validation;
		this.date = date;
	}

	public long getCinCandidate() {
		return cinCandidate;
	}

	public void setCinCandidate(long cinCandidate) {
		this.cinCandidate = cinCandidate;
	}

	public boolean getValidation() {
		return validation;
	}

	public void setValidation(boolean validation) {
		this.validation = validation;
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
				+"\n\t\t\t\texamination result         : "+this.validation
				+"\n\t\t\t\tCin Candidate              : "+this.cinCandidate;
	}
}
