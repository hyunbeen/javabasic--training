package op;

import java.util.Scanner;

public class IfTest {
	public static void main(String[] argc) {
		/*
		 * 입력받은 세 수 중에서 제일가장큰수?
		 */
		;
		System.out.print("세수를 입력하시오 : "); 
		int num1,num2,num3=0;
		Scanner input = new Scanner(System.in);
		num1 = input.nextInt();
		num2 = input.nextInt();
		num3 = input.nextInt();
		int max = 0;
		if(num1>=num2)
		{
			max = 1;
			if(num1<=num3)
			{
				max = 3;
			}
		}else
		{
			max = 2;
			if(num2<=num3)
			{
				max = 3;
			}
		}
		
		System.out.println(max+"번째가 제일크다");
	}
}
