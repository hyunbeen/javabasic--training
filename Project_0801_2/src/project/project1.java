package project;
import java.util.Scanner;
public class project1{

		
		
	public static void main(String[] args) {//�Ϲݳ����� ����
		
		Scanner sc = new Scanner(System.in);
		int year = sc.nextInt();
		if(year%4==0&&year%100!=0||year%400==0)
		{
			System.out.println("�����Դϴ�");
		}
		else
		{
			System.out.println("����Դϴ�");
		}
		
	}
	
	
}
