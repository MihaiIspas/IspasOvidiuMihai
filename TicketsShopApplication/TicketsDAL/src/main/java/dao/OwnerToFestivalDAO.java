package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.FestivalObj;
import entities.OwnerToFestivalObj;
import entities.UserObj;
import hibernate.HibernateUtil;

public class OwnerToFestivalDAO {
	
	private SessionFactory factory=HibernateUtil.getSessionFactory();
	
	FestivalDAO festdao=new FestivalDAO();
	UserDAO userdao=new UserDAO();
	
	Session session;
	
	public OwnerToFestivalDAO() {
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<OwnerToFestivalObj> selectAll(){
		session=factory.getCurrentSession();
		session.beginTransaction();
		ArrayList<OwnerToFestivalObj> list=new ArrayList<OwnerToFestivalObj>();
		list=(ArrayList<OwnerToFestivalObj>) session.createQuery("from OwnerToFestivalObj").getResultList();
		session.close();
		return list;
	}
	
	public OwnerToFestivalObj selectByID(int ID) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		OwnerToFestivalObj f=null;
		f=session.get(OwnerToFestivalObj.class, ID);
		session.getTransaction().commit();
		session.close();
		return f;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<OwnerToFestivalObj> selectByFestivalID(Integer ID){
		session=factory.getCurrentSession();
		session.beginTransaction();
		ArrayList<OwnerToFestivalObj> list=new ArrayList<OwnerToFestivalObj>();
		list=(ArrayList<OwnerToFestivalObj>) session.createQuery("from OwnerToFestivalObj t where t.festivalID="+Integer.toString(ID)).getResultList();
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<OwnerToFestivalObj> selectByOwnerID(Integer ID){
		session=factory.getCurrentSession();
		session.beginTransaction();
		ArrayList<OwnerToFestivalObj> list=new ArrayList<OwnerToFestivalObj>();
		list=(ArrayList<OwnerToFestivalObj>) session.createQuery("from OwnerToFestivalObj t where t.ownerID="+Integer.toString(ID)).getResultList();
		session.close();
		return list;
	}
	
	public ArrayList<FestivalObj> selectFestivalsByOwner(Integer ID){
		ArrayList<OwnerToFestivalObj> list=selectByOwnerID(ID);
		ArrayList<FestivalObj> festList=new ArrayList<FestivalObj>();
		for(OwnerToFestivalObj f:list) {
			festList.add(festdao.selectByID(f.getFestivalID()));
		}
		return festList;
	}
	
	public ArrayList<UserObj> selectOwnersByFestival(Integer ID){
		ArrayList<OwnerToFestivalObj> list=selectByFestivalID(ID);
		ArrayList<UserObj> ownerList=new ArrayList<UserObj>();
		for(OwnerToFestivalObj f:list) {
			ownerList.add(userdao.selectByID(f.getOwnerID()));
		}
		return ownerList;
	}
	
	public int insert(int festivalID,int ownerID) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		int ID=0;
		OwnerToFestivalObj f=new OwnerToFestivalObj(festivalID,ownerID);
		ID=(Integer) session.save(f);
		session.getTransaction().commit();
		session.close();
		return ID;
	}
	
	public void update(int ID,int festivalID,int ownerID) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		OwnerToFestivalObj f=null;
		f=session.get(OwnerToFestivalObj.class, ID);
		if(festivalID==-1) {
			f.setFestivalID(f.getFestivalID());;
		}
		else {
			f.setFestivalID(festivalID);
		}
		if(ownerID==-1) {
			f.setOwnerID(f.getOwnerID());
		}
		else {
			f.setOwnerID(ownerID);
		}
		session.getTransaction().commit();
		session.close();
	}
	
	public void delete(int ID) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		OwnerToFestivalObj f=null;
		f=session.get(OwnerToFestivalObj.class, ID);
		session.delete(f);
		session.getTransaction().commit();
		session.close();
	}
	
	public void closeConnection() {
		factory.close();
	}
	
}

