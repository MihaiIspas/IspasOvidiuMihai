package run.entity;

public class TicketDTO {
	
	int ID;
	String name;
	Integer festivalID;
	Integer userID;
	float price;
	String type;
	int sold;
	
	public TicketDTO() {
		
	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Integer getFestivalID() {
		return festivalID;
	}

	public void setFestivalID(Integer festivalID) {
		this.festivalID = festivalID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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
	
}
