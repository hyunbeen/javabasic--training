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
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import db.MenuDB;
import obejct.Menu;

public class MenuView extends JPanel implements ActionListener {

	MenuDB db;

	JComboBox cbcategory; // 카테고리 콤보박스
	JComboBox cbsearch; // 검색 콤보박스

	JTextField tfmenu; // 메뉴명 텍스트필드
	JTextField tfcost; // 가격 텍스트필드
	JTextField tfimg; // 이미지첨부 미리보기
	JTextField tfmenusearch; // 이미지첨부

	JButton bimgsearch; // 이미지첨부버튼
	JButton bmsearch;
	JButton bdelete;
	JButton bmodify;
	JButton binsert; // 삽입 수정 판매중단 검색 및 이미지 를 넣는 검색란

	JTable tbl_menu;
	TableModelMenu Mmodel;
	JLabel l_img;

	Font fth = new Font("인터파크고딕 L",Font.BOLD, 21);
	Font ftf = new Font("인터파크고딕 L",Font.PLAIN,20);
	Font fcb = new Font("인터파크고딕 L",Font.PLAIN,18);

	MenuView() {
		db = new MenuDB();
		setUI();
		display();
		evetproc();
		M_Show();
	}

	public void setUI() {

		String[] cbCategoryStr = { "햄버거", "세트", "드링크", "사이드메뉴" };
		cbcategory = new JComboBox(cbCategoryStr);
		cbcategory.setFont(fcb);

		String[] cbSearchStr = { "카테고리", "메뉴명" };
		cbsearch = new JComboBox(cbSearchStr);
		cbsearch.setFont(fcb);
		tfmenu = new JTextField(10);
		tfmenu.setFont(fcb);
		tfcost = new JTextField(10);
		tfcost.setFont(fcb);
		tfimg = new JTextField(10);
		tfmenusearch = new JTextField(10);
		tfmenusearch.setFont(fcb);

		bimgsearch = new JButton(new ImageIcon("src/img/link.png"));
		bimgsearch.setBackground(new Color(245,245,220));
		bmsearch = new JButton(new ImageIcon("src/img/search.png"));
		bmsearch.setBackground(new Color(245,245,220));
		bdelete = new JButton("판매중단");
		bdelete.setBackground(new Color(245,245,220));
		bdelete.setFont(fth);
		bmodify = new JButton("수정");
		bmodify.setBackground(new Color(245,245,220));
		bmodify.setFont(fth);
		binsert = new JButton("삽입");
		binsert.setBackground(new Color(245,245,220));
		binsert.setFont(fth);

		Mmodel = new TableModelMenu();
		tbl_menu = new JTable(Mmodel);
		tbl_menu.getTableHeader().setBackground(new Color(224,255,255));
		tbl_menu.getTableHeader().setFont(fth);
		tbl_menu.setFont(ftf);
		tbl_menu.setRowHeight(35);
		tbl_menu.getColumn("가격").setPreferredWidth(35);
		tbl_menu.getColumn("판매중").setPreferredWidth(40);

		l_img = new JLabel(new ImageIcon("src/img/image.png")); // 이미지 미리보기

	}

