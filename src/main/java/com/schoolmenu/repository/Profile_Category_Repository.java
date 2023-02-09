package com.schoolmenu.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import com.schoolmenu.entity.*;

@Repository
public interface Profile_Category_Repository extends JpaRepository<Profile_Category, Integer> {
	
	
}