package run.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import entities.LocationObj;
import run.entity.LocationDTO;
import run.service.LocationService;

@RestController
public class LocationController {
	
	@Autowired
	private LocationService service;
	
	@RequestMapping("/festivals/{id}/locations")
	public List<LocationObj> getAllLocations(){
		return service.getAllLocations();
	}
	
	@RequestMapping("/festivals/{id}/locations/{id}")
	public LocationObj getLocation(@PathVariable String id) {
		return service.getLocation(id);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/festivals/{id}/locations")
	public void addLocation(@RequestBody LocationDTO loc) {
		service.addLocation(loc);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/festivals/{id}/locations/{id}")
	public void updateLocation(@RequestBody LocationDTO loc,@PathVariable String id) {
		service.updateLocation(loc,id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/festivals/{id}/locations/{id}")
	public void deleteFestival(@PathVariable String id) {
		service.deleteLocation(id);
	}
	
}


