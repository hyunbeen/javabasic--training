package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import db.OrderDB;
import obejct.Staff;

public class OrderView{
	// 주문내역 테이블
	OrderDB db;
	JTable			tableOrder;
	OrderTableModel	taOrder;
	Staff admin;
	JFrame			frame;
	JTextArea ta;
	Font th = new Font("인터파크고딕 L",Font.BOLD, 24);
	Font tf = new Font("인터파크고딕 L",Font.PLAIN,20);
	Font plain = new Font("인터파크고딕 L",Font.PLAIN,18);
	
	public OrderView() {
		
		db = new OrderDB();
		setUI();
		display();
		show();
		evetProc();
		new Thread(new Runnable() {

			
			public void run() {
				
				String str;
				while(true) {
				try {
					show();
					str = db.orderThink();
					ta.setText("요구사항\n"+str);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				
				
				
			}
			
		}).start();
	}
	public void setUI() {
		taOrder		= new OrderTableModel();
		tableOrder	= new JTable(taOrder);
		
		frame		= new JFrame("주방화면");
		ta = new JTextArea("요구사항\n",15,15);
		

	

	}
	
	public void display() {
		frame.setLayout(new BorderLayout());
		frame.setFont(plain);
		tableOrder.setFont(plain);
		tableOrder.getTableHeader().setFont(th);
		frame.add(new JScrollPane(tableOrder), BorderLayout.CENTER);
		ta.setFont(plain);
		frame.add(new JScrollPane(ta),BorderLayout.SOUTH);
	
		tableOrder.setRowHeight(45);
		tableOrder.getColumn("주문내역").setPreferredWidth(250);
		
		//가운데 정렬
		DefaultTableCellRenderer tc = new DefaultTableCellRenderer();
		tc.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tord = tableOrder.getColumnModel();
				
		for (int i = 0; i < tord.getColumnCount(); i++) {
			 	tord.getColumn(i).setCellRenderer(tc);
		}
		
		frame.setSize(1000, 500);
		//frame.pack();
		frame.setVisible(true);
	}
	
	public void show() {
		ArrayList list = new ArrayList();
		try {
			list = db.show();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		taOrder.data = list;
		tableOrder.setModel(taOrder);
		taOrder.fireTableDataChanged();
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
	
	public void evetProc() {
		tableOrder.addMouseListener(new MouseAdapter() {
			
			
			public void mouseClicked(MouseEvent e) {
				
				if(tableOrder.getSelectedColumn() == 3) {
					try {
						db.update(String.valueOf(tableOrder.getValueAt(tableOrder.getSelectedRow(), 0)));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} // 조리완료로 바꾸는 함수
					taOrder.data.remove(tableOrder.getSelectedRow());
					tableOrder.setModel(taOrder);
					taOrder.fireTableDataChanged();
				}
			}
		});
	}
	public static void main(String[] args) {
		OrderView ov = new OrderView();
		
	}

	
}