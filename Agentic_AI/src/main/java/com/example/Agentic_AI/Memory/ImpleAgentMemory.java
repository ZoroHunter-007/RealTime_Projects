package com.example.Agentic_AI.Memory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ImpleAgentMemory implements AgentMemory{

	private final Deque<String>shortMemory=new ArrayDeque<String>();
	private final List<MemoryItem>longMemory=new ArrayList<MemoryItem>();
	@Override
	public void addShortMemory(String message) {
		// TODO Auto-generated method stub
		if(shortMemory.size()>6) {
			shortMemory.pollFirst();
		}
		shortMemory.addLast(message);
		
	}

	@Override
	public void addLongMemory(MemoryItem item) {
		// TODO Auto-generated method stub
		longMemory.add(item);
		
	}

	@Override
	public List<String> getShortMemoryContext() {
		// TODO Auto-generated method stub
		return List.copyOf(shortMemory);
	}

	@Override
	public List<MemoryItem> getLongMemoryContext() {
		// TODO Auto-generated method stub
		return List.copyOf(longMemory);
	}

}
