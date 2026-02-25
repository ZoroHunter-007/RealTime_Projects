package Java_AssessmentCode;

public class ArrayIndexException {

	static void ArrayBound(int []arr) {
		try {
			System.out.println(arr[1]);
		}
		catch(Exception e) {
			System.out.println("You enter the wrong index value...!");
			
		}
	}
	
	public static void main(String[] args) {
		int []arr= {1,2,3};
		ArrayBound(arr);
	}
}
