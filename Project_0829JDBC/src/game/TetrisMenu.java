package game;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


class TetrisMenu implements ActionListener,KeyListener{
	
	JMenuBar menuBar = new JMenuBar();
	JMenu menuName = new JMenu("옵션");
	
	JMenuItem start = new JMenuItem("시작");
	JMenuItem stop = new JMenuItem("일시정지");
	JMenuItem Log = new JMenuItem("기록");
	JMenuItem exit = new JMenuItem("종료");
	JMenuItem help = new JMenuItem("도움말");
	JFrame frame;
	
	int count=0;
	
	TetrisMenu(Tetris frame){
	
	menuBar.add(menuName);

	
	menuName.add(help);
	menuName.add(start);
	menuName.add(stop);
	menuName.add(Log);
	menuName.add(exit);
	
	this.frame = frame;
	((Tetris)frame).addKeyListener(this);
	start.addActionListener(this);
	help.addActionListener(this);
	Log.addActionListener(this);
	stop.addActionListener(this);
	}
	
	TetrisMenu(){	}


	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == start){
			((Tetris)frame).stop = false ; //프레임을 테트리스로 형변환
			((Tetris)frame).newCreate();
			System.out.println("시작");
			
		}
		
		if(e.getSource() == stop ){
			if(count==0){
			((Tetris)frame).over = false;
			System.out.println("정지");
			count++;
			}
			else if(count==1){
				((Tetris)frame).over = true;
				System.out.println("정지해제");
				count=0;
			}
		}
		
		if(e.getSource() == help){
			JLabel help =new JLabel("<html>블럭움직임 : 방향키<br>블럭내리기 스페이스키<br><br>-----구현하지않은목록-----<br>" +
					"누적점수를 위한 점수배열" +
					"<br>미리보기 등 기타오류</html>");
			JFrame hframe = new JFrame("도움말");
			Container ctp = hframe.getContentPane();
			ctp.add(help);
			hframe.setBounds(680, 200, 200, 200);
			hframe.setVisible(true);
		}
		
		if(e.getSource() == Log){
			JLabel help =new JLabel("<html>점수 : "+((Tetris)frame).TetrisPointGet() +"<br>누적점수 미구현<br></html>" );
			JFrame hhframe = new JFrame("도움말");
			Container ctpp = hhframe.getContentPane();
			ctpp.add(help);
			hhframe.setBounds(680, 200, 200, 200);
			hhframe.setVisible(true);
		}
		
		if(e.getSource() == exit ){
		
		}
	}
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_F1 :
			((Tetris)frame).stop = false; //프레임을 테트리스로 형변환
			((Tetris)frame).newCreate();
			System.out.println("시작");
			break;
			
		case KeyEvent.VK_F2 :
			if(count==0){
			((Tetris)frame).over = false;
			System.out.println("정지");
			count++;
			}
			else if(count==1){
				((Tetris)frame).over = true;
				System.out.println("정지해제");
				count=0;
			}
			
		case KeyEvent.VK_DOWN :
			((Tetris)frame).Down();
			break;
			
		case KeyEvent.VK_RIGHT :
			((Tetris)frame).Right();
			break;
			
		case KeyEvent.VK_LEFT :
			((Tetris)frame).Left();
			break;
			
		case KeyEvent.VK_UP :
			((Tetris)frame).Turn();
			break;
			
		case KeyEvent.VK_SPACE :

			while (((Tetris)frame).stop) {
				((Tetris)frame).Down();
			}
			((Tetris)frame).stop = true;
			break;
			
			}
	}
	public void keyReleased(KeyEvent e) {	}
	public void keyTyped(KeyEvent e) {	}
}
