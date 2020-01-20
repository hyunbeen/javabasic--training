package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import db.CustomDB;

public class CustomView implements Runnable{
	
	Socket s;
	CustomDB db;
	BufferedReader in;
	OutputStream out;
    int count = 0;
	int imagecount = 0;
	String str = null; //인기메뉴 표시
	
	Font th = new Font("인터파크고딕 L",Font.BOLD, 24);
	Font tf = new Font("인터파크고딕 L",Font.PLAIN,20);
	Font plain = new Font("인터파크고딕 L",Font.PLAIN,18);
	//font
	 
	void connProc() {
		//JOptionPane.showMessageDialog(null,"접속");
		//1.소켓생성(서버아이피,서버포트)
		//2.입출력 스트림 얻어오기
		try {
		System.out.println("시도");
		s= new Socket("127.0.0.1",9000);
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		out = s.getOutputStream();
		System.out.println("연결성공");
		new Thread(this).start(); 
	
	
		
		}catch(Exception ex) {
			System.out.println("서버에 접속하지 못함" + ex.getMessage());
		}
	} // connProc ends
	
	public void run() {
		String mmenu = "";
		String mprice = "";
		int ocnt;
		while(s!=null){
			try {
				
				String msg = in.readLine();
				StringTokenizer st = new StringTokenizer(msg);
				String temp = st.nextToken();
				
				if(temp.equals("/insert")) {
					mmenu = st.nextToken();
					mprice = st.nextToken();
					ocnt = Integer.parseInt(st.nextToken());
				
					insert(mmenu,mprice,ocnt);
					continue;
					
				}else if(temp.equals("/delete")) {
					mmenu = st.nextToken();
					mprice = st.nextToken();
					ocnt = Integer.parseInt(st.nextToken());
					
					delete(mmenu,mprice,ocnt);
					
					continue;
					
				}else if(temp.equals("/initial")) {
					initial();
					tfprice.setText("");
					continue; 
				}else if(temp.equals("/total")) {
					String price = st.nextToken();
					tfprice.setText(price);
				}	
			} catch (Exception ex) {
				
				
			}
			
		}
	}
	
