package project;

import java.util.Scanner;

public class project1 {

	public static void main( String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("시작공");
		int number = balltest(sc);
		int lose=0;
		boolean con = true;//공을 맞췄는지의 여부

		do {
			con = baseball(number,sc);
			lose++;
		}while(con&&lose<=10);
		
		System.out.println(lose>10?"패배 ":"승리 ");//승패여부
		System.out.println("프로그램이 끝났습니다");
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
			System.out.print("공의 번호를 입력해주세여 : ");
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
				System.out.println("숫자만 입력하세요");
				//condition = false;
				sc.nextLine();
			}			
		}while(ballnum>=0 && ballnum<=999 && ballequal!=1);//범위를 벗어나거나 번호가 같은공 처리
		
		return ballnum;

	}
	
	static boolean baseball(int num,Scanner sc)
	{
		boolean con = true;//게임을 빠져 나가기 위한 변수
		int basearr[] = new int[3];//최초 공넘버
		int basearri[] = new int[3];//test를 위한 공넘버
		int base = 0;
		basearr[0] = num/100;
		basearr[1] = num%100/10;
		basearr[2] = num%10;//최초 공의 자리마다의 수
		int inum = 0;//입력 받은 공의 변수


		System.out.println("test공");
		inum = balltest(sc);

		basearri[0] = inum/100;
		basearri[1] = inum%100/10;
		basearri[2] = inum%10;//테스트 공의 각 자리수

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
			System.out.println("정답을 맞추셨습니다");

		}
		return con;
	}

}
