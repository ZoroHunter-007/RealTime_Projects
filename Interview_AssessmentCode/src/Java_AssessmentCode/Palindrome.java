package Java_AssessmentCode;

public class Palindrome {

	public static boolean isPalindrome(String str) {
		String rev="";
		
		for(int i=str.length()-1;i>=0;i--) {
			rev+=str.charAt(i);
		}
		return str.equals(rev);
	}
	
	public static void main(String[] args) {
		System.out.println("Palindroem or not: "+isPalindrome("madam"));
	}
}
