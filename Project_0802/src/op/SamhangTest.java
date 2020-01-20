package op;

import java.util.Scanner;

public class SamhangTest {
	/*
	 * 조건 ? A : B
	 		조건이 참이면 A를 실행하고 거짓이면 B를 실행
	 */
	public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			int num1,num2;
			System.out.print("두수를 입력 하시오 : ");
			num1 = sc.nextInt();
			num2 = sc.nextInt();
			System.out.println(num1>num2?"첫번째수가 크다":"두번째수가 크다");
	}

}
