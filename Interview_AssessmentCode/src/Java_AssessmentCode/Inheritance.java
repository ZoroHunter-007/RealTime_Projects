package Java_AssessmentCode;

class Animal{
	void voice() {
		System.out.println("Animal voice");
	}
}

class Dog extends Animal{
	void bark() {
		System.out.println("Dog is barking");
	}
}

public class Inheritance {
	

	public static void main(String[] args) {
		
		Dog d=new Dog();
		d.voice();
		d.bark();
	}

}
