package project;
import java.util.Scanner;
public class project1{

		
		
	public static void main(String[] args) {//일반논리에만 적용
		
		Scanner sc = new Scanner(System.in);
		int year = sc.nextInt();
		if(year%4==0&&year%100!=0||year%400==0)
		{
			System.out.println("윤년입니다");
		}
		else
		{
			System.out.println("평년입니다");
		}
		
	}
	
	
}
