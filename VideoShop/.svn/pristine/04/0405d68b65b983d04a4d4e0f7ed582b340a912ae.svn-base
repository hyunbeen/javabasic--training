package  view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import model.RentModel;



public class RentView extends JPanel implements ActionListener
{
	JTextField tfRentTel, tfRentCustName, tfRentVideoNum;
	JButton bRent;
	
	JTextField tfReturnVideoNum;
	JButton bReturn;
	
	JTable tableRecentList;
	RentTableModel rentTM;
	
	
	RentModel db;
	//==============================================
	//	 생성자 함수
	public RentView(){
		addLayout();//객체 생성 및 화면 붙이기
		eventProc();//이벤트 등록
		connectDB();//비지니스 로직(JDBC 객체) 연결
		search();
	}


	public void connectDB() {
	
		try {
			db = new RentModel();
		} catch (Exception e) {
			System.out.println("접속성공");
		}
		
	}


	public void eventProc() {
		tfRentTel.addActionListener(this);
		bRent.addActionListener(this);
		bReturn.addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if(evt == tfRentTel) {
			nameSearch();
			
		}else if(evt == bRent) {
			rent();
			search();
			clear();
		}else if(evt == bReturn) {
			Return();
			search();
			clear();
		}
		
	}
	
	void clear() {
		tfRentCustName.setText("");
		tfRentTel.setText("");
		tfRentVideoNum.setText("");
		tfReturnVideoNum.setText("");
	}
	void Return() {
		try {
			db.Return(tfReturnVideoNum.getText());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void search() {
		
		try {
			ArrayList<ArrayList> list = new ArrayList<ArrayList>();
			list = db.search();
			rentTM.data = list;
			tableRecentList.setModel(rentTM);
			rentTM.fireTableDataChanged();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	void rent() {
		
		try {
			int result = db.rent(tfRentTel.getText(),Integer.parseInt(tfRentVideoNum.getText()));
			if(result == 1) {
				JOptionPane.showMessageDialog(null, "대출된 자료입니다");
			}
		} catch (NumberFormatException e) {
			
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
	}
	void nameSearch() {
		String name = "";
		try {
			name = db.nameSearch(tfRentTel.getText());
			tfRentCustName.setText(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addLayout() {
		
		tfRentTel = new JTextField(20);
		tfRentCustName  = new JTextField(20);  
		tfRentVideoNum  = new JTextField(20);
		tfReturnVideoNum  = new JTextField(15);
		
		bReturn = new JButton("반납");
		bRent = new JButton("대여");
		rentTM = new RentTableModel();
		tableRecentList = new JTable(rentTM);
		
	
		
		setLayout(new BorderLayout());
		//북쪽영역
		JPanel pNorth = new JPanel();
			
			JPanel pNorthLeft = new JPanel();
			pNorthLeft.setBorder(new TitledBorder("대여관리"));
			pNorthLeft.setLayout(new GridLayout(4,2));
			pNorthLeft.add(new JLabel("전화번호"));
			pNorthLeft.add(tfRentTel);
			pNorthLeft.add(new JLabel("고객명"));
			pNorthLeft.add(tfRentCustName);
			pNorthLeft.add(new JLabel("비디오 번호"));
			pNorthLeft.add(tfRentVideoNum);
			pNorthLeft.add(bRent);
			pNorthLeft.add(new JLabel(""));
				
			
			JPanel pNorthRight = new JPanel();
			pNorthRight.setBorder(new TitledBorder("반납관리"));
			pNorthRight.setLayout(new FlowLayout());
			pNorthRight.add(new JLabel("비디오번호"));
			pNorthRight.add(tfReturnVideoNum);
			pNorthRight.add(bReturn);
			
			pNorth.setLayout(new GridLayout(1,2));
			pNorth.add(pNorthLeft);
			pNorth.add(pNorthRight);
		add(pNorth,BorderLayout.NORTH);
		
		
		//가운데 영역
		add(new JScrollPane(tableRecentList),BorderLayout.CENTER);
		
	}
	
	class RentTableModel extends AbstractTableModel{
		ArrayList<ArrayList>data = new ArrayList<ArrayList>();
		String[] columnNames = {"비디오번호","비디오제목","고객명","전화번호","반납예정일","반납여부"};
		public int getRowCount() {
			
			return data.size();
		}

		
		public int getColumnCount() {
			
			return columnNames.length;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			ArrayList temp = data.get(rowIndex);
			return temp.get(columnIndex);
		}
		
		public String getColumnName(int col) {
			return columnNames[col];
		}
		
	}

	

	
}
