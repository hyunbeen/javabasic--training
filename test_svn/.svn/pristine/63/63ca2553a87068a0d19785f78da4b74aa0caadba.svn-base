package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import db.DecreaseDB;
import obejct.Decrease;

public class DecreaseView extends JPanel implements ActionListener {
	DecreaseDB db;
	Decrease searchDb;			//수정용 데이터 객체

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
	

	public DecreaseView() {
		db = new DecreaseDB();
		searchDb = new Decrease();		//수정용 데이터 객체 생성
		setUI();
		display();
		initial();
		eventProc();
	}

	public void setUI() {
		tfdisname = new JTextField(20);
		tfdisvalue = new JTextField(20);
		tfsearch = new JTextField(20);

		binsert = new JButton("삽입");
		bdelete = new JButton("삭제");
		bmodify = new JButton("수정");
		bsearch = new JButton("검색");
		tbModelDecrease = new DecreaseTableModel();
		tbdis = new JTable(tbModelDecrease);

	}

	public void display() {

		setLayout(new BorderLayout());

		JPanel p_west = new JPanel(new GridLayout(3, 1));
		p_west.add(new JLabel(""));

		JPanel p_modify = new JPanel(new GridLayout(3, 1));
		JPanel p_1 = new JPanel(new FlowLayout());
		p_1.add(new JLabel("할인명"));
		p_1.add(tfdisname);
		p_modify.add(p_1);
		JPanel p_2 = new JPanel(new FlowLayout());
		p_2.add(new JLabel("할인율"));
		p_2.add(tfdisvalue);
		p_modify.add(p_2);
		JPanel p_3 = new JPanel(new FlowLayout());
		p_3.add(binsert);
		p_3.add(bmodify);
		p_3.add(bdelete);
		p_modify.add(p_3);

		p_west.add(p_modify);
		p_west.add(new JLabel(""));
		add(p_west, BorderLayout.WEST); // 왼쪽 할인 종류와 이름 입력 수정 삭제

		JPanel p_east = new JPanel(new BorderLayout());
		p_east.add(new JScrollPane(tbdis), BorderLayout.CENTER);
		JPanel p_search = new JPanel(new FlowLayout());
		p_search.add(bsearch);
		p_search.add(tfsearch);
		p_east.add(p_search, BorderLayout.SOUTH); // 오른쪽 할인 종류와 이름 입력 수정 삭제

		add(p_east, BorderLayout.CENTER);

		setVisible(true);

	}

	//입력 메소드
	public void D_Insert() {
		Decrease d = new Decrease();
		d.setDecName(tfdisname.getText());
		d.setDecRate(Integer.parseInt(tfdisvalue.getText()));

		try {
			db.insertDc(d);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "입력오류!" + e.getMessage());
		}
		tbdis.setModel(tbModelDecrease);
		tbModelDecrease.fireTableDataChanged();
	}

	//수정 메소드
	public void D_Modify() {
		Decrease d = new Decrease();
		d.setDecName(tfdisname.getText());
		d.setDecRate(Integer.parseInt(tfdisvalue.getText()));
		String sDecName = searchDb.getDecName();
		int sDecRate = searchDb.getDecRate();
		try {
			db.modifyDc(d,sDecName,sDecRate);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "입력오류!" + e.getMessage());
		}
		tbdis.setModel(tbModelDecrease);
		tbModelDecrease.fireTableDataChanged();

	}

	//삭제 메소드
	public void D_Delete() {
		String sDecName = searchDb.getDecName();
		int sDecRate = searchDb.getDecRate();
		try {
			db.deleteDc(sDecName,sDecRate);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "삭제오류!" + e.getMessage());
		}
		tbdis.setModel(tbModelDecrease);
		tbModelDecrease.fireTableDataChanged();
	}

	//검색 메소드 - 할인명으로만 검색가능(추가기능 요망)
	public void D_Search() {
		ArrayList list;
		try {
			list = db.selectByKeyword(tfsearch.getText());
			tbModelDecrease.data = list;
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
			JOptionPane.showMessageDialog(null, "입력 완료!");
		} else if (evt == bsearch) {
			D_Search();
			JOptionPane.showMessageDialog(null, "검색 완료!");
		} else if( evt == bmodify) {
			D_Modify();
			clear();
			initial();
			JOptionPane.showMessageDialog(null, "수정 완료!");
		} else if( evt == bdelete) {
			D_Delete();
			clear();
			initial();
			JOptionPane.showMessageDialog(null, "삭제 완료!");
		}
	}
	//이벤트 프로시저
	public void eventProc() {
		clear();
		binsert.addActionListener(this);
		bsearch.addActionListener(this);
		bmodify.addActionListener(this);
		bdelete.addActionListener(this);
		tbdis.addMouseListener(new MouseAdapter() {
			//테이블 마우스 리스너
			public void mouseClicked(MouseEvent e) {
				int row = tbdis.getSelectedRow();
				int col = tbdis.getSelectedColumn();		
				
				//할인명 cell 클릭시
				if (col == 0) {
					//textfield에 값 띄우기
					tfdisname.setText(String.valueOf(tbdis.getValueAt(row, col)));
					tfdisvalue.setText(String.valueOf(tbdis.getValueAt(row, col+1)));
					
					//수정을 위한 데이터 객체 set
					searchDb.setDecName(String.valueOf(tbdis.getValueAt(row, col)));
					searchDb.setDecRate((int)(tbdis.getValueAt(row, col+1)));
				}
				//할인률 cell 클릭시
				else if (col == 1) {
					//textfield에 값 띄우기
					tfdisname.setText(String.valueOf( tbdis.getValueAt(row, col-1)));
					tfdisvalue.setText(String.valueOf(tbdis.getValueAt(row, col)));
					
					//수정을 위한 데이터 객체 set
					searchDb.setDecName(String.valueOf(tbdis.getValueAt(row, col-1)));
					searchDb.setDecRate((int)(tbdis.getValueAt(row, col)));
				}
			}
		});
	}

}
