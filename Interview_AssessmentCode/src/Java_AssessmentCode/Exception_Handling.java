package Java_AssessmentCode;

import java.util.Scanner;

class InvalidAgeException extends Exception{
	public InvalidAgeException(String message) {
		super(message);
	}
}

public class Exception_Handling {

	static void validateAeg(int age) throws InvalidAgeException {
		if(age<18) {
		
			throw new InvalidAgeException("Age must be 18 or above");
		}
		else {
			System.out.println("You are eligable to the vote");
		}
	}
	
	public static void main(String[] args) throws InvalidAgeException {
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter the age:");
		int age=sc.nextInt();
		
		validateAeg(age);
		sc.close();
	}
}
