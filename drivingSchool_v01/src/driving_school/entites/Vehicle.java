package driving_school.entites;

import java.util.Date;

public abstract class Vehicle {
	
	protected long id;
	protected String type;
	protected Date date;
	protected double totalMileage;
	protected double nextUpkeep;
	protected double mileageRemains;
	public Vehicle(long id, String type, Date date) {
		this.id = id;
		this.type = type;
		this.date = date;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setTotalMileage(double totalMileage) {
		this.totalMileage = totalMileage;
	}
	

	
	
}
