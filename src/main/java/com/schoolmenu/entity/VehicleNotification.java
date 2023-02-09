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
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
@Component
public class VehicleNotification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 500)
	private String comment;

	@Column(length = 100)
	private String imageUrl;

}
