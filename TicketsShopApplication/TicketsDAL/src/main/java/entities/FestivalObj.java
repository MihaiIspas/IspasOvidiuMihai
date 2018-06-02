package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="festival")
public class FestivalObj{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="festivalID")
	int ID;
	
	@Column(name="name")
	String name;
	
	@Column(name="genre")
	String genre;
	
	@Column(name="description")
	String description;

	public FestivalObj() {
		
	}

	public FestivalObj(int iD, String name, String genre, String description) {
		this.ID = iD;
		this.name = name;
		this.genre = genre;
		this.description=description;
	}

	public FestivalObj(String name, String genre, String description) {
		this.name = name;
		this.genre = genre;
		this.description=description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		this.ID = iD;
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
		return this.ID;
	}
	
	public boolean equals(Object obj) {
		if(obj!=null && obj instanceof FestivalObj) {
			int id=((FestivalObj) obj).getID();
			if(id==this.getID()) {
				return true;
			}
		}
		return false;
	}
	
}


