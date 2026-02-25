package Java_AssessmentCode;

public class ArithmeticException {

	static void divide(int a,int b) {
		try {
			int result=a/b;
			System.out.println("Result:"+result);
		}
		catch (Exception e) {
			System.out.println(a+" Can't divided by zero");
		}
	
	}
	
	public static void main(String[] args) {
		divide(10, 0);
	}
}
