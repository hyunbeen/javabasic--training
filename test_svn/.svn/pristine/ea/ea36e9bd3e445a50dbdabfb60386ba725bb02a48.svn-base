package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

public class CustomView {
	
	// 메뉴 이미지
	JLabel	l_Menu;
	
	// 주문내역 테이블 정보
	JTable		tableCusView;
	CusViewTableModel	taCusView;
	
	// 결제금액
	JTextField			tfprice;
	
	JFrame				frame;
	
	public void setUI() {
		l_Menu			= new JLabel( new ImageIcon("src/img/logo.png"));
		
		taCusView		= new CusViewTableModel();
		tableCusView	= new JTable(taCusView);
		
		tfprice			= new JTextField();
	
		frame			= new JFrame();
	}
	
	public void display() {
		frame.setLayout(new BorderLayout());
		
		// 상단 오른쪽
		JPanel pRtop	= new JPanel();
		pRtop.setLayout(new BorderLayout());
		pRtop.add(new JScrollPane(tableCusView));
		
		// 테이블 아래 결제금액
		JPanel pRbottom	= new JPanel();
		pRbottom.setLayout(new GridLayout(1, 3));
		pRbottom.add(new JLabel("총 금액 : "));
		pRbottom.add(tfprice);
		pRbottom.add(new JLabel("원"));
		
		// 오른쪽화면 붙이기
		JPanel pRight	= new JPanel();
		pRight.setLayout(new BorderLayout());
		pRight.add(pRtop,BorderLayout.CENTER);
		pRight.add(pRbottom,BorderLayout.SOUTH);
		
		// 전체화면에 붙이기
		frame.add(l_Menu, BorderLayout.WEST);
		frame.add(pRight, BorderLayout.EAST);
		
		frame.pack();
		frame.setVisible(true);
	}


	class CusViewTableModel extends AbstractTableModel{
		ArrayList data = new ArrayList();
		String[] columnNames = {"메뉴명", "가격", "개수"};

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
		 cv.setUI();
		 cv.display();
	}
	

}
