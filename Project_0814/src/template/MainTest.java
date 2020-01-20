package template;

import java.util.Scanner;

public class MainTest {

	public static void main(String[] args) {
		
		AbstractDisplay d1 = null;
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
	
		if(str.length()>2) {
			AbstractDisplay b1 = new StringDisplay(str);
			b1.display();
		}
		else
		{
			AbstractDisplay b2 = new CharDisplay(str.charAt(0));
			b2.display();
		}
		
		
		//����� �Է¹ޱ�
		//�� 1 Z ->[[zzzzz]]
		//�� 2 ���ɹ�����

	}

}
