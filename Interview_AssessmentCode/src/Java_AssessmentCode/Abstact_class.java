package Java_AssessmentCode;

abstract class Vehical {
	
	abstract void start();
}

class Bike extends Vehical{

	@Override
	void start() {
		System.out.println("Bike Start using the kick");
		
	}
	
}

class Car extends Vehical{

	@Override
	void start() {
		System.out.println("Car start using the starter");
		
	}
	
}

public class Abstact_class {

	public static void main(String[] args) {
		
		new Bike().start();
		new Car().start();
	}
}
