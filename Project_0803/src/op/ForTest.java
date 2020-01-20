package op;

import java.util.Scanner;

public class ForTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
	
		for(int i=0;i<s.length();i++)
		{
		System.out.print(s.substring(s.length()-(i-1),s.length()-i));
		}
		
		
		
		
		

	}

}
