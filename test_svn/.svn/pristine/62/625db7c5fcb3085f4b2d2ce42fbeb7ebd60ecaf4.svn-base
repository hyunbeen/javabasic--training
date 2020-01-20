package view;

import db.PayDB;
import obejct.Staff;
import view.GoOutView.ButtonEventHandler;

import java.awt.*;
import java.awt.color.CMMException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
public class PayView implements ActionListener {
	PayDB db;
	JFrame f_pay;

	JRadioButton rbPackY, rbPackN;	// 포장여부

	JLabel lono;		// 주문번호

	JLabel locost;		// 결제금액
	JLabel ldecost;		// 할인금액
	
	JTextField	tfpay;		// 받은금액
	JButton bcash, bcredit;	// 결제수단
	


	JComboBox cbodis;

	JButton bcost;
	Staff admin;
	
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
	}
	public void display() {

		f_pay.setLayout(new BorderLayout());

		// 결제방법 버튼영역
		JPanel p_pbutton = new JPanel(new GridLayout(1, 2));
		p_pbutton.add(bcash);
		p_pbutton.add(bcredit);


		// 결제 영역
		JPanel p_pay = new JPanel(new GridLayout(7, 2));
		p_pay.add(rbPackY);
		rbPackY.setSelected(true);
		p_pay.add(rbPackN);
		p_pay.add(new JLabel("주문번호"));
		p_pay.add(lono);

		p_pay.add(new JLabel("결제금액"));
		p_pay.add(locost);
		p_pay.add(new JLabel("할인금액"));
		p_pay.add(ldecost);
		p_pay.add(new JLabel("받은금액"));
		p_pay.add(tfpay);
		p_pay.add(new JLabel("할인"));
		p_pay.add(cbodis);
		p_pay.add(new JLabel("결제방법"));
		p_pay.add(p_pbutton);


		// 전체 화면 영역
		f_pay.add(p_pay,BorderLayout.WEST);//결제 요소 입력
		f_pay.add(bcost, BorderLayout.SOUTH);//결제 버튼

		f_pay.setVisible(true);
		f_pay.pack();
	}
	
	public PayView(Staff login, JTable tOrder, String cost ) {
		admin = login;
		connectDB();
		setUI();
		display();
		locost.setText(cost);
		this.cost = Integer.parseInt(cost);
		eventProc();
		comLoad();	// 할인 콤보박스 값 가져오기
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
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if(o==bcash) {
			JOptionPane.showMessageDialog(null, "현금결제");
		} else if(o==bcredit) {
			JOptionPane.showMessageDialog(null, "카드결제");
		} else if(o==bcost) {
			JOptionPane.showMessageDialog(null, "결제버튼");
		} else if(o==rbPackY) {
			rbPackN.setSelected(false);
		} else if(o==rbPackN) {
			rbPackY.setSelected(false);
		} else if(o==cbodis) {	// 할인선택
			//JOptionPane.showMessageDialog(null, "할인콤보박스선택");
			decSearch();
		}
	}
	
	void decSearch() {}

}
