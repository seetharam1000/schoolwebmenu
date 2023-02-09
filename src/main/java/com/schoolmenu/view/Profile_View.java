package com.schoolmenu.view;

import java.io.IOException;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.schoolmenu.entity.*;
import com.schoolmenu.global.GlobalConstants;
import com.schoolmenu.repository.*;
import com.schoolmenu.service.*;

@Service
public class Profile_View implements IProfile_Service {

	@Autowired
	private Profile_Details_Staff profile_Detail_Staff;

	@Autowired
	private Profile_Details_Student profile_Detail_Student;

	@Autowired
	private Department_Repository department_Repository;

	@Autowired
	private Profile_Detail_Repository_Staff profile_Detail_Repository_Staff;

	@Autowired
	private Profile_Detail_Repository_Student profile_Detail_Repository_Student;

	@Autowired
	private Profile_Category_Repository profile_Category_Repository;

	@Autowired
	private List<Profile_Details_Staff> profileDetails;

	@Autowired
	private Department dept;

	@Autowired
	private Profile_Category pf_catgy;

	@Override
	@Transactional
	public boolean email(String email_Id) {

		boolean email_Staff_Exist = false;
		boolean email_Student_Exist = false;

		email_Staff_Exist = profile_Detail_Repository_Staff.emailExist(email_Id);

		email_Student_Exist = profile_Detail_Repository_Student.emailExist(email_Id);

		if (email_Staff_Exist || email_Student_Exist) {
			email_Staff_Exist = true;
		}

		return email_Staff_Exist;
	}

	@Override
	@Transactional
	public boolean mobile(Long mobile_No) {

		boolean mobile_Staff_Exist = false;
		boolean mobile_Student_Exist = false;

		mobile_Staff_Exist = profile_Detail_Repository_Staff.mobileExist(mobile_No);

		mobile_Student_Exist = profile_Detail_Repository_Student.mobileExist(mobile_No);

		if (mobile_Staff_Exist || mobile_Student_Exist) {
			mobile_Staff_Exist = true;
		}

		return mobile_Staff_Exist;
	}

	@Override
	@Transactional
	public Profile_Details_Staff register_Staff_Credentials(Profile_Details_Staff profile_Details) {
		// TODO Auto-generated method stub

		boolean registerCheck = false;

		registerCheck = profile_Detail_Repository_Staff.registerCredCheck(profile_Details.getMobile_no(),
				profile_Details.getEmail_id());

		if (!registerCheck) {

			dept.setDept_id(profile_Details.getDept().getDept_id());
			profile_Details.setDept(dept);

			pf_catgy.setProfile_category_id(profile_Details.getProfile_catg().getProfile_category_id());
			profile_Details.setProfile_catg(pf_catgy);

			profile_Detail_Repository_Staff.saveAndFlush(profile_Details);

			String profile_id = "Stf" + profile_Details.getId();

			if (profile_Details.getProfile_url().equals("yes")) {
				profile_Details.setProfile_url(GlobalConstants.DIRECTORY + GlobalConstants.PROFILE_IMAGE_PATH
						+ profile_id + GlobalConstants.IMAGE_FORMAT);
			} else {
				profile_Details.setProfile_url("");
			}

			profile_Detail_Repository_Staff.update_Profile_Staff_ById(profile_id, profile_Details.getProfile_url(),
					profile_Details.getId());
			profile_Details.setProfile_id(profile_id);
		} else {
			profile_Details.setProfile_id(null);
		}
		return profile_Details;
	}

