package Java_AssessmentCode;

interface Payment{
	void pay();
}

class UPI implements Payment{

	@Override
	public void pay() {
		// TODO Auto-generated method stub
		System.out.println("The Payment is paid by the UPI");
	}
	
}

class COD implements Payment{

	@Override
	public void pay() {
		// TODO Auto-generated method stub
		System.out.println("The Payment is paid by the Cash on Delivery");
	}
	
}

class CreditCard implements Payment{

	@Override
	public void pay() {
		// TODO Auto-generated method stub
		System.out.println("The Payment is paid by the Credit Card");
	}
	
}
public class InterfaceDemo {

	public static void main(String[] args) {
		
		new UPI().pay();
		new COD().pay();
		new CreditCard().pay();
	}
}
