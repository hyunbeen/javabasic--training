package basic;

public class StringTest2 {

	public static void main(String[] args) {
		
		String a = "�ȳ�";
		a = a + "�Ͻ�";
		a = a + "�ɴϱ�";
		System.out.println(a);
		
		StringBuffer b = new StringBuffer("�ȳ�");
		b.append("�Ͻ�");
		b.append("�ɴϱ�");
		System.out.println(b);

	}

}
