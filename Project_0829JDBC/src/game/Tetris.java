
package game;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
//JFrame 을 상속받을시 이객체를 실행만으로도 frame 창이뜸
class Tetris extends JFrame implements Runnable{

	boolean stop = true ;
	boolean over = true;
	int stopCount = 0;
	private int count = 0;
	private int ecount=0;
	private int bcount=0;
	private final static int MOVE = 2 ;
	private final static int STOP =  1;
	private final static int NONE = 0 ;
	private static int PREV = 3;
	private int Type ;
	private int tmp , tmp2;
	
	int Speed = 1000;
	
	private int x = 10;
	private int y = 18;
	private int cell = 30;	
	
	private JPanel preview[][] ;
	private JPanel background[][] ;
	private JPanel center ;
	private Container ct ;
	private JLabel point = new JLabel("Point : ");
	private JLabel info = new JLabel();
	private JLabel level = new JLabel("Level 0 : 1초");
	private JLabel runkey = new JLabel("게임시작 = F1");
	private JLabel stopkey = new JLabel("일시정지/해제 = F2");
	
	private int Block[][] ;
	Random r = new Random() ;
	
	private int angle =0;
	private ArrayList<Color> color;
	
	
	public Tetris(){
		
		this.color = new ArrayList<Color>();
		this.color.add(new Color(50,50,50));
		this.color.add(new Color(255,255,255));
		this.color.add(new Color(0,255,0));
		this.color.add(new Color(255,0,0));
		this.color.add(new Color(255,128,0));
		this.color.add(new Color(91,173,255));
		this.color.add(new Color(180,104,255));

		LineBorder line = new LineBorder(new Color(189,189,189),1);
	
		this.center = new JPanel() ;
		this.center.setSize((x*cell), (y*cell));
		this.center.setLayout(null);
		
		this.center.add(runkey);
		this.runkey.setBounds(30+300, 30+100, 160,40);
		runkey.setFont(new Font("",1,15));
		
		this.center.add(stopkey);
		this.stopkey.setBounds(30+300,30+120,160,40);
		stopkey.setFont(new Font("",1,15));
		
		this.center.add(level);
		this.level.setBounds(30+300, 30+150, 150, 40);
		level.setFont(new Font("",1,15));
		
		this.center.add(info);
		this.info.setBounds(30+360,30+170, 170,40);
		info.setFont(new Font("",1,15));
		
		this.center.add(point);
		this.point.setBounds(30+300,30+170,50,40);
		point.setFont(new Font("",1,15));

		this.background = new JPanel[this.x][this.y];
		this.preview = new JPanel[5][5];
		
		this.ct = this.getContentPane();
		this.ct.add(center);

		
		this.setBounds(680, 230, (x*cell+200), (y*cell+62));
		
		this.Block  = new int[x][y];
		
		for(int i=0; i<x ; i++){
			for(int j=0; j<y; j++){
				this.background[i][j] = new JPanel();
				this.background[i][j].setBounds(i*this.cell, j*this.cell, this.cell, this.cell);
				
				this.background[i][j].setBorder(line);
				this.background[i][j].setBackground(new Color(50,50,50));
				
				this.center.add(background[i][j]);	
			}
		}
		
		for(int i=0; i<4 ; i++){
			for(int j=0; j<4; j++){
				this.preview[i][j] = new JPanel();
				this.preview[i][j].setBounds(i*30+330 ,j*30, this.cell , this.cell); //i*30(크기)+위치
				
				this.preview[i][j].setBorder(line);
				this.preview[i][j].setBackground(new Color(50,50,50));
				
				this.center.add(preview[i][j]);
			}
		}

		
		Thread t = new Thread(this);
		t.start();
		}

	
	void GameOver(){

			for(int a=0 ; a<3; a++){
				for(int b=0; b<10; b++){
					if(Block[b][a]==STOP){
		 System.out.println("클리어 실행");
		 for(int i=0; i<10; i++){
				for(int j=0; j<18; j++){
					background[i][j].setBackground(color.get(0));
					Block[i][j]=NONE;
						}
					}
				}
			}
		}
		 over = false;
		 JOptionPane.showMessageDialog(center, " 게임종료 ");
		 return ;
	}
	
