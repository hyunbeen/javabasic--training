package language;

import java.util.Scanner;

public class Start {
	
	public void init(){
		int select = 0;
		Scanner sc = new Scanner(System.in);
		String ch = "";
		Boolean condition = false; 
		System.out.print("�� �����Ͻðڽ��ϱ� ?(Y,N) : ");
		ch=sc.nextLine();
		
		if(ch.equals("Y")) {
			condition = true;
		}else if(ch.equals("N"))
		{
			condition = false;
		}
		Language i = null;
		while(condition) {
			System.out.print("�� �����ϼ��� (1.�ѱ���  2.����  3.�Ϻ���) ");
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
			System.out.print("�� �����Ͻðڽ��ϱ� ?(Y,N) : ");
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
