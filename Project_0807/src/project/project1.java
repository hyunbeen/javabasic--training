//package project;
//
//import java.util.Scanner;
//import java.util.StringTokenizer;
//
//public class project1 {
//	
//	public static void main(String[] args) {
//		
//		Scanner sc = new Scanner(System.in); //�Է¹޴� Ŭ����
//		int number = input(sc); //�л��� ���� �Է¹޴´�
//		ptable student[] = new ptable[number]; //�л��� �迭
//		datainput(student,number,sc); //������ �й�,����,����,����,�̸� ������ �ֱ�
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
//			System.out.print("�Է��� �л����� �Է��ϼ��� ");
//			number = sc.nextInt();
//	
//		}
//		catch(Exception ex) {
//			condition = true;
//			sc.nextLine();
//			System.out.println("����� �߸� �Է��ϼ̽��ϴ�");
//		}
//		}while(condition||(number<1)); //��� �Է� ����
//		sc.nextLine();
//		System.out.println();
//		System.out.println("3���� ����,����,���� ������ �޾� ��� ����ϱ�");
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
//			System.out.print(i+1+"° �л��� ������ �Է� -> ");
//			str = sc.nextLine();
//			StringTokenizer st = new StringTokenizer(str," ");
//			student[i].hakbun = st.nextToken();
//			student[i].name = st.nextToken();
//			student[i].kor = Integer.parseInt(st.nextToken());
//			student[i].eng = Integer.parseInt(st.nextToken());
//			student[i].math = Integer.parseInt(st.nextToken());		
//		
//		}
//		//������ ������ �Է�
//	}
//	
//	static void output(ptable student[],int number){
//		
//		System.out.println("[���]");
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
//			System.out.printf("%d° �л��� ������ %d �̰� ����� %.2f �Դϴ�\n",i+1,jumsu[i],jumsu[i]/3.0);
//		}
//		System.out.printf("������� ����  %d �̰� ����� %.2f �̴�\n",khap,khap/(double)number);
//		System.out.printf("������� ����  %d �̰� �����  %.2f �̴�\n",ehap,ehap/(double)number);
//		System.out.printf("���а��� ����  %d �̰� ����� %.2f �̴�\n",mhap,mhap/(double)number);
//	}
//
//}
