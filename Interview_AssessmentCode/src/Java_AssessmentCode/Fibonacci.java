package Java_AssessmentCode;

public class Fibonacci {

	public static void FibonacciNum(int n) {
		
		int a=0,b=1;
		
		for(int i=1;i<=n;i++) {
			System.out.println(a+" ");
			int c=a+b; //c=1 con1, c=2 con2, c=3 con3
			a=b;//a=1,a=1,a=2
			b=c;//b=1,b=2,b=3
		}
	}
	
	public static void main(String[] args) {
		
		FibonacciNum(5);
	}
}

/* i=1
 * print(0)
 *c=1
 *a=1,b=1

i=2
print(1)
c=2
a=1,b=2

i=3
print(1)
c=3
a=2,b=3

i=4
print(2)
c=5
a=3,b=5

i=5
print(3)
c=8
a=5,b=8



 * 
 */

