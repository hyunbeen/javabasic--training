package pattern;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Sagak extends Moyang {
	public Sagak(){
		input();
		size();
	}
	public void size() {
		System.out.println("�簢���� ���̴� "+this.height * this.length);
	}
	public void input() {
		Scanner sc = new Scanner(System.in);
		System.out.print("���̿� ���̸� �Է��� �ּ��� : ");
		String str = "";
		str = sc.nextLine();
		
		StringTokenizer st = new StringTokenizer(str," ");
		this.height = Integer.parseInt(st.nextToken());
		this.length = Integer.parseInt(st.nextToken());
	}
}
