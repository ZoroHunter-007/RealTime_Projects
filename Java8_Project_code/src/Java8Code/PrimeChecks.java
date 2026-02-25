package Java8Code;

import java.util.stream.IntStream;

public class PrimeChecks {

	public static boolean isPrime(int num) {
		
		return num>1
				&& IntStream.rangeClosed(2, (int) Math.sqrt(num))
				.noneMatch(i -> num %i ==0);
			
		
	}
	public static void main(String[] args) {
		System.out.println(isPrime(13));
	}
}