		 void newCreate(){
	
			tmp2 = r.nextInt(7);
			tmp = tmp2;
			Type= tmp;
			
			switch(Type){
			case 0: Square();
			break;
			
			case 1: Stick();
			break;
			
			case 2: RightPlus();
			break;
			
			case 3: LeftPlus();
			break;
			
			case 4: LongRightPlus();
			break;
			
			case 5: LongLeftPlus();
			break;
			
			case 6: School();
			break;
			}
		if(Block[5][1]==STOP)
				 GameOver();
	}
		 
	public void run() {
		while (true) {
			while(stop!=false){
				System.out.println();
			}
			
			try {
				while (over == true) {
					System.out.println("다운중");
					Thread.sleep(Speed);
					Down();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	int TetrisPointGet(){
		return count;
	}
	
	/* void GhostBlock(){
		for(int i=9; i<=0; i--){
			for(int j=17; j<=0; j--){
				while(Block[i][j]!=MOVE){
					this.background[i][j].setBackground(new Color(100,100,100));
					Block[i][j]=NONE;

				
				}
			}
		}
	}*/

	/*void GameLevel(){
		lo : if(count>10&&count<12){
			JOptionPane.showMessageDialog(center, "1레벨 상승");
			break lo;
		}
	}
		 
	void preview(){
		
		switch(tmp2){
		case 0: Square();
		break;
		
		case 1: Stick();
		break;
		
		case 2: RightPlus();
		break;
		
		case 3: LeftPlus();
		break;
		
		case 4: LongRightPlus();
		break;
		
		case 5: LongLeftPlus();
		break;
		
		case 6: School();
		break;
		}
		
		if(PREV==3){
		for(int i=9; i>=0; i--){
			for(int j=17; j>=0; j--){
				if(Block[i][j]==MOVE){
					this.preview[i-3][j+1].setBackground(new Color(255,255,255));
					this.background[i][j].setBackground(color.get(0));
					Block[i][j]=NONE;
					}
				}
			}
		}
		else
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				if(PREV==0){
					this.preview[i][j].setBackground(color.get(0));
					Block[i][j] =NONE;
				}
			}
		}
	}*/
	void SetStop() {
		if (stopCount == 0) {
			System.out.println("셋스톱이 호출됨");
			this.stop = false;
			System.out.println( "스톱의값 : " + stop );
			stopCount++;
		}
		if (stopCount == 1) {
			this.stop = true;
			stopCount = 0;
		}
	}
	
	void Down() {
		if (Moving(1)) {
			for (int i = 9; i >= 0; i--) {
				for (int j = 17; j >= 0; j--) { // 0의색깔을 넣어줌(=)
					if (Block[i][j] == MOVE) {
						
						this.background[i][j + 1].setBackground(this.background[i][j].getBackground()); // 한칸내리면서 색을그려줌
						this.background[i][j].setBackground(new Color(50, 50,50)); // 월래있던자리의 색을 지워줌
						
						Block[i][j + 1] = MOVE;
						Block[i][j] = NONE;
						
						PREV=3;
						//GhostBlock();
					}
				}
			}
		} else {
			for (int i = 9; i >= 0; i--) {
				for (int j = 17; j >= 0; j--) {
					if (Block[i][j] == MOVE){
						Block[i][j] = STOP; // 블럭 이동이 끝났을시 스탑으로 바꿔놓음
						angle=0; //앵글값 추가된 것을 블록이 땅에다을때마다 0 으로초기화
						PREV = 0;
					}
					}
				}
			for (int i=0 ; i<18; i++){
				int cnt =0;
				for(int j=0; j<10; j++){
					if(Block[j][i]==STOP){// 라인에 스톱 페널 한개의값
						cnt++; // 라인당 1개의 값이 증가
					}
					if (cnt == 10) { // 만약 한라인이 꽉차면
						ecount++;
						bcount++;
						count++;
						for (int up = i ; up > 0; up--) {
							for (int k = 0; k < 10; k++) {
								this.background[k][up].setBackground(this.background[k][up-1].getBackground());
								
								Block[k][up] = Block[k][up-1];
							}
						}
						for (int d = 0; d < 10; d++) {
							this.background[d][0].setBackground(new Color(50,50, 50)); // 꽉찬라인 한줄을 지움
							Block[d][0] = NONE; // 지운줄을 빈공간으로 바꿔줌
						}
					}
				}
			}
			stop = false;
			newCreate();
		}
		if(ecount>=4){ // 4개블록 동시에깻을시 8점추가
			count += ecount*2;
			ecount=0;
		}
		else
			ecount=0;
		
		if(bcount>=3){ // 3개블록 동시에꺳을시 5점추가
			count += bcount+2;
		}
		else
			bcount=0;
		
		if(count>10){
			Speed = 900;
			level.setText("Level 1 : 0.9초");
		}
		
		if(count>15){
			Speed = 800;
			level.setText("Level 2 : 0.8초");
		}
		
		if(count>20){
			Speed = 700;
			level.setText("Level 3 : 0.7초");
		}
		
		if(count>25){
			Speed = 600;
			level.setText("Level 4 : 0.6초");
		}
		
		if(count>50){
			Speed = 500;
			level.setText("Level 5 : 0.5초");
		}
		if(count>100){
			Speed = 300;
			level.setText("Level Max : 0.3초");
		}
		this.info.setText(""+count);
	}

	void Right() {
		if (Moving(2)) {
			for (int i = 9; i >= 0; i--) {
				for (int j = 0; j <= 17; j++) {
					if (Block[i][j] == MOVE) {
						this.background[i + 1][j].setBackground(this.background[i][j].getBackground());
						this.background[i][j].setBackground(new Color(50, 50,50));

						Block[i + 1][j] = MOVE;
						Block[i][j] = NONE;
					}
				}
			}
		}
	}
	void Left() {
		if(Moving(3)){
			for(int i = 0; i<=9 ; i++){
				for(int j=0; j<=17; j++){
					if(Block[i][j] == MOVE){
						this.background[ i - 1 ][j].setBackground(this.background[i][j].getBackground());
						this.background[i][j].setBackground(new Color(50,50,50));
						
						Block[ i - 1][j] = MOVE;
						Block[i][j] = NONE;
					}
				}
			}
		}
	}
	void Turn(){
		int num =  Type;
		
		
		loop: for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 18; j++) {
				if(Block[i][j]==MOVE){
					if(num==6 && angle==0 && Block[i+1][j+1]!=STOP ){ //school
						background[i+1][j+1].setBackground(color.get(6));
						background[i+2][j].setBackground(color.get(0));
						
						Block[i+1][j+1] = MOVE;
						Block[i+2][j] = NONE;
						angle++;
						break loop;
					}
					
					if(num==6 && angle==1 && Block[i+2][j]!=STOP ){
						background[i+2][j].setBackground(color.get(6));
						background[i+1][j-1].setBackground(color.get(0));
						
						Block[i+2][j] = MOVE;
						Block[i+1][j-1] = NONE;
						angle++;
						break loop;
					}
					
					if(num==6 && angle==2 && Block[i+1][j-1]!=STOP ){
						background[i+1][j-1].setBackground(color.get(6));
						background[i][j].setBackground(color.get(0));
						
						Block[i+1][j-1]= MOVE;
						Block[i][j]=NONE;
						angle++;
						break loop;
					}
					if(num==6 && angle==3 && Block[i-1][j+1]!=STOP){
						background[i-1][j+1].setBackground(color.get(6));
						background[i][j+2].setBackground(color.get(0));
						
						Block[i-1][j+1] = MOVE;
						Block[i][j+2] = NONE;
						angle=0;
						break loop;
					}
					 
					if(num==5 && angle==0 && Block[i+2][j+2]!=STOP){
						background[i+1][j+2].setBackground(color.get(5));
						background[i+2][j+2].setBackground(color.get(5));
						Block[i+1][j+2] = MOVE;
						Block[i+2][j+2] = MOVE;
						
						background[i][j].setBackground(color.get(0));
						background[i+1][j].setBackground(color.get(0));
						
						Block[i][j] = NONE;
						Block[i+1][j] =NONE;

						angle++;
						break loop;
					}
					
					if(num==5 && angle==1  && Block[i+2][j-1]!=STOP){	
						background[i][j].setBackground(color.get(0));
						background[i][j+1].setBackground(color.get(0));
						Block[i][j]=NONE;
						Block[i][j+1]=NONE;
						
						background[i+2][j].setBackground(color.get(5));
						background[i+2][j-1].setBackground(color.get(5));
						Block[i+2][j]=MOVE;
						Block[i+2][j-1]=MOVE;

						angle++;
						break loop;
					}
					
					if(num==5 &&angle==2 && Block[i-1][j-2]!=STOP){
						background[i][j].setBackground(color.get(0));
						background[i+1][j].setBackground(color.get(0));
						Block[i][j] = NONE;
						Block[i+1][j] =NONE;
						
						background[i][j-2].setBackground(color.get(5));
						background[i-1][j-2].setBackground(color.get(5));
						Block[i][j-2]=MOVE;
						Block[i-1][j-2]=MOVE;

						angle++;
						break loop;
					}
					if(num==5  && angle==3 &&Block[i][j+2]!=STOP &&Block[i][j+1]!=STOP){
						background[i][j+1].setBackground(color.get(5));
						background[i][j+2].setBackground(color.get(5));
						
						Block[i][j+1] =MOVE;
						Block[i][j+2] = MOVE;
						
						background[i+2][j].setBackground(color.get(0));
						background[i+2][j+1].setBackground(color.get(0));
						
						Block[i+2][j] = NONE;
						Block[i+2][j+1] = NONE;

						angle=0;
						break loop;
					}

					
					if(num==4  && angle==0 && Block[i-1][j+2]!=STOP){ //라이트플러스
						background[i][j+2].setBackground(color.get(4));
						background[i-1][j+2].setBackground(color.get(4));
						
						Block[i][j+2] = MOVE;
						Block[i-1][j+2]=  MOVE;
						
						background[i+1][j].setBackground(color.get(0));
						background[i][j].setBackground(color.get(0));
						
						Block[i+1][j] = NONE;
						Block[i][j] =NONE;

						angle++;
						break loop;	
					}

					if(num==4  && angle==1 && Block[i][j-2]!=STOP){
						background[i+2][j].setBackground(color.get(0));
						background[i+2][j-1].setBackground(color.get(0));
						
						Block[i+2][j] = NONE;
						Block[i+2][j-1] = NONE;
						
						background[i][j-1].setBackground(color.get(4));
						background[i][j-2].setBackground(color.get(4));
						
						Block[i][j-1] = MOVE;
						Block[i][j-2] = MOVE;

						angle++;
						break loop;
					}
					
					if(num==4  &&  angle==2 && Block[i+2][j]!=STOP ){
						background[i+1][j].setBackground(color.get(4));
						background[i+2][j].setBackground(color.get(4));
						
						Block[i+1][j] = MOVE;
						Block[i+2][j] = MOVE;
						
						background[i][j+2].setBackground(color.get(0));
						background[i+1][j+2].setBackground(color.get(0));
						
						Block[i][j+2] = NONE;
						Block[i+1][j+2] = NONE;

						angle++;
						break loop;
					}
					
					if(num==4  && angle==3 && Block[i+2][j+2]!=STOP){
						background[i+2][j+1].setBackground(color.get(4));
						background[i+2][j+2].setBackground(color.get(4));
						
						Block[i+2][j+1]  = MOVE;
						Block[i+2][j+2] = MOVE;
						
						background[i][j].setBackground(color.get(0));
						background[i][j+1].setBackground(color.get(0));
						
						Block[i][j] = NONE;
						Block[i][j+1] = NONE;

						angle=0;
						break loop;	
					}
					
					 if(num==3  &&  angle==0 && Block[i-1][j+2]!=STOP){ //라이트플러스
						background[i][j].setBackground(color.get(0));
						background[i][j+2].setBackground(color.get(3));
						
						Block[i][j] =NONE;
						Block[i][j+2]= MOVE;
						
						background[i+1][j+2].setBackground(color.get(0));
						background[i-1][j+2].setBackground(color.get(3));
						
						Block[i+1][j+2] = NONE;
						Block[i-1][j+2] = MOVE;

						angle++;
						break loop;
					}
					
					if(num==3  && angle==1 && Block[i+1][j-2]!=STOP){
						background[i][j].setBackground(color.get(0));
						background[i+2][j].setBackground(color.get(3));
						
						Block[i][j] = NONE;
						Block[i+2][j] = MOVE;
						
						background[i+1][j].setBackground(color.get(0));
						background[i+1][j-2].setBackground(color.get(3));
						
						Block[i+1][j] = NONE;
						Block[i+1][j-2] = MOVE;

						angle=0;
						break loop;
					}
					
				 if(num==2 && angle==0 && Moving(2) && Moving(3) && Moving(1) && Block[i+2][j+1]!=STOP){ // RightPlus 벽충돌시버그
					background[i+1][j-1].setBackground(color.get(0));
					background[i+1][j+1].setBackground(color.get(2));
					
					Block[i+1][j-1] = NONE;
					Block[i+1][j+1] = MOVE;
					
					background[i][j+1].setBackground(color.get(0));
					background[i+2][j+1].setBackground(color.get(2));
					
					Block[i][j+1] = NONE;
					Block[i+2][j+1] =MOVE;
					
					angle++;
					break loop;
				}
				
				if( num==2 && angle==1 && Moving(2) && Moving(3) && Moving(1) && Block[i+1][j-1]!=STOP && Block[i+1][j]!=STOP){
					background[i+1][j-1].setBackground(color.get(2));
					background[i+1][j+1].setBackground(color.get(0));
					
					Block[i+1][j-1] = MOVE;
					Block[i+1][j+1] = NONE;
					
					background[i][j+1].setBackground(color.get(2));
					background[i+2][j+1].setBackground(color.get(0));
					
					Block[i][j+1] =MOVE;
					Block[i+2][j+1] = NONE;
					
					angle =0;
					break loop;
				}
							
			if(num==1 && angle==0 && i-3<10 && i+2<10 && Block[i+2][j+3]!=STOP){ //스틱 벽충돌시 버그해결바람

				background[i + 2][j + 1].setBackground(new Color(255,255,255));
				background[i + 3][j].setBackground(new Color(50,50, 50));
				Block[i + 2][j + 1] = MOVE;
				Block[i + 3][j] = NONE;

				background[i + 2][j + 2].setBackground(new Color(255,255,255));
				background[i + 1][j].setBackground(new Color(50,50, 50));
				Block[i + 2][j + 2] = MOVE;
				Block[i + 1][j] = NONE;
				
				background[i + 2][j + 3].setBackground(new Color(255,255,255));
				background[i][j].setBackground(new Color(50, 50, 50));
				Block[i + 2][j + 3] = MOVE;
				Block[i][j] = NONE;
				angle++;

				break loop;
					}
					
				if (num==1 && angle == 1 && (i-2)>=0 && (i+1)<=9 &&Block[i-1][j]!=STOP &&Block[i-2][j]!=STOP &&Block[i+1][j]!=STOP  ) {
					background[i-1][j].setBackground(color.get(1));
					background[i+1][j].setBackground(color.get(1));
					background[i-2][j].setBackground(color.get(1));
					
					Block[i-1][j] = MOVE;
					Block[i+1][j] = MOVE;
					Block[i-2][j] = MOVE;
					
					background[i][j+2].setBackground(color.get(0));
								
					Block[i][j+2] = NONE;
					background[i][j + 3].setBackground(color.get(0));
					Block[i][j+3] = NONE;
					background[i][j+1].setBackground(color.get(0));
					
					Block[i][j+1] =NONE;
				
					angle=0;
					break loop;
				}
				break loop;
				}
			}
		}
	}
		 
	boolean Moving(int run) {
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (Block[i][j] == MOVE) {
					if (run == 1) { 
						if ((j + 1) > 17)
							return false;
						if (Block[i][j + 1] == STOP)
							return false;
					}
					
					else if (run == 2) {	
						if ((i + 1) > 9)
							return false;
						if(Block[i+1][j]==STOP) //각 도형들 충돌시 정지 
							return false;
					}
					
					else if(run == 3){
						if((i - 1) < 0)
							return false;
						if(Block[i-1][j]==STOP)
							return false;
					
					}
				}
			}
		}
		return true;
	}
		 void Square(){
			 Block[4][0] = MOVE;
			 Block[5][0] = MOVE;
			 Block[4][1] = MOVE;
			 Block[5][1] = MOVE;
			 
			 this.background[4][0].setBackground(new Color(255,255,0));
			 this.background[5][0].setBackground(new Color(255,255,0));
			 this.background[4][1].setBackground(new Color(255,255,0));
			 this.background[5][1].setBackground(new Color(255,255,0));
		 }
		 
