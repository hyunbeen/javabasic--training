package basic;
/*
 * GUI(Graphic User Interface)
 * -AWT : 1.2버전 이전
 * 			운영체제의 UI(모양)을 따름
 * -Swing : 1.2 이후
 * 			자바의 자체 UI
 */
import java.awt.*;

class UI extends Frame{
	//1. 멤버변수 선언
	Button b1;
	Button b2;
	TextField t1;
	TextArea t2;
	Choice ch;
	Checkbox cb1,cb2;
	//2. 멤버변수의 객체 생성
	UI(){
		super("자바 첫화면");
		b1 = new Button("확인");
		b2 = new Button("취소");
		t1 = new TextField("안녕");
		t2 = new TextArea("잘가");
		ch = new Choice();
		ch.add("초졸"); ch.add("고졸"); ch.add("대졸");
		CheckboxGroup cbg = new CheckboxGroup();
		cb1 = new Checkbox("Man",cbg,true);
		cb2 = new Checkbox("woman",cbg,false);
		
		//li1 = new li();
		//cb1 = new cb1("콤보박스");
			 
			
			 
			 
	}
	//3.화면구성
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
