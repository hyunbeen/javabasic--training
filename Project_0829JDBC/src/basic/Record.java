package basic;

public class Record {
	String name;
	String tel ;
	String jumin ;
	int age;
	String home ;
	String gender;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getJumin() {
		return jumin;
	}
	public void setJumin(String jumin) {
		this.jumin = jumin;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Record(String name, String tel, String jumin, int age, String home, String gender) {
		super();
		this.name = name;
		this.tel = tel;
		this.jumin = jumin;
		this.age = age;
		this.home = home;
		this.gender = gender;
	}
	public Record() {
		
	}
	
	
}
