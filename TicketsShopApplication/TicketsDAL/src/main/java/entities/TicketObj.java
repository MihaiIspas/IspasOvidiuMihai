package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ticket")
public class TicketObj {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ticketID")
	int ID;
	
	@Column(name="name")
	String name;
	
	@Column(name="festivalID")
	Integer festivalID;
	
	@Column(name="userID")
	Integer userID;
	
	@Column(name="price")
	float price;
	
	@Column(name="type")
	String type;
	
	@Column(name="sold")
	int sold;

	public TicketObj() {
		
	}

	public TicketObj(int iD, String name, Integer festivalID, Integer userID, float price,String type,int sold) {
		ID = iD;
		this.name=name;
		this.festivalID = festivalID;
		this.userID=userID;
		this.price = price;
		this.type=type;
		this.sold=sold;
	}

	public TicketObj(String name,Integer festivalID, Integer userID, float price,String type,int sold) {
		this.festivalID = festivalID;
		this.userID=userID;
		this.price = price;
		this.type=type;
		this.sold=sold;
		this.name=name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSold() {
		return sold;
	}

	public void setSold(int sold) {
		this.sold = sold;
	}
	
	public Integer getFestivalID() {
		return festivalID;
	}

	public void setFestivalID(Integer festivalID) {
		this.festivalID = festivalID;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID=userID;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "TicketObj [ID=" + ID + ", festivalID=" + festivalID + ", price=" + price + "]";
	}
	
	public int hashCode() {
		return this.ID;
	}
	
	public boolean equals(Object obj) {
		if(obj!=null && obj instanceof TicketObj) {
			int id=((TicketObj) obj).getID();
			if(id==this.getID()) {
				return true;
			}
		}
		return false;
	}

}

