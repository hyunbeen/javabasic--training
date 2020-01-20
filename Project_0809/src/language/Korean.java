package language;

import java.util.Scanner;

public class Korean extends Language {

	public Korean() {
		this.hi = "안녕하세요";
		this.introduce = "내이름은 이현빈 입니다";
		this.haza = "화이팅 해요";
		output();
	}
	public void output(){
		System.out.print("메시지를 선택하세요 (1.인사말  2.자기소개  3.하고픈말 ) ");
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
