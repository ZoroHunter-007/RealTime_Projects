package Java8Code;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EvenList {

	public static List<Integer> getEvenList(List<Integer> list){
		return list.stream()
				.filter(n -> n%2==0)
				.collect(Collectors.toList());
				
	}
	public static void main(String[] args) {
		System.out.println(getEvenList(Arrays.asList(10,15,45,20,47)));
	}
}
