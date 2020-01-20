package casting;

public class MainTest {

	public static void main(String[] args) {
		Ddal d = new Ddal();
		
		Umma temp = (Umma)d; //upcasting
		Ddal imsi = (Ddal)temp; //downcasting
//		if(d instanceof Object) {
//			System.out.println("딸의 객체임");
//		}else {
//			System.out.println("딸의 객체가 아님");
//		}
		
		
		 //casting 연산자
		 //1.기본형에서만 
	     //2.상속관계에서만 가능
		 //String str = "안녕하세요"
		 //StringBuffer sb = (StringBuffer)str
		 
	}

}
