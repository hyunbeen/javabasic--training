package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import db.CostGraphDB;
public class CostGraphView extends JPanel implements ActionListener {
	CostGraphDB db;
	
	
	JButton bsearch; //월별 매출 그래프를 보여주는 버튼과 검색버튼
	
	JTable tbl_cost;
	GraphViewTableModel Gmodel;
	JComboBox cbyear;
	JComboBox cbmonth;//검색을 위한 콤보박스
	
	CostGraphView(){
		db = new CostGraphDB();
		setUI();
		display();
		evtProc();
		showTable();
	}

	public void showTable() {
		ArrayList list = new ArrayList();
		try {
			list = db.showTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gmodel.data = list;
		tbl_cost.setModel(Gmodel);
		Gmodel.fireTableDataChanged();
	}
	public void setUI() {
		Gmodel = new GraphViewTableModel();
		tbl_cost = new JTable(Gmodel);
		bsearch = new JButton("검색");
		
		cbyear = new JComboBox();
		for(int i=2000;i<=2017;i++) {
			cbyear.addItem(String.valueOf(i));
		}
		cbmonth= new JComboBox();
		for(int j=1;j<=12;j++){
			cbmonth.addItem(String.valueOf(j));
		}
		
		
	}
	public void display() {
		try {
		
		setLayout(new BorderLayout());
	
		JPanel p_search = new JPanel(new FlowLayout());
		p_search.add(cbyear);
		p_search.add(new JLabel("년"));
		p_search.add(cbmonth);//검색을 위한 콤보박스
		p_search.add(new JLabel("월"));
		p_search.add(bsearch);
		add(p_search, BorderLayout.SOUTH);
		add(new JScrollPane(tbl_cost),BorderLayout.CENTER);
		
		
		setVisible(true);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public void evtProc() {
		bsearch.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if(evt == bsearch) {
			search();
		}
		
	}
	
	public void search() {
		ArrayList list = new ArrayList();
		try {
			list = db.search(String.valueOf(cbyear.getSelectedItem()),String.valueOf(cbmonth.getSelectedItem()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gmodel.data = list;
		tbl_cost.setModel(Gmodel);
		Gmodel.fireTableDataChanged();
	}
	class GraphViewTableModel extends AbstractTableModel{
		ArrayList data = new ArrayList();
		String[] columnNames = {"카테고리", "메뉴명", "판매량"};

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
