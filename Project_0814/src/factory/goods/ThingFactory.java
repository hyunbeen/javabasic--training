package factory.goods;

import java.util.ArrayList;
import java.util.List;

import factory.framework.Factory;
import factory.framework.Product;

public class ThingFactory extends Factory {

	private List  thingsList  = new ArrayList();
	
//	public static  ThingFactory getFactory() {
//		return new ThingFactory();		
//	}
//	
//	private ThingFactory() {}
	
	@Override
	protected Product createProduct(String thing) {
		return new Good(thing);
	}

	@Override
	protected void registerProduct(Product product) {
		thingsList.add(  ((Good)product).getThing() );
	}

	public List getThingsList(){
		return thingsList;
	}
}
