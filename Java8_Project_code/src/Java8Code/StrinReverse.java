package Java8Code;

public class StrinReverse {

	public static String ReverseString (String str) {
		
		String rev=new StringBuilder(str).reverse().toString();
		return rev;
	}
	public static void main(String[] args) {
		System.out.println("Reversed String:"+ ReverseString("Dhruv"));
	}
}
