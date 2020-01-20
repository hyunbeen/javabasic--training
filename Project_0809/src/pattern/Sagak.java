package pattern;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Sagak extends Moyang {
	public Sagak(){
		input();
		size();
	}
	public void size() {
		System.out.println("사각형의 넓이는 "+this.height * this.length);
	}
	public void input() {
		Scanner sc = new Scanner(System.in);
		System.out.print("높이와 길이를 입력해 주세요 : ");
		String str = "";
		str = sc.nextLine();
		
		StringTokenizer st = new StringTokenizer(str," ");
		this.height = Integer.parseInt(st.nextToken());
		this.length = Integer.parseInt(st.nextToken());
	}
}
