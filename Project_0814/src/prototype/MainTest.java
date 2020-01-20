package prototype;

public class MainTest {

	public static void main(String[] args) {
		
		//-------------- 사용자측에서 원하는 구조를 만들고 사용할 수 있도록 하는 것
		
		Manager mgr = new Manager();
		
		UnderlinePen u1 = new UnderlinePen('_');
		UnderlinePen u2 = new UnderlinePen('~');
		
		MessageBox m1 = new MessageBox('*');
		MessageBox m2 = new MessageBox('#');
		
		mgr.register("밑줄",u1);
		mgr.register("물결",u2);
		mgr.register("별표박스",m1);
		mgr.register("샵박스",m2);
		
		Product p1 = mgr.create("밑줄");
		p1.use("아싸 불타는 월요일");
		
		
	}

}
