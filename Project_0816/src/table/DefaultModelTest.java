package table;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DefaultModelTest {
	JFrame f;
	JButton b;
	JTable tbl;
	
	DefaultTableModel model;
	Vector columnName = new Vector();
	Vector data = new Vector();

	
	model = new DefaultTableModel(data,columnName);
	
	public DefaultModelTest() {
		f = new JFrame("중요한 데이타");
		b = new JButton("데이터 가져오기");
		
		columnName.add("첫");
		columnName.add("두번째");
		columnName.add("세번째");
		columnName.add("네번째");
		
		model = new DefaultTableModel(data,columnName);
		tbl = new JTable(model);
		
		f.setLayout(new BorderLayout());
		f.add(new JScrollPane(tbl),BorderLayout.CENTER); //테이블 가져올때는  JScrollPane
		f.add(b, BorderLayout.SOUTH);
		
		f.setSize(600, 500);
		f.setVisible(true);
		
		//버튼이 눌려 졌을때
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<3;i++) {
					Vector temp = new Vector();
					for(int j = 0;j<4;j++) {
						temp.add("데이타 : "+i+":"+j);
						
					}
					data.add(temp);
					
				}
				model.setDataVector(data,columnName);
				tbl.setModel(model);
				model.fireTableDataChanged(); //데이터 측에서 화면에 내용을 바꿧을때 신호
			}
		
		});
	}
	
	public static void main(String[] args) {
		new DefaultModelTest();
		

	}

}
