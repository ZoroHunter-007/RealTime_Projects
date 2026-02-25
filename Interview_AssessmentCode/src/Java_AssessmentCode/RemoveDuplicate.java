package Java_AssessmentCode;


public class RemoveDuplicate {

	static void RemoveDuplicateElement(int arr[]) {
		
		boolean [] visited= new boolean[arr.length];
		
		for(int i=0;i<arr.length;i++) {
			if(visited[i]) {
				continue;
			}
			int count=1;
			for(int j=i+1;j<arr.length;j++) {
				if(arr[i]==arr[j]) {
					visited[j]=true;
					count++;
				}
			}
			
			
		}
		System.out.println("\nUnique number:");
		for(int l=0;l<arr.length;l++) {
			if(!visited[l])
			System.out.print(arr[l]+",");
		}
	}
	
	public static void main(String[] args) {
		int arr[]= {1,2,3,4,4,5,5,5,6,6,6,6};
		System.out.println("Original Array:");
		for(int num:arr) {
			System.out.print(num+",");
		}
		RemoveDuplicateElement(arr);
	}
	
}
