package pattern;

import java.util.Scanner;

public class Circle extends Moyang {
	public Circle(){
			input();
			size();
	}
	public void size() {
		System.out.println("���� ���̴� "+Math.PI*this.length * this.length);
	}
	public void input() {
		Scanner sc = new Scanner(System.in);
		System.out.print("���̸� �Է��� �ּ��� : ");
		this.length = sc.nextInt();
		
	}
}
