package iterator.book;

import iterator.interfaces.Aggregate;
import iterator.interfaces.IteratorM;

public class BookShelf  implements  Aggregate {

	//************************
	public IteratorM iterator() {		
		return new BookShelfIterator(this);
	}
	//************************
	
	private Book[] books;
	private int last = 0;
	public BookShelf( int maxsize ){
		this.books = new Book[ maxsize ];
	}
	public Book getBookAt( int index ){
		return books[ index ];
	}
	public void appendBook( Book book ){
		this.books [ last ] = book;
		last++;
	}
	public int getLength (){
		return last;
	}
}
