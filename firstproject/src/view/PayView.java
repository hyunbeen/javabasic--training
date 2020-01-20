package view;

import db.PayDB;
import obejct.Staff;
import view.GoOutView.ButtonEventHandler;

import java.awt.*;
import java.awt.color.CMMException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
public class PayView implements ActionListener {
	PayDB db;
	JFrame f_pay;
	JTable tOrder;
	ArrayList list;
	JRadioButton rbPackY, rbPackN;	// 포장여부

	JLabel lono;		// 주문번호

	JLabel locost;		// 결제금액
	JLabel ldecost;		// 할인금액
	
	JTextField	tfpay;		// 받은금액
	JButton bcash, bcredit;	// 결제수단
	


	JComboBox cbodis;

	JButton bcost;
	JButton binitial;
	Staff admin;
	
	String stretc;//기타사항
	String how = ""; //결제방법
	
	Font th = new Font("인터파크고딕 L",Font.BOLD, 24);
	Font tf = new Font("인터파크고딕 L",Font.PLAIN,20);
	Font plain = new Font("인터파크고딕 L",Font.PLAIN,18);
	//font
	int cost;
	
	public void setUI() {
		f_pay = new JFrame();

		rbPackY	= new JRadioButton("포장");
		rbPackN = new JRadioButton("매장");

		lono = new JLabel();

		locost = new JLabel();	// 결제금액
		ldecost	= new JLabel();	// 할인금액
		tfpay	= new JTextField();		// 받은금액
		bcash	= new JButton("현금");	// 현금버튼
		bcredit = new JButton("카드");	// 카드버튼
		

		cbodis = new JComboBox();
		bcost = new JButton("결제");
		binitial = new JButton("결제화면");
	}
	public void display() {

		f_pay.setLayout(new BorderLayout());

		// 결제방법 버튼영역
		JPanel p_pbutton = new JPanel(new GridLayout(1, 2));
		p_pbutton.setFont(plain);
		p_pbutton.add(bcash);
		bcash.setFont(plain);
		p_pbutton.add(bcredit);
		bcredit.setFont(plain);

		// 결제 영역
		JPanel p_pay = new JPanel(new GridLayout(8, 2));
		p_pay.setFont(plain);
		p_pay.add(rbPackY);
		rbPackY.setFont(plain);
		rbPackY.setSelected(true);
		p_pay.add(rbPackN);
		rbPackN.setFont(plain);
		JLabel l_1 = new JLabel("주문번호");
		l_1.setFont(plain);
		p_pay.add(l_1);
		p_pay.add(lono);
		lono.setFont(plain);
		JLabel l_2 = new JLabel("결제금액");
		l_2.setFont(plain);
		p_pay.add(l_2);
		JLabel l_3 = new JLabel("할인금액");
		l_3.setFont(plain);
		p_pay.add(locost);
		locost.setFont(plain);
		p_pay.add(l_3);
		p_pay.add(ldecost);
		ldecost.setFont(plain);
		JLabel l_4 = new JLabel("받은금액");
		l_4.setFont(plain);
		p_pay.add(l_4);
		p_pay.add(tfpay);
		tfpay.setFont(plain);
		JLabel l_5 = new JLabel("할인");
		l_5.setFont(plain);
		cbodis.setFont(plain);
		p_pay.add(l_5);
		p_pay.add(cbodis);
		JLabel l_6 = new JLabel("결제방법");
		l_6.setFont(plain);
		p_pay.add(l_6);
		
		p_pbutton.setFont(plain);
		p_pay.add(p_pbutton);
		bcost.setFont(plain);
		p_pay.add(bcost);
		binitial.setFont(plain);
		p_pay.add(binitial);
		

		// 전체 화면 영역
		f_pay.add(p_pay,BorderLayout.CENTER);//결제 요소 입력
		

		f_pay.setVisible(true);
		f_pay.pack();
	}
	
	public PayView(Staff login,String cost, ArrayList data, String etc ) {
		System.out.println("실행");
		admin = login;
		list = data;
		stretc = etc;
		connectDB();
		setUI();
		display();
	
		comLoad();	// 할인 콤보박스 값 가져오기
	
		locost.setText(cost);
		this.cost = Integer.parseInt(cost);
		eventProc();
		
		setpay();	// 결제금액 가져오기
	}
	
	void comLoad() {
		String[] colName;
		try {
			colName = db.comList();
			for(int i = 0; i<colName.length;i++) {
			cbodis.addItem(colName[i]);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
	void setpay() {	// 결제 첫화면
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);	// 주문시간으로 주문번호만들기
		Date date = new Date();
		String ono = df.format(date);
		System.out.println(ono);
		lono.setText(ono);
	}
	
	// DB연결
	public void connectDB() {
		try {
		db = new PayDB();
		System.out.println("연결성공");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DB연결실패:"+e.getMessage());
		}
	}

	public void eventProc() {

		bcash.addActionListener(this);
		bcredit.addActionListener(this);
		bcost.addActionListener(this);
		rbPackY.addActionListener(this);
		rbPackN.addActionListener(this);
		cbodis.addActionListener(this);
		binitial.addActionListener(this);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if(o==bcash) {
			JOptionPane.showMessageDialog(null, "현금결제");
			how = "현금";
		} else if(o==bcredit) {
			JOptionPane.showMessageDialog(null, "카드결제");
			how = "결제";
		} else if(o==bcost) {
			pay();
		} else if(o==rbPackY) {
			rbPackN.setSelected(false);
		} else if(o==rbPackN) {
			rbPackY.setSelected(false);
		} else if(o==cbodis) {	// 할인선택
			
			decSearch();
		} else if(o==binitial) {
			MainView mv = new MainView(admin);
			f_pay.dispose();
		}
	}
	
	public void pay() {
		String pack;
		if(rbPackN.isSelected()) {
			pack = "매장";
		}else {
			pack = "포장";
		}
		if(tfpay.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "받은 금액을 입력해 주세요"); //금액을 입력하지 않은경우
		}else if(Integer.parseInt(tfpay.getText())<Integer.parseInt(locost.getText())){
			JOptionPane.showMessageDialog(null, "금액이 맞지 않습니다"); //긍액을 적게 입력한 경우
		}else if(how.equals("")){
			
			JOptionPane.showMessageDialog(null, "결제방법을 선택하지 않으셨습니다"); //결제방법이 선택되지 않을 경우
		}else {
			
		
		try {
			db.order(stretc,lono.getText(),list,admin.getEid(),pack);
			db.pay(how,cost,lono.getText());
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
			JOptionPane.showMessageDialog(null,"잔돈 : "+String.valueOf(Integer.parseInt(tfpay.getText())-Integer.parseInt(locost.getText()))+" 계산완료");
			f_pay.dispose();
			MainView mv = new MainView(admin);//결제후  결제 창으로 가기
		}
	}
	public void decSearch() {
		int discount;
		try {
		
			try {
				discount = db.decSearch(String.valueOf(cbodis.getSelectedItem()));
				locost.setText(String.valueOf(cost - cost * discount / 100));
				ldecost.setText(String.valueOf(cost * discount / 100));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
