package pattern;

import java.util.Scanner;

public class Start {
	public void init(){
		int select = 0;
		Scanner sc = new Scanner(System.in);
		boolean condition = true;
		System.out.print("���̸� �� ���Ͻðڽ��ϱ� ? : ");
		condition=sc.nextBoolean();
		sc.nextLine();
		Moyang i = null;
		while(condition) {
			System.out.print("���ϴ� ������ �����Ͻÿ� (1 �ﰢ�� 2 �簢�� 3 ��): ");
			select = sc.nextInt();
			switch(select) {
			case 1: 
				i = new Samgak();
				break;
			case 2:
				i = new Sagak();
				break;
			case 3:
				i = new Circle();
				break;
			}
			System.out.print("���̸� �� ���Ͻðڽ��ϱ� ? : ");
			condition=sc.nextBoolean();
			sc.nextLine();
		}
	}
}
