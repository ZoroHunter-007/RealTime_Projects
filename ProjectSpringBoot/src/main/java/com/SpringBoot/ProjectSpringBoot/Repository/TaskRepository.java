package com.SpringBoot.ProjectSpringBoot.Repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SpringBoot.ProjectSpringBoot.Entity.Task;
import com.SpringBoot.ProjectSpringBoot.Entity.TaskPriority;
import com.SpringBoot.ProjectSpringBoot.Entity.TaskStatus;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

	List<Task> findByUserId(Long id);
	
	@Query("""
			SELECT t FROM Task t 
			WHERE t.user.id=:userId
			AND(:taskStatus IS NULL OR t.taskStatus= :taskStatus)
			AND(:taskPriority IS NULL OR t.taskPriority= :taskPriority) 
			""")
	Page<Task>findTaskWithFilters(
			@Param("userId")Long userId,
			@Param("taskStatus")TaskStatus status,
			@Param("taskPriority")TaskPriority priority,
			Pageable pageable);
	Page<Task>findByUserId(Long id,Pageable pageable);
}
