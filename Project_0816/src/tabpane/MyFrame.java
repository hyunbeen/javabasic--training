package tabpane;
import javax.swing.*;
public class MyFrame extends JFrame {
	CustomerView cv;
	VideoView vv;
	MyFrame(){
		cv = new CustomerView();
		vv = new VideoView();
		
		JTabbedPane tab = new JTabbedPane();
		tab.add("고객관리",cv);
		tab.add("비디오관리", vv);
		tab.setSelectedIndex(1);
		add(tab);
		
		setSize(600,400);
		setVisible(true);
	}
		public static void main(String[] args) {
			new MyFrame();
		}
	}


