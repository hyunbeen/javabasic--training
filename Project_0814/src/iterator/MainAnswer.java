package iterator;

import iterator.fruit.Basket;
import iterator.fruit.Fruit;
import iterator.interfaces.IteratorM;

public class MainAnswer {
	public static void main(String[] args) {
			
		Basket fruitshelf = new Basket(4);
		fruitshelf.appendFruit(new Fruit("사과"));
		fruitshelf.appendFruit(new Fruit("배"));
		fruitshelf.appendFruit(new Fruit("바나나"));
		fruitshelf.appendFruit(new Fruit("참외"));
		
		IteratorM it = fruitshelf.iterator();
		while(it.hasNext()) {
			Fruit f = (Fruit)it.next();
			System.out.println(f.getCate());
		}
		
		
	}
}
