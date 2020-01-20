package calendar;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ComboTest  {
	
	//멤버 변수 선언
	JFrame f;
	JComboBox cbY,cbM,cbD;
	JButton btnDate;
	int []lastDay = {31,28,31,30,31,30,31,31,30,31,30,31};
	JTextField tf;
	
	//객체 생성
	public ComboTest(){
		Integer[] intY = new Integer[11];
		for(int i=0;i<intY.length;i++) {
			intY[i] = 2010+i;
			
		}
		Integer[] intM = new Integer[12];
				for(int i=0;i<intM.length;i++) {
					intM[i] = i+1;
				}
		f = new JFrame("calendar");

		cbY = new JComboBox(intY);
		
	
		cbM = new JComboBox(intM);
	
		cbD = new JComboBox();
		btnDate = new JButton("▶");
		
		tf = new JTextField(10);
	}
	
	//화면 구성
	void addLayout() {
		f.setLayout(new FlowLayout());
		f.add(cbY);
		f.add(new JLabel("년"));
		f.add(cbM);
		f.add(new JLabel("월"));
		f.add(cbD);
		f.add(new JLabel("일"));
		f.add(btnDate);
		f.add(tf);
		f.add(new JLabel("요일"));
		
		f.setSize(600,500);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//일 초기화
		setDay();
		Calendar calendar = Calendar.getInstance();
		
		cbY.setSelectedItem(calendar.get(calendar.YEAR));
		cbM.setSelectedItem((calendar.get(calendar.MONTH))+1);
		cbD.setSelectedItem((calendar.get(calendar.DATE)));
	}
	
	//이벤트 등록
	void eventProc() {
		cbY.addActionListener(new EvtHandler());
		cbM.addActionListener(new EvtHandler());
		btnDate.addActionListener(new EvtHandler());
	}
	
	class EvtHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object evt = e.getSource();
			if(evt == cbY || evt == cbM) {
				setDay();
			}else if(evt == btnDate) {
				setDate();
			}
			
		}
		
	}
	
	
	// 콤보박스가 클릭시 해당 월의 일수 출력
	void setDay() {
		cbD.removeAllItems();
		int year = (int)cbY.getSelectedItem();
		if(year % 4 ==0 && year %100 !=0 || year % 400 == 0) {
			lastDay[1] = 29;
		}
		else {
			lastDay[1] = 28;
		}
		Integer[] intD = new Integer[lastDay[cbM.getSelectedIndex()]];
		
		for(int i=0;i<intD.length;i++) {
			intD[i] = i+1;
			cbD.addItem(intD[i]);
		}
		
	

	}
	
	//버튼이 클릭시 해당 요일을 구해서 출력
	void setDate() {
		
		int year = (int)cbY.getSelectedItem(); 
		int month = (int)cbM.getSelectedItem();
		int day = (int)cbD.getSelectedItem();
		int sumday = 0;
		int sumyear = 0;
		int sum = 0;
		switch(month) {
		
		 
		case 12: sumday += 30; 
		case 11: sumday += 31; 
		case 10 : sumday += 30;
		case 9 : sumday += 31;
		case 8 : sumday += 31; 
		case 7 : sumday += 30; 
		case 6 : sumday += 31;
		case 5 : sumday += 30;
		case 4 : sumday += 31;
		case 3 : if(year % 4 ==0 && year %100 !=0 || year % 400 == 0) {
			sumday += 29;
		}else
		{
			sumday+=28;
		}
		case 2: sumday+=31; 
		}
		
		
		switch(year) {
		
		case 2020 : sumyear += 365;
		case 2019 : sumyear += 365;
		case 2018 : sumyear += 365;
		case 2017 : sumyear += 366;
		case 2016 : sumyear += 365;
		case 2015 : sumyear += 365;
		case 2014 : sumyear += 365;
		case 2013 : sumyear += 366;
		case 2012 : sumyear += 365;
		case 2011 : sumyear += 365;
		
			
			
		}
		
		sum = sumyear + sumday + day - 1;
		switch(sum%7) {
		case 0:tf.setText("금");break;
		case 1:tf.setText("토");break;
		case 2:tf.setText("일");break;
		case 3:tf.setText("월");break;
		case 4:tf.setText("화");break;
		case 5:tf.setText("수");break;
		case 6:tf.setText("목");break;
		
		}
	}
	
	public static void main(String [] args) {
		ComboTest cb = new ComboTest();
		cb.addLayout();
		cb.eventProc();
	}
}
