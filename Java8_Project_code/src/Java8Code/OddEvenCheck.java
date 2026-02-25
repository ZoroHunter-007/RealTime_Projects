package Java8Code;

public class OddEvenCheck {

	public static String OddEven(int num) {
	
		return (num%2==0) ? "Even" : "Odd";
	}
	public static void main(String[] args) {
		System.out.println(OddEven(5));
	}
}
