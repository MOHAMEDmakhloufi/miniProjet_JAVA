package entities;

import java.util.Date;



public class SeanceDriving extends Seance{

	protected long idVehicle;

	public SeanceDriving(long cinI, long cinC, Date date, long idVehicle) {
		super(cinI, cinC, date);
		this.idVehicle = idVehicle;
	}

	public long getIdVehicle() {
		return idVehicle;
	}

	public void setIdVehicle(long idVehicle) {
		this.idVehicle = idVehicle;
	}
	



}
