package com.schoolmenu.entity;

import javax.persistence.*;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "profile_category_id")})
@Component
public class Profile_Category { 

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11)
	private Long profile_category_id;

	@Column(length = 100) 
	private String profile_type;

//	@Column(length = 11)
//	private int admin_id;
	
}
