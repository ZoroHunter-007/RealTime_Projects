package Java_AssessmentCode;

public class DuplicateChar {

	static void findDuplicateChar(String str) {
		
		char[]arr=str.toCharArray();
		boolean[] visited=new boolean[arr.length];
		
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
			
			if(count>1) {
				System.out.println("Repeated characters:"+arr[i]);
			}
		}
	}
	
	public static void main(String[] args) {
		findDuplicateChar("Programming");
	}
}
