package pos;

import java.awt.*;
import javax.swing.*;

public class SideView extends JPanel {
	JButton mbbang = new JButton("빵");
	JButton mgold = new JButton("골드");
	JButton mcheese = new JButton("치즈");
	JButton mmenu[] = new JButton[9];

	public SideView() {
		
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
		
	}
}