	public void display() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());

		JPanel p_menu = new JPanel(new GridLayout(3, 2)); // 왼쪽 상단영역
		JLabel l1 = new JLabel("카테고리");
		p_menu.add(l1);
		l1.setFont(ftf);
		p_menu.add(cbcategory);
		JLabel l2 = new JLabel("메뉴명");
		p_menu.add(l2);
		l2.setFont(ftf);
		p_menu.add(tfmenu);
		JLabel l3 = new JLabel("가격");
		p_menu.add(l3);
		l3.setFont(ftf);
		p_menu.add(tfcost);
		p_menu.setBackground(Color.WHITE);

		JPanel p_cimg = new JPanel(new BorderLayout());
		p_cimg.add(l_img, BorderLayout.CENTER);
		p_cimg.setBackground(Color.WHITE);

		JPanel p_imgsearch = new JPanel(new FlowLayout()); // 왼쪽 이미지첨부영역
		JLabel limg = new JLabel("이미지첨부");
		p_imgsearch.add(limg);
		limg.setFont(ftf);
		p_imgsearch.add(tfimg);
		p_imgsearch.add(bimgsearch);
		p_imgsearch.setBackground(Color.WHITE);

		JPanel p_button = new JPanel(new FlowLayout()); // 왼쪽 버튼영역
		p_button.add(binsert);
		p_button.add(bmodify);
		p_button.add(bdelete);
		p_button.setBackground(Color.WHITE);

		JPanel p_west = new JPanel(new BorderLayout());
		p_west.add(p_menu, BorderLayout.NORTH); // 메뉴 영역
		JPanel p_cwest = new JPanel(new BorderLayout()); // 이미지, 첨부영역
		p_cwest.add(p_cimg, new BorderLayout().CENTER);
		p_cwest.add(p_imgsearch, new BorderLayout().SOUTH);
		p_west.add(p_cwest, BorderLayout.CENTER);
		p_west.add(p_button, new BorderLayout().SOUTH);
		p_west.setBackground(Color.WHITE);
		p_cwest.setBackground(Color.WHITE);

		JPanel p_east = new JPanel(new BorderLayout()); // 오른쪽 메뉴 출력 영역
		p_east.add(new JScrollPane(tbl_menu), BorderLayout.CENTER);
		p_east.setBackground(Color.WHITE);

		JPanel p_msearch = new JPanel(new FlowLayout()); // 검색영역
		p_msearch.add(cbsearch);
		p_msearch.add(tfmenusearch);
		p_msearch.add(bmsearch);
		p_msearch.setBackground(Color.WHITE);

		p_east.add(p_msearch, BorderLayout.SOUTH);

		add(p_west, BorderLayout.WEST);
		add(p_east, BorderLayout.CENTER);

		// 테이블 가운데정렬
		DefaultTableCellRenderer tc = new DefaultTableCellRenderer();
		tc.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tm = tbl_menu.getColumnModel();
		for(int i=0; i<tm.getColumnCount(); i++) {
			tm.getColumn(i).setCellRenderer(tc);
		}
	}

	public void evetproc() {
		bmsearch.addActionListener(this);
		bimgsearch.addActionListener(this);
		binsert.addActionListener(this);
		bdelete.addActionListener(this);
		bmodify.addActionListener(this);
		tfmenu.addActionListener(this);
		tbl_menu.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				mousesearch();

			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if (evt == bimgsearch) {
			imgsearch();
		} else if (evt == binsert) {
			M_Insert();
			M_Show();

		} else if (evt == bdelete) {
			M_Delete();
			M_Show();
			Clear();
		} else if (evt == bmodify) {
			M_Modify();
			M_Show();
			Clear();
		} else if (evt == bmsearch) {
			M_Search();
			Clear();
		}

	}

	public void imgsearch() {
		JFileChooser chooser = new JFileChooser();
		String filePath = "";
		int ret = chooser.showOpenDialog(null);
		if (ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "경로를 선택하지않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}
		filePath = chooser.getSelectedFile().getPath();
		tfimg.setText(filePath);
		l_img.setIcon(new ImageIcon(filePath));

	}

	class TableModelMenu extends AbstractTableModel {

		ArrayList data = new ArrayList();
		String[] columnNames = { "카테고리", "메뉴명", "가격", "판매중" };
		

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

	public void M_Show() {
		ArrayList list = new ArrayList();
		try {
			list = db.Show();
			Mmodel.data = list;
			tbl_menu.setModel(Mmodel);
			Mmodel.fireTableDataChanged();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "메뉴보기오류:" + e.getMessage());
		}
	}

	// 메뉴삽입
	public void M_Insert() {
		Menu menu = new Menu();

		if (tfmenu.getText().equals("")) { // 메뉴이름 입력 검사
			JOptionPane.showMessageDialog(null, "메뉴이름을 입력해주세요.");
			return;
		} else if (tfcost.getText().equals("")) { // 가격 입력 검사
			JOptionPane.showMessageDialog(null, "가격을 입력해주세요.");
			return;
		} else if (tfimg.getText().equals("")) { // 이미지 첨부 검사
			JOptionPane.showMessageDialog(null, "이미지를 첨부해주세요.");
			return;
		}
		menu.setMcategory(String.valueOf(cbcategory.getSelectedItem()));
		menu.setMmenu(tfmenu.getText());
		menu.setMprice(Integer.parseInt(tfcost.getText()));
		menu.setMphoto_url(tfimg.getText());
		try {
			db.Insert(menu);
			JOptionPane.showMessageDialog(null, "메뉴삽입완료");
			Clear();
		} catch (SQLIntegrityConstraintViolationException e1) {
			JOptionPane.showMessageDialog(null, "이미 있는 메뉴입니다. 수정해주세요.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "메뉴삽입오류:" + e.getMessage());
		}

	}

	public void M_Modify() {

		if (tfmenu.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "수정할 메뉴를 선택하세요.");
		} else if (tfcost.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "금액이 올바르지 않습니다.");
		} else {
			try {
				Menu menu = new Menu();
				menu.setMcategory(String.valueOf(cbcategory.getSelectedItem()));
				menu.setMmenu(tfmenu.getText());
				menu.setMphoto_url(tfimg.getText());
				menu.setMprice(Integer.parseInt(tfcost.getText()));
				int result = db.Modify(menu, tfmenu.getText());
				if(result == 0) {
					JOptionPane.showMessageDialog(null, "수정할 메뉴가 없습니다.");
				} else {
					JOptionPane.showMessageDialog(null, "수정완료");
				}
			}

			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "수정오류:" + e.getMessage());
			}
		}
	}

	public void M_Delete() {
		if (tfmenu.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "판매중단할 메뉴를 선택해주세요.");
		} else {
			try {
				int result = db.Delete(tfmenu.getText());
				if(result == 0) {
					JOptionPane.showMessageDialog(null, "판매중단할 메뉴가 없습니다.");
				} else {
					JOptionPane.showMessageDialog(null, "판매중단완료");
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "판매중단오류:" + e.getMessage());
			}
		}
	}

	public void M_Search() {
		ArrayList list = new ArrayList();
		String mmenu = tfmenusearch.getText();
		String search = String.valueOf(cbsearch.getSelectedItem());
		try {

			if (tfmenusearch.getText().equals("")) { // 검색값이 없을때 전체보기
				list = db.Show();
				Mmodel.data = list;
			} else {
				list = db.Search(mmenu, search);
				if (list.size() == 0) {
					JOptionPane.showMessageDialog(null, "검색결과가 없습니다.");
					return;
				} else {
					Mmodel.data = list;
				}
			}

			tbl_menu.setModel(Mmodel);
			Mmodel.fireTableDataChanged();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "검색오류:" + e.getMessage());
		}
	}

	public void Clear() {
		String[] cbCategoryStr = { "햄버거", "세트", "드링크", "사이드메뉴" };
		cbcategory = new JComboBox(cbCategoryStr);
		cbcategory.setEnabled(true);
		tfmenu.setText("");
		tfcost.setText("");
		tfimg.setText("");
		tfmenusearch.setText(""); // textfield에 입력란
	}

	public void mousesearch() {
		String mname = String.valueOf(tbl_menu.getValueAt(tbl_menu.getSelectedRow(), 1));
		Menu menu = new Menu();
		try {
			menu = db.Mouse(mname);

			cbcategory.setSelectedItem(menu.getMcategory());
			tfmenu.setText(menu.getMmenu());
			tfcost.setText(String.valueOf(menu.getMprice()));
			tfimg.setText(menu.getMphoto_url());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
