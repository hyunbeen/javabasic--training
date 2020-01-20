package array;

public final class LottoTest {
	public static void main(String[] args) {
		int [] lotto;
		lotto = new int[6];
		int temp = 0;
		
		System.out.println("5줄 로또번호");
		for(int k=0;k<5;k++)
		{
		for(int i=0;i<6;i++) {
			lotto[i] = (int)(Math.random()*45)+1;
			for(int j=0;j<i;j++)
			{
				if(lotto[i]==lotto[j])
				{
					i=i-1;
					break;
				}
			}//같을시
			
		}
		for(int i=0;i<6;i++) {
			System.out.print(lotto[i]+"/");
		}
		System.out.println();
		
		}
		
		System.out.println("");
		
		
		//bubble
//		for(int i=0;i<5;i++) {
//			for(int j=0;j<5-i;j++)
//			{
//				if(lotto[j]>lotto[j+1])
//				{
//					temp = lotto[j];
//					lotto[j]= lotto[j+1];
//					lotto[j+1]=temp;
//				}
//			}
//		}
		
		
//		for(int i=0;i<6;i++) {
//			System.out.print(lotto[i]+"/");
//		}
		
		//selection
//		for(int i=0;i<5;i++) {
//			for(int j=i+1;j<6;j++)
//			{
//				if(lotto[i]>lotto[j])
//				{
//					temp = lotto[i];
//					lotto[i]= lotto[j];
//					lotto[j]=temp;
//				}
//			}
//		}
//		
//		
//		for(int i=0;i<6;i++) {
//			System.out.print(lotto[i]+"/");
//		}
//		
		//insertion
//		for(int i=1;i<6;i++) {
//			for(int j=0;j<i;j++)
//			{
//				if(lotto[j]>lotto[i])
//				{
//					temp = lotto[i];
//					lotto[i]= lotto[j];
//					lotto[j]=temp;
//				}
//			}
//		}
//		
//		
//		for(int i=0;i<6;i++) {
//			System.out.print(lotto[i]+"/");
//		}
		
		
		
		int hap = 0;
		for(int i=0;i<6;i++) {
		hap+=lotto[i];
	}
		if(hap>=106 && hap<=170)
		{
			System.out.println("106과 170 사이에 있다");
		}
	}
	
		
		
	}

