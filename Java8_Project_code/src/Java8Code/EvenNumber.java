package Java8Code;

import java.util.Arrays;
import java.util.List;

public class EvenNumber {

	public static void EvenNum(List<Integer>list) {
		list.stream()
		.filter(n -> n%2==0)
		.forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		List<Integer>arr=Arrays.asList(10,45,20,78,89,30);
		
		EvenNum(arr);
	}
}
