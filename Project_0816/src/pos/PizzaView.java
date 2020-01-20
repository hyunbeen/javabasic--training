package pos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;package pos;

import java.awt.*;
import javax.swing.*;

public class PosLayout extends JFrame{
   JLabel mrpizza;
   JLabel[] pizzaLabel = new JLabel[9];
   
   JPanel allPane;
   JPanel mainPane;
   JPanel psbPane;
   JPanel menuPane;
   JPanel movePane;
   JPanel payPane;
   JPanel selPane;
   JPanel payPane2;
   JPanel[] selPizzaPane = new JPanel[9];
   
   JButton bPizza, bSide, bBev;   //피자, 사이드, 음료 버튼
   JButton[] selPizza = new JButton[9]; //피자 종류버튼
   JButton bPrev, bNext; //이전, 다음 버튼
   JButton bPay;//결제버튼
   
   JTable pList;
   JTextField tfPrice;
   
   PosLayout(){
      super("Mr.Pizza");
      mrpizza = new JLabel(new ImageIcon("src/imgs/logo.PNG"));
      allPane = new JPanel();
      mainPane = new JPanel();
      payPane = new JPanel();
      selPane = new JPanel();
      psbPane = new JPanel();
      menuPane = new JPanel();
      movePane = new JPanel();
      payPane2 = new JPanel();
      
      for(int i = 0; i <9;i++) {
      selPizzaPane[i] = new JPanel();
      selPizza[i] = new JButton();
      pizzaLabel[i] = new JLabel("피자");
      }
      bPizza = new JButton("피자");
      bSide = new JButton("사이드");
      bBev = new JButton("음료");
      
      bPrev = new JButton("◀");
      bNext = new JButton("▶");
      bPay = new JButton("결제");
      
      pList = new JTable();
      tfPrice = new JTextField(15);
   }
   void setUI() {
      setLayout(new BorderLayout());
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      add(allPane,BorderLayout.CENTER);
      allPane.setLayout(new BorderLayout());
      allPane.setBackground(Color.WHITE);
      allPane.add(mrpizza, BorderLayout.NORTH);
      allPane.add(mainPane,BorderLayout.CENTER);
      
      mainPane.setBackground(Color.WHITE);
      mainPane.setLayout(new GridLayout(1,2));
      mainPane.add(payPane);
      mainPane.add(selPane);
      
      payPane.setLayout(new BorderLayout());
      payPane.add(pList, BorderLayout.CENTER);
      payPane.add(payPane2, BorderLayout.SOUTH);
      payPane2.setBackground(Color.WHITE);
      payPane2.add(tfPrice);
      payPane2.add(bPay);
      
      bPay.setBackground(Color.WHITE);
      
      selPane.setBackground(Color.WHITE);
      selPane.setLayout(new BorderLayout());
      selPane.add(psbPane,BorderLayout.NORTH);
      selPane.add(menuPane,BorderLayout.CENTER);
      selPane.add(movePane,BorderLayout.SOUTH);      
      
      psbPane.setBackground(Color.WHITE);
      psbPane.setLayout(new GridLayout(1,3));
      psbPane.add(bPizza);
      psbPane.add(bSide);
      psbPane.add(bBev);
      
      menuPane.setBackground(Color.WHITE);
      menuPane.setLayout(new GridLayout(3, 3));
      for(int i = 0; i <9 ; i++) {
         menuPane.add(selPizzaPane[i]);
         selPizzaPane[i].setLayout(new BorderLayout());
         selPizzaPane[i].setBackground(Color.WHITE);
         selPizzaPane[i].add(selPizza[i],BorderLayout.CENTER);
         selPizzaPane[i].add(pizzaLabel[i],BorderLayout.SOUTH);
      }
      for(int i = 0; i <3; i++) {
      selPizza[i].setIcon(new ImageIcon("src/imgs/고르곤졸라.png"));
      }
      for(int i = 3; i <6; i++) {
      selPizza[i].setIcon(new ImageIcon("src/imgs/따블퐈.png"));
      }
      for(int i = 6; i<9;i++) {
      selPizza[i].setIcon(new ImageIcon("src/imgs/불닭신화.png"));
      }
      
      
      movePane.setBackground(Color.WHITE);
      movePane.setLayout(new GridLayout(1,3));
      movePane.add(bPrev);
      movePane.add(new JPanel());
      movePane.add(bNext);
      
      

      setSize(1200,900);
      setVisible(true);
   }

}
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.TableColumn;

public class PizzaView extends JPanel {
	JButton mbbang = new JButton("빵");
	JButton mgold = new JButton("골드");
	JButton mcheese = new JButton("치즈");
	JButton mmenu[] = new JButton[9];

