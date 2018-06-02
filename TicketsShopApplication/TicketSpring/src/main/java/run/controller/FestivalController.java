package run.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import dao.FestivalToLocationDAO;
import dao.LocationDAO;
import dao.OwnerToFestivalDAO;
import dao.TicketDAO;
import dao.UserDAO;
import entities.FestivalObj;
import entities.FestivalToLocationObj;
import entities.LocationObj;
import entities.OwnerToFestivalObj;
import entities.TicketObj;
import entities.UserObj;
import run.entity.FestivalDTO;
import run.entity.LocationDTO;
import run.entity.TicketDTO;
import run.entity.UserDTO;
import run.service.FestivalService;
import run.convert.ConvertEntitesToDTOs;

@Controller
public class FestivalController {
	
	@Autowired
	private FestivalService service;
	
	UserDAO dao=new UserDAO();
	FestivalToLocationDAO flDAO=new FestivalToLocationDAO();
	OwnerToFestivalDAO ofDAO=new OwnerToFestivalDAO();
	LocationDAO locDAO=new LocationDAO();
	TicketDAO tDAO=new TicketDAO();
	
	String selLocID;
	String selFestID;
	
	List<String> locList=new ArrayList<String>();
	List<String> ownerList=new ArrayList<String>();
	
	int ifSearch=0;
	List<FestivalDTO> searchList;
	
	@GetMapping("/festivals/userID/{id}")
	public String getAllFestivals(Model model,@PathVariable String id){
		List<FestivalDTO> list=new ArrayList<FestivalDTO>();
		list=service.getAllFestivals();
		if(ifSearch==0) {
			model.addAttribute("allFestivals",list);
		}
		else {
			model.addAttribute("allFestivals", searchList);
			ifSearch=0;
		}
		//model.addAttribute("user", userService.getByID(id));
		UserObj user=dao.selectByID(Integer.parseInt(id));
		model.addAttribute("user",user);
		model.addAttribute("sum", new UserDTO());
		//String search="";
		model.addAttribute("search",new UserDTO());
		return "festivalspanel";
	}
	
	@PostMapping(value="/festivals/userID/{id1}" ,params = "searchbtn")
	public String search(@Valid UserDTO search,@PathVariable String id1,Model model) {
		List<FestivalDTO> list=new ArrayList<FestivalDTO>();
		for(FestivalDTO f:service.getAllFestivals()) {
			if(f.getName().contains(search.getName())) {
				list.add(f);
			}
		}
		if(!(list.isEmpty())) {
			ifSearch=1;
			searchList=list;
		}
		return "redirect:/festivals/userID/"+id1;
	}
	
	@PostMapping(value="/festivals/userID/{id1}" ,params = "selectfestivalbtn")
	public String getSelectedFestival(@RequestParam(name="id") String id,@PathVariable String id1) {
		return "redirect:/festivals/userID/"+id1+"/festivalID/"+service.getFestival(id).getID();
	}
	
	@GetMapping("/festivals/userID/{id}/festivalID/{id2}")
	public String getFestival(@PathVariable String id,@PathVariable String id2,Model model) {
		FestivalDTO fest=service.getFestival(id2);
		model.addAttribute("name",fest.getName());
		model.addAttribute("genre",fest.getGenre());
		model.addAttribute("description", fest.getDescription());
		List<LocationDTO> locList=new ArrayList<LocationDTO>();
		for(FestivalToLocationObj f:flDAO.selectByFestivalID(Integer.parseInt(id2))) {
			LocationDTO loc=ConvertEntitesToDTOs.convertLocationObjToDTO(locDAO.selectByID(f.getLocationID()));
			locList.add(loc);
		}
		model.addAttribute("locationList",locList);
		List<TicketDTO> ticketList=new ArrayList<TicketDTO>();
		TicketDTO dailyTicket=new TicketDTO();
		TicketDTO generalTicket=new TicketDTO();
		TicketDTO vipTicket=new TicketDTO();
		Integer nrDaily=0;
		Integer nrGeneral=0;
		Integer nrVIP=0;
		for(TicketObj t:tDAO.selectByFestivalID(Integer.parseInt(id2))) {
			if(t.getType().equals("Daily") && t.getSold()==0) {
				dailyTicket=ConvertEntitesToDTOs.convertTicketObjToDTO(t);
				nrDaily++;
			}
			if(t.getType().equals("General Access") && t.getSold()==0) {
				generalTicket=ConvertEntitesToDTOs.convertTicketObjToDTO(t);
				nrGeneral++;
			}
			if(t.getType().equals("VIP") && t.getSold()==0) {
				vipTicket=ConvertEntitesToDTOs.convertTicketObjToDTO(t);
				nrVIP++;
			}
		}
		dailyTicket.setID(nrDaily);
		generalTicket.setID(nrGeneral);
		vipTicket.setID(nrVIP);
		ticketList.add(dailyTicket);
		ticketList.add(generalTicket);
		ticketList.add(vipTicket);
		model.addAttribute("ticketList",ticketList);
		model.addAttribute("user",ConvertEntitesToDTOs.convertUserObjToDTO(dao.selectByID(Integer.parseInt(id))));
		model.addAttribute("festivalID",id2);
		model.addAttribute("sum",new UserDTO());
		return "festivalpanel";
	}
	
