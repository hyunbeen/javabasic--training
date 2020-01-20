package iterator;

import iterator.book.Book;
import iterator.book.BookShelf;
import iterator.interfaces.IteratorM;

public class MainTest {
	public static void main(String[] args) {
		
		BookShelf bookshelf = new BookShelf(4);
		bookshelf.appendBook(new Book("인생은 무엇"));
		bookshelf.appendBook(new Book("자바란"));
		bookshelf.appendBook(new Book("좋은책"));
		bookshelf.appendBook(new Book("나쁜책"));
		
		IteratorM it = bookshelf.iterator();
		while(it.hasNext()) {
			Book b = (Book)it.next();
			System.out.println(b.getName());
		}
		
		
		
	}
}
