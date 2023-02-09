package com.schoolmenu.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.stereotype.Component;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
@Table(name = "profile_details_student", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "profile_id"),
        @UniqueConstraint(columnNames = "mobile_no"),
        @UniqueConstraint(columnNames = "email_id") })
@Component
public class Profile_Details_Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11)
	private Integer id;

	@Column(length = 11)
	private String profile_id;

	@Column(length = 100)
	private String first_name;

	@Column(length = 100)
	private String last_name;

	@Column(length = 100)
	private String password;

	@Column(length = 6)
	private Timestamp dob;

	@Column(length = 10)
	private Long mobile_no;
 
	@Column(length = 50) 
	private String email_id;
	
	@Column(length = 256) 
	private String profile_url;

	@OneToOne
	private Profile_Category profile_catg;
	
	@OneToOne
    private Department dept;
	
	
}
