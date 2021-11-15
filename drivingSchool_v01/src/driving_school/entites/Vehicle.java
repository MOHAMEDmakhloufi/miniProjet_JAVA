package driving_school.entites;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import driving_school.connections_with_data_file.ConnectionWithDataSeance;
import driving_school.date.MyDate;
import driving_school.settings.Setting;

public abstract class Vehicle {
	
	protected long id;
	protected String name;
	protected Date theDateOfPurchase;
	protected double totalMileage;
	protected double nextUpkeep;
	//protected double mileageRemains;
	public Vehicle(long id, String name, Date theDateOfPurchase) {
		this.id = id;
		this.name = name;
		this.theDateOfPurchase = theDateOfPurchase;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getTheDateOfPurchase() {
		return theDateOfPurchase;
	}
	public void setTheDateOfPurchase(Date theDateOfPurchase) {
		this.theDateOfPurchase = theDateOfPurchase;
	}
	//format date dd/mm/yyyy
	private String formatDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.theDateOfPurchase);
	}
	@Override 
	public String toString() {
		double totalMilege = this.totalMileage();
		return     "\t\t\t\tId              : "+this.id
				+"\n\t\t\t\tName             : "+this.name
				+"\n\t\t\t\ttheDateOfPurchase : "+formatDate()
				+"\n\t\t\t\ttotalMileage      : "+totalMilege+" Km"
				+"\n\t\t\t\tmileageRemains    : "+this.mileageRemains(totalMileage)+" Km";
	}
	
	public double totalMileage() {
		ArrayList<Seance> listSeanceDriving= ConnectionWithDataSeance.getByIdVehicle(this.id);
		return listSeanceDriving.size() * Setting.mileageOfSeance ;
	}
	
	public double mileageRemains( double totalMileage) {
		return Setting.maximumMileage - (totalMileage % Setting.maximumMileage) ;
	}
	
	
	public void consultAllSeanceDriving() {
		ArrayList<Seance> listSeanceDriving= ConnectionWithDataSeance.getByIdVehicle(this.id);
		
		for(Seance sea : listSeanceDriving)
			System.out.println("\t\t\t\t"+sea);
		
	}

	
	public void consultNextSeanceDriving() {
		ArrayList<Seance> listSeanceDriving= ConnectionWithDataSeance.getByIdVehicle(this.id);
		
		for(Seance sea : listSeanceDriving)
			if (MyDate.testAfterOrBefore(sea.getDate()))
			System.out.println("\t\t\t\t"+sea);
		
	}

	
	public void consultPreviousSeanceDriving() {
		ArrayList<Seance> listSeanceDriving= ConnectionWithDataSeance.getByIdVehicle(this.id); 
		
		for(Seance sea : listSeanceDriving)
			if ( !MyDate.testAfterOrBefore(sea.getDate()))
			System.out.println("\t\t\t\t"+sea);
		
	}
}
