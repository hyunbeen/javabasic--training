package oop;

public class DataTest2 {

	String name = "";
	int age = 0;
	double height = 0;
	
	public static void main(String[] args) {
		DataTest2 d = new DataTest2();
		
		
		method(d);
		System.out.println("�̸�:"+d.name+" ����:"+d.age+" ����:"+d.height);
	}
	
	static void method(DataTest2 d) {
		d.name = "ȫ���";
		d.age= 33;
		d.height = 150.33;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

}
