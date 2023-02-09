package com.schoolmenu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolmenu.entity.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
	
}