package run.convert;

import entities.FestivalObj;
import entities.LocationObj;
import entities.TicketObj;
import entities.UserObj;
import run.entity.FestivalDTO;
import run.entity.LocationDTO;
import run.entity.TicketDTO;
import run.entity.UserDTO;

public abstract class ConvertEntitesToDTOs {
	
	public static FestivalDTO convertFestivalObjToDTO(FestivalObj fest) {
		FestivalDTO festDTO=new FestivalDTO();
		festDTO.setID(Integer.toString(fest.getID()));
		festDTO.setName(fest.getName());
		festDTO.setGenre(fest.getGenre());
		festDTO.setDescription(fest.getDescription());
		return festDTO;
	}
	
	public static LocationDTO convertLocationObjToDTO(LocationObj loc) {
		LocationDTO locDTO=new LocationDTO();
		locDTO.setID(loc.getID());
		locDTO.setName(loc.getName());
		return locDTO;
	}
	
	public static TicketDTO convertTicketObjToDTO(TicketObj ticket) {
		TicketDTO tDTO=new TicketDTO();
		tDTO.setID(ticket.getID());
		tDTO.setFestivalID(ticket.getFestivalID());
		tDTO.setUserID(ticket.getUserID());
		tDTO.setName(ticket.getName());
		tDTO.setPrice(ticket.getPrice());
		tDTO.setSold(ticket.getSold());
		tDTO.setType(ticket.getType());
		return tDTO;
	}
	
	public static UserDTO convertUserObjToDTO(UserObj user) {
		UserDTO userDTO=new UserDTO();
		userDTO.setID(Integer.toString(user.getID()));
		userDTO.setName(user.getName());
		userDTO.setUserName(user.getUserName());
		userDTO.setPassword(user.getPassword());
		userDTO.setAccount(user.getAccount());
		userDTO.setType(user.getType());
		return userDTO;
	}
	
	public static FestivalObj convertFestivalDTOToObj(FestivalDTO fest) {
		FestivalObj festObj=new FestivalObj();
		festObj.setID(Integer.parseInt(fest.getID()));
		festObj.setName(fest.getName());
		festObj.setGenre(fest.getGenre());
		festObj.setDescription(fest.getDescription());
		return festObj;
	}
	
	public static LocationObj convertLocationDTOToObj(LocationDTO loc) {
		LocationObj locObj=new LocationObj();
		locObj.setID(loc.getID());
		locObj.setName(loc.getName());
		return locObj;
	}
	
	public static UserObj convertUserDTOToObj(UserDTO user) {
		UserObj userObj=new UserObj();
		userObj.setID(Integer.parseInt(user.getID()));
		userObj.setName(user.getName());
		userObj.setUserName(user.getUserName());
		userObj.setPassword(user.getPassword());
		userObj.setAccount(user.getAccount());
		userObj.setType(user.getType());
		return userObj;
	}
	
	public static TicketObj convertTicketDTOToObj(TicketDTO ticket) {
		TicketObj t=new TicketObj();
		t.setID(ticket.getID());
		t.setFestivalID(ticket.getFestivalID());
		t.setUserID(ticket.getUserID());
		t.setName(ticket.getName());
		t.setPrice(ticket.getPrice());
		t.setSold(ticket.getSold());
		return t;
	}
	
	

}
