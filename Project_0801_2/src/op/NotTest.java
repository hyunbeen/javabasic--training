package op;

public class NotTest {
	public static void main(String[] args) {
		boolean b = 3<4;
		System.out.println("B=" + b);
		System.out.println("B="+!b);
		
		int jumsu = 88;
		if(jumsu > 90) {
			System.out.println("���");
		}
		if(!(jumsu>90))
		{
			System.out.println("��~���");
		}
		
		int su = 15;
		System.out.println("su="+su);
		System.out.println("su="+~su);
	}
}
