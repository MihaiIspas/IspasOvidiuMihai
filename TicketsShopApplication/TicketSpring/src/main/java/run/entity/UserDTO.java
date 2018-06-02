package run.entity;

public class UserDTO {
	
	String ID;
	String name;
	String userName;
	String password;
	String type;
	float account;

	public UserDTO() {
		
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getAccount() {
		return account;
	}

	public void setAccount(float account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "UserObj [ID=" + ID + ", name=" + name + ", userName=" + userName + ", password=" + password + ", type="
				+ type + ", account=" + account + "]";
	}
	
	public int hashCode() {
		return Integer.parseInt(this.ID);
	}
	
	public boolean equals(Object obj) {
		if(obj!=null && obj instanceof UserDTO) {
			String id=((UserDTO) obj).getID();
			if(id==this.getID()) {
				return true;
			}
		}
		return false;
	}
	

}

