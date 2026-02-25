package com.SpringBoot.ProjectSpringBoot.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskDTO {

	@NotBlank(message = "Title is Required")
	private String title;
	
	
	private String description;
	
	@NotBlank(message = "Status is Required")
	private String status;
	
	@NotBlank(message = "Priority is Required")
	private String priority;
	
	
	
}
