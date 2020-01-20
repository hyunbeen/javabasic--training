package student;

public class MainTest {

	public static void main(String[] args) {
		Student s = new Student("930101","홍길동");
		Teacher t = new Teacher("0001","이순신");
		Subject obj = new Subject(10,"자바",s,t);
		obj.output();
	}

}
