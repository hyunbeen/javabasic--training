package iterator.fruit;

import iterator.interfaces.Aggregate;
import iterator.interfaces.IteratorM;

public class Basket  implements  Aggregate{
	//************************
	public IteratorM iterator() {		
		return new BasketIterator(this);
	}
	//************************
	
	private Fruit[] fruits;
	private int last = 0;
	public Basket( int maxsize ){
		this.fruits = new Fruit[ maxsize ];
	}
	public Fruit getFruitAt( int index ){
		return fruits[ index ];
	}
	public void appendFruit( Fruit fruits ){
		this.fruits [ last ] = fruits;
		last++;
	}
	public int getLength (){
		return last;
	}
}
