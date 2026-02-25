package com.example.Agentic_AI.Memory;

import java.util.List;

public interface AgentMemory {

	void addShortMemory(String message);
	
	void addLongMemory(MemoryItem item);
	
	List<String> getShortMemoryContext();
	List<MemoryItem>getLongMemoryContext();

	

	
}
