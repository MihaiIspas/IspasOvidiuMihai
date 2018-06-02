package run.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dao.FestivalDAO;
import dao.OwnerToFestivalDAO;
import dao.TicketDAO;
import dao.UserDAO;
import entities.FestivalObj;
import entities.TicketObj;
import entities.UserObj;
import run.entity.FestivalDTO;
import run.convert.ConvertEntitesToDTOs;

@Service
public class FestivalService {

	private FestivalDAO dao=new FestivalDAO();
	
	private TicketDAO tDAO=new TicketDAO();
	private UserDAO uDAO=new UserDAO();
	private OwnerToFestivalDAO oDAO=new OwnerToFestivalDAO();
	
	public List<FestivalDTO> getAllFestivals(){
		List<FestivalDTO> list=new ArrayList<FestivalDTO>();
		List<FestivalObj> list1=(List<FestivalObj>)dao.selectAll();
		for(FestivalObj f:list1) {
			FestivalDTO fDTO=ConvertEntitesToDTOs.convertFestivalObjToDTO(f);
			list.add(fDTO);
		}
		return list;
	}
	
	public void sellTicket(int userID,int festivalID,String type,float price) {
		ArrayList<TicketObj> list=new ArrayList<TicketObj>();
		list=tDAO.selectByFestivalID(festivalID);
		int ID=0;
		if(type.equals("Daily")) {
			for(TicketObj t:list) {
				if(t.getType().equals("Daily")) {
					if(t.getSold()==0) {
						ID=t.getID();
					}
				}
			}
		}
		if(type.equals("General Access")) {
			for(TicketObj t:list) {
				if(t.getType().equals("General Access")) {
					if(t.getSold()==0) {
						ID=t.getID();
					}
				}
			}
		}
		if(type.equals("VIP")) {
			for(TicketObj t:list) {
				if(t.getType().equals("VIP")) {
					if(t.getSold()==0) {
						ID=t.getID();
					}
				}
			}
		}
		if(ID!=0) {
			tDAO.update(ID,"",-1,userID,-1,1);
			float acc=uDAO.selectByID(userID).getAccount();
			if(acc>=price) {
				float newacc=acc-price;
				uDAO.update(userID, "", "", "", newacc);
				List<UserObj> oList=oDAO.selectOwnersByFestival(userID);
				System.out.println(oList.toString());
				for(UserObj o:oList) {
					float oacc=o.getAccount();
					float newoacc=oacc+(float)price/oList.size();
					uDAO.update(o.getID(), "", "", "", newoacc);
				}
			}
		}
		
	}
	
	public FestivalDTO getFestival(String id) {
		FestivalDTO fest=ConvertEntitesToDTOs.convertFestivalObjToDTO(dao.selectByID(Integer.parseInt(id)));
		return fest;
	}
	
	public int addFestival(FestivalDTO fest) {
		int id=dao.insert(fest.getName(), fest.getGenre(), fest.getDescription());
		return id;
	}
	
	public void updateFestival(FestivalDTO fest,String id) {
		try {
			dao.update(Integer.parseInt(id), fest.getName(), fest.getGenre(), fest.getDescription());
		} catch(IllegalArgumentException e){
			
		}
	}
	
	public void deleteFestival(String id) {
		try {
			dao.delete(Integer.parseInt(id));
		} catch(IllegalArgumentException e){
			
		}
	}
	
}
