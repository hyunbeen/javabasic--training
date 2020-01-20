package process;

public class RunnableTest {
	public static void main(String[] args) {
		MakeCar2 mc1 = new MakeCar2("차를만들기");
		Thread t1 = new Thread(mc1);//호출하기 까먹지 말기
		t1.start();
		
//		MakeCar2 mc2 = new MakeCar2("엔진부착");
//		Thread t2 = new Thread(mc2);
		new Thread(new MakeCar2("엔진부착")).start();
		

		System.out.println("구동중");
	}
	
}

class MakeCar2 extends Object implements Runnable{ //Thread랑 같이 인터페이스로 Tread 클래스 작용
	String work;
	MakeCar2(String _work){
		work = _work;
	}
	public void run() {
		for(int i=0;i<5;i++)
		{
			System.out.println(work + "작업중");
			try {
			Thread.sleep(500);
			}catch(InterruptedException e) {
				
			}
		}
	}
}