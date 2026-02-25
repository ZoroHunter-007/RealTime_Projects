package Java_AssessmentCode;

class Calulate{
	int add(int a,int b) {
		return a+b;
	}
	
	int add(int a, int b, int c) {
		return a+b+c;
	}
}
public class Method_Overloading {

	public static void main(String[] args) {
		
		Calulate c=new Calulate();
		System.out.println(c.add(10, 7));
		System.out.println(c.add(4, 5, 4));
	}
	
}
