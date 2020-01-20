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
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import db.DecreaseDB;
import obejct.Decrease;

public class DecreaseView extends JPanel implements ActionListener {
	DecreaseDB db;
	Decrease searchDb;			//수정용 데이터 객체
	Decrease deleteDb;			//삭제용 데이터 객체

	// 입력하는 textfield
	JTextField tfdisname;
	JTextField tfdisvalue;
	JTextField tfsearch;

	// 삽입,수정,삭제 버튼
	JButton binsert;
	JButton bdelete;
	JButton bmodify;
	JButton bsearch;

	JTable tbdis;
	DecreaseTableModel tbModelDecrease;
	
	Font bold = new Font("인터파크고딕 L",Font.BOLD, 24);
	Font plain = new Font("인터파크고딕 L",Font.PLAIN,24);


	public DecreaseView() {

		db = new DecreaseDB();
		searchDb = new Decrease();		//수정용 데이터 객체 생성
		deleteDb = new Decrease();		//삭제용 데이터 객체 생성
		setUI();
		display();
		initial();
		eventProc();
	}

	public void setUI() {
		
		tfdisname = new JTextField(8);
		tfdisname.setFont(plain);
		tfdisvalue = new JTextField(8);
		tfdisvalue.setFont(plain);
		tfsearch = new JTextField(8);
		tfsearch.setFont(plain);

		binsert = new JButton("삽입");
		binsert.setFont(plain);
		bdelete = new JButton("삭제");
		bdelete.setFont(plain);
		bmodify = new JButton("수정");
		bmodify.setFont(plain);
		bsearch = new JButton(new ImageIcon("src/img/search.png"));
		tbModelDecrease = new DecreaseTableModel();
		
		tbdis = new JTable(tbModelDecrease);
		tbdis.setFont(plain);
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(SwingConstants.CENTER);

		TableColumnModel tcm = tbdis.getColumnModel();
		for(int i = 0 ; i < tcm.getColumnCount();i++) {
			tcm.getColumn(i).setCellRenderer(celAlignCenter);
		}
		JTableHeader th = tbdis.getTableHeader();
		th.setFont(bold);
		th.setBackground(new Color(224,255,255));

	}

	public void display() {
		setLayout(new BorderLayout());

		JPanel p_west = new JPanel(new GridLayout(3, 1));
		p_west.add(new JLabel());
		p_west.setBackground(Color.WHITE);
		JPanel p_modify = new JPanel(new GridLayout(3, 1));
		JPanel p_1 = new JPanel(new FlowLayout());
		p_1.setBackground(Color.WHITE);
		JLabel l_1 = new JLabel("할인명");
		l_1.setFont(bold);
		p_1.add(l_1);
		p_1.add(tfdisname);
		p_modify.add(p_1);
		JPanel p_2 = new JPanel(new FlowLayout());
		p_2.setBackground(Color.WHITE);
		JLabel l_2 = new JLabel("할인율");
		l_2.setFont(bold);
		p_2.add(l_2);
		p_2.add(tfdisvalue);
		p_modify.add(p_2);
		JPanel p_3 = new JPanel(new FlowLayout());
		p_3.setBackground(Color.WHITE);
		binsert.setBackground(new Color(245,245,220));
		bmodify.setBackground(new Color(245,245,220));
		bdelete.setBackground(new Color(245,245,220));
		bsearch.setBackground(new Color(245,245,220));
		p_3.add(binsert);
		p_3.add(bmodify);
		p_3.add(bdelete);
		p_modify.add(p_3);

		p_west.add(p_modify);
		p_west.add(new JLabel(""));
		add(p_west, BorderLayout.WEST); // 왼쪽 할인 종류와 이름 입력 수정 삭제

		JPanel p_east = new JPanel(new BorderLayout());
		p_east.setBackground(Color.WHITE);
		p_east.add(new JScrollPane(tbdis), BorderLayout.CENTER);
		tbdis.setRowHeight(45);
		tbdis.getColumn("할인명").setPreferredWidth(250);
		
		
		JPanel p_search = new JPanel(new FlowLayout());
		p_search.setBackground(Color.WHITE);
		p_search.add(tfsearch);
		p_search.add(bsearch);
		p_east.add(p_search, BorderLayout.SOUTH); // 오른쪽 할인 종류와 이름 입력 수정 삭제

		add(p_east, BorderLayout.CENTER);
		


		setVisible(true);

	}

