package com.schoolmenu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolmenu.entity.VehicleNotification;

@Repository
public interface VehicleNotificationRepository extends JpaRepository<VehicleNotification, Integer> {
	
}