package op;

import java.util.Scanner;

public class gugu {

	public static void main(String[] args) {
		// TODO Auto-generated method stubScanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(System.in);
		String con = "Y";
		
//		do {
//			System.out.println("���ڸ� �Է��Ͻÿ� : ");
//			
//			int number = sc.nextInt();
//			sc.nextLine();
//		for(int i=1;i<10;i++)
//		{
//			System.out.print(number+" * "+i+" = "+i*number);
//			System.out.println();
//		}
//		System.out.print("�ٽ��Ͻðڽ��ϱ�? (Y/N) ");
//		con = sc.nextLine();
//		}while(con.equals("Y")||con.equals("y"));
//		
//		}
		
		while(con.equals("Y")||con.equals("y")) {
		System.out.println("���ڸ� �Է��Ͻÿ� : ");
		
		int number = sc.nextInt();
		sc.nextLine();
	for(int i=1;i<10;i++)
	{
		System.out.print(number+" * "+i+" = "+i*number);
		System.out.println();
	}
	System.out.print("�ٽ��Ͻðڽ��ϱ�? (Y/N) ");
	con = sc.nextLine();
	};
	
	}

	}


