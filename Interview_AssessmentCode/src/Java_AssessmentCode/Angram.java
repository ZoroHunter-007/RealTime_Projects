package Java_AssessmentCode;

import java.util.Arrays;

public class Angram {

	static boolean Angram_Method(String s1,String s2) {
	
		if(s1.length()!=s2.length()) {
			return false;
		}
		char[]a=s1.toCharArray();
		char[]b=s2.toCharArray();
		
		Arrays.sort(a);
		Arrays.sort(b);
		
		return Arrays.equals(a, b);
	}
	
	public static void main(String[] args) {
	System.out.println(Angram_Method("listen", "silent"));	
	}
}
