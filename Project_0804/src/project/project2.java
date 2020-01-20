package project;

import java.util.Scanner;

public class project2 {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		sc.nextLine();
		String str[] = new String[num];
		for(int i=0;i<num;i++)
		{
			str[i] = sc.nextLine();
		}
		int point = 0;
		
		for(int i=0;i<num;i++)
		{
			System.out.println(jumsu(str[i]));
		}
		
	}

	static int jumsu(String str)
	{
		int hap = 0;
		int point = 0;
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)=='x')
			{
				point = 0;
			}
			else if(str.charAt(i)=='o')
			{
				point++;
				hap+=point;
			}
		
		}
		
		return hap;
	}
}
