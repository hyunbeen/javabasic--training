package statictest;

public class StaticTest {

}

class Outer
{
	static class Inner
	{
		static void najabara() {
			System.out.println("���� ȣ��");
		}
	}
}

class InnerClass
{
	public static void main(String[] args) {
		
		Outer.Inner.najabara();
		
	}
}