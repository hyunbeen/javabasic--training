package heritance;

import java.awt.Frame;

public class IsA extends Frame {
	IsA(){
		super("���� �ι�° ȭ��");
	}
	void init() {
		setSize(400,300);
		setVisible(true);
	}
	public static void main(String[] args) {
		IsA test = new IsA();
		test.init();
	}
}
