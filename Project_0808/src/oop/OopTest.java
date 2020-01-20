package oop;

class AClass
{
	private int b;
	AClass(){
		this(15);
	}
	AClass(int b){
		this.b = b;
	}
	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}
}
public class OopTest {

	public static void main(String[] args) {
		AClass ac = new AClass();
		ac.setB(1000);
		System.out.println(ac.getB());
		AClass ac2 = new AClass(500);
	}

}
