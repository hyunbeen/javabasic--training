package rentproject;

public class Book extends Item {
	private String writer;
	private String publisher;
	public Book() {
		this("�˼�����","�˼�����","�˼�����","�˼�����");
		System.out.println("Book �⺻����");
	}
	public Book(String num,String title,String writer,String publisher) {
		this.num = num;
		this.title = title;
		this.writer =writer;
		this.publisher = publisher;
		System.out.println("Book ���ڻ���");
		
	}
	public void output() {
		System.out.println("��ȣ : "+num+" ���� : "+title+" �۰� : "+writer+" ���ǻ� : "+publisher);
	}
}
