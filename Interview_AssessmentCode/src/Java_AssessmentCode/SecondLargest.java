package Java_AssessmentCode;

public class SecondLargest {

	static int secondLargestElement(int arr[]) {
		
		int largest=Integer.MIN_VALUE;//smallest integer value if the array contain the negative values it not give the error in run-time
		int secondLargest=Integer.MIN_VALUE;
		
		//check the largest and second largest
		for(int num:arr) {
			if(num>largest) {
				secondLargest=largest;//if condition is true then the largest value becomes the second largest
				largest=num;//nd current largest number value store into the largest
				
				if(arr.length<2) {
					throw new IllegalArgumentException("Array must have atleast 2 elements!");
				}
			}
			else if(num>secondLargest && num!=largest) // if the number is greater than the second largest and ignore the duplicate
				{
				secondLargest=num;
			}
		}
		return secondLargest;
	}
	
	public static void main(String[] args) {
		int arr[]= {10,10,54,89,47,65};
		System.out.println("Second Largest Element:"+secondLargestElement(arr));
	}
}
