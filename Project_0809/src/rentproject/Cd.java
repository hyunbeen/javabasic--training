package rentproject;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Cd extends Item {
	
	private String singer;
	public Cd() {
		this("�˼�����","�˼�����","�˼�����");
		System.out.println("Dvd �⺻����");
	}
	
	public Cd(String num,String title,String singer) {
		this.num = num;
		this.title = title;
		this.singer = singer;
	}
	public void output() {
		System.out.println("��ȣ : "+this.num+" ���� : "+this.title+" ���� : "+singer);
	}
	
}
