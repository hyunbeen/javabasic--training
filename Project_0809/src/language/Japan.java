package language;

import java.util.Scanner;

public class Japan extends Language{
	public Japan() {
		this.hi = "����˪���";
		this.introduce = "���٣��ϫ��ҫ��ӫ�Ǫ�";
		this.haza = "�ի����ƫ��󫰪ު��窦"; 
		output();
				
	}
	public void output(){
		System.out.print("�޽����� �����ϼ��� (1.�λ縻  2.�ڱ�Ұ�  3.�ϰ��¸� ) ");
		Scanner sc = new Scanner(System.in);
		int select;
		select = sc.nextInt();
		switch(select) {
		case 1:
			System.out.println(this.hi);
			break;
		case 2:
			System.out.println(this.introduce);
			break;
		case 3:
			System.out.println(this.haza);
			break;
			
		}
	}
}
