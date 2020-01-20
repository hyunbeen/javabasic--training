package table;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
public class TableModelTest {
	
	JFrame f;
	JButton b;
	JTable tbl;
	TModel model;
	Vector data = new Vector();
	
	public TableModelTest() {
		f = new JFrame("테이블예제");
		b = new JButton("눌려");
		model = new TModel();
		tbl = new JTable(model);
		f.add(new JScrollPane(tbl),BorderLayout.CENTER);
		f.add(b, BorderLayout.NORTH);
		f.setSize(600, 500);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dataInsert();
				
			}
		});
		
//		tbl.addMouseListener(new MouseAdapter() {
//		
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				
//				int row = tbl.getSelectedRow();
//				int col = tbl.getSelectedColumn();
//				
//				JOptionPane.showMessageDialog(null,tbl.getValueAt(row,col));
//				
//			}
//		});
//		
	}
	void dataInsert() {
		for(int i=0;i<10;i++) {
			Vector temp = new Vector();
			for(int j = 0;j<10;j++) {
				temp.add("데이타 : "+i+":"+j);
				System.out.println(temp);
			}
			data.add(temp);
			
		}
		
		TableColumn column = tbl.getColumnModel().getColumn(3);

		JComboBox cb = new JComboBox<>();
		
		cb.addItem("공포");
		cb.addItem("로맨스");
		cb.addItem("코메디");
		column.setCellEditor(new DefaultCellEditor(cb));//수정 가능하게 만들기
		
		
		model.data = data;
		tbl.setModel(model);
		model.fireTableDataChanged();
	}
	class TModel extends AbstractTableModel{
		
		Vector data = new Vector();
		String [] columnName = {"첫","두번째","3","네엣"};
		
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
//		public Class getColumnClass(int c) {
//			return getValueAt(0,c).getClass();
//		}
	}
	public static void main(String[] args) {
		TableModelTest tb = new TableModelTest();
		
	}

}


