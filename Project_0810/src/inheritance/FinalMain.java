package inheritance;
abstract class Parent{
	final int value = 10;
	abstract void method();
	
}
class Child extends Parent{
	Child(){
		//value = 20;
	}
	void method() {
		System.out.println("�θ�Բ� ����");
	}
}
public class FinalMain {

	public static void main(String[] args) {
		Parent p = new Child();
		System.out.println("�� : "+p.value);
		p.method();

	}

}