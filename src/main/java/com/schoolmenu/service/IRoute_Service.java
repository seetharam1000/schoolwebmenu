package com.schoolmenu.service;

import java.util.List;
import java.util.Optional;

import com.schoolmenu.entity.News;
import com.schoolmenu.entity.Vehicle;
import com.schoolmenu.entity.VehicleNotification;

public interface IRoute_Service {

	public List<Vehicle> route();

	public Optional<Vehicle> maprefresh(Integer id);

	public List<VehicleNotification> vehiclenotification();

	public List<News> news();
	
}