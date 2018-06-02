package run.entity;

public class LocationDTO {
	
	int ID;
	String name;

	public LocationDTO() {
		
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
		if(obj!=null && obj instanceof LocationDTO) {
			int id=((LocationDTO) obj).getID();
			if(id==this.getID()) {
				return true;
			}
		}
		return false;
	}

}

