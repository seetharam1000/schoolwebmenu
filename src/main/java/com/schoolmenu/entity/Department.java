package com.schoolmenu.entity;

import javax.persistence.*;
import org.springframework.stereotype.Component;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "dept_id") })
@Component
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dept_id; 
	
	@Column(length = 100)
	private String dept_name;

	@Column(length = 11)
	private int staff_student;

}
