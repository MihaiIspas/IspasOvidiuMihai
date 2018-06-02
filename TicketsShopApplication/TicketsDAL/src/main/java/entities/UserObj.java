package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class UserObj {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userID")
	int ID;
	
	@Column(name="name")
	String name;
	
	@Column(name="userName")
	String userName;
	
	@Column(name="password")
	String password;
	
	@Column(name="type")
	String type;
	
	@Column(name="account")
	float account;

	public UserObj() {
		
	}

	public UserObj(int iD, String name, String userName, String password, String type, float account) {
		ID = iD;
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.type = type;
		this.account = account;
	}

	public UserObj(String name, String userName, String password, String type, float account) {
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.type = type;
		this.account = account;
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
		return this.ID;
	}
	
	public boolean equals(Object obj) {
		if(obj!=null && obj instanceof UserObj) {
			int id=((UserObj) obj).getID();
			if(id==this.getID()) {
				return true;
			}
		}
		return false;
	}
	
}

