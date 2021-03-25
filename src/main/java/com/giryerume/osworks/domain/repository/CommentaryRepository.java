package com.giryerume.osworks.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giryerume.osworks.domain.model.Commentary;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Long>{
	
}
