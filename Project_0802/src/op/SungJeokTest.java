package op;

import java.util.Scanner;

public class SungJeokTest {

		public static void main(String[] argc) {
			Scanner input = new Scanner(System.in);
			int kor = 0;
			int eng = 0;
			int math = 0;
			//�ַܼκ��� ������ ���� �Է�
			System.out.println("����,����,�������� �Է� : ");
			kor = input.nextInt();
			eng = input.nextInt();
			math = input.nextInt();
			 int hap = kor + eng + math;
			 double average = hap / 3.0;
			 
			 //����̾簡
			 
			 switch((int)(average/10)) {
			 case   10 :
			 case   9  : System.out.println("��");
				 break;
			 case   8  : System.out.println("��");
			 	break;
			 case   7  : System.out.println("��");
			 	break;
			 case   6  : System.out.println("��");
			 	break;
			 default :
				 System.out.println("��");
				break;
			 
			 }
		}
}