	@Override
	@Transactional
	public Profile_Details_Staff register_Student_Credentials(Profile_Details_Staff profile_Details) {
		// TODO Auto-generated method stub

		boolean registerCheck = false;

		registerCheck = profile_Detail_Repository_Student.registerCredCheck(profile_Details.getMobile_no(),
				profile_Details.getEmail_id());

		if (!registerCheck) {

			Profile_Details_Student pdf = new Profile_Details_Student();
			pdf.setProfile_id(profile_Details.getProfile_id());
			pdf.setFirst_name(profile_Details.getFirst_name());
			pdf.setLast_name(profile_Details.getLast_name());
			pdf.setPassword(profile_Details.getPassword());
			pdf.setDob(profile_Details.getDob());
			pdf.setMobile_no(profile_Details.getMobile_no());
			pdf.setEmail_id(profile_Details.getEmail_id());
			pdf.setProfile_catg(profile_Details.getProfile_catg());
			pdf.setDept(profile_Details.getDept());

			dept.setDept_id(pdf.getDept().getDept_id());
			pdf.setDept(dept);

			pf_catgy.setProfile_category_id(pdf.getProfile_catg().getProfile_category_id());
			pdf.setProfile_catg(pf_catgy);

			profile_Detail_Repository_Student.save(pdf);

			String profile_id = "Std" + pdf.getId();
			profile_Details.setId(pdf.getId());
			profile_Details.setProfile_id(profile_id);
			pdf.setProfile_id(profile_id);
			if (profile_Details.getProfile_url().equals("yes")) {
			pdf.setProfile_url(GlobalConstants.DIRECTORY + GlobalConstants.PROFILE_IMAGE_PATH + profile_id
					+ GlobalConstants.IMAGE_FORMAT);
			} else {
				pdf.setProfile_url("");
			}
			
			profile_Detail_Repository_Student.save(pdf);

		} else {
			profile_Details.setProfile_id(null);
		}
		return profile_Details;
	}

	@Override
	@Transactional
	public Profile_Details_Staff update_Staff_Credentials(Profile_Details_Staff profile_Details) {
		// TODO Auto-generated method stub

		profile_Detail_Repository_Staff.findById(profile_Details.getId());
		dept.setDept_id(profile_Details.getDept().getDept_id());
		profile_Details.setDept(dept);

		pf_catgy.setProfile_category_id(profile_Details.getProfile_catg().getProfile_category_id());
		profile_Details.setProfile_catg(pf_catgy);

		profile_Detail_Repository_Staff.save(profile_Details);

		return profile_Details;
	}

	@Override
	@Transactional
	public Profile_Details_Staff update_Student_Credentials(Profile_Details_Staff profile_Details) {
		// TODO Auto-generated method stub
		profile_Detail_Repository_Student.findById(profile_Details.getId());
		Profile_Details_Student pdf = new Profile_Details_Student();
		pdf.setId(profile_Details.getId());
		pdf.setProfile_id(profile_Details.getProfile_id());
		pdf.setFirst_name(profile_Details.getFirst_name());
		pdf.setLast_name(profile_Details.getLast_name());
		pdf.setPassword(profile_Details.getPassword());
		pdf.setDob(profile_Details.getDob());
		pdf.setMobile_no(profile_Details.getMobile_no());
		pdf.setEmail_id(profile_Details.getEmail_id());

		dept.setDept_id(profile_Details.getDept().getDept_id());
		pdf.setDept(dept);

		pf_catgy.setProfile_category_id(profile_Details.getProfile_catg().getProfile_category_id());
		pdf.setProfile_catg(pf_catgy);

		profile_Detail_Repository_Student.save(pdf);

		return profile_Details;
	}

	@Override
	@Transactional(readOnly = true)
	public Profile_Details_Staff login_credentials_staff(String email_id, String password) {
		// TODO Auto-generated method stub

		profile_Detail_Staff = profile_Detail_Repository_Staff.getUserDetails(email_id, password);

		return profile_Detail_Staff;
	}

