package rentproject;

public class Dvd extends Item{
	private String actor;
	private String director;
	public Dvd() {
		this("�˼�����","�˼�����","�˼�����","�˼�����");
		System.out.println("Dvd �⺻����");
	}
	public Dvd(String num,String title, String actor,String director) {
		this.num = num;
		this.title = title;
		this.actor =actor;
		this.director = director;
		System.out.println("Dvd ���ڻ���");
	}
	public void output() {
		System.out.println("��ȣ : "+this.num+" ���� : "+this.title+" ��� : "+actor+" ���� : "+director);
	}
}
