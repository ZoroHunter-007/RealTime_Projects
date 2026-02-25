package Java8Code;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RemoveDuplicate {

	static Map<Integer, Integer> RemoveDuplicateElement(List<Integer>list){
		
		return list.stream()
				.collect(Collectors.toMap(n -> n, n -> 1,
						(oldval,newval) -> oldval));
	}
	
	public static void main(String[] args) {
		List<Integer>arr=Arrays.asList(10,20,30,10,40,20,50);
		
		Map<Integer, Integer>map=RemoveDuplicateElement(arr);
		
		System.out.println("Elements:"+map);
	}
}
