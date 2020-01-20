package prototype;

public class MessageBox implements Product {

	private char deco;
	
	public MessageBox(char deco) {
		this.deco = deco;
	}

	@Override
	public void use(String s) {
		int length = s.getBytes().length;
		for( int i=0; i < length+2 ; i++){
			System.out.print( deco);
		}
		System.out.println( );
		System.out.println( deco + " " + s + " " + deco );
		for( int i=0; i < length+2 ; i++){
			System.out.print( deco);
		}
		System.out.println( );
	}

	@Override
	public Product createClone() {
		Product p = null;
		try{
			p = (Product) clone();
		}catch(CloneNotSupportedException e){ }
		return p;
	}

}
