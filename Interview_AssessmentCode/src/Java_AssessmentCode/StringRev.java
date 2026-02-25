package Java_AssessmentCode;

public class StringRev{
	
	public static String RevString(String str) {
		String rev="";
		
		/*for(int i=str.length()-1;i>=0;i--) {
			rev+=str.charAt(i);
		}
		return rev;*/
		
		return new StringBuilder(str).reverse().toString();
	}
	
	public static void main(String[] args) {
		
		System.out.println("Reverse String: "+ RevString("Dhruv"));
	}
}