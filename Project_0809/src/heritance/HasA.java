package heritance;

import java.awt.Frame;

public class HasA {
	
	Frame f = new Frame("���� ù �ڹ� ȭ��");
	void init() {
		
		f.setSize(400,300);
		f.setVisible(true);
		
	}
	public static void main(String[] args) {
		HasA test = new HasA();
		test.init();
	}
}
