package com.schoolmenu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolmenu.entity.Vehicle;

@Repository
public interface Vehicle_Repository extends JpaRepository<Vehicle, Integer> {
	
}