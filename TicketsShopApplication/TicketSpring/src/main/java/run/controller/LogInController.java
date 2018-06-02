package run.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import run.entity.UserDTO;
import run.service.UserService;

@Controller
public class LogInController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/login")
	public String logIn(Model model) {
		model.addAttribute("userDTO",new UserDTO());
		return "loginpanel";
	}
	
	@PostMapping("/login")
	public String checkLogIn(@Valid UserDTO userDTO,Model model) {
		if(userDTO.getPassword().equals("") || userDTO.getUserName().equals("")) {
			model.addAttribute("state","Credentials error");
			return "loginpanel";
		}
		for(UserDTO u:service.getAll()) {
			if(u.getUserName().equals(userDTO.getUserName()) && u.getPassword().equals(userDTO.getPassword())) {
				System.out.println(u.toString());
				if(u.getType().equals("U")) {
					//service.closeConnection();
					return "redirect:/festivals/userID/"+u.getID();
				}
				if(u.getType().equals("O")) {
					return "redirect:/owners/"+u.getID();
				}
				if(u.getType().equals("A")) {
					return "redirect:/admin";
				}
			}
		}
		model.addAttribute("state","Wrong user name or password");
		return "loginpanel";
	}
	

}
