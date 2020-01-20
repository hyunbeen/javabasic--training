package basic;

public class StringTest2 {

	public static void main(String[] args) {
		
		String a = "안녕";
		a = a + "하시";
		a = a + "옵니까";
		System.out.println(a);
		
		StringBuffer b = new StringBuffer("안녕");
		b.append("하시");
		b.append("옵니까");
		System.out.println(b);

	}

}