	private void initial() {
		taCusView.data.clear();
		tableCusView.setModel(taCusView);
		taCusView.fireTableDataChanged();
		
	}
	//결제가 완료되면 table 초기화
	public void delete(String mmenu, String mprice, int ocnt) {
		int i=0;
		
		if(count == 0) {
			
		}else {
			for(i=0;i<count;i++) {
				if(String.valueOf(tableCusView.getValueAt(i, 0)).equals(mmenu)) {
					if(ocnt != 0) {
					ArrayList list = (ArrayList)taCusView.data.get(i);
					list.set(0, mmenu);
					list.set(1, mprice);
					list.set(2, ocnt);
					try {
						str = db.popular(mmenu);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					list.set(3,str);
					tableCusView.setModel(taCusView);
					taCusView.fireTableDataChanged();
				
					return; //개수가 줄어들때 완전히 삭제되지 않는경우
					}else {
						taCusView.data.remove(i);
						count = count -1;

						
						tableCusView.setModel(taCusView);
						taCusView.fireTableDataChanged();
						labelSet();
						return;//개수가 떨어질때 완전히 삭제되는 경우
					}
				}
			}

			
		}
		
	}
	
	
	
	public void insert(String mmenu, String mprice, int ocnt) {

		int i=0;
		String imgpath;
		
		if(count == 0) {
			ArrayList temp = new ArrayList();
			temp.add(mmenu);
			temp.add(mprice);
			temp.add(ocnt);
			
			try {
				str = db.popular(mmenu);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			temp.add(str);
			taCusView.data.add(temp);
	
			count++; //행의 수를 세기위한 변수  
			
		
							
							imagecount++;
			
		}else { //데이터가 하나이상일때
			
			for(i=0;i<count;i++) {
			
				if((String.valueOf(tableCusView.getValueAt(i, 0))).equals(mmenu)) {

					ArrayList list = (ArrayList)taCusView.data.get(i);
					list.set(0, mmenu);
					list.set(1, mprice);
					list.set(2, ocnt);
					try {
						str = db.popular(mmenu);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					list.set(3,str);
					tableCusView.setModel(taCusView);
					taCusView.fireTableDataChanged();
					labelSet();
					return; //메뉴명이 같은 것이 있는 지 비교한다
				}
			}
			//행의수가 추가되지 않는다
			//같은것이 없을시
		
			ArrayList temp = new ArrayList();
			temp.add(mmenu);
			temp.add(mprice);
			temp.add(ocnt);
			try {
				str = db.popular(mmenu);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			temp.add(str);
			taCusView.data.add(temp);
			count++;//행이 추가된다
			imagecount++;
			
			
		}
		tableCusView.setModel(taCusView);
		taCusView.fireTableDataChanged();
		labelSet();
	}

	
	
	
	// 메뉴 이미지 배열
	JLabel	l_Menu[];
	
	// 주문내역 테이블 정보
	JTable		tableCusView;
	CusViewTableModel	taCusView;
	
	// 결제금액
	JTextField			tfprice;
	
	JFrame				frame;
	
	public void setUI() {
		db = new CustomDB();
		l_Menu = new JLabel[25];
		
		for(int i=0;i<25;i++) {
			l_Menu[i] = new JLabel("+");
			l_Menu[i].setSize(40,40);
		}
		
		taCusView		= new CusViewTableModel();
		tableCusView	= new JTable(taCusView);
		
		tfprice			= new JTextField();
	
		frame			= new JFrame("고객화면");
	}
	
	public void display() {
		
		frame.setLayout(new BorderLayout());
		
		// 상단 오른쪽
		JPanel pRtop	= new JPanel();
		pRtop.setFont(plain);
		pRtop.setLayout(new BorderLayout());
		tableCusView.setFont(plain);
		tableCusView.getTableHeader().setFont(th);
		pRtop.add(new JScrollPane(tableCusView));
		
		// 테이블 아래 결제금액
		JPanel pRbottom	= new JPanel();
		
		pRbottom.setLayout(new GridLayout(1, 3));
		pRbottom.setFont(th);
		JLabel l_1 = new JLabel("총 금액 : ");
		l_1.setFont(th);
		pRbottom.add(l_1);
		tfprice.setFont(th);
		pRbottom.add(tfprice);
		JLabel l_2 = new JLabel("원");
		l_2.setFont(th);
		pRbottom.add(l_2);
		
		// 오른쪽화면 붙이기
		JPanel pRight	= new JPanel();
		pRight.setLayout(new BorderLayout());
		pRight.add(pRtop,BorderLayout.CENTER);
		pRight.add(pRbottom,BorderLayout.SOUTH);
		
		// 전체화면에 붙이기
		JPanel p_Menu = new JPanel(new GridLayout(5,5));
		for(int i=0;i<25;i++) {
			p_Menu.add(l_Menu[i]);
		}
		
		
		//칸조절
		tableCusView.setRowHeight(45);
		tableCusView.getColumn("메뉴명").setPreferredWidth(150);
		tableCusView.getColumn("가격").setPreferredWidth(100);
		tableCusView.getColumn("개수").setPreferredWidth(100);
		tableCusView.getColumn("인기메뉴").setPreferredWidth(100);
		
		//가운데 정렬
		DefaultTableCellRenderer tc = new DefaultTableCellRenderer();
		tc.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tord = tableCusView.getColumnModel();
		for (int i = 0; i < tord.getColumnCount(); i++) {
				tord.getColumn(i).setCellRenderer(tc);
		}
		
		
				
		frame.add(p_Menu, BorderLayout.CENTER);
		frame.add(pRight, BorderLayout.EAST);
		
		frame.setSize(1200, 500);
		frame.setVisible(true);
		
	}
	
	public void labelSet() {
		String path[] = new String[25];
		String mmenu;
		for(int i = 0;i<25;i++) {
			l_Menu[i].setIcon(null);
		}
		for(int i = 0;i<taCusView.data.size();i++) {
			mmenu = String.valueOf(tableCusView.getValueAt(i, 0));
			try {
				path[i] = db.labelSet(mmenu);
				l_Menu[i].setIcon(new ImageIcon(path[i]));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}


	class CusViewTableModel extends AbstractTableModel{
		ArrayList data = new ArrayList();
		String[] columnNames = {"메뉴명", "가격", "개수","인기메뉴"};

		public int getRowCount() {
			return data.size();
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			ArrayList temp = (ArrayList)data.get(rowIndex);
			return temp.get(columnIndex);
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}
	}
	
	public static void main(String[] args) {
		 CustomView cv = new CustomView();
		 cv.connProc();
		 cv.setUI();
		 cv.display();
	}
	

}
