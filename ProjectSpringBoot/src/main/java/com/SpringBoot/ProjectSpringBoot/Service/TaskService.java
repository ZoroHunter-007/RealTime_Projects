package com.SpringBoot.ProjectSpringBoot.Service;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.SpringBoot.ProjectSpringBoot.DTO.TaskDTO;
import com.SpringBoot.ProjectSpringBoot.Entity.Task;
import com.SpringBoot.ProjectSpringBoot.Entity.TaskPriority;
import com.SpringBoot.ProjectSpringBoot.Entity.TaskStatus;
import com.SpringBoot.ProjectSpringBoot.Entity.User;
import com.SpringBoot.ProjectSpringBoot.Repository.TaskRepository;
import com.SpringBoot.ProjectSpringBoot.Repository.UserRepository;
import com.SpringBoot.ProjectSpringBoot.Response.TaskResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

	private final UserRepository userRepository;
	private final TaskRepository taskRepository;
	
	public TaskResponse createTask(TaskDTO dto) {
		String email=SecurityContextHolder.getContext()
				.getAuthentication()
				.getName();
		
		User user=userRepository.findByEmail(email)
				.orElseThrow(()-> new RuntimeException("Email is Existes"));
		
		Task task=Task.builder()
				.title(dto.getTitle())
				.description(dto.getDescription())
				.taskStatus(TaskStatus.valueOf(dto.getStatus()))
				.taskPriority(TaskPriority.valueOf(dto.getPriority()))
				.createdAt(LocalDateTime.now())
				.user(user)
				.build();
		
		taskRepository.save(task);
		
		return TaskResponse.builder()
				.id(task.getId())
				.title(task.getTitle())
				.description(task.getDescription())
				.priority(task.getTaskPriority().name())
				.status(task.getTaskStatus().name())
				.userId(user.getId())
				.build();		
		
	}
	
	public Page<TaskResponse>getTasksForLoggedInUser(int page,
			int size
			,String sortBy
			,String direction
			,String status
			,String priority){
		
		String email=SecurityContextHolder.getContext()
				.getAuthentication()
				.getName();
		
		User user=userRepository.findByEmail(email)
				.orElseThrow(()-> new RuntimeException("User not found"));
		
		Sort sort=direction.equalsIgnoreCase("desc")?
				Sort.by(sortBy).descending():
					Sort.by(sortBy).ascending();
		Pageable pageable=PageRequest.of(page, size, sort);
		TaskStatus taskStatus=(status!=null)?
				TaskStatus.valueOf(status.toUpperCase()):null;
		TaskPriority taskPriority=(status!=null)?
				TaskPriority.valueOf(status.toUpperCase()):null;
		Page<Task>taskpage=taskRepository.findTaskWithFilters(
					user.getId()
					,taskStatus
					,taskPriority
					,pageable
					
				);
		return taskpage.map(task->
		new TaskResponse(
				task.getId(),
				task.getTitle(),
				task.getDescription(),
				task.getTaskStatus().name(),
				task.getTaskPriority().name(),
				user.getId()
				)
		);
	}
}
