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
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "bus_no") })
@Component
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bus_no; 
	
	@Column(length = 50)
	private String first_name;

	@Column(length = 50)
	private String last_name;
	
	@Column(length = 10)
	private long mobile;
	
	@Column(length = 50)
	private String route;
	
	@Column(length = 20)
	private double lat;
	
	@Column(length = 20)
	private double lon;

}
