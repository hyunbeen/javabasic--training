package op;

import java.util.Scanner;

public class SamhangTest {
	/*
	 * ���� ? A : B
	 		������ ���̸� A�� �����ϰ� �����̸� B�� ����
	 */
	public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			int num1,num2;
			System.out.print("�μ��� �Է� �Ͻÿ� : ");
			num1 = sc.nextInt();
			num2 = sc.nextInt();
			System.out.println(num1>num2?"ù��°���� ũ��":"�ι�°���� ũ��");
	}

}
