//package project;
//
//import java.util.Scanner;
//import java.util.StringTokenizer;
//
//public class project1 {
//	
//	public static void main(String[] args) {
//		
//		Scanner sc = new Scanner(System.in); //입력받는 클래스
//		int number = input(sc); //학생수 값을 입력받는다
//		ptable student[] = new ptable[number]; //학생의 배열
//		datainput(student,number,sc); //각각의 학번,국어,영어,수학,이름 데이터 넣기
//		output(student,number);
//
//	}
//	
//	static int input(Scanner sc) {
//		
//		boolean condition = true;
//		int number = 0;
//		do
//		{
//		try {
//			condition =false;
//			System.out.print("입력할 학생수를 입력하세요 ");
//			number = sc.nextInt();
//	
//		}
//		catch(Exception ex) {
//			condition = true;
//			sc.nextLine();
//			System.out.println("명수를 잘못 입력하셨습니다");
//		}
//		}while(condition||(number<1)); //명수 입력 조건
//		sc.nextLine();
//		System.out.println();
//		System.out.println("3명의 국어,영어,수학 점수를 받아 결과 출력하기");
//		
//		return number;
//	}
//	
//	static void datainput(ptable student[],int number,Scanner sc) {
//		System.out.println();
//		String str = "";
//		for(int i=0;i<number;i++)
//		{
//			student[i] = new ptable();
//			System.out.print(i+1+"째 학생의 성적을 입력 -> ");
//			str = sc.nextLine();
//			StringTokenizer st = new StringTokenizer(str," ");
//			student[i].hakbun = st.nextToken();
//			student[i].name = st.nextToken();
//			student[i].kor = Integer.parseInt(st.nextToken());
//			student[i].eng = Integer.parseInt(st.nextToken());
//			student[i].math = Integer.parseInt(st.nextToken());		
//		
//		}
//		//각각의 점수를 입력
//	}
//	
//	static void output(ptable student[],int number){
//		
//		System.out.println("[결과]");
//		int jumsu [] = new int[number];
//		int khap = 0;
//		int ehap = 0;
//		int mhap = 0;
//		int hap = 0;
//		
//		for(int i=0;i<number;i++)
//		{
//			jumsu[i]=student[i].kor+student[i].eng+student[i].math;
//			khap +=student[i].kor;
//			ehap +=student[i].eng;
//			mhap +=student[i].math;
//		}
//		
//		for(int i=0;i<number;i++)
//		{
//			System.out.printf("%d째 학생의 총점은 %d 이고 평균은 %.2f 입니다\n",i+1,jumsu[i],jumsu[i]/3.0);
//		}
//		System.out.printf("국어과목 총점  %d 이고 평균은 %.2f 이다\n",khap,khap/(double)number);
//		System.out.printf("영어과목 총점  %d 이고 평균은  %.2f 이다\n",ehap,ehap/(double)number);
//		System.out.printf("수학과목 총점  %d 이고 평균은 %.2f 이다\n",mhap,mhap/(double)number);
//	}
//
//}