	@GetMapping("/owners/{id}")
	public String getOwner(@PathVariable String id,Model model) {
		//List<FestivalObj> list=ofDAO.selectFestivalsByOwner(Integer.parseInt(id));
		List<FestivalDTO> festList=new ArrayList<FestivalDTO>();
		for(FestivalObj f:ofDAO.selectFestivalsByOwner(Integer.parseInt(id))) {
			if(f!=null) {
				FestivalDTO fest=ConvertEntitesToDTOs.convertFestivalObjToDTO(f);
				festList.add(fest);
			}
		}
		model.addAttribute("festList",festList);
		UserDTO owner=ConvertEntitesToDTOs.convertUserObjToDTO(dao.selectByID(Integer.parseInt(id)));
		model.addAttribute("owner", owner);
		model.addAttribute("festDTO", new FestivalDTO());
		model.addAttribute("location",new LocationDTO());
		List<LocationDTO> locList=new ArrayList<LocationDTO>();
		for(LocationObj l:locDAO.selectAll()) {
			LocationDTO location=ConvertEntitesToDTOs.convertLocationObjToDTO(l);
			locList.add(location);
		}
		model.addAttribute("locationList",locList);
		List<UserDTO> ownerList=new ArrayList<UserDTO>();
		for(UserObj u:dao.selectAllOwners()) {
			if(u.getID()!=Integer.parseInt(id)) {
				UserDTO o=ConvertEntitesToDTOs.convertUserObjToDTO(u);
				ownerList.add(o);
			}
		}
		model.addAttribute("otherOwners",ownerList);
		model.addAttribute("ticketDTO",new TicketDTO());
		return "ownerpanel";
	}
	
	@PostMapping(value="/festivals/userID/{id}/festivalID/{id2}",params="buybtn")
	public String buyTicket(@RequestParam(name="price") Float price,@RequestParam(name="type") String type,@PathVariable String id,@PathVariable String id2) {
		service.sellTicket(Integer.parseInt(id), Integer.parseInt(id2), type, price);
		return "redirect:/festivals/userID/"+id+"/festivalID/"+id2;
	}
	
	@PostMapping(value= "/owners/{id1}",params = "selectlocationbtn1")
	public String getSelectedLocationUD(@RequestParam(name="locid1") String id,@PathVariable String id1) {
		selLocID=id;
		//System.out.println(selLocID);
		return "redirect:/owners/"+id1;
	}
	
	@PostMapping(value= "/owners/{id1}",params = "selectlocationbtn")
	public String getSelectedLocation(@RequestParam(name="locid") String id,@PathVariable String id1) {
		locList.add(id);
		System.out.println(locList.toString());
		return "redirect:/owners/"+id1;
	}
	
	@PostMapping(value= "/owners/{id1}",params = "selectownerbtn1")
	public String getSelectedOwner(@RequestParam(name="ownerid") String id,@PathVariable String id1) {
		ownerList.add(id);
		System.out.println(ownerList.toString());
		return "redirect:/owners/"+id1;
	}
	
