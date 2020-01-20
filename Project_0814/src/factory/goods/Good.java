package factory.goods;

import factory.framework.Product;

public class Good extends Product {

	private String thing;
	
	Good(String thing){
		this.thing = thing;
		System.out.println( thing +"을 만듭니다");
	}
	
	public String getThing(){
		return thing;
	}
	
	@Override
	public void use() {
		System.out.println(thing +"를 사용합니다");
	}

}
