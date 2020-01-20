package language;

import java.util.Scanner;

public class Start {
	
	public void init(){
		int select = 0;
		Scanner sc = new Scanner(System.in);
		String ch = "";
		Boolean condition = false; 
		System.out.print("언어를 번역하시겠습니까 ?(Y,N) : ");
		ch=sc.nextLine();
		
		if(ch.equals("Y")) {
			condition = true;
		}else if(ch.equals("N"))
		{
			condition = false;
		}
		Language i = null;
		while(condition) {
			System.out.print("언어를 선택하세요 (1.한국어  2.영어  3.일본어) ");
			select = sc.nextInt();
			switch(select) {
			case 1: 
				i = new Korean();
				break;
			case 2:
				i = new English();
				break;
			case 3:
				i = new Japan();
				break;
			}
			System.out.print("언어를 번역하시겠습니까 ?(Y,N) : ");
			ch=sc.nextLine();
			
			if(ch.equals("Y")) {
				condition = true;
			}else if(ch.equals("N"))
			{
				condition = false;
			}
			sc.nextLine();
			
		}
	}
}
