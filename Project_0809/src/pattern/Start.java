package pattern;

import java.util.Scanner;

public class Start {
	public void init(){
		int select = 0;
		Scanner sc = new Scanner(System.in);
		boolean condition = true;
		System.out.print("넓이를 더 구하시겠습니까 ? : ");
		condition=sc.nextBoolean();
		sc.nextLine();
		Moyang i = null;
		while(condition) {
			System.out.print("원하는 도형을 선택하시오 (1 삼각형 2 사각형 3 원): ");
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
			System.out.print("넓이를 더 구하시겠습니까 ? : ");
			condition=sc.nextBoolean();
			sc.nextLine();
		}
	}
}
