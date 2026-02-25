package Java_AssessmentCode;

public class NumberFormatException {

	static void convert(String value) {
		try {
			int  num=Integer.parseInt(value);
			System.out.println(num);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Invalid Number format...!");
		}
	}
	
	public static void main(String[] args) {
		convert("13dhruv");
	}
}
