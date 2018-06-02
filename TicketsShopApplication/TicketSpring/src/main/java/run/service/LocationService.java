package run.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dao.LocationDAO;
import entities.LocationObj;
import run.entity.LocationDTO;

@Service
public class LocationService {

	private LocationDAO dao=new LocationDAO();
	
	public List<LocationObj> getAllLocations(){
		return dao.selectAll();
	}
	
	public LocationObj getLocation(String id) {
		return dao.selectByID(Integer.parseInt(id));
	}
	
	public void addLocation(LocationDTO loc) {
		dao.insert(loc.getName());
	}
	
	public void updateLocation(LocationDTO loc,String id) {
		dao.update(Integer.parseInt(id), loc.getName());
	}
	
	public void deleteLocation(String id) {
		dao.delete(Integer.parseInt(id));
	}
	
}