	@PostMapping(value= "/owners/{id1}",params = "selectfestivalbtn")
	public String getSelectedFestivalOwner(@RequestParam(name="id") String id,@PathVariable String id1) {
		selFestID=id;
		return "redirect:/owners/"+id1;
	}
	
	@PostMapping(value="/owners/{id}", params="insertlocbtn")
	public String insertLocation(@Valid LocationDTO location,@PathVariable String id) {
		locDAO.insert(location.getName());
		return "redirect:/owners/"+id;
	}
	
	@PostMapping(value="/owners/{id}", params="updatelocbtn")
	public String updateLocation(@Valid LocationDTO location,@PathVariable String id) {
		locDAO.update(Integer.parseInt(selLocID), location.getName());
		return "redirect:/owners/"+id;
	}
	
	@PostMapping(value="/owners/{id}", params="deletelocbtn")
	public String deleteLocation(@Valid LocationDTO location,@PathVariable String id) {
		locDAO.delete(Integer.parseInt(selLocID));
		return "redirect:/owners/"+id;
	}
	
	@PostMapping(value="/owners/{id}", params="insertfestbtn")
	public String insertFestival(@Valid FestivalDTO festDTO,@PathVariable String id) {
		int festID=service.addFestival(festDTO);
		if(!(locList.isEmpty())) {
			Set<String> set=new HashSet<String>();
			set.addAll(locList);
			locList.clear();
			locList.addAll(set);
			for(String s:locList) {
				flDAO.insert(festID, Integer.parseInt(s));
			}
		}
		if(!(ownerList.isEmpty())) {
			Set<String> set=new HashSet<String>();
			set.addAll(ownerList);
			ownerList.clear();
			ownerList.addAll(set);
			for(String s:ownerList) {
				ofDAO.insert(festID, Integer.parseInt(s));
			}
		}
		ofDAO.insert(festID, Integer.parseInt(id));
		locList=new ArrayList<String>();
		ownerList=new ArrayList<String>();
		return "redirect:/owners/"+id;
	}
	
	@PostMapping(value="/owners/{id1}", params="updatefestbtn")
	public String updateFestival(@Valid FestivalDTO festDTO,@PathVariable String id1) {
		service.updateFestival(festDTO, selFestID);
		return "redirect:/owners/"+id1;
	}
	
	@PostMapping(value="/owners/{id1}", params="deletefestbtn")
	public String deleteFestival(@PathVariable String id1) {
		service.deleteFestival(selFestID);
		for(FestivalToLocationObj l:flDAO.selectByFestivalID(Integer.parseInt(selFestID))) {
			flDAO.delete(l.getID());
		}
		for(OwnerToFestivalObj o:ofDAO.selectByFestivalID(Integer.parseInt(selFestID))) {
			ofDAO.delete(o.getID());
		}
		return "redirect:/owners/"+id1;
	}
	
	@PostMapping(value="/owners/{id1}", params="insertdailybtn")
	public String insertDailyTickets(@Valid TicketDTO ticketDTO,@PathVariable String id1) {
		for(int i=0;i<ticketDTO.getID();i++) {
			tDAO.insert(ticketDTO.getName(), Integer.parseInt(selFestID), ticketDTO.getPrice(), "Daily");
		}
		return "redirect:/owners/"+id1;
	}
	
	@PostMapping(value="/owners/{id1}", params="insertgeneralbtn")
	public String insertGeneralTickets(@Valid TicketDTO ticketDTO,@PathVariable String id1) {
		for(int i=0;i<ticketDTO.getID();i++) {
			tDAO.insert(ticketDTO.getName(), Integer.parseInt(selFestID), ticketDTO.getPrice(), "General Access");
		}
		return "redirect:/owners/"+id1;
	}
	
	@PostMapping(value="/owners/{id1}", params="insertvipbtn")
	public String insertVIPTickets(@Valid TicketDTO ticketDTO,@PathVariable String id1) {
		for(int i=0;i<ticketDTO.getID();i++) {
			tDAO.insert(ticketDTO.getName(), Integer.parseInt(selFestID), ticketDTO.getPrice(), "VIP");
		}
		return "redirect:/owners/"+id1;
	}
	
	
	
}

