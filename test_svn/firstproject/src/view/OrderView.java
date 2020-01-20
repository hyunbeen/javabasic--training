package view;

import java.awt.BorderLayout;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import db.OrderDB;
import obejct.Staff;

public class OrderView {
	// 주문내역 테이블
	JTable			tableOrder;
	OrderTableModel	taOrder;
	Staff admin;
	JFrame			frame;
	
	public OrderView() {
		setUI();
		display();
	}
	public OrderView(Staff login) {
		admin = login;
		setUI();
		display();
	}
	public void setUI() {
		taOrder		= new OrderTableModel();
		tableOrder	= new JTable(taOrder);
		
		frame		= new JFrame();
	}
	
	public void display() {
		frame.setLayout(new BorderLayout());
		frame.add(new JScrollPane(tableOrder), BorderLayout.CENTER);
		
		frame.pack();
		frame.setVisible(true);
		
	}

	class OrderTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = {"No", "주문내역", "요구사항", "-"};

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
		OrderView ov = new OrderView();
		
	}
}