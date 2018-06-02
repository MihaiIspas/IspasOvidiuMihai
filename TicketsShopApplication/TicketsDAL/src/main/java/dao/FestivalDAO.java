package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.FestivalObj;
import hibernate.HibernateUtil;



public class FestivalDAO {
	
	private SessionFactory factory=HibernateUtil.getSessionFactory();
	
	Session session;
	
	public FestivalDAO() {
		
	}
	
	@SuppressWarnings("unchecked")
	public List<FestivalObj> selectAll(){
		session=factory.getCurrentSession();
		session.beginTransaction();
		List<FestivalObj> list=new ArrayList<FestivalObj>();
		list=(List<FestivalObj>) session.createQuery("from FestivalObj").getResultList();
		session.close();
		return list;
	}
	
	public FestivalObj selectByID(int ID) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		FestivalObj festival=null;
		festival=(FestivalObj)session.get(FestivalObj.class, ID);
		session.getTransaction().commit();
		session.close();
		return festival;
	}
	
	public int insert(String name,String genre,String description) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		int ID=0;
		FestivalObj match=new FestivalObj(name,genre,description);
		ID=(Integer) session.save(match);
		session.getTransaction().commit();
		session.close();
		return ID;
	}
	
	public void update(int ID,String name,String genre,String description) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		FestivalObj fest=null;
		fest=session.get(FestivalObj.class, ID);
		if(name==null || name=="") {
			fest.setName(fest.getName());
		}
		else {
			fest.setName(name);
		}
		if(genre==null || genre=="") {
			fest.setGenre(fest.getGenre());
		}
		else {
			fest.setGenre(genre);
		}
		if(description==null || description=="") {
			fest.setDescription(fest.getDescription());
		}
		else {
			fest.setDescription(description);
		}
		session.getTransaction().commit();
		session.close();
	}
	
	public void delete(int ID) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		FestivalObj match=null;
		System.out.println(ID+"------------------");
		//if(ID!=null) {
			match=session.get(FestivalObj.class, ID);
			session.delete(match);
			session.getTransaction().commit();
		//}
		session.close();
	}
	
	public void closeConnection() {
		factory.close();
	}

}

