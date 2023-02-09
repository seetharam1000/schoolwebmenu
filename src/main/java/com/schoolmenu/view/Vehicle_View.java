package com.schoolmenu.view;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolmenu.entity.News;
import com.schoolmenu.entity.Vehicle;
import com.schoolmenu.entity.VehicleNotification;
import com.schoolmenu.repository.NewsRepository;
import com.schoolmenu.repository.VehicleNotificationRepository;
import com.schoolmenu.repository.Vehicle_Repository;
import com.schoolmenu.service.IRoute_Service;

@Service
public class Vehicle_View implements IRoute_Service{

	@Autowired
	List<Vehicle> vehicle_List;
	
	@Autowired
	Vehicle_Repository vehicle_Repository;
	
	@Autowired
	VehicleNotificationRepository vehicleNotificationRepository;
	
	@Autowired
	NewsRepository news;
	
	@Override
	public List<Vehicle> route() {
		
		return vehicle_Repository.findAll();
	}

	@Override
	public Optional<Vehicle> maprefresh(Integer id) {
		// TODO Auto-generated method stub
		return vehicle_Repository.findById(id);
	}
	
	@Override
	public List<VehicleNotification> vehiclenotification() {
		
		return vehicleNotificationRepository.findAll();
	}

	@Override
	public List<News> news() {
		// TODO Auto-generated method stub
		return news.findAll();
	}

	
	

}
