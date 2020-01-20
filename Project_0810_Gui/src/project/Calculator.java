package project;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Calculator extends Frame implements ActionListener {
	JFrame f = new JFrame("");
	JButton nb[] = new JButton[10];
	JButton bAdd = new JButton("+");
	JButton bSub = new JButton("-");
	JButton bMul = new JButton("*");
	JButton bDiv = new JButton("/"); 
	JButton bEqual = new JButton("=");
	JTextField tf = new JTextField("");
	
	int first,second;
	char op;
	String str ="";
	int select = 1;
	
	public Calculator() {
		f = new JFrame("");
		for(int i=0;i<nb.length;i++)
		nb[i] = new JButton(Integer.toString(i));
		bAdd = new JButton("+");
		bSub = new JButton("-");
		bMul = new JButton("*");
		bDiv = new JButton("/"); 
		bEqual = new JButton("=");
		tf = new JTextField("");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton evt = (JButton)e.getSource();
		
		for(int i=0;i<nb.length;i++)
		{
			if(select == 0) {
				tf.setText("");
				select = 1;
			}
			
			if(evt==nb[i])
			{
				
				str = tf.getText();
				str = str + nb[i].getText();
				tf.setText(str);
				if(select == 1) {
					first = Integer.parseInt(str);
				}
				else if(select == 2) {
					second = Integer.parseInt(str);
				}
				
			}
		}
		
		if(evt == bAdd || evt == bSub || evt == bMul || evt == bDiv ) {
			tf.setText("");
			if(evt == bAdd) {
				op = '+';
			}
			else if(evt == bSub)
			{
				op = '-';
			}
			else if (evt == bMul)
			{
				op = '*';
			}
			else if(evt == bDiv) {
				op = '/';
			}
			str = "";
			select = 2;
		}
		if(evt == bEqual)
		{
			str = "";
			switch(op) {
			case '+' :
				tf.setText(Integer.toString(first+second));
				break;
			case '-' :
				tf.setText(Integer.toString(first-second));
				break;
			case '*' :
				tf.setText(Integer.toString(first*second));
				break;
			case '/' :
				tf.setText(Integer.toString(first/second));
				break;
					
			}
			select = 0;
			
		}
	}
	
	
	
	public void setUI() {
		
		
		setLayout(new BorderLayout());
		add(tf,BorderLayout.NORTH);
		Panel p = new Panel();
		p.setLayout(new GridLayout(5,3));
		
		for(int i=1;i<nb.length;i++)
		{
			
			nb[i].setBackground(new Color(253,227,167));
			p.add(nb[i]);
		}
		
		p.add(bAdd);
		nb[0].setBackground(new Color(253,227,167));
		p.add(nb[0]);
		p.add(bSub);
		p.add(bMul);
		p.add(bDiv);
		p.add(bEqual);
		
		add(p,BorderLayout.CENTER);
		setSize(300,400);
		setVisible(true);
		
		for(int i=0;i<nb.length;i++)
		{
			nb[i].addActionListener(this);
		}
		bAdd.addActionListener(this);
		bSub.addActionListener(this);
		bMul.addActionListener(this);
		bDiv.addActionListener(this);
		bEqual.addActionListener(this);
	}
	

	
		
	
	
	public static void main(String[] args) {
		Calculator cal = new Calculator();
		cal.setUI();
		

	}
	

}
