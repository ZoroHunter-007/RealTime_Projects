package Java_AssessmentCode;

public class Encapsultion_Prg {

	private int marks;

	public int getMarks() {
		return marks;
	}

	public void setMarks(int m) {
		if(marks>=0 && marks<=100) {
			marks=m;
		}
	}
	
	public static void main(String[] args) {
		Encapsultion_Prg e= new Encapsultion_Prg();
		e.setMarks(85);
		
		System.out.println("Marks:"+e.getMarks());
	}
	
}
