package com.SpringBoot.ProjectSpringBoot.Controller;



import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.ProjectSpringBoot.DTO.TaskDTO;
import com.SpringBoot.ProjectSpringBoot.Response.TaskResponse;
import com.SpringBoot.ProjectSpringBoot.Service.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/tasks")
@RequiredArgsConstructor
public class TaskController {

	private final TaskService taskService;
	
	@PostMapping
	public TaskResponse createTask(@Valid @RequestBody TaskDTO dto) {
		return taskService.createTask(dto);
	}
	
	@GetMapping("/myTask")
	public Page<TaskResponse> getMyTask(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "createdAt") String sortBy,
			@RequestParam(defaultValue = "desc") String direction,
			@RequestParam(required = false) String status,
			@RequestParam(required = false) String priority
			){
		 return taskService.getTasksForLoggedInUser(page, size, sortBy, direction,status,priority);
	}
}
