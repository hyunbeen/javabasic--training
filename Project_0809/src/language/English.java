package language;

import java.util.Scanner;

public class English extends Language {
	public English() {
		super.hi = "Good Morning";
		super.introduce = "My name is Hyun Bin";
		super.haza = "I'm going to fight.";
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
