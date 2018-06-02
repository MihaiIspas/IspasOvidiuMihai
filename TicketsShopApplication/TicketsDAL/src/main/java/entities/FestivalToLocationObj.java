package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fesivaltolocation")
public class FestivalToLocationObj {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	int ID;
	
	@Column(name="festivalID")
	int festivalID;
	
	@Column(name="locationID")
	int locationID;
	
	
	
	public FestivalToLocationObj() {
		//super();
	}

	public FestivalToLocationObj(int ID, int festivalID, int locationID) {
		this.ID=ID;
		this.festivalID = festivalID;
		this.locationID = locationID;
	}
	
	public FestivalToLocationObj(int festivalID, int locationID) {
		this.festivalID = festivalID;
		this.locationID = locationID;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
	
	public int getFestivalID() {
		return festivalID;
	}

	public void setFestivalID(int festivalID) {
		this.festivalID = festivalID;
	}

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

}

