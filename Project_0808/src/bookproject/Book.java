package bookproject;

public class Book {
	static int a;
	private static int count;
	public static int getCount() {
		a=10;
		return count;
	}
	
	Book(){
		count++;
	}
	
	static {
		System.out.println("static");
	}

}
