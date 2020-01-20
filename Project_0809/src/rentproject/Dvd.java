package rentproject;

public class Dvd extends Item{
	private String actor;
	private String director;
	public Dvd() {
		this("알수없음","알수없음","알수없음","알수없음");
		System.out.println("Dvd 기본생성");
	}
	public Dvd(String num,String title, String actor,String director) {
		this.num = num;
		this.title = title;
		this.actor =actor;
		this.director = director;
		System.out.println("Dvd 인자생성");
	}
	public void output() {
		System.out.println("번호 : "+this.num+" 제목 : "+this.title+" 배우 : "+actor+" 감독 : "+director);
	}
}
