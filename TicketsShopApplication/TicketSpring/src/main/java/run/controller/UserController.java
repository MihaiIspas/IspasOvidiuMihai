package run.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import entities.UserObj;
import run.entity.UserDTO;
import run.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping(value="/festivals/userID/{id}/festivalID/{id2}",params="depositbtn")
	public String deposit1(@Valid UserDTO sum,@PathVariable String id,@PathVariable String id2) {
		service.depositUser(id, sum.getAccount());
		return "redirect:/festivals/userID/"+id+"/festivalID/"+id2;
	}
	
	@PostMapping(value="/festivals/userID/{id}/festivalID/{id2}",params="withdrawbtn")
	public String withdraw1(@Valid UserDTO sum,@PathVariable String id,@PathVariable String id2) {
		service.withdrawUser(id, sum.getAccount());
		return "redirect:/festivals/userID/"+id+"/festivalID/"+id2;
	}
	
	@PostMapping(value="/festivals/userID/{id}",params="depositbtn")
	public String deposit(@Valid UserDTO sum,@PathVariable String id) {
		service.depositUser(id, sum.getAccount());
		return "redirect:/festivals/userID/"+id;
	}
	
	@PostMapping(value="/festivals/userID/{id}",params="withdrawbtn")
	public String withdraw(@Valid UserDTO sum,@PathVariable String id) {
		service.withdrawUser(id, sum.getAccount());
		return "redirect:/festivals/userID/"+id;
	}
	
	@RequestMapping("/users")
	public String getAllUsers(Model model){
		List<UserDTO> list=service.getAllUsers();
		model.addAttribute("allUsers",list);
		model.addAttribute("userForm",new UserDTO());
		return "adminusersform";
	}
	
	@RequestMapping("/owners")
	public List<UserDTO> getAllOwners(){
		return service.getAllUsers();
	}
	
	@RequestMapping("/users/{id}")
	public UserDTO getUser(@PathVariable String id) {
		return service.getUser(id);
	}
	
	@RequestMapping("/owners/{id}")
	public UserDTO getOwner(@PathVariable String id) {
		return service.getOwner(id);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/users")
	public void addUser(@RequestBody UserDTO user) {
		service.createUser(user);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/owners")
	public void addOwner(@RequestBody UserDTO user) {
		service.createOwner(user);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/users/{id}")
	public void updateUser(@RequestBody UserDTO user,@PathVariable String id) {
		service.updateUser(user, id);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/owners/{id}")
	public void updateOwner(@RequestBody UserDTO user,@PathVariable String id) {
		service.updateUser(user, id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/users/{id}")
	public void deleteUser(@PathVariable String id) {
		service.deleteUser(id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/owners/{id}")
	public void deleteOwner(@PathVariable String id) {
		service.deleteUser(id);
	}
	
}

