package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import db.CostGraphDB;
public class CostGraphView extends JPanel implements ActionListener {
	CostGraphDB db;
	
	
	JButton bsearch; //월별 매출 그래프를 보여주는 버튼과 검색버튼
	JButton binitial;
	JTable tbl_cost;
	GraphViewTableModel Gmodel;
	JComboBox cbyear;
	JComboBox cbmonth;//검색을 위한 콤보박스
	
	Font th = new Font("인터파크고딕 L",Font.BOLD, 24);
	Font tf = new Font("인터파크고딕 L",Font.PLAIN,20);
	Font plain = new Font("인터파크고딕 L",Font.PLAIN,18);
	//폰트 설정
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
	}//table을 설정하는 함수
	public void setUI() {
		Gmodel = new GraphViewTableModel();
		tbl_cost = new JTable(Gmodel);
		bsearch = new JButton("검색");
		binitial = new JButton("새로고침");
		binitial.setFont(th);
		cbyear = new JComboBox();
		for(int i=2000;i<=2017;i++) {
			cbyear.addItem(String.valueOf(i));
		}//연도별로 검색
		cbmonth= new JComboBox();
		for(int j=1;j<=12;j++){
			cbmonth.addItem(String.valueOf(j));
		}//월별로 검색
		
		
	}
	public void display() {
		try {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
		JPanel p_search = new JPanel(new FlowLayout());
		p_search.setFont(plain);
		cbyear.setFont(plain);
		p_search.add(cbyear);
		p_search.add(new JLabel("년"));
		cbmonth.setFont(plain);
		p_search.add(cbmonth);//검색을 위한 콤보박스
		p_search.add(new JLabel("월"));
		bsearch.setFont(plain);
		p_search.add(bsearch);
		binitial.setFont(plain);
		p_search.add(binitial);
		add(p_search, BorderLayout.SOUTH);
		tbl_cost.setFont(plain);
		add(new JScrollPane(tbl_cost),BorderLayout.CENTER);
		cbyear.setSelectedIndex(17);
		cbmonth.setSelectedIndex(8);
		tbl_cost.getTableHeader().setFont(th);
		setVisible(true);
		
		tbl_cost.setRowHeight(45);
		tbl_cost.getColumn("메뉴명").setPreferredWidth(250);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		
		//가운데 정렬
				DefaultTableCellRenderer tc = new DefaultTableCellRenderer();
				tc.setHorizontalAlignment(SwingConstants.CENTER);
				TableColumnModel tord = tbl_cost.getColumnModel();
						
				for (int i = 0; i < tord.getColumnCount(); i++) {
					 	tord.getColumn(i).setCellRenderer(tc);
				}
	}
	
	public void evtProc() {
		bsearch.addActionListener(this);
		binitial.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if(evt == bsearch) {
			search();
		}else if(evt == binitial) {
			showTable();
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
