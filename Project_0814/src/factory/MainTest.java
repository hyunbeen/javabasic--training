package factory;

import factory.framework.Factory;
import factory.framework.Product;
import factory.goods.ThingFactory;

public class MainTest {

	public static void main(String[] args) {
		Factory factory = new ThingFactory();
		
		Product p1 = factory.create("티비");// Good 객체가 생성됨
		Product p2 = factory.create("냉장고");
												//디자인 패턴 factory구조 
		p1.use();
		p2.use();
	}

}
