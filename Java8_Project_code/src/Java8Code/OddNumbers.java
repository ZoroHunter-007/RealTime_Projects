package Java8Code;

import java.util.Arrays;
import java.util.List;

public class OddNumbers {

	public static void OddNum(List<Integer>list) {
		list.stream()
		.filter(n -> n % 2 !=0)
		.forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		List<Integer>arr=Arrays.asList(1,2,8,9,13,16,17,45,20);
		
		OddNum(arr);
	}
}
