package project;

import java.util.Scanner;

public class project1 {

	public static void main( String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("���۰�");
		int number = balltest(sc);
		int lose=0;
		boolean con = true;//���� ��������� ����

		do {
			con = baseball(number,sc);
			lose++;
		}while(con&&lose<=10);
		
		System.out.println(lose>10?"�й� ":"�¸� ");//���п���
		System.out.println("���α׷��� �������ϴ�");
	}
	
	static int balltest(Scanner sc) {
		int ballnum = 0;
		int ballequal = 0;
		int first = 0;
		int second = 0;
		int third = 0;
		//boolean condition = true;
		do{
			try {
			//condition = true;
			System.out.print("���� ��ȣ�� �Է����ּ��� : ");
			ballnum=sc.nextInt();
			//sc.nextLine();
			
			//if(condition)
			//{
				first = ballnum/100;
				second = ballnum%100/10;
				third = ballnum%10;
				if(first!=second&&first!=third&&second!=third)
				{
					ballequal = 1;
				}

			//}
			}catch(Exception ex) {
				System.out.println("���ڸ� �Է��ϼ���");
				//condition = false;
				sc.nextLine();
			}			
		}while(ballnum>=0 && ballnum<=999 && ballequal!=1);//������ ����ų� ��ȣ�� ������ ó��
		
		return ballnum;

	}
	
	static boolean baseball(int num,Scanner sc)
	{
		boolean con = true;//������ ���� ������ ���� ����
		int basearr[] = new int[3];//���� ���ѹ�
		int basearri[] = new int[3];//test�� ���� ���ѹ�
		int base = 0;
		basearr[0] = num/100;
		basearr[1] = num%100/10;
		basearr[2] = num%10;//���� ���� �ڸ������� ��
		int inum = 0;//�Է� ���� ���� ����


		System.out.println("test��");
		inum = balltest(sc);

		basearri[0] = inum/100;
		basearri[1] = inum%100/10;
		basearri[2] = inum%10;//�׽�Ʈ ���� �� �ڸ���

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
			System.out.println("������ ���߼̽��ϴ�");

		}
		return con;
	}

}
