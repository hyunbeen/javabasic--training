package oop;

public class SwapMain {

	public static void main(String[] args) {
		Swapping s = new Swapping();
		StringBuffer a = new StringBuffer("5");
		StringBuffer b = new StringBuffer("10");
	
		
		s.swap(a, b);
		System.out.println("3--->A= "+a+" B= "+b);

	}

}
