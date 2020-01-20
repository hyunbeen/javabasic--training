package oop;

public class Student {
	Student(String hakbun,String name,int kor,int eng,int math){
		this.hakbun = hakbun;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		
	}
Student(){
		this.name = "È«±æµ¿";
		this.kor = 50;
		this.eng = 50;
		this.math = 50;
	}
	public String getHakbun() {
		return hakbun;
	}
	public void setHakbun(String hakbun) {
		this.hakbun = hakbun;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public void output() {
		System.out.println("["+hakbun+"]"+name+" ÃÑÁ¡:"+tot);
	}
	public void caltot() {
		tot = kor+eng+math;
	}
	public void calavg() {
		avg = tot/3.0;
	}
	private String hakbun;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int tot;
	private double avg = (kor+eng+math)/3.0;
}
