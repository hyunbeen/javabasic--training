package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import db.OrderListDB;
import obejct.Staff;

public class OrderListView implements ActionListener{
	OrderListDB db;
	
	JButton				bBill, bRePay,bInitial;
	
	JTable				tableOrderList;
	OrderListTableModel	taOrderList;
	
	JFrame				frame;
	
	Staff admin;
	
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
		bInitial	= new JButton("새로고침");
		
		
		taOrderList	= new OrderListTableModel();
		tableOrderList = new JTable(taOrderList);
		
		frame		= new JFrame();
		
	}
	public void evtProc() {
		bInitial.addActionListener(this);
	}
	public void display() {
		
		frame.setLayout(new BorderLayout());
			
		// 가운데 주문내역 테이블
		JPanel pcenter	= new JPanel();
		pcenter.setLayout(new BorderLayout());
		pcenter.add(new JScrollPane(tableOrderList), BorderLayout.CENTER);
		
		// 하단 버튼영역
		JPanel pbottom = new JPanel();
		pbottom.setLayout(new GridLayout(1, 3));
		pbottom.add(bBill);
		pbottom.add(bRePay);
		pbottom.add(bInitial);
		
		
		// 전체화면에 붙이기
		frame.add(pcenter, BorderLayout.CENTER);
		frame.add(pbottom, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
		
	}
	
	class OrderListTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = {"주문시간", "주문내역", "결제수단"};

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
	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if(evt == bInitial) {
			run();
		}
		
	}
	
	
}
