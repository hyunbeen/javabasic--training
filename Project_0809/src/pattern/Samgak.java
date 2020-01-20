package pattern;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Samgak extends Moyang {
	public Samgak(){
			input();
			size();
			}
	public void size() {
		System.out.println("삼각형의 넓이는 "+this.height * this.length /2.0);
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
