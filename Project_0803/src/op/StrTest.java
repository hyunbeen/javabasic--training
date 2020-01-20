package op;

import java.util.*;
import java.util.StringTokenizer;


public class StrTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		StringTokenizer st = new StringTokenizer(str,"/");
		int hap = 0;
		int number = 0;
		while(st.hasMoreTokens())
		{
			number++;
			hap+=Integer.parseInt(st.nextToken());	
		}
		System.out.println("Че : "+hap+" ЦђБе : "+hap/(double)number);
	}
}
