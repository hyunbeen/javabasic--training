package basic;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Info{
	//1.�����������
	JFrame f;
	Database db = null;
	JButton bInput;
	JButton bModify;
	JButton bDelete;
	JButton bShow;
	JButton bExit;
	
	JLabel LName;
	JLabel LTel;
	JLabel LJumin;
	JLabel LAge;
	JLabel LNHome;
	JLabel LGender;
	
	JTextArea ta;
	
	JTextField t1;
	JTextField t2;
	JTextField t3;
	JTextField t4;
	JTextField t5;
	JTextField t6;
	
	JMenu mfile;
	JMenu mHelp;
	JMenu m169;

	JMenuItem miNew;
	JMenuItem miOpen;
	JMenuItem miSave;
	JMenuItem miExit;
	JMenuItem miKosta;
	JMenuItem miAbout;
	JMenuItem miFor;
	
	JMenuBar mb;
	
	
	
	
	
	//2.��ü����
	Info(){
		f = new JFrame();
		bInput = new JButton("입력",new ImageIcon("src/img/1.png"));
		bInput.setRolloverIcon(new ImageIcon("src/img/2.png"));
		bInput.setPressedIcon(new ImageIcon("src/img/3.png"));
		bInput.setToolTipText("�����͸� �����մϴ�");
		bInput.setMnemonic('i');//alt + i ����Ű
		bInput.setSize(200,300);
		bModify = new JButton("수정");
		bModify.setSize(200,300);
		bDelete = new JButton("삭제");
		bDelete.setSize(200,300);
		bShow = new JButton("출력");
		bShow.setSize(200,300);
		bExit = new JButton("나가기");
		bExit.setSize(200,300);
		LName = new JLabel("이름",new ImageIcon("src/img/4.png"),JLabel.LEFT);
		LTel = new JLabel("전화");
		LJumin = new JLabel("주민번호");
		LAge = new JLabel("나이");
		LNHome = new JLabel("집");
		LGender = new JLabel("성별");
		
		ta = new JTextArea();
		t1 = new JTextField();
		t2 = new JTextField();
		t3 = new JTextField();
		t4 = new JTextField();
		t5 = new JTextField();
		t6 = new JTextField();
		
		mfile = new JMenu("����");
		mHelp = new JMenu("����");
		m169 = new JMenu("169");

		miNew = new JMenuItem("������");
		miOpen = new JMenuItem("����");
		miSave = new JMenuItem("����");
		miExit = new JMenuItem("������");
		miKosta = new JMenuItem("�ڽ�Ÿ");
		miAbout = new JMenuItem("���Ͽ�");
		miFor = new JMenuItem("���Ͽ�");
		
		mb = new JMenuBar();
		
		
	}
	void connect() {
		try {
			 db = new Database();
			ta.setText("연결성공");
		}
		catch(Exception ex) {
			ta.setText("연결실패:" + ex.getMessage());
		}
	}
	//3.ȭ�鱸��
	void setDisplay() {
		f.setLayout(new BorderLayout());
		f.add(ta,BorderLayout.EAST);
		Panel p = new Panel();
		p.setLayout(new GridLayout(6,2));
		p.add(LName);
		p.add(t1);
		p.add(LTel);
		p.add(t2);
		p.add(LJumin);
		p.add(t3);
		p.add(LAge);
		p.add(t4);
		p.add(LNHome);
		p.add(t5);
		p.add(LGender);
		p.add(t6);
		f.add(p,BorderLayout.WEST);
		Panel p1 = new Panel();
		p1.setLayout(new GridLayout(1,5));
		p1.add(bInput);
		p1.add(bModify);
		p1.add(bDelete);
		p1.add(bShow);
		p1.add(bExit);
		f.add(p1,BorderLayout.SOUTH);
		
		mfile.add(miNew);
		mfile.add(miOpen);
		mfile.add(miSave);
		mfile.add(miExit);
		
		mHelp.add(miKosta);
		mHelp.add(m169);
		
		m169.add(miAbout);
		m169.add(miFor);

		mb.add(mfile);
		mb.add(mHelp);
		
		//f.setJMenuBar(mb);
		f.add(mb,BorderLayout.NORTH);
		f.setSize(600,500);
		f.setVisible(true);
	}
	
	//4.�̺�Ʈ ó��
	void eventProc() {
		//Evn evn = new Evn();
		//bExit.addActionListener(evn);
		//bExit.addActionListener(new Evn());
//		bExit.addActionListener(new ActionListener(){
//
//			
//			public void actionPerformed(ActionEvent e) {
//				System.exit(0);
//			}
//			//�̺�Ʈ �ڵ鷯
//		});
//		
//		miExit.addActionListener(new ActionListener(){
//
//			
//			public void actionPerformed(ActionEvent e) {
//				System.exit(0);
//			}
//			//�̺�Ʈ �ڵ鷯
//		});
//		
//		t2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e)
//			{
//				String tel = t2.getText();
//				if(tel.equals("010-5097-9559")) {
//					t1.setText("������");
//				}else
//				{
//					JOptionPane.showMessageDialog(null, "���� ��ȣ�� �ƴմϴ�");
//				}
//			}
//		});
		BtnHdlr bh = new BtnHdlr();
		bInput.addActionListener(bh);
		bShow.addActionListener(bh);
		bModify.addActionListener(bh);
		bDelete.addActionListener(bh);
		//�ֹι�ȣ �Է�â���� ��Ŀ���� �Ҿ�����
		t2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					Record r = db.selectByPk(t2.getText());
					if(r==null) {
						JOptionPane.showMessageDialog(null, "데이터가 없습니다");
					}
					t1.setText(r.getName());
					t2.setText(r.getTel());
					t3.setText(r.getJumin());
					t4.setText(String.valueOf(r.getAge()));
					t5.setText(r.getHome());
					t6.setText(r.getGender());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
			

		
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			JOptionPane.showMessageDialog(null, "����");
			System.exit(0);
			
			}
		});
		
	}
	
	class BtnHdlr implements ActionListener
	{
		public void actionPerformed(ActionEvent ev) {
		JButton evt = (JButton)ev.getSource();
		if(evt==bInput) {
			try {
				insert();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("에러" + e.getMessage());
			}
		}else if(evt == bExit) {
			System.exit(0);
		}else if(evt == bShow) {
			try {
				selectall();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(evt==bModify) {
			try {
				modify();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(evt == bDelete) {
			try {
				delete();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		}
	void delete() throws SQLException {
		db.delete(t2.getText());
	}
	void modify() throws SQLException {
		Record r1 = new Record();
		r1.setName(t1.getText());
		r1.setTel(t2.getText());
		r1.setJumin(t3.getText());
		r1.setAge(Integer.parseInt(t4.getText()));
		r1.setHome(t5.getText());
		r1.setGender(t6.getText());
		db.modify(r1);
	}
	void selectall() throws Exception {
		ta.setText("name    tel     jumin    age    home    gender\n");
		ArrayList list = new ArrayList();
		list = db.selectAll();
		for(int i=0;i<list.size();i++) {
		Record rs = (Record)list.get(i);
		ta.append(rs.getName()+"\t");
		ta.append(rs.getTel()+"\t");
		ta.append(rs.getJumin()+"\t");
		ta.append(String.valueOf(rs.getAge())+"\t");
		ta.append(rs.getHome()+"\t");
		ta.append(rs.getGender());
		ta.append("\n");
		}
	}
	void insert() throws SQLException {
		String name = t1.getText();
		String tel = t2.getText();
		String jumin = t3.getText();
		int age = Integer.parseInt(t4.getText());
		String home = t5.getText();
		String gender = t6.getText();
		Record r = new Record(name, tel,jumin,age, home, gender);
		db.insert(r);
		
	}
//	class Evn implements ActionListener{
//
//		
//		public void actionPerformed(ActionEvent e) {
//			JOptionPane.showMessageDialog(null, "�ƽ� �̺�Ʈ");
//		}
//		//�̺�Ʈ �ڵ鷯
//	}

 }//end of Info class
}	
public class InfoMain {			

	public static void main(String[] args) {
		Info info = new Info();
		info.setDisplay();
		info.connect();
		info.eventProc();
	}

}
