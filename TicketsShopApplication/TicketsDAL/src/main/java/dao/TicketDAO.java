package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.TicketObj;
import hibernate.HibernateUtil;

public class TicketDAO {
	
	private SessionFactory factory=HibernateUtil.getSessionFactory();
	
	Session session;
	
	public TicketDAO() {
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<TicketObj> selectAll(){
		session=factory.getCurrentSession();
		session.beginTransaction();
		ArrayList<TicketObj> list=new ArrayList<TicketObj>();
		list=(ArrayList<TicketObj>) session.createQuery("from TicketObj").getResultList();
		session.close();
		return list;
	}
	
	public TicketObj selectByID(int ID) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		TicketObj ticket=null;
		ticket=session.get(TicketObj.class, ID);
		session.getTransaction().commit();
		session.close();
		return ticket;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<TicketObj> selectByFestivalID(Integer ID){
		session=factory.getCurrentSession();
		session.beginTransaction();
		ArrayList<TicketObj> list=new ArrayList<TicketObj>();
		list=(ArrayList<TicketObj>) session.createQuery("from TicketObj t where t.festivalID="+Integer.toString(ID)).getResultList();
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<TicketObj> selectByUserID(Integer ID){
		session=factory.getCurrentSession();
		session.beginTransaction();
		ArrayList<TicketObj> list=new ArrayList<TicketObj>();
		list=(ArrayList<TicketObj>) session.createQuery("from TicketObj t where t.userID="+Integer.toString(ID)).getResultList();
		session.close();
		return list;
	}
	
	public int insert(String name,int festivalID,float price,String type) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		int ID=0;
		TicketObj ticket=new TicketObj(name,festivalID,null,price,type,0);
		ID=(Integer) session.save(ticket);
		session.close();
		return ID;
	}
	
	public void update(int ID,String name,int festivalID,int userID,float price,int sold) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		TicketObj ticket=null;
		ticket=session.get(TicketObj.class, ID);
		if(name=="" || name==null) {
			ticket.setName(ticket.getName());
		}
		else {
			ticket.setName(name);
		}
		if(festivalID==-1) {
			ticket.setFestivalID(ticket.getFestivalID());;
		}
		else {
			ticket.setFestivalID(festivalID);
		}
		if(userID==-1) {
			ticket.setUserID(ticket.getUserID());
		}
		else {
			ticket.setUserID(userID);
		}
		if(price==-1) {
			ticket.setPrice(ticket.getPrice());
		}
		else {
			ticket.setPrice(price);
		}
		if(sold==-1) {
			ticket.setSold(ticket.getSold());
		}
		else {
			ticket.setSold(sold);
		}
		session.getTransaction().commit();
		session.close();
	}
	
	public void delete(int ID) {
		session=factory.getCurrentSession();
		session.beginTransaction();
		TicketObj ticket=null;
		ticket=session.get(TicketObj.class, ID);
		session.delete(ticket);
		session.getTransaction().commit();
		session.close();
	}
	
	public void closeConnection() {
		factory.close();
	}

}


