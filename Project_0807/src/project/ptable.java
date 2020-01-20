package project;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ptable {
	
	ptable(int number){
		Scanner sc = new Scanner(System.in);
		String hakbun[]=new String[number];
		String name[]=new String[number];
		int kor[]=new int[number];
		int eng[]=new int[number];
		int math[]=new int[number];
		datainput(hakbun,name,kor,eng,math,number,sc);
	}
	void datainput(String hakbun[],String name[],int kor[],int eng[],int math[],int number,Scanner sc){
		String str = "";
		for(int i=0;i<number;i++)
		{
			
			System.out.print(i+1+"째 학생의 성적을 입력 -> ");
			str = sc.nextLine();
			StringTokenizer st = new StringTokenizer(str," ");
			hakbun[i]= st.nextToken();
			name[i] = st.nextToken();
			kor[i] = Integer.parseInt(st.nextToken());
			eng[i] = Integer.parseInt(st.nextToken());
			math[i] = Integer.parseInt(st.nextToken());		
		
		}
		
	}
}
//ptable class


