package run.entity;

public class FestivalDTO {
	
	String ID;
	String name;
	String genre;
	String description;

	public FestivalDTO() {
		
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "FestivalObj [ID=" + ID + ", name=" + name + ", genre=" + genre + "]";
	}
	
	public int hashCode() {
		return Integer.parseInt(this.ID);
	}
	
	public boolean equals(Object obj) {
		if(obj!=null && obj instanceof FestivalDTO) {
			String id=((FestivalDTO) obj).getID();
			if(id==this.getID()) {
				return true;
			}
		}
		return false;
	}

}

