package com.schoolmenu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.*;

import com.schoolmenu.entity.*;

@Repository
public interface Department_Repository extends JpaRepository<Department, Integer> {
	
	@Query("FROM Department d WHERE d.staff_student = :staff_student") 
	List<Department> getDepartments(@Param("staff_student") Integer staff_student);
	
	@Query("FROM Department d")
	List<Department> getAllDepartments();
	
}