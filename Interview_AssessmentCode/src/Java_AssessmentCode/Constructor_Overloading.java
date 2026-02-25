package Java_AssessmentCode;

import java.util.Scanner;

public class Constructor_Overloading {

	private int id;
	private String name;
	
	Constructor_Overloading() {
	
		id=0;
		name="NA";
	}
	Constructor_Overloading(int id,String name){
		this.id=id;
		this.name=name;
	}
	
	void displayData() {
		System.out.println("ID:"+id);
		System.out.println("Name:"+name);
	}
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter the ID:");
		int id=sc.nextInt();
		
		System.out.println("Enter the name:");
		String name=sc.next();
		
		new Constructor_Overloading();
		
		new Constructor_Overloading(id, name).displayData();;
	
		sc.close();
	}
	
}
