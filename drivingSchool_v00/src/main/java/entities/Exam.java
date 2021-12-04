package entities;

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
}