	public PizzaView() {
		
		Panel p = new Panel();
		p.setLayout(new GridLayout(4, 3));
		p.add(mbbang);
		p.add(mgold);
		p.add(mcheese);
		setBackground(new Color(255,255,255));
		for(int i=0;i<9;i++)
		{
			mmenu[i] = new JButton();
			p.add(mmenu[i]);
		}
		
		add(p);
		setVisible(true);
		mmenu[i].addActionListener(l);
	}
}


class BtnHdlr implements ActionListener
{
	public void actionPerformed(ActionEvent ev) {
	JButton evt = (JButton)ev.getSource();
	//selnum[100] 초기화 0으로  때린다
	//input index 
	
	for(int i=0;i<9;i++) {
		if(evt == selPizza[i]) {
				Vector temp = new Vector();
				StringTokenizer st = new StringTokenizer(" ",selPizza[i].setText());
				st.nextToken();
				st.nextToken();
				temp.add(st.nextToken());
				st.nextToken();
				st.nextToken();
				temp.add(st.nextToken());
				
				
					
					
				
				data.add(temp);
				
		}
	}
	
	if(evt == bPizza) {
		select = 1;
		
		bPizza.setText();
		bSide.setText();
		bBev.setText();
		setPizza[0].setIcon(new ImageIcon());
		setPizza[0].setText();
		setPizza[1].setIcon(new ImageIcon());
		setPizza[1].setText();
		setPizza[2].setIcon(new ImageIcon());
		setPizza[2].setText();
		setPizza[3].setIcon(new ImageIcon());
		setPizza[3].setText();
		setPizza[4].setIcon(new ImageIcon());
		setPizza[4].setText();
		setPizza[5].setIcon(new ImageIcon());
		setPizza[5].setText();
		setPizza[6].setIcon(new ImageIcon());
		setPizza[6].setText();
		setPizza[7].setIcon(new ImageIcon());
		setPizza[7].setText();
		setPizza[8].setIcon(new ImageIcon());
		setPizza[8].setText();
	}
	
	if(evt == bSide) {
		select =2;
		
		
		bPizza.setText();
		bSide.setText();
		bBev.setText();
		setPizza[0].setIcon(new ImageIcon());
		setPizza[0].setText();
		setPizza[1].setIcon(new ImageIcon());
		setPizza[1].setText();
		setPizza[2].setIcon(new ImageIcon());
		setPizza[2].setText();
		setPizza[3].setIcon(new ImageIcon());
		setPizza[3].setText();
		setPizza[4].setIcon(new ImageIcon());
		setPizza[4].setText();
		setPizza[5].setIcon(new ImageIcon());
		setPizza[5].setText();
		setPizza[6].setIcon(new ImageIcon());
		setPizza[6].setText();
		setPizza[7].setIcon(new ImageIcon());
		setPizza[7].setText();
		setPizza[8].setIcon(new ImageIcon());
		setPizza[8].setText();
	}
	
	if(evt == bBev) {
		select =3;
				
		bPizza.setText();
		bSide.setText();
		bBev.setText();
		setPizza[0].setIcon(new ImageIcon());
		setPizza[0].setText();
		setPizza[1].setIcon(new ImageIcon());
		setPizza[1].setText();
		setPizza[2].setIcon(new ImageIcon());
		setPizza[2].setText();
		setPizza[3].setIcon(new ImageIcon());
		setPizza[3].setText();
		setPizza[4].setIcon(new ImageIcon());
		setPizza[4].setText();
		setPizza[5].setIcon(new ImageIcon());
		setPizza[5].setText();
		setPizza[6].setIcon(new ImageIcon());
		setPizza[6].setText();
		setPizza[7].setIcon(new ImageIcon());
		setPizza[7].setText();
		setPizza[8].setIcon(new ImageIcon());
		setPizza[8].setText();
	}
	
	if(evt == bPay )
	{
	
	}
	if(evt == bPrev) {
		page--;
		switch(select)
		{
		case 1:
			if(page == 1)
			{
				setPizza[0].setIcon(new ImageIcon());
				setPizza[0].setText();
				setPizza[1].setIcon(new ImageIcon());
				setPizza[1].setText();
				setPizza[2].setIcon(new ImageIcon());
				setPizza[2].setText();
				setPizza[3].setIcon(new ImageIcon());
				setPizza[3].setText();
				setPizza[4].setIcon(new ImageIcon());
				setPizza[4].setText();
				setPizza[5].setIcon(new ImageIcon());
				setPizza[5].setText();
				setPizza[6].setIcon(new ImageIcon());
				setPizza[6].setText();
				setPizza[7].setIcon(new ImageIcon());
				setPizza[7].setText();
				setPizza[8].setIcon(new ImageIcon());
				setPizza[8].setText();
			}
			else if(page == 2)
			{
				setPizza[0].setIcon(new ImageIcon());
				setPizza[0].setText();
				setPizza[1].setIcon(new ImageIcon());
				setPizza[1].setText();
				setPizza[2].setIcon(new ImageIcon());
				setPizza[2].setText();
				setPizza[3].setIcon(new ImageIcon());
				setPizza[3].setText();
				setPizza[4].setIcon(new ImageIcon());
				setPizza[4].setText();
				setPizza[5].setIcon(new ImageIcon());
				setPizza[5].setText();
				setPizza[6].setIcon(new ImageIcon());
				setPizza[6].setText();
				setPizza[7].setIcon(new ImageIcon());
				setPizza[7].setText();
				setPizza[8].setIcon(new ImageIcon());
				setPizza[8].setText();
			}
			break;
		case 2:
			if(page == 1)
			{
				setPizza[0].setIcon(new ImageIcon());
				setPizza[0].setText();
				setPizza[1].setIcon(new ImageIcon());
				setPizza[1].setText();
				setPizza[2].setIcon(new ImageIcon());
				setPizza[2].setText();
				setPizza[3].setIcon(new ImageIcon());
				setPizza[3].setText();
				setPizza[4].setIcon(new ImageIcon());
				setPizza[4].setText();
				setPizza[5].setIcon(new ImageIcon());
				setPizza[5].setText();
				setPizza[6].setIcon(new ImageIcon());
				setPizza[6].setText();
				setPizza[7].setIcon(new ImageIcon());
				setPizza[7].setText();
				setPizza[8].setIcon(new ImageIcon());
				setPizza[8].setText();
			}
			else if(page == 2)
			{
				setPizza[0].setIcon(new ImageIcon());
				setPizza[0].setText();
				setPizza[1].setIcon(new ImageIcon());
				setPizza[1].setText();
				setPizza[2].setIcon(new ImageIcon());
				setPizza[2].setText();
				setPizza[3].setIcon(new ImageIcon());
				setPizza[3].setText();
				setPizza[4].setIcon(new ImageIcon());
				setPizza[4].setText();
				setPizza[5].setIcon(new ImageIcon());
				setPizza[5].setText();
				setPizza[6].setIcon(new ImageIcon());
				setPizza[6].setText();
				setPizza[7].setIcon(new ImageIcon());
				setPizza[7].setText();
				setPizza[8].setIcon(new ImageIcon());
				setPizza[8].setText();
			}
			break;
		case 3:
			if(page == 1)
			{
				setPizza[0].setIcon(new ImageIcon());
				setPizza[0].setText();
				setPizza[1].setIcon(new ImageIcon());
				setPizza[1].setText();
				setPizza[2].setIcon(new ImageIcon());
				setPizza[2].setText();
				setPizza[3].setIcon(new ImageIcon());
				setPizza[3].setText();
				setPizza[4].setIcon(new ImageIcon());
				setPizza[4].setText();
				setPizza[5].setIcon(new ImageIcon());
				setPizza[5].setText();
				setPizza[6].setIcon(new ImageIcon());
				setPizza[6].setText();
				setPizza[7].setIcon(new ImageIcon());
				setPizza[7].setText();
				setPizza[8].setIcon(new ImageIcon());
				setPizza[8].setText();
			}
			else if(page == 2)
			{
				setPizza[0].setIcon(new ImageIcon());
				setPizza[0].setText();
				setPizza[1].setIcon(new ImageIcon());
				setPizza[1].setText();
				setPizza[2].setIcon(new ImageIcon());
				setPizza[2].setText();
				setPizza[3].setIcon(new ImageIcon());
				setPizza[3].setText();
				setPizza[4].setIcon(new ImageIcon());
				setPizza[4].setText();
				setPizza[5].setIcon(new ImageIcon());
				setPizza[5].setText();
				setPizza[6].setIcon(new ImageIcon());
				setPizza[6].setText();
				setPizza[7].setIcon(new ImageIcon());
				setPizza[7].setText();
				setPizza[8].setIcon(new ImageIcon());
				setPizza[8].setText();
			}
			break;
		}
	}
	
	if(evt == bNext) {
		page++;
		switch(select)
		{
		case 1:
			if(page == 1)
			{
				setPizza[0].setIcon(new ImageIcon());
				setPizza[0].setText();
				setPizza[1].setIcon(new ImageIcon());
				setPizza[1].setText();
				setPizza[2].setIcon(new ImageIcon());
				setPizza[2].setText();
				setPizza[3].setIcon(new ImageIcon());
				setPizza[3].setText();
				setPizza[4].setIcon(new ImageIcon());
				setPizza[4].setText();
				setPizza[5].setIcon(new ImageIcon());
				setPizza[5].setText();
				setPizza[6].setIcon(new ImageIcon());
				setPizza[6].setText();
				setPizza[7].setIcon(new ImageIcon());
				setPizza[7].setText();
				setPizza[8].setIcon(new ImageIcon());
				setPizza[8].setText();
			}
			else if(page == 2)
			{
				setPizza[0].setIcon(new ImageIcon());
				setPizza[0].setText();
				setPizza[1].setIcon(new ImageIcon());
				setPizza[1].setText();
				setPizza[2].setIcon(new ImageIcon());
				setPizza[2].setText();
				setPizza[3].setIcon(new ImageIcon());
				setPizza[3].setText();
				setPizza[4].setIcon(new ImageIcon());
				setPizza[4].setText();
				setPizza[5].setIcon(new ImageIcon());
				setPizza[5].setText();
				setPizza[6].setIcon(new ImageIcon());
				setPizza[6].setText();
				setPizza[7].setIcon(new ImageIcon());
				setPizza[7].setText();
				setPizza[8].setIcon(new ImageIcon());
				setPizza[8].setText();
			}
			break;
		case 2:
			if(page == 1)
			{
				setPizza[0].setIcon(new ImageIcon());
				setPizza[0].setText();
				setPizza[1].setIcon(new ImageIcon());
				setPizza[1].setText();
				setPizza[2].setIcon(new ImageIcon());
				setPizza[2].setText();
				setPizza[3].setIcon(new ImageIcon());
				setPizza[3].setText();
				setPizza[4].setIcon(new ImageIcon());
				setPizza[4].setText();
				setPizza[5].setIcon(new ImageIcon());
				setPizza[5].setText();
				setPizza[6].setIcon(new ImageIcon());
				setPizza[6].setText();
				setPizza[7].setIcon(new ImageIcon());
				setPizza[7].setText();
				setPizza[8].setIcon(new ImageIcon());
				setPizza[8].setText();
			}
			else if(page == 2)
			{
				setPizza[0].setIcon(new ImageIcon());
				setPizza[0].setText();
				setPizza[1].setIcon(new ImageIcon());
				setPizza[1].setText();
				setPizza[2].setIcon(new ImageIcon());
				setPizza[2].setText();
				setPizza[3].setIcon(new ImageIcon());
				setPizza[3].setText();
				setPizza[4].setIcon(new ImageIcon());
				setPizza[4].setText();
				setPizza[5].setIcon(new ImageIcon());
				setPizza[5].setText();
				setPizza[6].setIcon(new ImageIcon());
				setPizza[6].setText();
				setPizza[7].setIcon(new ImageIcon());
				setPizza[7].setText();
				setPizza[8].setIcon(new ImageIcon());
				setPizza[8].setText();
			}
			break;
		case 3:
			if(page == 1)
			{
				setPizza[0].setIcon(new ImageIcon());
				setPizza[0].setText();
				setPizza[1].setIcon(new ImageIcon());
				setPizza[1].setText();
				setPizza[2].setIcon(new ImageIcon());
				setPizza[2].setText();
				setPizza[3].setIcon(new ImageIcon());
				setPizza[3].setText();
				setPizza[4].setIcon(new ImageIcon());
				setPizza[4].setText();
				setPizza[5].setIcon(new ImageIcon());
				setPizza[5].setText();
				setPizza[6].setIcon(new ImageIcon());
				setPizza[6].setText();
				setPizza[7].setIcon(new ImageIcon());
				setPizza[7].setText();
				setPizza[8].setIcon(new ImageIcon());
				setPizza[8].setText();
			}
			else if(page == 2)
			{
				setPizza[0].setIcon(new ImageIcon());
				setPizza[0].setText();
				setPizza[1].setIcon(new ImageIcon());
				setPizza[1].setText();
				setPizza[2].setIcon(new ImageIcon());
				setPizza[2].setText();
				setPizza[3].setIcon(new ImageIcon());
				setPizza[3].setText();
				setPizza[4].setIcon(new ImageIcon());
				setPizza[4].setText();
				setPizza[5].setIcon(new ImageIcon());
				setPizza[5].setText();
				setPizza[6].setIcon(new ImageIcon());
				setPizza[6].setText();
				setPizza[7].setIcon(new ImageIcon());
				setPizza[7].setText();
				setPizza[8].setIcon(new ImageIcon());
				setPizza[8].setText();
			}
			break;
		}
		
	}
		
	}
	
	
	
		void dataInsert() {
		for(int i=0;i<10;i++) {
			Vector temp = new Vector();
			for(int j = 0;j<10;j++) {
				temp.add("데이타 : "+i+":"+j);
				
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
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
}