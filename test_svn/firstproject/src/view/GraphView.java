package view;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;


public class GraphView extends JPanel 
{
			

	
	public GraphView() {
	
				
				MonthGraphView  month;		
				month = new MonthGraphView();
				
				CostGraphView cost;
				cost = new CostGraphView();
		//각각의 화면을 관리하는 클래스 객체 생성
					JTabbedPane  pane = new JTabbedPane();
					pane.add("손익그래프",cost);
					pane.add("월별판매량",month);

					pane.setSelectedIndex(0);
					
					// 화면크기지정
					setLayout(new BorderLayout());
					add( "Center",pane );
				
					

				
					
	}
	
}

//JButton button = new JButton("Button");
//JTabbedPane tabPane = new JTabbedPane();
//
//public MyPanel(){
//    JPanel panel1 = new JPanel();
//    JPanel panel2 = new JPanel();
//
//    tabPane.add("Panel 1", panel1);
//    tabPane.add("Panel 2", panel2);
//    tabPane.setBorder(new EmptyBorder(10, 10, 10, 10));
//
//    setLayout(new BorderLayout());
//    add(tabPane, BorderLayout.CENTER);
//    add(button, BorderLayout.SOUTH);
//
//}