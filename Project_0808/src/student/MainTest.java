package student;

public class MainTest {

	public static void main(String[] args) {
		Student s = new Student("930101","ȫ�浿");
		Teacher t = new Teacher("0001","�̼���");
		Subject obj = new Subject(10,"�ڹ�",s,t);
		obj.output();
	}

}
