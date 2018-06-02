package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="location")
public class LocationObj {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="locationID")
	int ID;
	
	@Column(name="name")
	String name;

	public LocationObj() {
		
	}

	public LocationObj(int iD, String name) {
		ID = iD;
		this.name = name;
	}

	public LocationObj(String name) {
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "LocationObj [ID=" + ID + ", name=" + name + "]";
	}
	
	public int hashCode() {
		return this.ID;
	}
	
	public boolean equals(Object obj) {
		if(obj!=null && obj instanceof LocationObj) {
			int id=((LocationObj) obj).getID();
			if(id==this.getID()) {
				return true;
			}
		}
		return false;
	}
	
}

