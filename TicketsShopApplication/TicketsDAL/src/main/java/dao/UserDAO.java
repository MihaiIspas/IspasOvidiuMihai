package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.UserObj;
import hibernate.HibernateUtil;

public class UserDAO {
	
	private SessionFactory factory=HibernateUtil.getSessionFactory();
	
	Session session;
	
	public UserDAO() {
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<UserObj> selectAll(){
		session=factory.getCurrentSession();
		session.beginTransaction();
		ArrayList<UserObj> list=new ArrayList<UserObj>();
		list=(ArrayList<UserObj>) session.createQuery("from UserObj").getResultList();
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<UserObj> selectAllUsers(){
		session=factory.getCurrentSession();
		session.beginTransaction();
		ArrayList<UserObj> list=new ArrayList<UserObj>();
		list=(ArrayList<UserObj>) session.createQuery("from UserObj u where u.type='U'").getResultList();
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<UserObj> selectAllOwners(){
		session=factory.getCurrentSession();
		session.beginTransaction();
		ArrayList<UserObj> list=new ArrayList<UserObj>();
		list=(ArrayList<UserObj>) session.createQuery("from UserObj u where u.type='O'").getResultList();
		session.close();
		return list;
	}
	
	public UserObj selectByID(int ID) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		UserObj user=null;
		user=session.get(UserObj.class, ID);
		session.getTransaction().commit();
		session.close();
		return user;
	}
	
	public UserObj selectUserByID(int ID) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		UserObj user=null;
		user=session.get(UserObj.class, ID);
		session.getTransaction().commit();
		if(user.getType().equals("U")) {
			//session.close();
			return user;
		}
		session.close();
		return null;
	}
	
	public UserObj selectOwnerByID(int ID) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		UserObj user=null;
		user=session.get(UserObj.class, ID);
		session.getTransaction().commit();
		if(user.getType().equals("O")) {
			//session.close();
			return user;
		}
		session.close();
		return null;
	}
	
	public int insertUser(String name, String userName, String password, float account) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		int ID=0;
		UserObj user=new UserObj(name,userName,password,"U",account);
		ID=(Integer) session.save(user);
		session.getTransaction().commit();
		session.close();
		return ID;
	}
	
	public int insertOwner(String name, String userName, String password, float account) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		int ID=0;
		UserObj user=new UserObj(name,userName,password,"O",account);
		ID=(Integer) session.save(user);
		session.getTransaction().commit();
		session.close();
		return ID;
	}
	
	public void update(int ID,String name, String userName, String password, float account) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		UserObj user=null;
		user=session.get(UserObj.class, ID);
		if(name==null || name=="") {
			user.setName(user.getName());
		}
		else {
			user.setName(name);
		}
		if(userName==null || userName=="") {
			user.setUserName(user.getUserName());
		}
		else {
			user.setUserName(userName);
		}
		if(password==null || password=="") {
			user.setPassword(user.getPassword());
		}
		else {
			user.setPassword(password);
		}
		if(account==-1) {
			user.setAccount(user.getAccount());
		}
		else {
			user.setAccount(account);
		}
		session.getTransaction().commit();
		session.close();
	}
	
	public void delete(int ID) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		UserObj user=null;
		user=session.get(UserObj.class, ID);
		session.delete(user);
		session.getTransaction().commit();
		session.close();
	}
	
	public void closeConnection() {
		factory.close();
	}

}

