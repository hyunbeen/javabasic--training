package oop;

public class Swapping {

	public void swap(int a, int b) {
		int temp = 0;
		temp = a;
		a = b;
		b= temp;
		System.out.println("A= "+a+" B= "+b);
	}
	public void swap(StringBuffer a,StringBuffer b) {
		
		StringBuffer temp1 = new StringBuffer(a);
		StringBuffer temp2 = new StringBuffer(b);
		
		a.delete(0, a.length());
		a.append(temp2);
		
		b.delete(0,b.length());
		b.append(temp1);
		System.out.println("2----->  A= "+a+" B= "+b);
	}

}
