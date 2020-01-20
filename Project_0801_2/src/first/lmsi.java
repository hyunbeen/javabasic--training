package first;

public class lmsi {

	
	public static void main(String[] args) {
		boolean p =true;
		String s = new String("안녕하세요");
		String s1 = new String("안녕하세요");
		
		if(s.equals(s1))
		{
			System.out.println("내용이 같다");
		}
		else {
			System.out.println("내용이 다르다");
		}
		if(s==s1)
		{
			System.out.println("같다");
		}
		else {
			System.out.println("다르다");
		}
	}

}
