package com.schoolmenu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.schoolmenu.entity.Department;
import com.schoolmenu.entity.Profile_Category;
import com.schoolmenu.entity.Profile_Details_Staff;
import com.schoolmenu.service.IProfile_Service;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/schoolmenu")
public class Profile_Controller_Menu {

	@Autowired
	private IProfile_Service profile_Service;

	@Autowired
	Profile_Details_Staff profile_Details;

	@Autowired
	List<Profile_Details_Staff> profile_Details_List;

	@Autowired
	List<Profile_Category> profile_Category_List;

	@Autowired
	List<Department> department_List;
	
	@RequestMapping(value = "/email/{email_Id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean email(@PathVariable String email_Id) {
		
		boolean emailExist = false;
		
		emailExist = profile_Service.email(email_Id);
		
		return emailExist;
	}
	
	@RequestMapping(value = "/mobile/{mobile_No}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean mobile(@PathVariable Long mobile_No) {
		
		boolean mobileExist = false;
		
		mobileExist = profile_Service.mobile(mobile_No);
		
		return mobileExist;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Profile_Details_Staff register(@RequestBody Profile_Details_Staff registerParams) { 
		
		Profile_Details_Staff userRegister = null;
		
		if(registerParams.getId() == null || registerParams.getId().equals(0) || registerParams.getId() == 0) {
			
		if(registerParams.getProfile_catg().getProfile_category_id() == 3)
			userRegister = profile_Service.register_Student_Credentials(registerParams);
		
		if(registerParams.getProfile_catg().getProfile_category_id() == 1 || registerParams.getProfile_catg().getProfile_category_id() == 2)
			userRegister = profile_Service.register_Staff_Credentials(registerParams);
		
			return userRegister;
		}
		
		if(registerParams.getProfile_catg().getProfile_category_id() == 3)
			userRegister = profile_Service.update_Student_Credentials(registerParams);
		
		if(registerParams.getProfile_catg().getProfile_category_id() == 1 || registerParams.getProfile_catg().getProfile_category_id() == 2)
			userRegister = profile_Service.update_Staff_Credentials(registerParams);
		
		return userRegister;
	}
	
	@RequestMapping(value = "/login/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Profile_Details_Staff login(@RequestBody Profile_Details_Staff userParams) {
		
		if(userParams.getProfile_catg().getProfile_category_id() == 2) {
			profile_Details = profile_Service.login_credentials_staff(userParams.getEmail_id(), userParams.getPassword());
		}
		
		if(userParams.getProfile_catg().getProfile_category_id() == 3) {
			profile_Details = profile_Service.login_credentials_student(userParams.getEmail_id(), userParams.getPassword());		
		}
		
		return profile_Details;
	}
	
	 
	@RequestMapping(value = "/profile_category",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Profile_Category> profileCategory() { 
		
		profile_Category_List = profile_Service.profile_category();
		
		return profile_Category_List;
	}
	
	@RequestMapping(value = "/departments/{admin_id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Department> departments(@PathVariable Integer admin_id) {
		
		department_List = profile_Service.departments(admin_id);
		
		return department_List;
	}
	
	@RequestMapping(value = "/profiles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Profile_Details_Staff> getProfiles(@RequestBody Profile_Details_Staff userParams) {
		
		if(userParams.getProfile_catg().getProfile_category_id() != null) {
		if(userParams.getProfile_catg().getProfile_category_id() == 1 || userParams.getProfile_catg().getProfile_category_id() == 2) {
			profile_Details_List = profile_Service.getStaffProfiles(userParams);
			
			return profile_Details_List;
		}
		
		if(userParams.getProfile_catg().getProfile_category_id() == 3) {
			profile_Details_List = profile_Service.getStudentProfiles(userParams);
			
			return profile_Details_List;
		}
		}
		
		profile_Details_List = profile_Service.getStudentProfiles(userParams);

		if(profile_Details_List == null || profile_Details_List.size() == 0)
			profile_Details_List = profile_Service.getStaffProfiles(userParams);
		
		return profile_Details_List;
	}
	
	@RequestMapping(value = "/deleteProfile/{profile_Id}/{profile_category_id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer deleteProfile(@PathVariable String profile_Id, @PathVariable Long profile_category_id) {
		
		Integer status = null;
		
		if(profile_category_id == 1 || profile_category_id == 2) {
			status = profile_Service.delete_Profile_Staff(profile_Id);
			
		}
		
		if(profile_category_id == 3) {
			status = profile_Service.delete_Profile_Student(profile_Id);
			
		}
		
		return status;
	}
	
}