package op;

import java.util.*;
import java.util.StringTokenizer;


public class StrTest2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = " ";
		System.out.println("명수를 입력해 주세요 : ");
		int student = sc.nextInt();
		sc.nextLine();
		int hap = 0;
		int number = 0;
		int []jumsu[]  = new int[student][3];
		int i = 0;
		int j = 0;
		int khap=0;
		int ehap=0;
		int mhap=0;
		for(i=0;i<student;i++)
		{
			System.out.println(i+1+"번째의 학생의 점수를 입력해 주세요 : ");
			str = sc.nextLine();
			StringTokenizer st = new StringTokenizer(str,"/");
			while(st.hasMoreTokens())
			{
				jumsu[i][j]=Integer.parseInt(st.nextToken());
				j++;
				if(j==3)
				{
					j=0;
					break;
				}
			}
			
			
		
			
		}
		for(i=0;i<student;i++)
		{
			for(j=0;j<3;j++)
			{
				hap = hap+ jumsu[i][j];
			}
			
			System.out.println(i+1+"째 학생의 총점은 "+hap+"이고 평균은 "+hap/3.0+"입니다");
			hap = 0;
		}
		
		
		
		for(i=0;i<student;i++)
		{
			
				khap+=jumsu[i][0];
				ehap+=jumsu[i][1];
				mhap+=jumsu[i][2];
			
		}
		
	
		
	
		System.out.println("국어 총합 : "+khap+" 평균: "+khap/(double)student);
		System.out.println("영어 총합 : "+ehap+" 평균: "+ehap/(double)student);
		System.out.println("수학 총합 : "+mhap+" 평균: "+mhap/(double)student);
		
	}
}
