package com.example.AIChatBox;



import lombok.Data;

@Data
public class OllamaResponse {

	private Message message;
	
	@Data
	public static class Message{
		private String role;
		private String content;
	}
}
