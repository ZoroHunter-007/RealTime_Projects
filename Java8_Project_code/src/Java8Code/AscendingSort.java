package Java8Code;

import java.util.Arrays;
import java.util.List;

public class AscendingSort {

	public  static List<Integer> AscSort(List<Integer>list){
		
		return list.stream()
				.sorted()
				.toList();
	}
	public static void main(String[] args) {
		
		List<Integer>list = Arrays.asList(78,56,99,47,10,5,89,20,74,2);
		
		List<Integer> sort=AscSort(list);
		
		System.out.println("Sorted Array:"+sort);
	}
}
