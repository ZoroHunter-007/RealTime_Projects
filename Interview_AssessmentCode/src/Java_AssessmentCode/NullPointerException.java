package Java_AssessmentCode;

public class NullPointerException {

	static void printLength(String str) {

		try {
			System.out.println(str.length());
		}
		catch (Exception e) {
			System.out.println("You can't pass the String null");
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		printLength("Dhruv");
	}
}
