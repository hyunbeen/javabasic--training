package statictest;

public class StaticTest {

}

class Outer
{
	static class Inner
	{
		static void najabara() {
			System.out.println("나를 호출");
		}
	}
}

class InnerClass
{
	public static void main(String[] args) {
		
		Outer.Inner.najabara();
		
	}
}