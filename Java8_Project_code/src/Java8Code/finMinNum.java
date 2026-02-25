package Java8Code;

import java.util.Arrays;
import java.util.List;

public class finMinNum {

	public static int finMin(List<Integer> list) {
		
		return list.stream()
				.min((a,b) -> a.compareTo(b))
				.get();
	}
	public static void main(String[] args) {
		
		List<Integer>arr=Arrays.asList(20,99,1,50,78,25,5);
		System.out.println("Min Number:"+ finMin(arr));
	}
}
