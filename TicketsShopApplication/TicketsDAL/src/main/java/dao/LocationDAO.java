package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.LocationObj;
import hibernate.HibernateUtil;

public class LocationDAO {
	
	private SessionFactory factory=HibernateUtil.getSessionFactory();
	
	Session session;
	
	public LocationDAO() {
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<LocationObj> selectAll(){
		session=factory.getCurrentSession();
		session.beginTransaction();
		ArrayList<LocationObj> list=new ArrayList<LocationObj>();
		list=(ArrayList<LocationObj>) session.createQuery("from LocationObj").getResultList();
		session.close();
		return list;
	}
	
	public LocationObj selectByID(int ID) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		LocationObj loc=null;
		loc=session.get(LocationObj.class, ID);
		session.getTransaction().commit();
		session.close();
		return loc;
	}
	
	public int insert(String name) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		int ID=0;
		LocationObj loc=new LocationObj(name);
		ID=(Integer) session.save(loc);
		session.getTransaction().commit();
		session.close();
		return ID;
	}
	
	public void update(int ID,String name) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		LocationObj loc=null;
		loc=session.get(LocationObj.class, ID);
		if(name==null || name=="") {
			loc.setName(loc.getName());
		}
		else {
			loc.setName(name);
		}
		session.getTransaction().commit();
		session.close();
	}
	
	public void delete(int ID) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		LocationObj match=null;
		match=session.get(LocationObj.class, ID);
		session.delete(match);
		session.getTransaction().commit();
		session.close();
	}
	
	public void closeConnection() {
		factory.close();
	}

}


