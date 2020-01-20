package ex;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CounterTest extends JFrame{
    private JPanel p1,p2;
    private JButton btn1;
    private JTextArea res;
    private JLabel lb;
    private static boolean inputChk = true;
    
    public CounterTest() {
        setTitle("단일 스레드 테스트!");
        p1 = new JPanel();
        p1.add(btn1 = new JButton("Click"));
        p1.add(lb = new JLabel("Count!"));//추가 
        add(p1,"North");
        p2 = new JPanel();
        res = new JTextArea(20,50);
        p2.add(res);
        add(p2);
        setBounds(200, 200, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        btn1.addActionListener(new ActionListener() {
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
						};
						
					}
				}).start();
            	
            	new Thread(new Runnable() {
					
					
					public void run() {
							String name = JOptionPane.showInputDialog("이름은?");
							if(name.equals("이현빈")) {
								inputChk = false;
							}
							res.append(name + "님 입장");
					}
				}).start();
  
            }
        });
    }
    public static void main(String[] args) {
        new CounterTest();
    }
}
