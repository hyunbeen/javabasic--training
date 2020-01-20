package view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import db.GoOutDB;

public class GoOutView extends JPanel{

	JTable	tableGOList;
	GOListTableModel	taGOList;

	JComboBox	cbSelect;
	JTextField	tfSearch;
	JButton		bSearch;
	
	GoOutDB db;

	public GoOutView(){
		setUI();
		display();		// 화면설계
		eventProc();
		connectDB();	// DB연결
		setTable();		// Table 리스트 보이기
	}
	
	void setTable() {
		try {
			taGOList.data = db.setTable();
			tableGOList.setModel(taGOList);
			taGOList.fireTableDataChanged();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void connectDB() {	// DB연결
		try {
			db = new GoOutDB();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "연결실패:"+e.getMessage());
		}
	}
	
	public void setUI() {
	

		taGOList	= new GOListTableModel();
		tableGOList	= new JTable(taGOList);

		String [] cbSelectStr = {"날짜", "아이디", "이름"};
		cbSelect	= new JComboBox(cbSelectStr);
		tfSearch	= new JTextField();
		bSearch		= new JButton("검색");
	}

	public void display() {
		setLayout(new BorderLayout());

		// 가운데 주문내역 테이블
		JPanel pcenter	= new JPanel();
		pcenter.setLayout(new BorderLayout());
		pcenter.add(new JScrollPane(tableGOList), BorderLayout.CENTER);

		// 하단 검색영역
		JPanel pbottom	= new JPanel();
		pbottom.setLayout(new GridLayout(1, 3));
		pbottom.add(cbSelect);
		pbottom.add(tfSearch);
		pbottom.add(bSearch);
		
		// 전체화면에 붙이기
		add(pcenter, BorderLayout.CENTER);
		add(pbottom, BorderLayout.SOUTH);
		
		setSize(500,500);
		setVisible(true);
	}
	
	void eventProc() {
		ButtonEventHandler btHandler = new ButtonEventHandler();
		
		// 이벤트 등록
		bSearch.addActionListener(btHandler);
	}
	
	class ButtonEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent ev) {
			Object o = ev.getSource();
			
			if(o==bSearch) { // 콤보박스검색
				//JOptionPane.showMessageDialog(null, "검색버튼");
				Search();	
			}
		}
	}
	
	void Search() {
		int idx = cbSelect.getSelectedIndex();
		String word = tfSearch.getText();
		ArrayList list;
		try {
			list = db.Search(idx, word);
			if (list == null) {
				JOptionPane.showMessageDialog(null, "검색에 해당하는 직원이 없습니다.");
				return;
			}
			taGOList.data = list;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "검색오류"+e.getMessage());
		}
		tableGOList.setModel(taGOList);
		taGOList.fireTableDataChanged();
	}
	
	

	class GOListTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String [] columnNames = {"날짜", "아이디", "이름", "출근시간", "퇴근시간"};

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
	
	
		
	
	
}
