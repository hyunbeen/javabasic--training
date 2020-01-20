package pos;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLabelUI;
import javax.swing.table.AbstractTableModel;

import tabpane.CustomerView;
import tabpane.VideoView;

public class PosMain {
	JFrame f =  new JFrame("Pos 미스터 피자 ");
	JLabel MLab = new JLabel("",new ImageIcon("src/img/main.png"),JLabel.CENTER);
	
	JButton bcal = new JButton("계산"); 
	JButton card = new JButton("카드");
	JButton money = new JButton("현금");
	
	JTextField tf = new JTextField(10);
	JTextField totaltf = new JTextField(20);
	
	TModel model = new TModel();
	JTable tbl = new JTable(model);
	
	
	
	public void setUI(){
		
		
		f.setLayout(new BorderLayout());
		Panel pl = new Panel();
		pl.setBackground(new Color (255,255,255));
		f.add(pl);
		f.add(MLab,BorderLayout.NORTH);
		
		
		Panel p1 = new Panel();
		p1.setLayout(new GridLayout(1,2));
		Panel p2 = new Panel();
		p2.add(new JScrollPane(tbl),BorderLayout.CENTER);
		
		Panel p4 = new Panel();
		p4.add(totaltf,BorderLayout.CENTER);
		p4.add(bcal,BorderLayout.EAST);
		p2.add(p4, BorderLayout.SOUTH);
		p1.add(p2);
		f.add(p1,BorderLayout.CENTER);
		
		
		
	
		
		
		
	
		
		
		
		PizzaView pizza = new PizzaView();
		BeverageView beverage = new BeverageView();
		SideView side = new SideView();
		
		
		Panel p3 = new Panel();
		p3.setLayout(new GridLayout(2,1));
		JTabbedPane tab = new JTabbedPane();
		tab.add("pizza",pizza);
		tab.add("beverage",beverage);
		tab.add("sidemenu",side );
		
		p3.add(tab);
		
		Panel p5 = new Panel();
		p5.setLayout(new FlowLayout());
		p5.add(money);
		p5.add(card);
		
		p3.add(p5);
		
		p1.add(p3,BorderLayout.CENTER);
		
		f.setSize(1000, 700);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		PosMain pos = new PosMain();
		pos.setUI();

	}

}

class TModel extends AbstractTableModel{
	
	Vector data = new Vector();
	String [] columnName = {"메뉴","가격","수량","취소"};
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnName.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Vector temp = (Vector)data.get(rowIndex);
		return temp.get(columnIndex);
	}
	
	//컬럼명을 지정하기
	public String getColumnName(int col) {
		return columnName[col];
	}
	
	// 각 셀의 편집모드로 변경
	public boolean isCellEditable(int row,int col) {
		if(col==3) return true;
		else return false;
	}
	
	public void setValueAt(Object value,int row, int col) {
		Vector temp = (Vector)data.get(row);
		temp.set(col, value);
		fireTableCellUpdated(row, col);
	}
	
	//각 셀을 클래스 별로 변경
//	public Class getColumnClass(int c) {
//		return getValueAt(0,c).getClass();
//	}
}