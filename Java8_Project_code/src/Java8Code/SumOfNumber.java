package Java8Code;

import java.util.Arrays;
import java.util.List;

public class SumOfNumber {

	public static int sumOfNumber(List<Integer>list){
		return list.stream()
				.mapToInt(Integer::intValue)
				.sum();
	}
	public static void main(String[] args) {
		System.out.println("Sum of Numbers:"+sumOfNumber(Arrays.asList(10,20,45,78,30)));
	}
}
