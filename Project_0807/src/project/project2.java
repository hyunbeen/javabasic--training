package project;

import java.util.Scanner;

public class project2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); //입력받는 클래스
		int number = input(sc); //학생수 값을 입력받는다
		ptable stu = new ptable(number);
	

	}


	static int input(Scanner sc) {
		
		boolean condition = true;
		int number = 0;
		do
		{
		try {
			condition =false;
			System.out.print("입력할 학생수를 입력하세요 ");
			number = sc.nextInt();
	
		}
		catch(Exception ex) {
			condition = true;
			sc.nextLine();
			System.out.println("명수를 잘못 입력하셨습니다");
		}
		}while(condition||(number<1)); //명수 입력 조건
		sc.nextLine();
		System.out.println();
		System.out.println("3명의 국어,영어,수학 점수를 받아 결과 출력하기");
		
		return number;
	}
}
