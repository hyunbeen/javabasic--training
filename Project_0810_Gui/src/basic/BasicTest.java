package basic;
/*
 * GUI(Graphic User Interface)
 * -AWT : 1.2���� ����
 * 			�ü���� UI(���)�� ����
 * -Swing : 1.2 ����
 * 			�ڹ��� ��ü UI
 */
import java.awt.*;

class UI extends Frame{
	//1. ������� ����
	Button b1;
	Button b2;
	TextField t1;
	TextArea t2;
	Choice ch;
	Checkbox cb1,cb2;
	//2. ��������� ��ü ����
	UI(){
		super("�ڹ� ùȭ��");
		b1 = new Button("Ȯ��");
		b2 = new Button("���");
		t1 = new TextField("�ȳ�");
		t2 = new TextArea("�߰�");
		ch = new Choice();
		ch.add("����"); ch.add("����"); ch.add("����");
		CheckboxGroup cbg = new CheckboxGroup();
		cb1 = new Checkbox("Man",cbg,true);
		cb2 = new Checkbox("woman",cbg,false);
		
		//li1 = new li();
		//cb1 = new cb1("�޺��ڽ�");
			 
			
			 
			 
	}
	//3.ȭ�鱸��
	void setDisplay() {
		
		//setLayout(new FlowLayout());
		//setLayout(new GridLayout(2,4));
		setLayout(new BorderLayout());
		add(b1,BorderLayout.WEST);
		add(b2,BorderLayout.EAST);
		add(t1,BorderLayout.NORTH);
		add(t2,BorderLayout.CENTER);
		//setLayout(new FlowLayout());
		Panel p = new Panel();
		p.setLayout(new GridLayout(2,1));
		p.add(cb1);
		p.add(cb2);
		add(p,BorderLayout.SOUTH);
		//add(ch);
		setSize(600,500);
		setVisible(true);
		
	}
	
}
public class BasicTest {
	public static void main(String[] args) {
		UI ui = new UI();
		ui.setDisplay();
	
	}
}
