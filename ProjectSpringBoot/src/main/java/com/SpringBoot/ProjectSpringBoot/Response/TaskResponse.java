package com.SpringBoot.ProjectSpringBoot.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TaskResponse {

	private Long id;
	private String title;
	private String description;
	private String status;
	private String priority;
	private Long userId;
}