	//입력 메소드
	public void D_Insert() throws NumberFormatException {
		Decrease d = new Decrease();

		if(tfdisname.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "할인명을 입력하세요.");
		} else if(tfdisvalue.getText().equals("")){
			JOptionPane.showMessageDialog(null, "할인율을 입력하세요.");
		} else {
			try {
				d.setDecName(tfdisname.getText());
				d.setDecRate(Integer.parseInt(tfdisvalue.getText()));
				db.insertDc(d);
				JOptionPane.showMessageDialog(null, "입력 완료!");
			} catch(java.lang.NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "할인율은 숫자만 입력하세요.");
			} catch(SQLIntegrityConstraintViolationException e2) {	// 메뉴명 무결성 제약 조건 검사
				JOptionPane.showMessageDialog(null, "이미 있는 할인입니다. 수정해주세요.");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "입력오류!" + e.getMessage());
			}
			tbdis.setModel(tbModelDecrease);
			tbModelDecrease.fireTableDataChanged();
		}
	}

	//수정 메소드
	public void D_Modify() {

		try {
			if(tfdisname.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "할인명을 입력하세요.");
			} else if(tfdisvalue.getText().equals("")){
				JOptionPane.showMessageDialog(null, "할인율을 입력하세요.");
			} else {
				Decrease d = new Decrease();
				d.setDecName(tfdisname.getText());
				d.setDecRate(Integer.parseInt(tfdisvalue.getText()));
				String sDecName = searchDb.getDecName();
				int sDecRate = searchDb.getDecRate();

				int result = db.modifyDc(d,sDecName,sDecRate);
				if(result == 0) {
					JOptionPane.showMessageDialog(null, "수정할 할인이 없습니다.");
				} else {
					JOptionPane.showMessageDialog(null, "수정 완료!");
				}
			}
		}
		catch(NumberFormatException e1){
			JOptionPane.showMessageDialog(null, "할인율은 숫자만 입력하세요.");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "입력오류!" + e.getMessage());
		}
		tbdis.setModel(tbModelDecrease);
		tbModelDecrease.fireTableDataChanged();
	}



	//삭제 메소드
	public void D_Delete() {
		String sDecName = deleteDb.getDecName();
		int sDecRate = deleteDb.getDecRate();
		try {
			int result = db.deleteDc(sDecName,sDecRate);

			if (result == 0) {
				JOptionPane.showMessageDialog(null, "삭제할 할인이 없습니다.");
			} else {
				JOptionPane.showMessageDialog(null, "삭제 완료!");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "삭제오류!" + e.getMessage());
		}

		tbdis.setModel(tbModelDecrease);
		tbModelDecrease.fireTableDataChanged();
	}

	//검색 메소드 - 할인명, 할인율 동시검색
	public void D_Search() {
		ArrayList list;
		try {
			if (tfsearch.getText().equals("")) {
				list = db.show();
				tbModelDecrease.data = list;
			} else {
			list = db.selectByKeyword(tfsearch.getText());
			tbModelDecrease.data = list;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "검색실패" + e.getMessage());
		}
		tbdis.setModel(tbModelDecrease);
		tbModelDecrease.fireTableDataChanged();
	}

	// 초기화면
	void initial() {
		ArrayList list;
		try {
			list = db.selectByAll();
			tbModelDecrease.data = list;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "할인 테이블 출력실패" + e.getMessage());
		}
	}

	//textfield 비우기
	void clear() {
		tfdisname.setText("");
		tfdisvalue.setText("");
	}

	// table 모델 클래스
	class DecreaseTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "할인명", "할인율" };

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.size();
		}

		public Object getValueAt(int row, int col) {
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

	}

	//이벤트 처리
	public void actionPerformed(ActionEvent ev) {
		Object evt = ev.getSource();
		if (evt == binsert) {
			D_Insert();
			clear();
			initial();

		} else if (evt == bsearch) {
			D_Search();
			//JOptionPane.showMessageDialog(null, "검색 완료!");
		} else if( evt == bmodify) {
			D_Modify();
			clear();
			initial();

		} else if( evt == bdelete) {
			D_Delete();
			clear();
			initial();
		} else if(evt == tfsearch) {
			D_Search();
		}
	}
	//이벤트 프로시저
	public void eventProc() {
		clear();
		binsert.addActionListener(this);
		bsearch.addActionListener(this);
		bmodify.addActionListener(this);
		bdelete.addActionListener(this);
		tfsearch.addActionListener(this);
		tbdis.addMouseListener(new MouseAdapter() {
			//테이블 마우스 리스너
			public void mouseClicked(MouseEvent e) {
				int row = tbdis.getSelectedRow();
				int col = tbdis.getSelectedColumn();		

				tfdisname.setText(String.valueOf(tbdis.getValueAt(row, 0)));
				tfdisvalue.setText(String.valueOf(tbdis.getValueAt(row, 1)));

				searchDb.setDecName(String.valueOf(tbdis.getValueAt(row, 0)));
				searchDb.setDecRate((int)(tbdis.getValueAt(row, 1)));
				deleteDb.setDecName(String.valueOf(tbdis.getValueAt(row, 0)));
				deleteDb.setDecRate((int)(tbdis.getValueAt(row, 1)));
			}
		});
	}

}
