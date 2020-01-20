package pattern;

import java.util.Scanner;

public class Circle extends Moyang {
	public Circle(){
			input();
			size();
	}
	public void size() {
		System.out.println("원의 넓이는 "+Math.PI*this.length * this.length);
	}
	public void input() {
		Scanner sc = new Scanner(System.in);
		System.out.print("길이를 입력해 주세요 : ");
		this.length = sc.nextInt();
		
	}
}
