package com.schoolmenu.service;

import java.util.List;

import com.schoolmenu.entity.*;

public interface IProfile_Service {

	public List<Department> departments(Integer admin_id);

	public List<Profile_Category> profile_category();

	public List<Profile_Details_Staff> getStaffProfiles(Profile_Details_Staff userParams);
	
	public List<Profile_Details_Staff> getStudentProfiles(Profile_Details_Staff userParams);
	
	public Profile_Details_Staff login_credentials_staff(String email_id, String password);
	
	public Profile_Details_Staff login_credentials_student(String email_id, String password);
	
	public Profile_Details_Staff register_Student_Credentials(Profile_Details_Staff registerParams);
	
	public Profile_Details_Staff register_Staff_Credentials(Profile_Details_Staff registerParams);
	
	public Profile_Details_Staff update_Student_Credentials(Profile_Details_Staff registerParams);
	
	public Profile_Details_Staff update_Staff_Credentials(Profile_Details_Staff registerParams);

	public boolean email(String email_Id);

	public boolean mobile(Long mobile_No);

	public Integer delete_Profile_Staff(String profile_Id);
	
	public Integer delete_Profile_Student(String profile_Id);
	
}