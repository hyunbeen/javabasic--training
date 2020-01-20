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
import javax.swing.table.AbstractTableModel;

import db.MenuDB;
import obejct.Menu;
import obejct.Staff;


public class MenuView extends JPanel implements ActionListener{
	
	MenuDB db;

	
	JComboBox cbcategory;
	JComboBox cbsearch;
	
	JTextField tfmenu;
	JTextField tfcost;
	JTextField tfimg;
	JTextField tfmenusearch; //textfield에 입력란
	
	
	JButton bimgsearch;
	JButton bmsearch;
	JButton bdelete;
	JButton bmodify;
	JButton binsert; //삽입 수정 삭제 검색 및 이미지 를 넣는 검색란
	
	JTable tbl_menu;
	TableModelMenu Mmodel;
	JLabel l_img;
	
	MenuView(){
		db = new MenuDB();
		setUI();
		display();
		evetproc();
		M_Show();
	}
	
	public void setUI() {
		
		String[] cbCategoryStr = {"햄버거", "세트", "드링크", "사이드메뉴"};
		cbcategory = new JComboBox(cbCategoryStr);
		String[] cbSearchStr = {"카테고리", "메뉴명"};
		cbsearch = new JComboBox(cbSearchStr);
		tfmenu = new JTextField(20);
		tfcost = new JTextField(20);
		tfimg = new JTextField(20);
		tfmenusearch = new JTextField(20);
		
		
		bimgsearch = new JButton("이미지검색");
	    bmsearch = new JButton("메뉴검색");
		bdelete = new JButton("삭제");
		bmodify = new JButton("수정");
		binsert = new JButton("삽입");
		
		Mmodel = new TableModelMenu();
		tbl_menu = new JTable(Mmodel);
		
		l_img = new JLabel( new ImageIcon("src/img/logo.png"));
		l_img.setSize(100, 200);

	}
	
	public void display() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		JPanel p_west = new JPanel(new BorderLayout());
		
		
		JPanel p_menu = new JPanel(new GridLayout(3,2));
		p_menu.add(new JLabel("카테고리"));
		p_menu.add(cbcategory);
		p_menu.add(new JLabel("메뉴명"));
		p_menu.add(tfmenu);
		p_menu.add(new JLabel("가격"));
		p_menu.add(tfcost);
		
		p_west.add(p_menu, BorderLayout.NORTH); //메뉴 입력란
		
		JPanel p_img = new JPanel(new GridLayout(2, 1));
		p_img.add(l_img);
		JPanel p_imgsearch = new JPanel(new FlowLayout());
		p_imgsearch.add(bimgsearch);
		p_imgsearch.add(tfimg);
		p_img.add(p_imgsearch);
		
		p_west.add(p_img,BorderLayout.CENTER); //이미지 검색
		
		JPanel p_button = new JPanel(new FlowLayout());
		p_button.add(binsert);
		p_button.add(bdelete);
		p_button.add(bmodify);
		
		p_west.add(p_button, BorderLayout.SOUTH);//삽입 수정 삭제 버튼 넣기
		
		add(p_west,BorderLayout.WEST);
		
		JPanel p_east = new JPanel(new BorderLayout());
		p_east.add(new JScrollPane(tbl_menu), BorderLayout.CENTER);
		
		JPanel p_msearch = new JPanel(new FlowLayout());
		p_msearch.add(bmsearch);
		p_msearch.add(cbsearch);
		p_msearch.add(tfmenusearch);
		
		p_east.add(p_msearch, BorderLayout.SOUTH);
		
		add(p_east, BorderLayout.CENTER);
		
		setSize(1200, 500);
		setVisible(true);
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
		if(evt == bimgsearch) {
			imgsearch();
		}else if(evt == binsert) {
			M_Insert();
			M_Show();
			Clear();
		}else if(evt == bdelete) {
			M_Delete();
			M_Show();
			Clear();
		}else if(evt == bmodify) {
			M_Modify();
			M_Show();
			Clear();
		}else if(evt == bmsearch) {
			M_Search();
			Clear();
		}
		
	}
	public void imgsearch() {
		JFileChooser chooser = new JFileChooser(); 
		String filePath = "";
		int ret = chooser.showOpenDialog(null); 
		if (ret != JFileChooser.APPROVE_OPTION) {
		    JOptionPane.showMessageDialog(null, "경로를 선택하지않았습니다.",
		      "경고", JOptionPane.WARNING_MESSAGE);
		    return;
		   }
		filePath = chooser.getSelectedFile().getPath();
		tfimg.setText(filePath);
		l_img.setIcon(new ImageIcon(filePath));

		
	}
		class TableModelMenu extends AbstractTableModel{

		
		ArrayList data = new ArrayList();
		String [] columnNames = {"카테고리","메뉴명", "가격"};

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
	public void M_Show() {
		ArrayList list = new ArrayList();
		try {
			list = db.Show();
			Mmodel.data = list;
			tbl_menu.setModel(Mmodel);
			Mmodel.fireTableDataChanged();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "메뉴보기오류:"+e.getMessage());
		}
	}
	
	// 메뉴삽입
	public void M_Insert() {
		Menu menu = new Menu();
		menu.setMcategory(String.valueOf(cbcategory.getSelectedItem()));
		menu.setMmenu(tfmenu.getText());
		menu.setMprice(Integer.parseInt(tfcost.getText()));
		menu.setMphoto_url(tfimg.getText());
		try {
			db.Insert(menu);
			JOptionPane.showMessageDialog(null, "메뉴삽입완료");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "메뉴삽입오류:"+e.getMessage());
		}
		
	}
	
	public void M_Modify() {
		Menu menu = new Menu();
		menu.setMcategory(String.valueOf(cbcategory.getSelectedItem()));
		menu.setMmenu(tfmenu.getText());
		menu.setMphoto_url(tfimg.getText());
		menu.setMprice(Integer.parseInt(tfcost.getText()));
		
		try {
			db.Modify(menu,tfmenu.getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void M_Delete() {
		if(!tfmenu.getText().equals("")) {
			try {
				db.Delete(tfmenu.getText());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void M_Search() {
		ArrayList list = new ArrayList();
		String mmenu = tfmenusearch.getText();
		String search = String.valueOf(cbsearch.getSelectedItem());
		try {
			list = db.Search(mmenu,search);
			Mmodel.data = list;
			tbl_menu.setModel(Mmodel);
			Mmodel.fireTableDataChanged();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void Clear() {
		String[] cbCategoryStr = {"햄버거", "세트", "드링크", "사이드메뉴"};
		cbcategory = new JComboBox(cbCategoryStr);
		cbcategory.setEnabled(true);
		tfmenu.setText("");
		tfcost.setText("");
		tfimg.setText("");
		tfmenusearch.setText(""); //textfield에 입력란
	}
	
public void mousesearch() {
		String mname = String.valueOf(tbl_menu.getValueAt(tbl_menu.getSelectedRow(), 1));
		Menu menu = new Menu();
		try {
			menu=db.Mouse(mname);
			
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
