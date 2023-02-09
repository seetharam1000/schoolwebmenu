package com.schoolmenu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import com.schoolmenu.entity.*;

@Repository
public interface Profile_Detail_Repository_Student extends JpaRepository<Profile_Details_Student, Integer> {

	@Query("select count(ud) > 0 from Profile_Details_Student ud where ud.email_id = :#{#email} AND ud.password = :#{#password}")
	boolean userCredentialCheck(@Param("email") String email, @Param("password") String password);

	@Query("from Profile_Details_Student ud where ud.email_id = :#{#email} AND ud.password = :#{#password}")
	Profile_Details_Student userDetails(@Param("email") String email, @Param("password") String password);

	@Query("select case when count(ud) > 0 then true else false end from Profile_Details_Student ud where ud.email_id = :#{#email_Id}")
	boolean emailExist(@Param("email_Id") String emailid);

	@Query("select case when count(ud) > 0 then true else false end from Profile_Details_Student ud where ud.mobile_no = :#{#mobile_no}")
	boolean mobileExist(@Param("mobile_no") Long mobileno);
	
	@Query("select case when count(ud) > 0 then true else false end from  Profile_Details_Student ud where ud.mobile_no = :#{#mobile_no} or ud.email_id = :#{#email_Id}")
	boolean registerCredCheck(@Param("mobile_no") Long mobile_no, @Param("email_Id") String email_id);
	
	@Query("from Profile_Details_Student pd where pd.profile_id = :profile_id")
	List<Profile_Details_Student> profile_Details_Category_Id(@Param("profile_id") String profile_id);
	
//	@Query("from Profile_Details pd where pd.dept_id = :dept_id")
//	List<Profile_Details> profile_Details_Dept_Id(@Param("dept_id") Integer profile_id);
	
//	@Query("from Profile_Details pd where pd.profile_category_id = :profile_category_id")
//	List<Profile_Details> profile_Details_Id(@Param("profile_category_id") Integer profile_category_id);

	@Query("from Profile_Details_Student pd where pd.profile_id = :profile_id")
	List<Profile_Details_Student> profile_Details_Profile_Id(@Param("profile_id") String profile_id);
	
	@Query("from Profile_Details_Student pd where pd.profile_catg.profile_category_id = :profile_category_id AND pd.dept.dept_id = :dept_id")
	List<Profile_Details_Student> profile_Details_Profile_Category_Dept(@Param("profile_category_id") Long profile_category_id, @Param("dept_id") Long dept_id);
	
	@Query("from Profile_Details_Student pd where pd.mobile_no = :mobile_no")
	List<Profile_Details_Student> profile_Details_Mobile(@Param("mobile_no") Long mobile_no);

	@Query("From Profile_Details_Student ud where (ud.email_id = :email_id OR ud.profile_id = :email_id) AND ud.password = :password")
	Profile_Details_Student getUserDetails(@Param("email_id") String email_id, @Param("password") String password);

	@Modifying
	@Query("Delete from Profile_Details_Staff pd where pd.profile_id = :profile_id")
	Integer delete_Profile_Student(@Param("profile_id") String profile_id);
	
}