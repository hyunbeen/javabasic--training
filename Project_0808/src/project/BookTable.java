package project;

import java.util.Scanner;
import java.util.StringTokenizer;

public class BookTable {
	private int booknum;
	private String bookname;
	private String bookauth;
	private boolean loan;
	
	public void init(){
		BookTable bt[] = new BookTable[1000];
		input(bt);
		
	}
	public BookTable() {
		
	}
	
	public void input(BookTable bt[]) {
		Scanner sc = new Scanner (System.in);
		boolean condition = false;
		int i = 0;
		System.out.print("입력을 하시겠습니까? ");
		condition =sc.nextBoolean();
		sc.nextLine();
		
		
		while(condition) {
			bt[i]=new BookTable();
			System.out.print("데이터 입력 : ");
			String str = sc.nextLine();
			StringTokenizer st = new StringTokenizer(str," ");
			bt[i].booknum=Integer.parseInt(st.nextToken());
			bt[i].bookname=st.nextToken();
			bt[i].bookauth=st.nextToken();
			bt[i].loan=Boolean.valueOf(st.nextToken());

			System.out.print("입력을 하시겠습니까? ");
			condition = sc.nextBoolean();
			sc.nextLine();
			i++;
		}
		
		output(bt,i);
		swap(bt,i);
		output(bt,i);
	}
	
	public void output(BookTable bt[],int num) {
		
		for(int i=0;i<num;i++) {
			System.out.println("책번호 : "+bt[i].booknum+" 책이름 : "+bt[i].bookname+" 책저자 : "+bt[i].bookauth+" 대출여부 : "+bt[i].loan);
		}
	
	}
	
	public void swap(BookTable bt[],int num) {
		BookTable temp = new BookTable();
		for(int i=0;i<num;i++) {
			for(int j=0;j<=i-1;j++)
			{
				if(bt[j].bookname.compareTo(bt[j+1].bookname)>0)
				{
					temp = bt[j];
					bt[j]=bt[j+1];
					bt[j+1]=temp;
				}
					
			}
		}
	
	}
	
	
	

	public BookTable(int booknum,String bookname,String bookauth) {
		this.booknum = booknum;
		this.bookname = bookname;
		this.bookauth = bookauth;
	}
	public BookTable(int booknum) {
		
		this.booknum = booknum;
		this.bookname = "무제";
		this.bookauth = "작가미상";
	}
	
	public void setLoan(boolean loan) {
		this.loan = loan;
	}

	
	public int getBooknum() {
		return booknum;
	}

	public String getBookname() {
		return bookname;
	}

	public String getBookauth() {
		return bookauth;
	}

	public boolean isLoan() {
		return loan;
	}

	public void output() {
		System.out.println("책의 책번호 : "+booknum+" 책제목 : "+bookname+" 책저자 : "+bookauth+" 책 대출여부 : "+loan);
	}
	
	

	
}
