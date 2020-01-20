package method;

import java.util.Scanner;

public class AddTest {

	public static void main( String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("공의 번호를 입력해 주세요 : ");
		int number = sc.nextInt();
		
		boolean con = true;
		do {
			con = baseball(number);
		}while(con);
	}
	
	static boolean baseball(int num)
	{
		boolean con = true;
		int basearr[] = new int[3];
		int basearri[] = new int[3];
		int base = 0;
		basearr[0] = num/100;
		basearr[1] = num%100/10;
		basearr[2] = num%10;
		int inum = 0;
		int many = 0;
		System.out.print("맞출 번호를 입력해 주세요 : ");
		Scanner sc = new Scanner(System.in);
		inum = sc.nextInt();
		basearri[0] = inum/100;
		basearri[1] = inum%100/10;
		basearri[2] = inum%10;
		//strike
		int strike = 0;
		for(int i=0;i<3;i++)
		{
			if(basearri[i]==basearr[i])
			{
				strike++;
				base++;
			}
		}
		//ball
		int ball=0;
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(basearr[i]==basearri[j])
				{
					ball++;
				}
			}
		}
		System.out.println("strike:"+strike+"ball:"+ball);
		if(base==3)
		{
			con = false;
		}
		return con;
	}

}
