package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



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
}
