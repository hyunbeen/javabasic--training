package ex;

class Bread 
{
	String bread;
	boolean isChecked = false;
	int isChecked2 = 0;
	
	public void setBread( String bread )
	{	
		
		System.out.println("빵을 셋팅");
		this.bread = bread;
		isChecked = true;
		synchronized(this){
			notifyAll();  //대기하는 모든 프로세스가 내려온다
		}
		System.out.println("isChecked2 : "+isChecked2);
		
	}	

	public String getBread()
	{
		setBread("맛있는 빵");
		if(isChecked == false) {
			try{
				synchronized(this) {
					wait(); //실행중에 대기로 이동한다
				}
			}
			catch(InterruptedException ex) {
				
			}
		}
		isChecked2++;
		System.out.println("isChecked2 : "+isChecked2);
		return bread;
	}
}

class Baker extends Thread
{
	Bread bbang;

	Baker( Bread bbang )
	{
		this.bbang = bbang;
	}
	public void run()
	{
		bbang.setBread("맛있는 빵");
	}
}

class Customer extends Thread
{
	Bread bbang;

	Customer( Bread bbang )
	{
		this.bbang = bbang;
	}
	public void run()
	{
		System.out.println("빵을 사러옴");
		System.out.println("빵을 샀다 : " + bbang.getBread());
	}
}

class BreadTest
{
	public static void main(String[] args) 
	{
		Bread  bread = new Bread();

		Baker  baker = new Baker( bread );
		
		Customer customer = new Customer( bread );
		Customer customer2 = new Customer( bread );
		
		customer.start();
		customer2.start();
		baker.start();

		try{
			customer.join();
			baker.join();			
		}catch( Exception ex ){}

	}
}