	@Override
	@Transactional(readOnly = true)
	public Profile_Details_Staff login_credentials_student(String email_id, String password) {
		// TODO Auto-generated method stub

		profile_Detail_Student = profile_Detail_Repository_Student.getUserDetails(email_id, password);

		if (profile_Detail_Student != null) {
			profile_Detail_Staff = new Profile_Details_Staff();
			profile_Detail_Staff.setId(profile_Detail_Student.getId());
			profile_Detail_Staff.setProfile_id(profile_Detail_Student.getProfile_id());
			profile_Detail_Staff.setFirst_name(profile_Detail_Student.getFirst_name());
			profile_Detail_Staff.setLast_name(profile_Detail_Student.getLast_name());
			profile_Detail_Staff.setPassword(profile_Detail_Student.getPassword());
			profile_Detail_Staff.setDob(profile_Detail_Student.getDob());
			profile_Detail_Staff.setMobile_no(profile_Detail_Student.getMobile_no());
			profile_Detail_Staff.setEmail_id(profile_Detail_Student.getEmail_id());
			profile_Detail_Staff.setProfile_catg(profile_Detail_Student.getProfile_catg());
			profile_Detail_Staff.setDept(profile_Detail_Student.getDept());

		} else {
			profile_Detail_Staff = null;
		}

		return profile_Detail_Staff;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Profile_Category> profile_category() {
		// TODO Auto-generated method stub

		List<Profile_Category> profileCategory = new ArrayList<Profile_Category>();

		profile_Category_Repository.findAll().forEach(profile -> profileCategory.add(profile));

		return profileCategory;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Department> departments(Integer staff_student) {
		// TODO Auto-generated method stub

		if (staff_student == 2) {
			return department_Repository.findAll();

		}

		return department_Repository.getDepartments(staff_student);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Profile_Details_Staff> getStaffProfiles(Profile_Details_Staff userParams) {
		// TODO Auto-generated method stub

		profileDetails = null;

		Long profile_Category_id = userParams.getProfile_catg().getProfile_category_id();
		Long profile_Dept_id = userParams.getDept().getDept_id();
		String profile_id = userParams.getProfile_id();
		Long mobile = userParams.getMobile_no();

		if (strValidation(profile_id)) {

			profileDetails = profile_Detail_Repository_Staff
					.profile_Details_Profile_Id(userParams.getProfile_id().trim());
			return profileDetails;
		}

		if (intValidation(mobile)) {

			profileDetails = profile_Detail_Repository_Staff.profile_Details_Mobile(userParams.getMobile_no());
			return profileDetails;
		}

		if (intValidation(profile_Category_id) && intValidation(profile_Dept_id)) {

			profileDetails = profile_Detail_Repository_Staff.profile_Details_Profile_Category_Dept(
					userParams.getProfile_catg().getProfile_category_id(), userParams.getDept().getDept_id());
			return profileDetails;
		}

		if (intValidation(profile_Category_id)) {

			if (profile_Category_id == 1)
				profileDetails = profile_Detail_Repository_Staff.getAdmin(profile_Category_id);

			if (profile_Category_id == 2)
				profileDetails = profile_Detail_Repository_Staff.findAll();

			return profileDetails;
		}

		return profileDetails;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Profile_Details_Staff> getStudentProfiles(Profile_Details_Staff userParams) {
		// TODO Auto-generated method stub

		profileDetails = null;
		List<Profile_Details_Student> pds = new ArrayList<Profile_Details_Student>();

		Long profile_Category_id = userParams.getProfile_catg().getProfile_category_id();
		Long profile_Dept_id = userParams.getDept().getDept_id();
		String profile_id = userParams.getProfile_id();
		Long mobile = userParams.getMobile_no();

		if (strValidation(profile_id)) {

			pds = profile_Detail_Repository_Student.profile_Details_Profile_Id(userParams.getProfile_id().trim());
			return getExtractProfiles(pds);

		}

		if (intValidation(mobile)) {

			pds = profile_Detail_Repository_Student.profile_Details_Mobile(userParams.getMobile_no());

			return getExtractProfiles(pds);

		}

		if (intValidation(profile_Category_id) && intValidation(profile_Dept_id)) {

			pds = profile_Detail_Repository_Student.profile_Details_Profile_Category_Dept(
					userParams.getProfile_catg().getProfile_category_id(), userParams.getDept().getDept_id());
			return getExtractProfiles(pds);
		}

		if (intValidation(profile_Category_id)) {

			pds = profile_Detail_Repository_Student.findAll();

			return getExtractProfiles(pds);
		}

		return profileDetails;
	}

	public List<Profile_Details_Staff> getExtractProfiles(List<Profile_Details_Student> profile_Details) {

		Gson gson = new Gson();
		String student_Profile = gson.toJson(profile_Details);
		profileDetails = gson.fromJson(student_Profile, new TypeToken<List<Profile_Details_Staff>>() {
		}.getType());

		return profileDetails;
	}

	public boolean strValidation(String profileId) {

		String stringVal = Optional.ofNullable(profileId).orElse("");
		if (stringVal.trim().isEmpty()) {

			return false;
		}

		return true;
	}

	public boolean intValidation(Long profileId) {

		Long intVal = Optional.ofNullable(profileId).orElse(0L);
		if (intVal == 0 || intVal == null) {

			return false;
		}

		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public Integer delete_Profile_Staff(String profile_Id) {
		// TODO Auto-generated method stub
		Integer status = 0;

		status = profile_Detail_Repository_Staff.delete_Profile_Staff(profile_Id);

		return status;
	}

	@Override
	@Transactional(readOnly = false)
	public Integer delete_Profile_Student(String profile_Id) {
		// TODO Auto-generated method stub
		Integer status = 0;

		status = profile_Detail_Repository_Student.delete_Profile_Student(profile_Id);

		return status;
	}
}