package rentproject;

public class Book extends Item {
	private String writer;
	private String publisher;
	public Book() {
		this("알수없음","알수없음","알수없음","알수없음");
		System.out.println("Book 기본생성");
	}
	public Book(String num,String title,String writer,String publisher) {
		this.num = num;
		this.title = title;
		this.writer =writer;
		this.publisher = publisher;
		System.out.println("Book 인자생성");
		
	}
	public void output() {
		System.out.println("번호 : "+num+" 제목 : "+title+" 작가 : "+writer+" 출판사 : "+publisher);
	}
}
