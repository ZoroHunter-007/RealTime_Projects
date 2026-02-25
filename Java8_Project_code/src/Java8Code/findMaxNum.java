package Java8Code;

import java.util.Arrays;
import java.util.List;

public class findMaxNum {

	public static int findMax(List<Integer>list) {
		
		return list.stream()
				.max((a,b) -> a.compareTo(b))
				.get();
				
	}
	public static void main(String[] args) {
		List<Integer>list=Arrays.asList(10,25,35,20,89,2);
		int max=findMax(list);
		
		System.out.println("Max Number:"+ max);
	}
}
