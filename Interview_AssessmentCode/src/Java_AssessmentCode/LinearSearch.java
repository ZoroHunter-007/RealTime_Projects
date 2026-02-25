package Java_AssessmentCode;

public class LinearSearch {

	public static boolean linearSeacrh(int arr[],int key) {
		for(int num: arr) {
			if(num==key)
				return true;
		}
		return false;
	}
	public static void main(String[] args) {
		
		int arr[]= {10,20,30,45,50};
		System.out.println(linearSeacrh(arr, 45));
	}
}
