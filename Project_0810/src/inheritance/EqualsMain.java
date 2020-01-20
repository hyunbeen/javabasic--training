package inheritance;

public class EqualsMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p1 = new Person("1111","홍길동");
		Person p2 = new Person("1111","홍길동");
		if(p1.equals(p2)) {
			System.out.println("동일인");
		}else {
			System.out.println("다른사람");
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
		return "["+hakbun+"]"+name+"입니다!!!";
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