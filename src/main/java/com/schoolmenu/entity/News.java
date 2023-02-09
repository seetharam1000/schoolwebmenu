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
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 50)
	private String title;

	@Column(length = 200)
	private String url;
	
	@Column(length = 500)
	private String description;

}
