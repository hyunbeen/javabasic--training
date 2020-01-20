package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

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
	JRadioButton rbegender2; // 성별 라디오 버튼

	JTextField tfeaddress;
	JTextField tfeh_wage;

	JRadioButton rbetask1;
	JRadioButton rbetask2; // 업무 라디오 버튼

	JRadioButton rbeposition1;
	JRadioButton rbeposition2;// 직책 라디오 버튼

	JButton binsert;
	JButton bdelete;
	JButton bmodify;
	JButton bsearch;

	JTable tblstaff;
	TableModelStaff Smodel;

	JComboBox searchbox;// 검색 콤보박스

	ButtonGroup rbeTaskButtonGroup = new ButtonGroup();

	JTextField tfsearch;

	Font fth = new Font("인터파크고딕 L", Font.BOLD, 21);
	Font ftf = new Font("인터파크고딕 L", Font.PLAIN, 20);
	Font fcb = new Font("인터파크고딕 L", Font.PLAIN, 18);

	EmployeeView() {
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
		String regExp_pass = "^[A-Za-z0-9]{8,12}$";
		String regExp_tel = "^\\d{3}-\\d{3,4}-\\d{4}$";
		Pattern pattern_pass = Pattern.compile(regExp_pass);
		Pattern pattern_tel = Pattern.compile(regExp_tel);
		Matcher matcher_pass = pattern_pass.matcher(tfpw.getText());
		Matcher matcher_tel = pattern_tel.matcher(tfetel.getText());
		// 비밀번호와 핸드폰 번호의 형식을 지정하기위한 코드
		if (tfeid.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "직원id를 입력해주세요.");
		} else if (tfpw.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "password를 입력해주세요.");
		} else if (tfename.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "이름을 입력해주세요");
		} else if (tfetel.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "연락처를 입력해주세요.");
		} else if (tfeidentify.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "주민번호를 입력해주세요.");
		} else if (tfeaddress.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "주소를 입력새주세요.");
		} else if (tfeh_wage.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "시급을 입력해주세요.");
		} else if (!matcher_pass.find()) {
			JOptionPane.showMessageDialog(null, "비밀번호가 잘못된 형식입니다.");
		} else if (!matcher_tel.find()) {
			JOptionPane.showMessageDialog(null, "전화번호가 잘못된 형식입니다."); // 입력되지 않았을 경우의 예외처리
		} else {
			Staff staff = new Staff();
			staff.setEid(tfeid.getText());
			staff.setPw(tfpw.getText());
			staff.setEname(tfename.getText());
			staff.setEtel(tfetel.getText());
			staff.setEidentify(tfeidentify.getText());

			if (rbegender1.isSelected()) {
				staff.setEgender("여자");
			} else if (rbegender2.isSelected()) {
				staff.setEgender("남자");
			}

			if (rbetask1.isSelected()) {
				staff.setEtask("매장");
			} else if (rbetask2.isSelected()) {
				staff.setEtask("주방");
			}

			if (rbeposition1.isSelected()) {
				staff.setEposition("직원");
			} else if (rbeposition2.isSelected()) {
				staff.setEposition("매니저");
			}

			try {
				staff.setEaddress(tfeaddress.getText());
				staff.setEh_wage(Integer.parseInt(tfeh_wage.getText()));
				db.Insert(staff);
				JOptionPane.showMessageDialog(null, "삽입완료");
				Clear();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "시급은 숫자만 입력 가능합니다.");
				tfeh_wage.setText(null);
				return;
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "삽입오류:" + e.getMessage());
			}
			// 월급에 대한 예외처리
		}

	}

	public void E_Modify() {
		String regExp_pass = "^[A-Za-z0-9]{8,12}$";
		String regExp_tel = "^\\d{3}-\\d{3,4}-\\d{4}$";
		Pattern pattern_pass = Pattern.compile(regExp_pass);
		Pattern pattern_tel = Pattern.compile(regExp_tel);
		Matcher matcher_pass = pattern_pass.matcher(tfpw.getText());
		Matcher matcher_tel = pattern_tel.matcher(tfetel.getText());
		// 비밀번호와 핸드폰 번호의 형식을 지정하기위한 코드
		if (tfeid.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "직원id를 입력해주세요.");
		} else if (tfpw.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "password를 입력해주세요.");
		} else if (tfename.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "이름을 입력해주세요");
		} else if (tfetel.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "연락처를 입력해주세요.");
		} else if (tfeidentify.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "주민번호를 입력해주세요.");
		} else if (tfeaddress.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "주소를 입력새주세요.");
		} else if (tfeh_wage.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "시급을 입력해주세요.");
		} else if (!matcher_pass.find()) {
			JOptionPane.showMessageDialog(null, "비밀번호가 잘못된 형식입니다.");
		} else if (!matcher_tel.find()) {
			JOptionPane.showMessageDialog(null, "전화번호가 잘못된 형식입니다."); // 입력되지 않았을 경우의 예외처리
		} else {
			Staff staff = new Staff();
			staff.setEid(tfeid.getText());
			staff.setPw(tfpw.getText());
			staff.setEname(tfename.getText());
			staff.setEtel(tfetel.getText());
			staff.setEidentify(tfeidentify.getText());

			if (rbegender1.isSelected()) {
				staff.setEgender("여자");
			} else if (rbegender2.isSelected()) {
				staff.setEgender("남자");
			}

			if (rbetask1.isSelected()) {
				staff.setEtask("매장");
			} else if (rbetask2.isSelected()) {
				staff.setEtask("주방");
			}

			if (rbeposition1.isSelected()) {
				staff.setEposition("직원");
			} else if (rbeposition2.isSelected()) {
				staff.setEposition("매니저");
			}

			try {
				staff.setEaddress(tfeaddress.getText());
				staff.setEh_wage(Integer.parseInt(tfeh_wage.getText()));
				db.Modify(staff, tfeid.getText());
				JOptionPane.showMessageDialog(null, "수정완료");
				Clear();

			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "시급은 숫자만 입력 가능합니다.");
				tfeh_wage.setText(null);
				return;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "수정오류:" + e.getMessage());
			}
		}
	}

	public void E_Delete() {
		if (!tfeid.getText().equals("")) {
			try {
				db.Delete(tfeid.getText());
			} catch (SQLException e) {

				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "데이터를 입력하지 않아 삭제를 할수 없습니다");
		}
	}

	// TEXTFIELD에서 검색
	public void E_Search() {
		Staff staff = new Staff();
		String eid = tfeid.getText();
		// textfield eid를 가져와서 검색
		try {
			staff = db.Search(eid);
			tfeid.setText(staff.getEid());
			tfpw.setText(staff.getPw());
			tfename.setText(staff.getEname());
			tfetel.setText(staff.getEtel());
			tfeidentify.setText(staff.getEidentify());
			if (staff.getEgender().equals("여자")) {
				rbegender1.setSelected(true);
				rbegender2.setSelected(false);
			} else if (staff.getEgender().equals("남자")) {
				rbegender1.setSelected(false);
				rbegender2.setSelected(true);
			}
			tfeaddress.setText(staff.getEaddress());
			tfeh_wage.setText(String.valueOf(staff.getEh_wage()));
			if (staff.getEtask().equals("홀")) {
				rbetask1.setSelected(true);
				rbetask2.setSelected(false);
			} else if (staff.getEtask().equals("주방")) {
				rbetask1.setSelected(false);
				rbetask2.setSelected(true);
			}
			if (staff.getEposition().equals("직원")) {
				rbeposition1.setSelected(true);
				rbeposition2.setSelected(false);
			} else if (staff.getEposition().equals("매니저")) {
				rbeposition1.setSelected(false);
				rbeposition2.setSelected(true);
			}
			// textfield eid를 가져와서 검색하여 해당 데이터를 왼쪽 기입란에 넣어준다
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void mousesearch() {

		Staff staff = new Staff();
		String eid = String.valueOf(tblstaff.getValueAt(tblstaff.getSelectedRow(), 1));
		try {
			staff = db.Search(eid);
			// eid를 매게 변수로 해당데이터리를 staff 변수에 담는다

			tfeid.setText(staff.getEid());
			tfpw.setText(staff.getPw());
			tfename.setText(staff.getEname());
			tfetel.setText(staff.getEtel());
			tfeidentify.setText(staff.getEidentify());
			if (staff.getEgender().equals("여자")) {
				rbegender1.setSelected(true);
				rbegender2.setSelected(false);
			} else if (staff.getEgender().equals("남자")) {
				rbegender1.setSelected(false);
				rbegender2.setSelected(true);
			}
			tfeaddress.setText(staff.getEaddress());
			tfeh_wage.setText(String.valueOf(staff.getEh_wage()));
			if (staff.getEtask().equals("매장")) {
				rbetask1.setSelected(true);
				rbetask2.setSelected(false);
			} else if (staff.getEtask().equals("주방")) {
				rbetask1.setSelected(false);
				rbetask2.setSelected(true);
			}
			if (staff.getEposition().equals("직원")) {

				rbeposition1.setSelected(true);
				rbeposition2.setSelected(false);
			} else if (staff.getEposition().equals("매니저")) {
				rbeposition1.setSelected(false);
				rbeposition2.setSelected(true);
			}
			// staff 정보를 왼쪽 정보기입란에 뿌려준다

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// table을 누를시에 해당 eid를 통한 검색 데이터가 나타난다

	public void E_Table() {
		ArrayList list = new ArrayList();
		try {
			list = db.Table(String.valueOf(searchbox.getSelectedItem()), tfsearch.getText());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Smodel.data = list;
		tblstaff.setModel(Smodel);
		Smodel.fireTableDataChanged();
	} // search 콤보박스를 통한 검색 table 검색

	public void Clear() {
		tfeid.setText("");
		tfpw.setText("");

		tfename.setText("");
		tfetel.setText("");
		tfeidentify.setText("");
		rbegender1.setSelected(true);
		rbegender2.setSelected(false);

		tfsearch.setText("");
		tfeaddress.setText("");
		tfeh_wage.setText("");
		rbetask1.setSelected(true);
		rbetask2.setSelected(false);
		rbeposition1.setSelected(true);
		rbeposition2.setSelected(false);
	}

	public void setUI() {

		tfeid = new JTextField(12);
		tfpw = new JTextField(12);
		tfpw.setToolTipText("비밀번호를 8자 이상 영문자와 숫자를 조합하여 입력하시오 ");
		// 아이디와 패스워드를 입력

		tfename = new JTextField(12);
		// 직원 이름을 입력

		tfetel = new JTextField(12);
		tfetel.setToolTipText("xxx-xxxx-xxxx 형태로 입력하시오");
		// 직원 전화번호를 입력
		tfeidentify = new JTextField(12);

		tfsearch = new JTextField(8);
		// 검색 바

		tfeaddress = new JTextField(12);
		tfeh_wage = new JTextField(12);
		// 이메일과 임금을 입력

		rbegender1 = new JRadioButton("여자");
		rbegender2 = new JRadioButton("남자");

		rbetask1 = new JRadioButton("매장");
		rbetask2 = new JRadioButton("주방");

		rbeposition1 = new JRadioButton("직원");
		rbeposition2 = new JRadioButton("매니저");
		// 라디오 버튼으로 입력

		binsert = new JButton("삽입");
		bdelete = new JButton("삭제");
		bmodify = new JButton("수정");
		bsearch = new JButton(new ImageIcon("src/img/search.png"));
		// 삽입 삭제 수정 검색을 위한 버튼

		Smodel = new TableModelStaff();
		tblstaff = new JTable(Smodel);
		// 직원정보를 보여주는 테이블과 모델 데이터

		searchbox = new JComboBox(); // 검색을 위한 카테고리 선택 박스

	}

	public void display() {

		setLayout(new BorderLayout());

		JPanel p_west = new JPanel();
		p_west.setLayout(new BorderLayout());
		p_west.setBackground(Color.WHITE);
		JPanel p_west1 = new JPanel();
		p_west1.setBackground(Color.WHITE);
		p_west1.setLayout(new GridLayout(10, 2));
		JLabel l1 = new JLabel("ID");
		l1.setFont(fcb);
		p_west1.add(l1);
		p_west1.add(tfeid);
		tfeid.setFont(fcb);
		JLabel l2 = new JLabel("password");
		p_west1.add(l2);
		l2.setFont(fcb);
		p_west1.add(tfpw);
		tfpw.setFont(fcb);
		JLabel l3 = new JLabel("이름");
		p_west1.add(l3);
		l3.setFont(fcb);
		p_west1.add(tfename);
		tfename.setFont(fcb);
		JLabel l4 = new JLabel("연락처");
		p_west1.add(l4);
		l4.setFont(fcb);
		p_west1.add(tfetel);
		tfetel.setFont(fcb);
		JLabel l5 = new JLabel("주민번호");
		p_west1.add(l5);
		l5.setFont(fcb);
		p_west1.add(tfeidentify);
		tfeidentify.setFont(fcb);
		JLabel l6 = new JLabel("성별");
		l6.setFont(fcb);
		p_west1.add(l6);

		JPanel p_gender = new JPanel();
		p_gender.setBackground(Color.WHITE);
		//p_gender.setLayout(new FlowLayout());
		p_gender.add(rbegender1);
		rbegender1.setFont(fcb);
		rbegender1.setBackground(Color.WHITE);
		p_gender.add(rbegender2);
		rbegender2.setFont(fcb);
		rbegender2.setBackground(Color.WHITE);
		rbegender1.setSelected(true);

		p_west1.add(p_gender,BorderLayout.CENTER);
		JLabel l7 = new JLabel("주소");
		p_west1.add(l7);
		l7.setFont(fcb);
		p_west1.add(tfeaddress);
		tfeaddress.setFont(fcb);
		JLabel l8 = new JLabel("시급");
		l8.setFont(fcb);
		p_west1.add(l8);
		p_west1.add(tfeh_wage);
		tfeh_wage.setFont(fcb);
		JLabel l9 =new JLabel("업무");
		l9.setFont(fcb);
		p_west1.add(l9);

		JPanel p_task = new JPanel();
		p_task.setBackground(Color.WHITE);
		p_task.setLayout(new FlowLayout());
		p_task.add(rbetask1);
		p_task.add(rbetask2);
		rbetask1.setSelected(true);
		rbetask1.setFont(fcb);
		rbetask2.setFont(fcb);
		rbetask1.setBackground(Color.WHITE);
		rbetask2.setBackground(Color.WHITE);
		p_west1.add(p_task);// 업무 라디오 버튼

		JLabel l10 = new JLabel("직책");
		l10.setFont(fcb);
		p_west1.add(l10);
		JPanel p_position = new JPanel();
		p_position.setBackground(Color.WHITE);
		p_position.setLayout(new FlowLayout());
		p_position.add(rbeposition1);
		p_position.add(rbeposition2);
		rbeposition1.setFont(fcb);
		rbeposition2.setFont(fcb);
		rbeposition1.setBackground(Color.WHITE);
		rbeposition2.setBackground(Color.WHITE);
		rbeposition1.setSelected(true);
		p_west1.add(p_position);// 직책 라디오 버튼

		JPanel p_button = new JPanel();
		p_button.setBackground(Color.WHITE);
		p_button.setLayout(new FlowLayout());
		p_button.add(binsert);
		p_button.add(bmodify);// 삽입 수정 삭제 버튼
		p_button.add(bdelete);
		binsert.setFont(ftf);
		bmodify.setFont(ftf);
		bdelete.setFont(ftf);
		binsert.setBackground(new Color(245,245,220));
		bmodify.setBackground(new Color(245,245,220));
		bdelete.setBackground(new Color(245,245,220));

		p_west.add(p_west1, BorderLayout.CENTER);
		p_west.add(p_button, BorderLayout.SOUTH);

		add(p_west, BorderLayout.WEST);

		JPanel p_east = new JPanel(new BorderLayout());
		p_east.setBackground(Color.WHITE);
		p_east.add(new JScrollPane(tblstaff), BorderLayout.CENTER);
		
		tblstaff.setFont(fcb);
		tblstaff.setRowHeight(30);
		tblstaff.getColumn("No").setPreferredWidth(50);
		tblstaff.getColumn("아이디").setPreferredWidth(150);
		tblstaff.getColumn("이름").setPreferredWidth(100);
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(SwingConstants.CENTER);

		TableColumnModel tcm = tblstaff.getColumnModel();
		for(int i = 0 ; i < tcm.getColumnCount();i++) {
			tcm.getColumn(i).setCellRenderer(celAlignCenter);
		}
		JTableHeader th = tblstaff.getTableHeader();
		th.setFont(fth);
		th.setBackground(new Color(224,255,255));

		JPanel p_search = new JPanel();
		p_search.setBackground(Color.WHITE);
		//p_search.setLayout(new GridLayout(1, 3));
		searchbox.addItem("ID");
		searchbox.addItem("NAME");
		p_search.add(searchbox);
		p_search.add(tfsearch);// 데이터 검색
		p_search.add(bsearch);
		searchbox.setFont(ftf);
		tfsearch.setFont(ftf);
		bsearch.setBackground(new Color(245,245,220));
		

		p_east.add(p_search, BorderLayout.SOUTH);

		add(p_east, BorderLayout.CENTER);

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
		tblstaff.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				mousesearch();
			}

		});
	}

	// 이벤트 함수처리
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if (evt == rbegender1) {
			rbegender2.setSelected(false);
		} else if (evt == rbegender2) {
			rbegender1.setSelected(false);
		} else if (evt == rbeposition1) {
			rbeposition2.setSelected(false);
		} else if (evt == rbeposition2) {
			rbeposition1.setSelected(false);
		} else if (evt == rbetask1) {
			rbetask2.setSelected(false);
		} else if (evt == rbetask2) {
			rbetask1.setSelected(false); // 라디오 버튼 선택 이벤트
		} else if (evt == binsert) {
			E_Insert();
			E_Show();
		} else if (evt == bdelete) {
			E_Delete();
			E_Show();
			Clear();
		} else if (evt == tfeid) {
			E_Search();
		} else if (evt == bsearch) {
			E_Table();
		} else if (evt == bmodify) {
			E_Modify();
		} // 삽입 삭제 검색을 위한 이벤트

	}

	class TableModelStaff extends AbstractTableModel {

		ArrayList data = new ArrayList();
		String[] columnNames = { "No", "아이디", "이름" };

		public int getRowCount() {
			return data.size();
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			ArrayList temp = (ArrayList) data.get(rowIndex);
			return temp.get(columnIndex);
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

	}

}
