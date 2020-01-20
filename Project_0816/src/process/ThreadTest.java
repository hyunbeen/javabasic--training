package process;

public class ThreadTest {

	public static void main(String[] args) {
		MakeCar1 mc1 = new MakeCar1("차를만들기");
		mc1.start(); //start 호출시 대기실에 들어온다
		
		MakeCar1 mc2 = new MakeCar1("엔진부착");
		mc2.start();

		System.out.println("구동중");
	}
	
	

}

class MakeCar1 extends Thread{
	String work;
	MakeCar1(String _work){
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