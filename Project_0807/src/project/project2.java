package project;

import java.util.Scanner;

public class project2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); //�Է¹޴� Ŭ����
		int number = input(sc); //�л��� ���� �Է¹޴´�
		ptable stu = new ptable(number);
	

	}


	static int input(Scanner sc) {
		
		boolean condition = true;
		int number = 0;
		do
		{
		try {
			condition =false;
			System.out.print("�Է��� �л����� �Է��ϼ��� ");
			number = sc.nextInt();
	
		}
		catch(Exception ex) {
			condition = true;
			sc.nextLine();
			System.out.println("����� �߸� �Է��ϼ̽��ϴ�");
		}
		}while(condition||(number<1)); //��� �Է� ����
		sc.nextLine();
		System.out.println();
		System.out.println("3���� ����,����,���� ������ �޾� ��� ����ϱ�");
		
		return number;
	}
}
