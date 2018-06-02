package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ownertofestival")
public class OwnerToFestivalObj {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	int ID;
	
	@Column(name="festivalID")
	int festivalID;
	
	@Column(name="ownerID")
	int ownerID;
	
	public OwnerToFestivalObj()
	{
		
	}
	
	public OwnerToFestivalObj(int ID,int festivalID, int ownerID) {
		this.ID=ID;
		this.festivalID = festivalID;
		this.ownerID = ownerID;
	}
	
	public OwnerToFestivalObj(int festivalID, int ownerID) {
		this.festivalID = festivalID;
		this.ownerID = ownerID;
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

	public int getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}

}


