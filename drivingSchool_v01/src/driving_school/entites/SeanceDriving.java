package driving_school.entites;

import java.sql.Date;

public class SeanceDriving extends Seance{

	protected long idVehicle;

	public SeanceDriving(long id, long cinI, long cinC, Date date, long idVehicle) {
		super(id, cinI, cinC, date);
		this.idVehicle = idVehicle;
	}

	public long getIdVehicle() {
		return idVehicle;
	}

	public void setIdVehicle(long idVehicle) {
		this.idVehicle = idVehicle;
	}



}
