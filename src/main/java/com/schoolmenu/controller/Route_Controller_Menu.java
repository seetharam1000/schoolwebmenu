package com.schoolmenu.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.schoolmenu.entity.News;
import com.schoolmenu.entity.Vehicle;
import com.schoolmenu.entity.VehicleNotification;
import com.schoolmenu.service.IRoute_Service;

@RestController
@RequestMapping("/vehicle")
public class Route_Controller_Menu {
	
	@Autowired
	public IRoute_Service route_Service;

	@Autowired
	List<Vehicle> vehicle_List;
	
	@RequestMapping(value = "/route",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Vehicle> vehicle() { 
		
		return route_Service.route();
	}
	
	@RequestMapping(value = "/maprefresh/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Vehicle> maprefresh(@PathVariable Integer id) {
		
		return route_Service.maprefresh(id);
	}

	@RequestMapping(value = "/vehiclenotification",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VehicleNotification> vehiclenotification() { 
		
		return route_Service.vehiclenotification();
	}
	
	@RequestMapping(value = "/news",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<News> news() { 
		
		return route_Service.news();
	}
	
}
