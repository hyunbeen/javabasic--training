package inheritance;

public class EqualsMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p1 = new Person("1111","ȫ�浿");
		Person p2 = new Person("1111","ȫ�浿");
		if(p1.equals(p2)) {
			System.out.println("������");
		}else {
			System.out.println("�ٸ����");
		}
		System.out.println(p1);
		System.out.println(p2);
	}

}

class Person{
	String hakbun, name;
	Person(String hakbun,String name){
		this.hakbun = hakbun;
		this.name = name;
		
	
	}
	public String toString() {
		return "["+hakbun+"]"+name+"�Դϴ�!!!";
	}
	public boolean equals(Object obj) {
		Person other = (Person)obj;
		if(other.hakbun.equals(this.hakbun) && other.name.equals(this.name))
		{
			return true;
		}else {
			return false;
		}
	}
}