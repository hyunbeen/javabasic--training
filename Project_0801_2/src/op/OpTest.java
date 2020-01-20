package op;

public final class OpTest {

		public static void main(String[] args) {
			
			int a = 5;
			int b = 7;
			a++;//a = a + 1;
			b--;//b = b - 1;
			System.out.println("A="+a);
			System.out.println("B="+b);
			++a;//a = a + 1;
			--b;//b = b - 1;
			System.out.println("A="+ a);//7
			System.out.println("B="+ b);//5
			
			System.out.println("A="+ ++a);//8
			System.out.println("B="+ --b);//4
			
			System.out.println("A="+ a++);//8
			System.out.println("B="+ b--);//4
			
		}
}
