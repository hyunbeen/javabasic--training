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
	
	Font th = new Font("인터파크고딕 L",Font.BOLD, 24);
	Font tf = new Font("인터파크고딕 L",Font.PLAIN,20);
	Font plain = new Font("인터파크고딕 L",Font.PLAIN,18);

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
		cbSelect.setFont(plain);
		tfSearch	= new JTextField(20);
		tfSearch.setFont(plain);
		bSearch		= new JButton(new ImageIcon("src/img/search.png"));
		
		tableGOList.setRowHeight(35);
		
		tableGOList.getColumn("날짜").setPreferredWidth(120);
		tableGOList.getColumn("아이디").setPreferredWidth(90);
		tableGOList.getColumn("이름").setPreferredWidth(80);
		tableGOList.getColumn("출근시간").setPreferredWidth(80);
		tableGOList.getColumn("퇴근시간").setPreferredWidth(80);
		
		tableGOList.getTableHeader().setFont(th);
		tableGOList.getTableHeader().setBackground(new Color(224,255,255));
		tableGOList.setFont(tf);
		
		
	}

	public void display() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());

		// 가운데 주문내역 테이블
		JPanel pcenter	= new JPanel();
		pcenter.setLayout(new BorderLayout());
		pcenter.add(new JScrollPane(tableGOList), BorderLayout.CENTER);

		// 하단 검색영역
		JPanel pbottom	= new JPanel();
		//pbottom.setLayout(new GridLayout(1, 3));
		pbottom.add(cbSelect);
		pbottom.add(tfSearch);
		pbottom.add(bSearch);

		// 전체화면에 붙이기
		add(pcenter, BorderLayout.CENTER);
		add(pbottom, BorderLayout.SOUTH);
		
		bSearch.setBackground(new Color(245,245,220));
		
		// 테이블 가운데정렬
		DefaultTableCellRenderer tc = new DefaultTableCellRenderer();
		tc.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tm = tableGOList.getColumnModel();
		for(int i=0; i<tm.getColumnCount(); i++) {
			tm.getColumn(i).setCellRenderer(tc);
		}

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
			if(word.equals("")) {
				list = db.show();
			} else {
				list = db.Search(idx, word);
				if (list == null) {
					JOptionPane.showMessageDialog(null, "검색에 해당하는 직원이 없습니다.");
					return;
				}
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
