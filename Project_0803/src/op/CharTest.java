package op;

import java.util.Scanner;

public class CharTest {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("���ڸ� �Է��Ͻÿ� : ");
		int number = sc.nextInt();
		for(int i=0;i<number;i++)
		{
			for(int k=0;k<i;k++)
			System.out.print(" ");
			for(int j=0;j<=number-1-i;j++)
			{
				System.out.print("*");
			}
			System.out.println();
		}
		

	}

}
