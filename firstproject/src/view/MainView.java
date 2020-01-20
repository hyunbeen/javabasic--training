package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import db.MainDB;
import obejct.Menu;
import obejct.Staff;

public class MainView implements ActionListener, Runnable {
	ArrayList vc = new ArrayList(); // custommer
	BufferedReader in;
	OutputStream out;

	public void run() {
		System.out.println("소켓생성");
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(9000);
			System.out.println("서버생성");
		} catch (Exception e) {
			System.out.println(e);
		} // 소켓과 서버생성

		while (vc.size() <= 2) {
			try {
				Socket s = ss.accept();
				System.out.println("Client 가 접속시도 :" + s);
				in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				out = s.getOutputStream();

				vc.add(s);

			} catch (Exception e) {
			}
		}
	} // run ends

	void putMessage(String msg) {
		try {
			out.write((msg + "\r\n").getBytes());
		} catch (IOException e) {

			System.out.println("메세지를 받지 못합니다");
		} // 클라이언트가 없을시 예외처리

	}

	Staff admin;
	MainDB db;
	Menu menu;

	int price;

	// 프레임 선언
	JFrame mainFrame;

	// 메인 페널 선언
	JPanel pMain;

	// 결제 버튼 패널
	JPanel pPay;

	// 메뉴 테이블
	JTable tSet;
	JTable tBurger;
	JTable tSide;
	JTable tDrink;

	// 주문 테이블
	JTable tOrder;

	// 주문 테이블 모델
	OrderTableModel tbModelOrder;

	// 주문한 목록에 대한 ArrayList
	ArrayList orderlist;
	// ArrayList main_tmp;

	// 메뉴 테이블 모델
	SetTableModel tbModelSet;
	BurgerTableModel tbModelBurger;
	SideTableModel tbModelSide;
	DrinkTableModel tbModelDrink;

	// 로고 라벨
	JLabel lLogo;

	// 담당 카운터 이미지 라벨
	JLabel lStaffImage;

	// 요구 사항 라벨
	JLabel lOetc;

	// 카운터 담당 라벨
	JLabel lStaff;

	// 총 금액 라벨
	JLabel lSum;

	// 버튼
	JButton bManager;
	JButton bOrder;
	JButton bUp;
	JButton bDown;
	JButton bPay;
	JButton bReceipt;
	JButton bOff;

	// 손님 요구사항 텍스트필드
	JTextField tfOetc;

	// 메뉴 탭 패널
	JTabbedPane tpMenu;

	// 관리자여부
	boolean check;

	// 로그인한 관리자
	String user;

	// 폰트설정
	Font fth = new Font("인터파크고딕 L", Font.BOLD, 21);
	Font ftf = new Font("인터파크고딕 L", Font.PLAIN, 20);
	Font fcb = new Font("인터파크고딕 L", Font.PLAIN, 17);
	Font fbn = new Font("인터파크고딕 L", Font.PLAIN, 17);
	Font ftap = new Font("인터파크고딕 L", Font.BOLD, 24);

	public MainView() {
		db = new MainDB();
		menu = new Menu();

		// orderlist = new ArrayList();
		setUI();
		initial();
		display();
		evtProc();
	}

	public MainView(Staff login) {
		admin = login;
		db = new MainDB();
		user = admin.getEid();
		menu = new Menu();
		new Thread(this).start();
		// orderlist = new ArrayList();

		setUI();
		initial();
		if (admin.getEposition().equals("직원")) {
			check = false;
			bManager.setEnabled(check);
		}
		display();
		lStaff.setText(admin.getEid());
		evtProc();
	}

	public MainView(Staff login, ArrayList list) {
		admin = login;
		db = new MainDB();
		user = admin.getEid();
		menu = new Menu();
		new Thread(this).start();
		// orderlist = new ArrayList();
		
		setUI();
		initial();
		if (admin.getEposition().equals("직원")) {
			check = false;
			bManager.setEnabled(check);
		}
		display();
		lStaff.setText(admin.getEid());
		evtProc();
		tbModelOrder.data = list;
		tOrder.setModel(tbModelOrder);
		tbModelOrder.fireTableDataChanged();
	}

	public void setUI() {
		// 프레임 선언
		mainFrame = new JFrame("BurgerKing P.O.S Main");

		// 메인 페널 선언
		pMain = new JPanel();

		// 결제 패널 선언
		pPay = new JPanel();

		// 메뉴 테이블
		tbModelSet = new SetTableModel();
		tSet = new JTable(tbModelSet);
		tSet.getTableHeader().setFont(fth);
		tSet.setFont(ftf);
		tSet.getColumn("메뉴명").setPreferredWidth(160);
		tSet.setRowHeight(30);
		tbModelBurger = new BurgerTableModel();
		tBurger = new JTable(tbModelBurger);
		tBurger.getTableHeader().setFont(fth);
		tBurger.setFont(ftf);
		tBurger.getColumn("메뉴명").setPreferredWidth(160);
		tBurger.setRowHeight(30);
		tbModelSide = new SideTableModel();
		tSide = new JTable(tbModelSide);
		tSide.getTableHeader().setFont(fth);
		tSide.setFont(ftf);
		tSide.getColumn("메뉴명").setPreferredWidth(160);
		tSide.setRowHeight(30);
		tbModelDrink = new DrinkTableModel();
		tDrink = new JTable(tbModelDrink);
		tDrink.getTableHeader().setFont(fth);
		tDrink.setFont(ftf);
		tDrink.getColumn("메뉴명").setPreferredWidth(160);
		tDrink.setRowHeight(30);

		// 주문 테이블
		tbModelOrder = new OrderTableModel();
		tOrder = new JTable(tbModelOrder);
		tOrder.getTableHeader().setFont(fth);
		tOrder.setFont(ftf);
		tOrder.getColumn("메뉴명").setPreferredWidth(200);
		tOrder.getColumn("가격").setPreferredWidth(100);
		tOrder.setRowHeight(30);

		// 로고 라벨
		lLogo = new JLabel(new ImageIcon("src/img/logo.jpg"));

		// 카운터 담당 이미지 라벨
		lStaffImage = new JLabel(new ImageIcon("src/img/cashier.png"));

		// 요구 사항 라벨
		lOetc = new JLabel("요구사항");
		lOetc.setFont(fcb);

		// 카운터 담당 라벨
		lStaff = new JLabel("카운터 담당 : ");
		lStaff.setFont(ftf);

		// 총 금액 라벨
		lSum = new JLabel("총 금액 : 0");
		lSum.setFont(ftf);

		// 버튼
		bManager = new JButton("관리자 메뉴");
		bManager.setFont(fbn);
		bOrder = new JButton("주문내역");
		bOrder.setFont(fbn);
		bUp = new JButton(new ImageIcon("src/img/right.png"));
		bDown = new JButton(new ImageIcon("src/img/left.png"));
		bPay = new JButton("결제");
		bPay.setFont(fbn);
		bReceipt = new JButton("이전 영수증");
		bReceipt.setFont(fbn);
		bOff = new JButton("퇴근");

		bOff.setFont(fbn);

		// 손님 요구사항 텍스트필드
		tfOetc = new JTextField();
		tfOetc.setFont(ftf);

		// 메뉴 탭 패널
		tpMenu = new JTabbedPane(JTabbedPane.TOP);
		tpMenu.setFont(fbn);

		// 객체 생성 끝!!!

	}

	public void display() {
		// 메인 패널 속성
		pMain.setBackground(Color.WHITE);
		pMain.setLayout(null);

		// 로고 라벨 속성
		lLogo.setBounds(410, 15, 180, 180);
		pMain.add(lLogo);

		// 관리자 메뉴 버튼 속성
		bManager.setBounds(865, 15, 120, 45);
		bManager.setForeground(SystemColor.text);
		bManager.setBackground(new Color(52, 152, 219));
		bManager.setBorderPainted(false);
		pMain.add(bManager);

		// -------------------------------------
		// +버튼 속성
		bUp.setBackground(Color.WHITE);
		bUp.setBounds(460, 237, 80, 80);
		bUp.setBorderPainted(false);
		pMain.add(bUp);

		// -버튼 속성
		bDown.setBackground(Color.WHITE);
		bDown.setFont(new Font("굴림", Font.BOLD, 32));
		bDown.setBounds(460, 371, 80, 80);
		bDown.setBorderPainted(false);
		pMain.add(bDown);

		// 결제버튼 속성
		pPay.setBounds(604, 130, 357, 352);
		pPay.setBackground(Color.WHITE);
		pMain.add(pPay);
		pPay.setLayout(new BorderLayout(0, 0));

		// 총액 버튼 속성-----------------------(수정 필요)
		lSum.setFont(new Font("굴림", Font.PLAIN, 24));
		lSum.setHorizontalAlignment(SwingConstants.RIGHT);

		pPay.add(lSum, BorderLayout.SOUTH);

		// 요구사항 텍스트 필드 속성
		tfOetc.setBounds(701, 494, 260, 33);
		pMain.add(tfOetc);
		tfOetc.setColumns(10);

		// 결제 버튼 속성
		bPay.setBackground(new Color(220, 220, 220));
		bPay.setBounds(865, 541, 120, 47);
		bPay.setBorderPainted(false);
		pMain.add(bPay);

		// 이전 영수증 버튼 속성
		bReceipt.setBackground(new Color(220, 220, 220));
		bReceipt.setBounds(721, 541, 130, 47);
		bReceipt.setBorderPainted(false);
		pMain.add(bReceipt);

		// 라벨을 메인 패널에 add
		lOetc.setBounds(600, 501, 62, 18);
		pMain.add(lOetc);

		// 퇴근 버튼 속성
		bOff.setBackground(new Color(220, 220, 220));
		bOff.setBounds(303, 50, 93, 33);
		bOff.setBorderPainted(false);
		pMain.add(bOff);

		// 라벨을 메인 패널에 add
		lStaffImage.setBounds(30, 27, 68, 66);
		pMain.add(lStaffImage);

		// 라벨을 메인 패널에 add
		lStaff.setBounds(99, 59, 190, 18);
		pMain.add(lStaff);

		// 주문내역 버튼 속성
		bOrder.setBounds(731, 15, 120, 45);
		bOrder.setForeground(Color.WHITE);
		bOrder.setBackground(new Color(52, 152, 219));
		bOrder.setBorderPainted(false);
		pMain.add(bOrder);

		// 주문 테이블삽입을 위한 스크롤 패널
		pPay.add(new JScrollPane(tOrder), BorderLayout.CENTER);

		// 탭 패널 속성
		tpMenu.setFont(ftap);
		tpMenu.addTab("세트메뉴", new JScrollPane(tSet));
		tpMenu.addTab("버거", new JScrollPane(tBurger));
		tpMenu.addTab("사이드", new JScrollPane(tSide));
		tpMenu.addTab("드링크", new JScrollPane(tDrink));
		tpMenu.setSelectedIndex(0);
		tpMenu.setBounds(30, 105, 365, 420);
		pMain.add(tpMenu);

		// 메인 패널을 메인 프레임에 add
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(pMain);
		mainFrame.setSize(1015, 650);
		mainFrame.setVisible(true);

		// 테이블 가운데정렬
		DefaultTableCellRenderer tc = new DefaultTableCellRenderer();
		tc.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tmse = tSet.getColumnModel();
		for (int i = 0; i < tmse.getColumnCount(); i++) {
			tmse.getColumn(i).setCellRenderer(tc);
		}
		TableColumnModel tmb = tBurger.getColumnModel();
		for (int i = 0; i < tmb.getColumnCount(); i++) {
			tmb.getColumn(i).setCellRenderer(tc);
		}
		TableColumnModel tmsi = tSide.getColumnModel();
		for (int i = 0; i < tmsi.getColumnCount(); i++) {
			tmsi.getColumn(i).setCellRenderer(tc);
		}
		TableColumnModel tmd = tDrink.getColumnModel();
		for (int i = 0; i < tmd.getColumnCount(); i++) {
			tmd.getColumn(i).setCellRenderer(tc);
		}
		TableColumnModel tmo = tOrder.getColumnModel();
		for (int i = 0; i < tmo.getColumnCount(); i++) {
			tmo.getColumn(i).setCellRenderer(tc);
		}
	}

	void initial() {
		orderlist = new ArrayList();
		menuSetOutput();
		menuBurgerOutput();
		menuSideOutput();
		menuDrinkOutput();

	}

	// 메뉴 출력 메소드 Start
	void menuSetOutput() {
		ArrayList list;
		try {
			list = db.selectByCategory("세트");
			tbModelSet.data = list;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "세트 테이블 출력실패" + e.getMessage());
		}
	}

	void menuBurgerOutput() {
		ArrayList list;
		try {
			list = db.selectByCategory("햄버거");
			tbModelBurger.data = list;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "버거 테이블 출력실패" + e.getMessage());
			e.printStackTrace();
		}
	}

	void menuSideOutput() {
		ArrayList list;
		try {
			list = db.selectByCategory("사이드메뉴");
			tbModelSide.data = list;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "사이드메뉴 테이블 출력실패" + e.getMessage());
			e.printStackTrace();
		}
	}

	void menuDrinkOutput() {
		ArrayList list;
		try {
			list = db.selectByCategory("드링크");
			tbModelDrink.data = list;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "드링크 테이블 출력실패" + e.getMessage());
			e.printStackTrace();
		}
	}
	// END 메뉴 출력 메소드

	// 주문 JTable에 메뉴 추가하는 메소드
	void addOrderByMenu() {

		boolean flag = false;
		int setMenu = tSet.getSelectedRow();
		int burgerMenu = tBurger.getSelectedRow();
		int sideMenu = tSide.getSelectedRow();
		int drinkMenu = tDrink.getSelectedRow();
		int moveCnt = 0;
		int movePrice = 0; // 추가될 데이터에대한 내용을 저장할 변수
		String selMenu;
		int selPrice;

		if (setMenu > -1) {
			selMenu = String.valueOf(tSet.getValueAt(setMenu, 0));
			selPrice = (int) tSet.getValueAt(setMenu, 1);
		} else if (burgerMenu > -1) {
			selMenu = String.valueOf(tBurger.getValueAt(burgerMenu, 0));
			selPrice = (int) tBurger.getValueAt(burgerMenu, 1);
		} else if (sideMenu > -1) {
			selMenu = String.valueOf(tSide.getValueAt(sideMenu, 0));
			selPrice = (int) tSide.getValueAt(sideMenu, 1);
		} else {
			selMenu = String.valueOf(tDrink.getValueAt(drinkMenu, 0));
			selPrice = (int) tDrink.getValueAt(drinkMenu, 1);
		}

		for (int i = 0; i < tbModelOrder.data.size(); i++) {
			ArrayList rowTemp = (ArrayList) tbModelOrder.data.get(i);
			if (((String) rowTemp.get(0)).equals(selMenu)) {
				flag = true;
				int tmpPrice = (int) rowTemp.get(1);
				int tmpCnt = (int) rowTemp.get(2);
				rowTemp.set(1, tmpPrice + selPrice);
				rowTemp.set(2, tmpCnt + 1);
				moveCnt = tmpCnt + 1;
				movePrice = tmpPrice + selPrice;
				plusTable(selMenu, movePrice, moveCnt);

			}
		}
		if (flag == false) {
			ArrayList tmp = new ArrayList<>();
			tmp.add(selMenu);
			tmp.add(selPrice);
			tmp.add(1);
			tbModelOrder.data.add(tmp);
			movePrice = selPrice;
			plusTable(selMenu, movePrice, 1);
		}
		tOrder.setModel(tbModelOrder);
		tbModelOrder.fireTableDataChanged();

		int tmp_price = 0;
		for (int i = 0; i < tOrder.getRowCount(); i++) {
			tmp_price += (int) tOrder.getValueAt(i, 1);
		}
		lSum.setText("총 금액 : " + Integer.toString(tmp_price));
		price = tmp_price;

	}

	public void plusTable(String selMenu, int selPrice, int moveCnt) {

		String msg = "/insert " + selMenu + " " + String.valueOf(selPrice) + " " + String.valueOf(moveCnt);
		try {
			putMessage(msg);
		} catch (Exception e) {
			System.out.println("메세지를 받을 클라이언트가 없습니다");
		}
	}// 클라이언트에게 메뉴가 추가됫음을 알린다

	// 주문테이블의 내용 삭제
	void outMenuByOrder() {
		int selOrder = tOrder.getSelectedRow();

		String selMenu = String.valueOf(tOrder.getValueAt(selOrder, 0));
		int selPrice = (int) tOrder.getValueAt(selOrder, 1);
		int selCnt = (int) tOrder.getValueAt(selOrder, 2);
		int moveCnt = 0;
		int movePrice = 0; // 삭제될 데이터에대한 내용을 저장할 변수
		for (int i = 0; i < tbModelOrder.data.size(); i++) {
			ArrayList rowTemp = (ArrayList) tbModelOrder.data.get(i);
			if (((String) rowTemp.get(0)).equals(selMenu)) {
				if (selCnt > 1) {
					// int tmpPrice = (int) rowTemp.get(1);
					// int tmpCnt = (int) rowTemp.get(2);
					rowTemp.set(1, selPrice - (selPrice / selCnt));
					rowTemp.set(2, selCnt - 1);
					movePrice = selPrice - (selPrice / selCnt);
					moveCnt = selCnt - 1;
					minusTable(selMenu, movePrice, moveCnt);
				} else if (selCnt == 1) {
					tbModelOrder.data.remove(i);
					movePrice = 0;
					moveCnt = 0;
					minusTable(selMenu, movePrice, moveCnt);
				} else {
					JOptionPane.showMessageDialog(null, "선택된 행이 없습니다.");
				}
			}
		}

		tOrder.setModel(tbModelOrder);
		tbModelOrder.fireTableDataChanged();

		int tmp_price = 0;
		for (int i = 0; i < tOrder.getRowCount(); i++) {
			tmp_price += (int) tOrder.getValueAt(i, 1);
		}
		lSum.setText("총 금액 : " + Integer.toString(tmp_price));
		price = tmp_price;

	}

	public void minusTable(String selMenu, int selPrice, int moveCnt) {

		String msg = "/delete " + selMenu + " " + String.valueOf(selPrice) + " " + String.valueOf(moveCnt);
		try {
			putMessage(msg);
		} catch (Exception e) {
			System.out.println("메세지를 받을 클라이언트가 없습니다");
		} // 클라이언트에게 메뉴가 삭제됨을 알린다
	}

	// 세트 테이블 모델 클래스
	class SetTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "메뉴명", "가격" };

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

	// 버거 테이블 모델 클래스
	class BurgerTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "메뉴명", "가격" };

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

	// 사이드 테이블 모델 클래스
	class SideTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "메뉴명", "가격" };

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

	// 드링크 테이블 모델 클래스
	class DrinkTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "메뉴명", "가격" };

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

	// 주문 테이블 모델 클래스
	class OrderTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "메뉴명", "가격", "개수" };

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

	// 이벤트 프로시져
	public void evtProc() {
		bManager.addActionListener(this);
		bOrder.addActionListener(this);
		bPay.addActionListener(this);
		bOff.addActionListener(this);
		bUp.addActionListener(this);
		bDown.addActionListener(this);
		bReceipt.addActionListener(this);

		tSet.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tBurger.clearSelection();
				tSide.clearSelection();
				tDrink.clearSelection();
			}
		});
		tBurger.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tSet.clearSelection();
				tSide.clearSelection();
				tDrink.clearSelection();
			}
		});
		tSide.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tBurger.clearSelection();
				tSet.clearSelection();
				tDrink.clearSelection();
			}
		});
		tDrink.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tBurger.clearSelection();
				tSide.clearSelection();
				tSet.clearSelection();
			}
		});

	}

	public void actionPerformed(ActionEvent e) {
		Object evt = (Object) e.getSource();
		if (evt == bManager) {

			BurgerKing bk = new BurgerKing(admin);
		} else if (evt == bOrder) {
			OrderListView ov = new OrderListView(admin);
			mainFrame.dispose();
		} else if (evt == bPay) {
			PayView pv = new PayView(admin, Integer.toString(price), tbModelOrder.data, tfOetc.getText());
			mainFrame.dispose();
			// tableinitial();//포스기의 주문테이블의 데이터를 삭제한다

			try {
				putMessage("/initial");
				
			} catch (Exception e1) {

				System.out.println("메세지를 받을 클라이언트가 없습니다");
			}
		} else if (evt == bOff) {
			try {
				db.gooff(admin.getEid()); // 출근을 눌렀을시 출근을 찍는다
				mainFrame.dispose();
				LoginView lv = new LoginView();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if (evt == bUp) {
			addOrderByMenu();
			putMessage("/total "+ String.valueOf(price));
		} else if (evt == bDown) {
			outMenuByOrder();
			putMessage("/total "+ String.valueOf(price));
		} else if (evt == bReceipt) {
			ArrayList list = new ArrayList();
			try {
				list = db.printRec();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String str = "";
			try {
				FileWriter fw = new FileWriter("receipt.txt");
				BufferedWriter out = new BufferedWriter(fw);

				for (int i = 0; i < list.size(); i++) {
					ArrayList temp = (ArrayList) list.get(i);
					out.write("\r\n----------------------------\r\n");
					str = "";
					str += "주문번호 : " + String.valueOf(temp.get(0)) + "\r\n";
					str += "주문날짜 : " + String.valueOf(temp.get(1)) + "\r\n";
					str += "포장 : " + String.valueOf(temp.get(2)) + "\r\n";
					str += "메뉴이름 : " + String.valueOf(temp.get(3)) + "\r\n";
					str += "메뉴가격 : " + String.valueOf(temp.get(4)) + "\r\n";
					str += "개수 : " + String.valueOf(temp.get(5)) + "\r\n";
					str += "직원 : " + String.valueOf(temp.get(6)) + "\r\n----------------------------\r\n";
					System.out.println(str);
					out.write(str);

				}
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			JOptionPane.showMessageDialog(null, "출력되었습니다");
		}
	}

	private void tableinitial() {
		tbModelOrder.data.clear();
		tOrder.setModel(tbModelOrder);
		tbModelOrder.fireTableDataChanged();

	}

}
