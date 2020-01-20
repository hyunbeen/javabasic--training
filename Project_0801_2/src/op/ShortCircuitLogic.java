package op;

public class ShortCircuitLogic {

	public static void main(String[] args) {//일반논리에만 적용
		
		int a = 3;
		if(a>3 & ++a>3)
			{
				System.out.println("조건만족");
			}
		System.out.println("A="+a);
		
		if(a>1 | ++a>3)
		{
			System.out.println("조건만족2");
		}
		
		System.out.println("A="+a);
		
	}
	
	//조건만족2  A=5
}
