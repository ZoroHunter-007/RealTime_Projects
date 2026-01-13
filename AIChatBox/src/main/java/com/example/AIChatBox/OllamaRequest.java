package com.example.AIChatBox;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OllamaRequest {

	private String model;
	private List<Message> messages;
	private boolean stream=false;
	
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Message{
		private String role;
		private String content;
	}
	
}
