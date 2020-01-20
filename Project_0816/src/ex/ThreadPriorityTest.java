package ex;


public class ThreadPriorityTest
{
	public static void main( String args [] )
	{
		MakeCar work1 = new MakeCar("차틀만들기", Thread.MAX_PRIORITY);
		work1.start();
		
		MakeCar work2 = new MakeCar("엔진 부착 ", Thread.NORM_PRIORITY);
		work2.start();

		MakeCar work3 = new MakeCar("도색 작업 ", Thread.MIN_PRIORITY);
		work3.start();
	
	}
	
}

class MakeCar extends Thread
{
	String work;

	
	MakeCar( String _work, int prio ){
		work = _work;

		setPriority( prio );
	}
	
	public void run(){
		for( int i=0; i< 5; i++){
			System.out.println(work +" 작업을 진행중");
			
			try{
				sleep(500);
			}catch( InterruptedException ex ){
				
			}	
		}
	}
}