package game;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CharGame extends JFrame implements ActionListener{

	int getsu = 4;
	JButton [][] b = new JButton[getsu][getsu];
	char[][] answer = new char[getsu][getsu];
	JButton firstClick = null;
	int firstRow = 0;
	int firstCol = 0;
	//객체 생성 및 화면 구성
	CharGame(){
		setLayout(new GridLayout(getsu,getsu));
		for( int i = 0;i<getsu;i++){
			
			for(int j=0;j<getsu;j++) {
				b[i][j] = new JButton();
				b[i][j].addActionListener(this);
				add(b[i][j]);
				answer[i][j] = '0';
			}
		}
		setSize(400,400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	//각각의 버튼의 임의의 위치에 임의의 문자를 지정
	void initChar() {
	char alpha = '0';
	BACK:
	for( int i = 0;i<getsu*getsu;){
			//임의의 문자 만들기	
			if(i%2==0) {
				alpha = (char)('A' +  (int)(Math.random()*26));
				for(int r=0;r<getsu;r++) {
					for(int c=0;c<getsu;c++) {
						if(answer[r][c] == alpha)continue BACK;
					}
				}
			}
			//임의의 위치를 지정
			boolean ok = false;
			do {
			int row = (int)(Math.random() * 4);
			int col = (int)(Math.random() * 4);
			if(answer[row][col]=='0') {
				answer[row][col] = alpha;
				ok = true;
				i++;
			}
			}while(!ok);
		}
	
		
	}
	
	//각각 문자를 화면에 출력
	void showAnswer() {
		//화면에 정답 보여주기
		for( int i = 0;i<getsu;i++){
			
			for(int j=0;j<getsu;j++) {
				b[i][j].setText(String.valueOf(answer[i][j]));
			}
		}
	//	1초를 잠시 멈춤
		try{
		Thread.sleep(5000);
		}catch(Exception ex)
		{}
			for( int i = 0;i<getsu;i++){
				
				for(int j=0;j<getsu;j++) {
					b[i][j].setText(null);
				}
			}
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton evt =(JButton)e.getSource();
		
		
		for(int i=0;i<getsu;i++) {
	
			for(int j=0;j<getsu;j++) {
					if(evt == b[i][j]) {
						if(firstClick == null) {
							evt.setBackground(new Color(255,0,0));
							firstClick = evt;
							firstRow = i;
							firstCol = j;
							b[i][j].setEnabled(false);
							b[i][j].setText(String.valueOf(answer[i][j]));
						}//두번째선택
						else {
							if(answer[firstRow][firstCol]==answer[i][j]) {
								b[firstRow][firstCol].setBackground(new Color(255,255,255));
								b[i][j].setBackground(new Color(255,255,255));
								b[i][j].setEnabled(false);
								b[firstRow][firstCol].setEnabled(false);
								b[i][j].setText(String.valueOf(answer[i][j]));
							}else {
								
								
								try{
									b[i][j].setText(String.valueOf(answer[i][j]));
									Thread.sleep(5000);
									}catch(Exception ex)
								{
										
								}
								b[i][j].setText(null);
								b[i][j].setBackground(null);
								firstClick.setBackground(null);
								firstClick = null;
								b[firstRow][firstCol].setEnabled(true);
								b[firstRow][firstCol].setText(null);
							}
							firstClick = null;
						}
					}
				}
			
		}
		
	}
	
	public static void main(String[] args) {
		CharGame chg = new CharGame();
		chg.initChar();
		chg.showAnswer();
	}
	

}
