package project;

import java.util.Scanner;

public class project3 {
	public static void main(String[] args)
	{
		char ch = '%';
		Scanner sc = new Scanner(System.in);
		int number = sc.nextInt();
		int num = 0;
		
		double jumsu[] = new double[number];
		
		for(int i=0;i<number;i++)
		{
			num = sc.nextInt();
			jumsu[i] = calcul(num,sc);
		}
		
		for(int i=0;i<number;i++)
		{
			System.out.printf("%.3f",jumsu[i]);
			System.out.printf("%c\n",ch);
		}


	}

	static double calcul(int num,Scanner sc) {
		System.out.println(num);
		double persent = 0;
		int plus=0;
		int jumsu[] = new int[num];
		int hap = 0;
		for(int i=0;i<num;i++)
		{
			jumsu[i]=sc.nextInt();
			hap+=jumsu[i];
		}
		double average = hap/(double)num;
		for(int i=0;i<num;i++)
		{
			if(jumsu[i]>average)
			{
				plus++;
			}
		}
		persent = plus/(double)num;
		return persent*100;
	}


}
