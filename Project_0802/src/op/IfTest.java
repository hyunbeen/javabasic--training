package op;

import java.util.Scanner;

public class IfTest {
	public static void main(String[] argc) {
		/*
		 * �Է¹��� �� �� �߿��� ���ϰ���ū��?
		 */
		;
		System.out.print("������ �Է��Ͻÿ� : "); 
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
		
		System.out.println(max+"��°�� ����ũ��");
	}
}
