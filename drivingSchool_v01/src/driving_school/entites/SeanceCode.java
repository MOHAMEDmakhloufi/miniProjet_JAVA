package driving_school.entites;

import java.util.Date;

public class SeanceCode extends Seance {

	public SeanceCode( long cinI, long cinC, Date date) {
		super( cinI, cinC, date);
	}

	@Override
	public double priceForInstructor() {
		return 7;
	}

	@Override
	public double priceForCandidate() {
		return 0;
	}




	
}
