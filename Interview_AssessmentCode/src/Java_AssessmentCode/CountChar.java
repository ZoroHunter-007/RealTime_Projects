package Java_AssessmentCode;

public class CountChar {

	static void count(String str) {
		
	int vowels=0, consonants=0,digit=0,space=0;
	str=str.toLowerCase();
		
	for(int i=0;i<str.length();i++) {
		char ch=str.charAt(i);
		
		if(ch>='a' && ch<='z') {
			if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u') {
				vowels++;
			}
			else {
				consonants++;
			}
		}
		else if(ch>='0' && ch<='9')
		{
			digit++;
		}
		else if(ch==' ') {
			space++;
		}
	}
	System.out.println("Voweles:"+vowels);
	System.out.println("Consonants:"+consonants);
	System.out.println("Digits:"+digit);
	System.out.println("Spaces:"+space);
	}
	
	public static void main(String[] args) {
		count("Java 123 Interview");
	}
}
