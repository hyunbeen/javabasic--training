package process;

class Count{
	int i = 0;
void increment() {
	synchronized(this) {
		i++;
	}
	
	}
}

class ThreadCount extends Thread{
	Count cnt;
	ThreadCount(Count cnt){
		this.cnt = cnt;
	}
	public void run() {
		for(int i=0;i<1000000000;i++) {
			cnt.increment();
	
		}
	}
}

public class SyhchTest {
	public static void main(String [] args) {
		Count cnt = new Count();
		ThreadCount tc1 =  new ThreadCount(cnt);
		tc1.start();
		ThreadCount tc2 = new ThreadCount(cnt);
		tc2.start();
		try {
			tc1.join();
			tc2.join();//끝나구 수행하라는 함수
		} catch (InterruptedException e) {
		
		}
	
		System.out.println("i="+cnt.i);
	}
}
