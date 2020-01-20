package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import db.OrderListDB;
import obejct.Staff;

public class OrderListView implements ActionListener{
	OrderListDB db;
	
	JButton				bBill, bRePay,bInitial;
	
	JTable				tableOrderList;
	OrderListTableModel	taOrderList;
	
	JFrame				frame;
	
	Staff admin;
	
	Font th = new Font("인터파크고딕 L",Font.BOLD, 24);
	Font tf = new Font("인터파크고딕 L",Font.PLAIN,20);
	Font plain = new Font("인터파크고딕 L",Font.PLAIN,18);
	//font
	boolean ischk = true ; // 주문테이블을 띄우기 위한 함수
	public OrderListView(Staff login) {
		admin = login;
		db=new OrderListDB();
		setUI();
		display();
		run();
		evtProc();
	}
	public OrderListView() {
		
	}

	public void setUI() {
		bBill		= new JButton("영수증출력");
		bRePay		= new JButton("재결제");
		bInitial	= new JButton("결제화면");
		
		
		taOrderList	= new OrderListTableModel();
		tableOrderList = new JTable(taOrderList);
		
		frame		= new JFrame();
		
	}
	public void evtProc() {
		bInitial.addActionListener(this);
		bRePay.addActionListener(this);
		bBill.addActionListener(this);
	}
	public void display() {
	
		frame.setLayout(new BorderLayout());
			
		// 가운데 주문내역 테이블
		JPanel pcenter	= new JPanel();
		pcenter.setFont(plain);
		pcenter.setLayout(new BorderLayout());
		tableOrderList.setFont(plain);
		tableOrderList.getTableHeader().setFont(th);
		pcenter.add(new JScrollPane(tableOrderList), BorderLayout.CENTER);
		
		// 하단 버튼영역
		JPanel pbottom = new JPanel();
		pbottom.setFont(plain);
		pbottom.setLayout(new GridLayout(1, 3));
		bBill.setFont(plain);
		pbottom.add(bBill);
		bRePay.setFont(plain);
		pbottom.add(bRePay);
		bInitial.setFont(plain);
		pbottom.add(bInitial);
		
		tableOrderList.setRowHeight(45);
		tableOrderList.getColumn("주문내역").setPreferredWidth(250);
		
		//가운데 정렬
		DefaultTableCellRenderer tc = new DefaultTableCellRenderer();
		tc.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tord = tableOrderList.getColumnModel();
		for (int i = 0; i < tord.getColumnCount(); i++) {
			tord.getColumn(i).setCellRenderer(tc);
		}
	
		
		// 전체화면에 붙이기
		frame.add(pcenter, BorderLayout.CENTER);
		frame.add(pbottom, BorderLayout.SOUTH);
		
		frame.setSize(800, 700);
	
		frame.setVisible(true);
		
	}
	
	class OrderListTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = {"주문번호","주문시간", "주문내역"};

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

	
	public void run() {
		ArrayList list = new ArrayList();
		
			try {
				list = db.orderTable();
				taOrderList.data = list;
				tableOrderList.setModel(taOrderList);
				taOrderList.fireTableDataChanged();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	public void repay() {
		ArrayList list = new ArrayList();
		try {
			
			list = db.repay(String.valueOf(tableOrderList.getValueAt(tableOrderList.getSelectedRow(),0)));
			
			MainView mv = new MainView(admin,list);
			frame.dispose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if(evt == bInitial) {
			MainView mv = new MainView(admin);
			frame.dispose();
		}else if(evt == bRePay) {
			repay();
		
		}else if(evt == bBill) {
			receipt();
		}
		
	}
	public void receipt() {
		ArrayList list = new ArrayList();
		String ono = String.valueOf(tableOrderList.getValueAt(tableOrderList.getSelectedRow(), 0));
		try {
			list = db.receipt(ono);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str = "";
		try {
			FileWriter fw =  new FileWriter("receipt.txt");
             BufferedWriter out = new BufferedWriter(fw);
		
		
		for(int i=0;i<list.size();i++) {
			ArrayList temp = (ArrayList)list.get(i);
			out.write("\r\n----------------------------\r\n");
			str = "";
			str += "주문번호 : "+String.valueOf(temp.get(0))+"\r\n";
			str += "주문날짜 : "+String.valueOf(temp.get(1))+"\r\n";
			str += "포장 : "+String.valueOf(temp.get(2))+"\r\n";
			str += "메뉴이름 : "+String.valueOf(temp.get(3))+"\r\n";
			str += "메뉴가격 : "+String.valueOf(temp.get(4))+"\r\n";
			str += "개수 : "+String.valueOf(temp.get(5))+"\r\n";
			str += "직원 : "+String.valueOf(temp.get(6))+"\r\n----------------------------\r\n";
			System.out.println(str);
			out.write(str);
			
						
		}
		out.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	
}
