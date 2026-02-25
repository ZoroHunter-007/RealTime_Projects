package Java8Code;

import java.util.Arrays;
import java.util.List;

public class DesendingSort {

	public static List<Integer> DesSort(List<Integer>list){
		
		return list.stream()
				.sorted((a,b) -> b.compareTo(a))
				.toList();
	}
	
	public static void main(String[] args) {
		List<Integer> arr=Arrays.asList(2,78,10,5,98,12,47,30,8);
		
		List<Integer> desc=DesSort(arr);
		System.out.println("Decending Order:"+desc);
	}
}
