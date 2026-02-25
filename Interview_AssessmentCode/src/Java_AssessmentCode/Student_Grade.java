package Java_AssessmentCode;

import java.util.Scanner;

public class Student_Grade {

	private String name;
	private int marks;
	
	public Student_Grade(String name, int marks) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.marks=marks;
		calculateGrade();
	}
	
	char calculateGrade() {
		if(marks>=75) {
			return 'A';
		}
		else if(marks>=65) {
			return 'B';
		}
		else if(marks>=55) {
			return 'C';
		}
		else {
			return 'F';
		}
	}
	
	void display() {
		System.out.println("Student  Name:"+ name);
		System.out.println("Marks:"+marks);
		System.out.println("Grade:"+calculateGrade());
	}
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enetr the Student Name:");
		String name=sc.next();
		
		System.out.println("Enter the Marks:");
		int marks=sc.nextInt();
		
		
		new Student_Grade(name, marks).display();;
		
	}
}
