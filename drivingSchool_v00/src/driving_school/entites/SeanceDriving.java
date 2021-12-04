package driving_school.entites;

import java.util.Date;

import driving_school.connections_with_data_file.ConnectionWithDataPerson;
import driving_school.connections_with_data_file.ConnectionWithDataVehicle;

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
	@Override
	public String toString() {
		//name vehicle
		String nameVehicle = (ConnectionWithDataVehicle.search(idVehicle, "Car")!= null)?ConnectionWithDataVehicle.search(idVehicle, "Car").getName():
			(ConnectionWithDataVehicle.search(idVehicle, "Moto")!= null)?ConnectionWithDataVehicle.search(idVehicle, "Moto").getName():
				( ConnectionWithDataVehicle.search(idVehicle, "Truck")!=null)?ConnectionWithDataVehicle.search(idVehicle, "Truck").getName(): "";
		
		return super.toString()+"\n\t\t\t\t Vehicle	 	   : "+ this.idVehicle+" "+nameVehicle+"\n";
	}
	



}
