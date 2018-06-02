package run.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dao.UserDAO;
import entities.UserObj;
import run.entity.UserDTO;
import run.convert.ConvertEntitesToDTOs;

@Service
public class UserService {
	
	private UserDAO dao=new UserDAO();
	
	public List<UserDTO> getAll(){
		List<UserDTO> list=new ArrayList<UserDTO>();
		for(UserObj u:dao.selectAll()) {
			UserDTO user=ConvertEntitesToDTOs.convertUserObjToDTO(u);
			list.add(user);
		}
		return list;
	}
	
	public List<UserDTO> getAllUsers(){
		List<UserDTO> list=new ArrayList<UserDTO>();
		for(UserObj u:dao.selectAllUsers()) {
			UserDTO user=ConvertEntitesToDTOs.convertUserObjToDTO(u);
			list.add(user);
		}
		return list;
	}
	
	public List<UserDTO> getAllOwners(){
		List<UserDTO> list=new ArrayList<UserDTO>();
		for(UserObj u:dao.selectAllOwners()) {
			UserDTO user=ConvertEntitesToDTOs.convertUserObjToDTO(u);
			list.add(user);
		}
		return list;
	}
	
	public void closeConnection() {
		dao.closeConnection();
	}
	
	public UserDTO getByID(String id) {
		UserDTO user=ConvertEntitesToDTOs.convertUserObjToDTO(dao.selectByID(Integer.parseInt(id)));
		System.out.println(user.toString());
		return user;
	}
	
	public UserDTO getUser(String id) {
		UserDTO user=ConvertEntitesToDTOs.convertUserObjToDTO(dao.selectUserByID(Integer.parseInt(id)));
		return user;
	}
	
	public UserDTO getOwner(String id) {
		UserDTO user=ConvertEntitesToDTOs.convertUserObjToDTO(dao.selectOwnerByID(Integer.parseInt(id)));
		return user;
	}
	
	public void createUser(UserDTO user) {
		dao.insertUser(user.getName(), user.getUserName(), user.getPassword(), user.getAccount());
	}
	
	public void createOwner(UserDTO user) {
		dao.insertOwner(user.getName(), user.getUserName(), user.getPassword(), user.getAccount());
	}
	
	public void updateUser(UserDTO user,String id) {
		try {
			dao.update(Integer.parseInt(id),user.getName(), user.getUserName(), user.getPassword(), user.getAccount());
		} catch(IllegalArgumentException e){
			
		}
	}
	
	public void deleteUser(String id) {
		try {
			dao.delete(Integer.parseInt(id));
		} catch(IllegalArgumentException e){
			
		}
	}
	
	public void depositUser(String id,float sum) {
		float s=dao.selectByID(Integer.parseInt(id)).getAccount();
		s+=sum;
		dao.update(Integer.parseInt(id), "", "", "", s);
	}
	
	public void withdrawUser(String id,float sum) {
		float s=dao.selectByID(Integer.parseInt(id)).getAccount();
		s-=sum;
		dao.update(Integer.parseInt(id), "", "", "", s);
	}
	
}


