package rentproject;

import java.util.Scanner;

public class Store {
	public void init()
	{
		
		Item i = null;
		Scanner sc = new Scanner(System.in);
		System.out.print("�Է� �Ͻðڽ��ϱ�? : ");
		System.out.print("å(1) Dvd(2) Cd(3) : ");
			int select = sc.nextInt();
			switch(select) {
			case 1:
				i = new Book();
				
				break;
			case 2:
				
				i = new Dvd();
				break;
			case 3:
				i= new Cd();
				break;
			default:
				System.out.println("�ٽ� �Է��� �ּ���");
				break;
			}
			
			i.output();
			
		}
	}

