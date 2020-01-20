package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import db.EmployeeDB;
import obejct.Staff;

public class EmployeeView extends JPanel implements ActionListener {
	EmployeeDB db;
	

	
	JTextField tfeid;
	JTextField tfpw;
	JTextField tfename;
	JTextField tfetel;
	JTextField tfeidentify;
	
	JRadioButton rbegender1;
	JRadioButton rbegender2; //성별 라디오 버튼
	
	JTextField tfeaddress;
	JTextField tfeh_wage;
	
	JRadioButton rbetask1;
	JRadioButton rbetask2; //업무 라디오 버튼
	
	JRadioButton rbeposition1;
	JRadioButton rbeposition2;//직책 라디오 버튼
	
	JButton binsert;
	JButton bdelete;
	JButton bmodify;
	JButton bsearch;
	
	JTable tblstaff;
	TableModelStaff Smodel;
	
	JComboBox searchbox;//검색 콤보박스
	

	ButtonGroup rbeTaskButtonGroup = new ButtonGroup();
	
	JTextField tfsearch;
	
	EmployeeView(){
		db = new EmployeeDB();
		setUI();
		display();
		evetproc();
		E_Show();
	}
	public void E_Show() {
		ArrayList list = new ArrayList();
		try {
			list = db.Show();
			Smodel.data = list;
			tblstaff.setModel(Smodel);
			Smodel.fireTableDataChanged();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void E_Insert() {
		Staff staff = new Staff();
		staff.setEid(tfeid.getText());
		staff.setPw(tfpw.getText());
		staff.setEname(tfename.getText());
		staff.setEtel(tfetel.getText());
		staff.setEidentify(tfeidentify.getText());
		
		if(rbegender1.isSelected()) {
			staff.setEgender("여자");
		}else if(rbegender2.isSelected()) {
			staff.setEgender("남자");
		}
		
		if(rbetask1.isSelected()) {
			staff.setEtask("매장");
		}else if(rbetask2.isSelected()) {
			staff.setEtask("주방");
		}
		
		if(rbeposition1.isSelected()) {
			staff.setEposition("직원");
		}else if(rbetask2.isSelected()) {
			staff.setEposition("매니저");
		}
		
		staff.setEaddress(tfeaddress.getText());
		staff.setEh_wage(Integer.parseInt(tfeh_wage.getText()));
		
		try {
			db.Insert(staff);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void E_Modify() {
		Staff staff = new Staff();
		staff.setEid(tfeid.getText());
		staff.setPw(tfpw.getText());
		staff.setEname(tfename.getText());
		staff.setEtel(tfetel.getText());
		staff.setEidentify(tfeidentify.getText());
		
		if(rbegender1.isSelected()) {
			staff.setEgender("여자");
		}else if(rbegender2.isSelected()) {
			staff.setEgender("남자");
		}
		
		if(rbetask1.isSelected()) {
			staff.setEtask("매장");
		}else if(rbetask2.isSelected()) {
			staff.setEtask("주방");
		}
		
		if(rbeposition1.isSelected()) {
			staff.setEposition("직원");
		}else if(rbetask2.isSelected()) {
			staff.setEposition("매니저");
		}
		
		staff.setEaddress(tfeaddress.getText());
		staff.setEh_wage(Integer.parseInt(tfeh_wage.getText()));
		
		try {
			db.Modify(staff,tfeid.getText());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void E_Delete() {
		if(!tfeid.getText().equals("")) {
			try {
				db.Delete(tfeid.getText());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void E_Search() {
		Staff staff =new Staff();
		String eid = tfeid.getText();
		try {
			staff=db.Search(eid);
			tfeid.setText(staff.getEid());
			tfpw.setText(staff.getPw());
			tfename.setText(staff.getEname());
			tfetel.setText(staff.getEtel());
			tfeidentify.setText(staff.getEidentify());
			if(staff.getEgender().equals("여자")) {
				rbegender1.setSelected(true);
				rbegender2.setSelected(false);
			}else if(staff.getEgender().equals("남자")) {
				rbegender1.setSelected(false);
				rbegender2.setSelected(true);
			}
			tfeaddress.setText(staff.getEaddress());
			tfeh_wage.setText(String.valueOf(staff.getEh_wage()));
			if(staff.getEtask().equals("홀")) {
				rbetask1.setSelected(true);
				rbetask2.setSelected(false);
			}else if(staff.getEtask().equals("주방")) {
				rbetask1.setSelected(false);
				rbetask2.setSelected(true);
			}
			if(staff.getEposition().equals("직원")) {
				rbeposition1.setSelected(true);
				rbeposition2.setSelected(false);
			}else if(staff.getEtask().equals("매니저")) {
				rbeposition1.setSelected(false);
				rbeposition2.setSelected(true);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mousesearch() {
		
		
		Staff staff =new Staff();
		String eid = String.valueOf(tblstaff.getValueAt(tblstaff.getSelectedRow(), 1));
		try {
			staff=db.Search(eid);
			tfeid.setText(staff.getEid());
			tfpw.setText(staff.getPw());
			tfename.setText(staff.getEname());
			tfetel.setText(staff.getEtel());
			tfeidentify.setText(staff.getEidentify());
			if(staff.getEgender().equals("여자")) {
				rbegender1.setSelected(true);
				rbegender2.setSelected(false);
			}else if(staff.getEgender().equals("남자")) {
				rbegender1.setSelected(false);
				rbegender2.setSelected(true);
			}
			tfeaddress.setText(staff.getEaddress());
			tfeh_wage.setText(String.valueOf(staff.getEh_wage()));
			if(staff.getEtask().equals("홀")) {
				rbetask1.setSelected(true);
				rbetask2.setSelected(false);
			}else if(staff.getEtask().equals("주방")) {
				rbetask1.setSelected(false);
				rbetask2.setSelected(true);
			}
			if(staff.getEposition().equals("직원")) {
				
				rbeposition1.setSelected(true);
				rbeposition2.setSelected(false);
			}else if(staff.getEtask().equals("매니저")) {
				rbeposition1.setSelected(false);
				rbeposition2.setSelected(true);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void E_Table() {
		ArrayList list = new ArrayList();
		try {
			list = db.Table(String.valueOf(searchbox.getSelectedItem()),tfsearch.getText());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Smodel.data = list;
		tblstaff.setModel(Smodel);
		Smodel.fireTableDataChanged();
	}
	
	public void Clear() {
		tfeid.setText("");
		tfpw.setText("");
		
		tfename.setText("");
		tfetel.setText("");
		tfeidentify.setText("");
		rbegender1.setSelected(false);
		rbegender2.setSelected(false);
	    
		tfsearch.setText("");
		tfeaddress .setText("");
		tfeh_wage.setText("");
	    rbetask1.setSelected(false);
	    rbetask2.setSelected(false);
		rbeposition1.setSelected(false);
		rbeposition2.setSelected(false);
	}
	
	public void setUI() {
		
	
		tfeid = new JTextField(20);
		tfpw = new JTextField(20);;
		
		tfename = new JTextField(20);;
		tfetel = new JTextField(20);;
		tfeidentify = new JTextField(20);;
		rbegender1 = new JRadioButton("여자");
		rbegender2 = new JRadioButton("남자");
	    
		tfsearch = new JTextField(20);
		tfeaddress = new JTextField(20);
		tfeh_wage = new JTextField(20);
	    rbetask1 = new JRadioButton("매장");
	    rbetask2 = new JRadioButton("주방");
		rbeposition1 = new JRadioButton("직원");
		rbeposition2 = new JRadioButton("매니저");
		
		binsert = new JButton("삽입");
		bdelete = new JButton("삭제");
		bmodify = new JButton("수정");
		bsearch = new JButton("검색");
		
		Smodel = new TableModelStaff();
		tblstaff = new JTable(Smodel);
		searchbox = new JComboBox();
		
		
	}
	
	public void display() {
		setBackground(Color.white);
		setLayout(new BorderLayout());
		
		JPanel p_west = new JPanel();
		p_west.setLayout(new BorderLayout());
		
		JPanel p_west1 = new JPanel();
		p_west1.setLayout(new GridLayout(10,2));
		p_west1.add(new JLabel("직원id"));
		p_west1.add(tfeid);
		p_west1.add(new JLabel("password"));
		p_west1.add(tfpw);
		p_west1.add(new JLabel("이름"));
		p_west1.add(tfename);
		p_west1.add(new JLabel("연락처"));
		p_west1.add(tfetel);
		p_west1.add(new JLabel("주민번호"));
		p_west1.add(tfeidentify);
		p_west1.add(new JLabel("성별"));
		
		JPanel p_gender = new JPanel();
		p_gender.setLayout(new FlowLayout());
		p_gender.add(rbegender1);
		p_gender.add(rbegender2);
		rbegender1.setSelected(true);
		
		p_west1.add(p_gender);
		p_west1.add(new JLabel("주소"));
		p_west1.add(tfeaddress);
		p_west1.add(new JLabel("시급"));
		p_west1.add(tfeh_wage);
		p_west1.add(new JLabel("업무"));
		
		JPanel p_task = new JPanel();
		p_task.setLayout(new FlowLayout());
		p_task.add(rbetask1);
		p_task.add(rbetask2);
		rbetask1.setSelected(true);
		p_west1.add(p_task);//업무 라디오 버튼
		
		p_west1.add(new JLabel("직책"));
		JPanel p_position = new JPanel();
		p_position.setLayout(new FlowLayout());
		p_position.add(rbeposition1);
		p_position.add(rbeposition2);
		rbeposition1.setSelected(true);
		p_west1.add(p_position);//직책 라디오 버튼
		
		JPanel p_button = new JPanel();
		p_button.setLayout(new FlowLayout());
		p_button.add(binsert);
		p_button.add(bdelete);
		p_button.add(bmodify);//삽입 수정 삭제 버튼
		
		p_west.add(p_west1, BorderLayout.CENTER);
		p_west.add(p_button, BorderLayout.SOUTH);
		
		add(p_west,BorderLayout.WEST);
		
		JPanel p_east = new JPanel(new BorderLayout());
		p_east.add(new JScrollPane(tblstaff),BorderLayout.CENTER);
		
		JPanel p_search = new JPanel();
		p_search.setLayout(new GridLayout(1,3));
		searchbox.addItem("ID");
		searchbox.addItem("NAME");
		p_search.add(searchbox);
		p_search.add(tfsearch);//데이터 검색
		p_search.add(bsearch);
		
		
		p_east.add(p_search,BorderLayout.SOUTH);
		
		add(p_east,BorderLayout.CENTER);
		
		
		setVisible(true);
		setSize(1200,700);
		
		
	}
	
	public void evetproc() {
		rbegender1.addActionListener(this);
		rbegender2.addActionListener(this);
		rbeposition1.addActionListener(this);
		rbeposition2.addActionListener(this);
		rbetask1.addActionListener(this);
		rbetask2.addActionListener(this);
		binsert.addActionListener(this);
		bdelete.addActionListener(this);
		bmodify.addActionListener(this);
		bsearch.addActionListener(this);
		tfeid.addActionListener(this);
		tblstaff.addMouseListener( new MouseAdapter(){

		
			public void mouseClicked(MouseEvent e) {
				
				mousesearch();
			}

			
			
		});
	}

	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if(evt == rbegender1) {
			rbegender2.setSelected(false);
		}else if(evt == rbegender2) {
			rbegender1.setSelected(false);
		}else if(evt == rbeposition1) {
			rbeposition2.setSelected(false);
		}else if(evt == rbeposition2) {
			rbeposition1.setSelected(false);
		}else if(evt == rbetask1) {
			rbetask2.setSelected(false);
		}else if(evt == rbetask2) {
			rbetask1.setSelected(false);
		}else if(evt == binsert) {
			E_Insert();
			E_Show();
			Clear();
		}else if(evt == bdelete) {
			E_Delete();
			E_Show();
			Clear();
		}else if(evt == tfeid) {
			E_Search();
		}else if(evt == bsearch) {
			E_Table();
		}else if(evt == bmodify) {
			E_Modify();
			Clear();
		}
		
	}
	
	class TableModelStaff extends AbstractTableModel{

		
		ArrayList data = new ArrayList();
		String [] columnNames = {"No", "아이디", "이름"};

		public int getRowCount() {
			return data.size();
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			ArrayList temp = (ArrayList)data.get(rowIndex);
			return temp.get(columnIndex);
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}
		
	}

	
	
	
}
