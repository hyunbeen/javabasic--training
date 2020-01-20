package rentproject;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Cd extends Item {
	
	private String singer;
	public Cd() {
		this("알수없음","알수없음","알수없음");
		System.out.println("Dvd 기본생성");
	}
	
	public Cd(String num,String title,String singer) {
		this.num = num;
		this.title = title;
		this.singer = singer;
	}
	public void output() {
		System.out.println("번호 : "+this.num+" 제목 : "+this.title+" 가수 : "+singer);
	}
	
}
