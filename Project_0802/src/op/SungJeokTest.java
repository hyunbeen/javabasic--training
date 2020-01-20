package op;

import java.util.Scanner;

public class SungJeokTest {

		public static void main(String[] argc) {
			Scanner input = new Scanner(System.in);
			int kor = 0;
			int eng = 0;
			int math = 0;
			//콘솔로부터 각각의 점수 입력
			System.out.println("국어,영어,수학점수 입력 : ");
			kor = input.nextInt();
			eng = input.nextInt();
			math = input.nextInt();
			 int hap = kor + eng + math;
			 double average = hap / 3.0;
			 
			 //수우미양가
			 
			 switch((int)(average/10)) {
			 case   10 :
			 case   9  : System.out.println("수");
				 break;
			 case   8  : System.out.println("우");
			 	break;
			 case   7  : System.out.println("미");
			 	break;
			 case   6  : System.out.println("양");
			 	break;
			 default :
				 System.out.println("가");
				break;
			 
			 }
		}
}