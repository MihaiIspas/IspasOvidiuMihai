package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.FestivalObj;
import entities.FestivalToLocationObj;
import entities.LocationObj;
import hibernate.HibernateUtil;

public class FestivalToLocationDAO {
	
	private SessionFactory factory=HibernateUtil.getSessionFactory();
	
	FestivalDAO festdao=new FestivalDAO();
	LocationDAO locdao=new LocationDAO();
	
	Session session;
	
	public FestivalToLocationDAO() {
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<FestivalToLocationObj> selectAll(){
		session=factory.getCurrentSession();
		session.beginTransaction();
		ArrayList<FestivalToLocationObj> list=new ArrayList<FestivalToLocationObj>();
		list=(ArrayList<FestivalToLocationObj>) session.createQuery("from FestivalToLocationObj").getResultList();
		session.close();
		return list;
	}
	
	public FestivalToLocationObj selectByID(int ID) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		FestivalToLocationObj f=null;
		f=session.get(FestivalToLocationObj.class, ID);
		session.getTransaction().commit();
		session.close();
		return f;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<FestivalToLocationObj> selectByFestivalID(Integer ID){
		session=factory.getCurrentSession();
		session.beginTransaction();
		ArrayList<FestivalToLocationObj> list=new ArrayList<FestivalToLocationObj>();
		list=(ArrayList<FestivalToLocationObj>) session.createQuery("from FestivalToLocationObj t where t.festivalID="+Integer.toString(ID)).getResultList();
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<FestivalToLocationObj> selectByLocationID(Integer ID){
		session=factory.getCurrentSession();
		session.beginTransaction();
		ArrayList<FestivalToLocationObj> list=new ArrayList<FestivalToLocationObj>();
		list=(ArrayList<FestivalToLocationObj>) session.createQuery("from FestivalToLocationObj t where t.locationID="+Integer.toString(ID)).getResultList();
		session.close();
		return list;
	}
	
	public int insert(int festivalID,int locationID) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		int ID=0;
		FestivalToLocationObj f=new FestivalToLocationObj(festivalID,locationID);
		ID=(Integer) session.save(f);
		session.getTransaction().commit();
		session.close();
		return ID;
	}
	
	public void update(int ID,int festivalID,int locationID) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		FestivalToLocationObj f=null;
		f=session.get(FestivalToLocationObj.class, ID);
		if(festivalID==-1) {
			f.setFestivalID(f.getFestivalID());;
		}
		else {
			f.setFestivalID(festivalID);
		}
		if(locationID==-1) {
			f.setLocationID(f.getLocationID());
		}
		else {
			f.setLocationID(locationID);
		}
		session.getTransaction().commit();
		session.close();
	}
	
	public void delete(int ID) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		FestivalToLocationObj f=null;
		f=session.get(FestivalToLocationObj.class, ID);
		session.delete(f);
		session.getTransaction().commit();
		session.close();
	}
	
	public void closeConnection() {
		factory.close();
	}
	
}

