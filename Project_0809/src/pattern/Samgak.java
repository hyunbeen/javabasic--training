package pattern;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Samgak extends Moyang {
	public Samgak(){
			input();
			size();
			}
	public void size() {
		System.out.println("�ﰢ���� ���̴� "+this.height * this.length /2.0);
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