		 void Stick(){
			 Block[3][0] = MOVE;
			 Block[4][0] = MOVE;
			 Block[5][0] = MOVE;
			 Block[6][0] = MOVE;
			 
			 this.background[3][0].setBackground(new Color(255,255,255));
			 this.background[4][0].setBackground(new Color(255,255,255));
			 this.background[5][0].setBackground(new Color(255,255,255));
			 this.background[6][0].setBackground(new Color(255,255,255));
		 }
		 
		 void RightPlus(){
			 Block[5][0] = MOVE;
			 Block[5][1] = MOVE;
			 Block[4][1] = MOVE;
			 Block[4][2] = MOVE;
	 
			 this.background[5][0].setBackground(new Color(0,255,0));
			 this.background[5][1].setBackground(new Color(0,255,0));
			 this.background[4][1].setBackground(new Color(0,255,0));
			 this.background[4][2].setBackground(new Color(0,255,0));	

		 }
		 
		 void LeftPlus(){
			 Block[4][0] = MOVE;
			 Block[4][1] = MOVE;
			 Block[5][1] = MOVE;
			 Block[5][2] = MOVE;
			 
			 this.background[4][0].setBackground(new Color(255,0,0));
			 this.background[4][1].setBackground(new Color(255,0,0));
			 this.background[5][1].setBackground(new Color(255,0,0));
			 this.background[5][2].setBackground(new Color(255,0,0));
		 }
		 
