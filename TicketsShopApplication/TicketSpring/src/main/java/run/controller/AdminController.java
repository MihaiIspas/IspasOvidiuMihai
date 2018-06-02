package run.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import run.entity.UserDTO;
import run.service.UserService;
import run.convert.ConvertEntitesToDTOs;

@Controller
public class AdminController {
	
	@Autowired
	UserService service;
	
	String selID;
	String selID2;
	
	//String sel;
	
	@GetMapping(value = "/admin")
	public String admin(Model model) {
		model.addAttribute("userList",service.getAllUsers());
		model.addAttribute("ownerList",service.getAllOwners());
		model.addAttribute("userDTO", new UserDTO());
		model.addAttribute("ownerDTO", new UserDTO());
		return "adminpanel";
	}
	
	@PostMapping(value= "/admin",params = "selectuserbtn")
	public String getSelectedUser(@RequestParam(name="id") String id) {
		selID=id;
		return "redirect:/admin";
	}
	
	@PostMapping(value= "/admin",params = "selectownerbtn")
	public String getSelectedOwner(@RequestParam(name="id") String id) {
		selID2=id;
		return "redirect:/admin";
	}
	
	@PostMapping(value = "/admin",params = "insertuserbtn")
	public String insertUser(@Valid UserDTO userDTO) {
		service.createUser(userDTO);
		return "redirect:/admin";
	}
	
	@PostMapping(value = "/admin",params = "insertownerbtn")
	public String insertOwner(@Valid UserDTO userDTO) {
		service.createOwner(userDTO);
		return "redirect:/admin";
	}
	
	@PostMapping(value = "/admin",params = "updateuserbtn")
	public String updateUser(@Valid UserDTO userDTO) {
		service.updateUser(userDTO, selID);
		return "redirect:/admin";
	}
	
	@PostMapping(value = "/admin",params = "updateownerbtn")
	public String updateOwner(@Valid UserDTO userDTO) {
		service.updateUser(userDTO, selID2);
		return "redirect:/admin";
	}
	
	@PostMapping(value = "/admin",params = "deleteuserbtn")
	public String deleteUser() {
		service.deleteUser(selID);
		return "redirect:/admin";
	}
	
	@PostMapping(value = "/admin",params = "deleteownerbtn")
	public String deleteOwner() {
		service.deleteUser(selID2);
		return "redirect:/admin";
	}


}
