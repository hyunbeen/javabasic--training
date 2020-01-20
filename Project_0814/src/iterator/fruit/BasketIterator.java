package iterator.fruit;

import iterator.book.Book;
import iterator.book.BookShelf;
import iterator.interfaces.IteratorM;

public class BasketIterator implements  IteratorM{
	private Basket  basket;
	private int index;
	
	public BasketIterator(Basket  basket) {
		this.basket = basket;
		this.index = 0;
	}

	@Override
	public boolean hasNext() {
		if( index < basket.getLength() ){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Object next() {
		Fruit fruit = basket.getFruitAt(index);
		index++;
		return fruit;
	}
}
