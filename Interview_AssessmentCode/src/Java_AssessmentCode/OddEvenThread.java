package Java_AssessmentCode;

public class OddEvenThread {

	static Runnable printOdd() {
		return()->{
			for(int i=1;i<=10;i+=2) {
				System.out.println("Odd numbers:"+i);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
		
		static Runnable printEven() {
			return()->{
				for(int i=2;i<=10;i+=2) {
					System.out.println("Even numbers:"+i);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
	}
		
		public static void main(String[] args) {
			Thread oddThread=new Thread(printOdd());
			Thread EvenThread=new Thread(printEven());
		
			oddThread.start();
			EvenThread.start();
		}
}
