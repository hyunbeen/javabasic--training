package basic;

public class StringTest {

	public static void main(String[] args) {
		//문자열 관리 클래스 : String StringBuffer StringBuilder
		StringBuffer sb = new StringBuffer("안녕하세여");
		StringBuffer sb2 = new StringBuffer("안녕하세여");
		
		if(sb.equals(sb2))
		{
			//조건이 맞다면
			System.out.println("같다");
		}else {
			//조건이 아니면 	
		}
		
		String sb3 = "안녕하세여";
		String sb4 = "안녕하세여";
		
		sb3 = sb3 + "하이";
		if(sb3.equals(sb4))
		{
			System.out.println("같다");
		}
		// == : 주소비교
	}

}