		 void LongRightPlus(){
			 Block[4][0] = MOVE;
			 Block[5][0] = MOVE;
			 Block[5][1] = MOVE;
			 Block[5][2] = MOVE;
			 

			 this.background[4][0].setBackground(new Color(255,128,0));
			 this.background[5][0].setBackground(new Color(255,128,0));
			 this.background[5][1].setBackground(new Color(255,128,0));
			 this.background[5][2].setBackground(new Color(255,128,0));
			 
		}
		 
		 void LongLeftPlus(){
			 Block[4][0] = MOVE;
			 Block[5][0] = MOVE;
			 Block[4][1] = MOVE;
			 Block[4][2] = MOVE;
			 
			 this.background[4][0].setBackground(new Color(91,173,255));
			 this.background[5][0].setBackground(new Color(91,173,255));
			 this.background[4][1].setBackground(new Color(91,173,255));
			 this.background[4][2].setBackground(new Color(91,173,255));
		 }
		 
		 void School (){
			 Block[4][1] = MOVE;
			 Block[5][0] = MOVE;
			 Block[5][1] = MOVE;
			 Block[6][1] = MOVE;
			 
			 this.background[4][1].setBackground(new Color(180,104,255));
			 this.background[5][0].setBackground(new Color(180,104,255));
			 this.background[5][1].setBackground(new Color(180,104,255));
			 this.background[6][1].setBackground(new Color(180,104,255));
		 }

	public static void main(String args[]){

		Tetris frame = new Tetris();
		TetrisMenu menu = new TetrisMenu(frame);
		frame.setJMenuBar(menu.menuBar);
		frame.setTitle("테트리스");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}