package bomb;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BombTest extends JFrame{
	private JPanel p1,p2;
	private JButton btn;
	private JLabel lb, image;
	private boolean inputChk = true;

	BombTest(){
		setTitle("폭탄 테스트!");
		p1 = new JPanel();
		p1.add(btn = new JButton("시작")); 
		p1.add(lb = new JLabel("카운트다운")); 
		add(p1,"North");
		p2 = new JPanel();
		p2.add(image = new JLabel(new ImageIcon("src\\img\\bomb_1.jpg")));

		add(p2, "Center");
		setBounds(200, 200, 600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Thread(new Runnable() {
					
					
					public void run() {
						
						for(int i = 10;i>=0;i--) {
							if(!inputChk) {
								break;
							}
							lb.setText(String.valueOf(i));
							try{Thread.sleep(1000);}
							catch(Exception e) {
								
							}
							
							if(i==0) {
								image.setIcon(new ImageIcon("src\\img\\bomb_2.jpg"));
							}//폭탄이 터졋을시
						};
						
						
						
					}
				}).start();
            	
            	new Thread(new Runnable() {
					
					
					public void run() {
							String name = JOptionPane.showInputDialog("이름은?");
							if(name.equals("이현빈")) {
								inputChk = false;
								image.setIcon(new ImageIcon("src\\img\\bomb_3.jpg"));

							}
							
					}
				}).start();
   	

			}
		});
	}

	public static void main(String[] args) {
		new BombTest();
	}
}
