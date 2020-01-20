package rentproject;

import java.util.Scanner;

public class Store {
	public void init()
	{
		
		Item i = null;
		Scanner sc = new Scanner(System.in);
		System.out.print("입력 하시겠습니까? : ");
		System.out.print("책(1) Dvd(2) Cd(3) : ");
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
				System.out.println("다시 입력해 주세여");
				break;
			}
			
			i.output();
			
		}
	}

