package Java8Code;

public class PalindromeString {

	public static boolean PalindroemString(String str) {
		return str.equals(new StringBuilder(str).reverse().toString());
	}
	public static void main(String[] args) {
		System.out.println("Palindroem or Not:"+ PalindroemString("madam"));
	}
}
