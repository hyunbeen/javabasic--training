package process;

public class ThreadStopTest {

	public static void main(String[] args) {
		ThreadWork tw = new ThreadWork();
		tw.start();
		try {
			Thread.sleep(2000);
		}catch(Exception ex) {
			
		}
		System.out.println("프로그램 종료");
		tw.flag=false;

	}

}

class ThreadWork extends Thread{
	boolean flag = true;
	public void run() {
		while(flag) {
			System.out.println(getName()+"작업중");
			
			try{
				Thread.sleep(1000);
			}
			catch(InterruptedException ex)
			{
				System.out.println("예외처리");
			}
			catch(ThreadDeath ex) {
				System.out.println("내가 예외잡음");
			}
		}
	}
}