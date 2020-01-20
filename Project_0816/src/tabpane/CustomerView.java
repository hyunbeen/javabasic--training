package tabpane;

import javax.swing.*;
import java.awt.*;

public class CustomerView extends JPanel {
	JButton bOK = new JButton("확인버튼");
	JButton bCancel = new JButton("취소버튼");
	
	public CustomerView() {
		setLayout(new GridLayout(2,1));
		
		add(bOK);
		add(bCancel);
		setBackground(new Color(200,50,88));
	}
}
