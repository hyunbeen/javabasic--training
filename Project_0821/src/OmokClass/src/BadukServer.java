package OmokClass.src;
import java.io.*;
import java.util.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;


public class BadukServer  implements Runnable
{
	
  Vector 				member;		// 클라이언트접속시 정보 저장할 벡터
  ServerSocket 			server;
  Thread 				serverThread;
  int                 	serverPort = 7777;
  
 
	// 게임에 관한 멤버 변수
	BadukService	blackClient;	// 흑돌게임 클라이언트
	BadukService	whiteClient;	// 흰돌게임 클라이언트
	BadukService	currentClient;	// 현재차례 클라이언트
	Vector			badukRock;		// 바둑알정보를 저장할 벡터
	int				badukPan[][];	// 바둑판 위치 저장
	String			winner;			// 이긴사람의 아이디 저장

  //--------------------------------------------------------
  /*	생성자 함수
	1. ServerSocket 객체 생성
	2. 벡터 객체 생성
	3. 클라이언트 접속을 기다리기 위해 쓰레드 구현
  */
  public BadukServer(){
    try{
       server = new ServerSocket(serverPort);
    }catch(Exception e){
	    //System.out.println(e);
    }
    member = new Vector();

    serverThread = new Thread(this);
    serverThread.start();
  }
  
  //--------------------------------------------------------
  /* 쓰레드 구현
	1. ServerSocket의 accept() 함수로 클라이언트 접속 대기
	2. 접속한 클라이언트와 소켓을 주고받을 역할을 하는 클래스 생성
		( BadukService )
	3. 2의 객체를 벡터에 추가
  */
  public void run(){
      try{
			System.out.println("서버 구동 시작 !!!  ");    			
    		while(true) {
				Socket s = server.accept();
				BadukService cs = new BadukService(this, s );
				member.add(cs);
				System.out.println("Client 접속 : " + cs);    			
    		}
      }catch(Exception e){
    	  
      }
  }

  //-------------------------------------------------------
  /* 각 클라이언트와 데이타 전송을 담당하는 BadukService객체에서
    한 클라이언트에서 데이타를 읽었으면 모든 멤버에게 전송
  */  
  public synchronized void setChatting(Object data){
		BadukServerProtocol  p = new BadukServerProtocol();
		p.setState(BadukServerProtocol.Chatting);
		p.setData(data);
		broadcast(p);
	}

  public  synchronized void broadcast(BadukServerProtocol obj){
  		for ( int i = 0; i < member.size() ; i++ ){
  				BadukService client = (BadukService)member.get(i);
  				client.sendInformation(obj);
  		}
  }
  
  public synchronized void setErase() {
	  BadukServerProtocol obj = new BadukServerProtocol();
	   obj.setState(BadukServerProtocol.All_ERASE);
	   obj.setData("");
	   broadcast(obj);

		blackClient = null;	// 흑돌게임 클라이언트
		whiteClient = null;	// 흰돌게임 클라이언트
		currentClient = null;	// 현재차례 클라이언트
		badukRock = null;		// 바둑알정보를 저장할 벡터
		badukPan= null;	// 바둑판 위치 저장

  }


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//<-- 3
   //-------------------------------------------------------
   /*	Vector의 요소인 BadukService를 얻어서 각각의 아이디를 얻어
		String 배열에 추가후 각각 클라이언트에 전송
		1. Vector의 크기만큼 String 배열을 생성
		2. 벡터에서 하나씩 요소(BadukService)를 얻기
		3. 얻은 각 BadukService의 id값을 String 배열에 추가
		4. 클라이언트로 전송할 객체 생성 ( BadukServerProtocol )
		5. 4번 객체의 state값을 BadukServerProtocol.CHANGE_MEMBER_ID로 지정
		6. 4번 객체의 data값을 위의 String배열로 지정
		7. 모든 클라이언트에 전송 ( broadcast() 이용 )
	*/
   public synchronized void sendAddMember(){
	   String ids[] = new String[member.size()];
	   for(int i=0 ; i<member.size();i++) {
		   BadukService bs = (BadukService)member.get(i);
		   ids[i]= bs.getID(); 
	   }
	   BadukServerProtocol obj = new BadukServerProtocol();
	   obj.setState(BadukServerProtocol.CHANGE_MEMBER_ID);
	   obj.setData(ids);
	   broadcast(obj);
  }

  // 종료시
  /* 나가는 접속자의 아이디를 인자로 받아 같은 아이디를 가지고 있는 BadukService를 찾아
    벡터에서 제거
	1. 벡터의 크기만큼 반복문 구동하여 요소(BadukService)를 하나씩 얻어온다
	2. 인자로 받은 아이디와 같은 아이디 있는지 비교하여
	3. 벡터에서 그 요소를 제거
	4. 다른 클라이언트에게 전송
   */
  void sendRemoveMember( String removeId ){
	  	
		for(int i=0;i<member.size();i++) {
			 BadukService bs = (BadukService)member.get(i);
			 if(bs.getID().equals(removeId)) {
				 member.removeElementAt(i);
				 sendAddMember();
			 }
		}
		
		
  }

//<-- 4
  //------------------------------------------------------
  /* 처음에 신청한 게임자는 흑돌로 지정하고, 그다음에 신청한
	게임자는 흰돌로 지정한후 게임을 시작한다
	1. 흑돌 게임자 변수( blackClient )가 null 이라면 첫번째 게임자이므로 
		인자로 넘어온 BadukService객체를 흑돌게임머 변수로 지정
	2. 흑돌 게임자 아이디를 모든 클라이언트에 전송
		sendBadukGammer()를 이용하여 모든 클라이언트에 전송
	3. 흰돌 게임자 변수( whiteClient )가 null 이라면 두번째 게임자이므로 
		인자로 넘어온 BadukService객체를 흰돌게임머 변수로 지정
	4. 흰돌 게임자 아이디를 모든 클라이언트에 전송
		sendBadukGammer()를 이용하여 모든 클라이언트에 전송
   */
  public synchronized void requestGame(BadukService c){
	  if(blackClient == null) {
		  blackClient = c;
		  BadukServerProtocol p = sendBadukGammer();
		  broadcast(p);
	  }else if(whiteClient == null) {
		  whiteClient = c;
		  BadukServerProtocol p = sendBadukGammer();
		  broadcast(p);
		  
		  //게임시작
		  startGame();
	  }
	  
   }


  //####################################################
  //----------------------------------------------------
  /* 흑돌게임자의 아이디와 흰돌게임자의 아이디를 얻어
	벡터에 저장하고나서 모든 클라이언트에 전송
	( BadukServerProtocol 객체로 전송하고자 )
   */
  public synchronized BadukServerProtocol sendBadukGammer(){
		String blackID = "";
		if ( blackClient != null )
		   blackID = blackClient.getID();
		String whiteID = "";
		if ( whiteClient != null )
			whiteID = whiteClient.getID();
		Vector v = new Vector();
		v.addElement(blackID);
		v.addElement(whiteID);
		BadukServerProtocol  p = new BadukServerProtocol();
		p.setState(BadukServerProtocol.SET_BADUK_GAMMER);
		p.setData(v);
		return p;
  }

  //-------------------------------------------------
  /* 게임을 시작하면 흑돌 먼저 순서로 지정하고 바둑알 저장할 벡터 생성하고
	바둑판정보 저장할 변수 초기화 한 후	모든 클라이언트에게 게임시작임을 전송
   */
  public synchronized void startGame(){
		currentClient = blackClient;
  		badukRock = new Vector(5, 5);
  		badukPan = new int[19][19];
  		for ( int i = 0; i < 19; i++ )
  			for ( int j = 0; j < 19; j++ )
  				badukPan[i][j] = Baduk.NONE_BADUK;
		BadukServerProtocol  p2 = new BadukServerProtocol();
		p2.setState(BadukServerProtocol.START_GAME);
		broadcast(p2);
  }
//--> 4


//<-- 6
//------------------------------------------------------------
/*  1. 클릭한 접속자가 현재게임자가 아니라면 리턴
	2. 넘겨받은 Object 인자에서 x,y좌표 얻어오기
	3. 흑돌게임자라면 흑색을 흰돌게임자라면 흰색을 지정
	4. 2번과 3번의 데이타 ( x값, y값, 색상)을 Baduk 객체에 저장
	5. 4번의 Baduk객체를 바둑알정보를 저장하는 벡터(badukRock)에 추가 저장
	6. 모든 클라이언트에 SET_BADUK_ROCK 상태에 5번 객체를 전송
 */
  public synchronized void sendBadukRock(BadukService c, Object obj){
	  
	  if(currentClient != c) return;
	  Vector v = (Vector)obj;
	  int row = (Integer)v.get(0);
	  int col = ((Integer)v.get(1)).intValue();
	  
	 
	  
	  int color = Baduk.BLACK_BADUK;
	  if(c==whiteClient) color = Baduk.WHITE_BADUK;
	  
	  Baduk al = new Baduk(color,row,col);
	  badukRock.add(al);
	  
	  BadukServerProtocol p = new BadukServerProtocol();
	  p.setData(al);
	  p.setState(BadukServerProtocol.SET_BADUK_ROCK);
	  broadcast(p);
	  
	  //오목확인
	  badukPan[row][col] = color;
	  if( isGame(al)) {
		  gameEnd();
		  return;
	  }
	  
	  //현재게임자가 흑돌게임자라면 현제게임자를 백돌게임자로 지정
	  //그렇지 않다면 흑돌게임자
	  if(currentClient == blackClient)currentClient = whiteClient;
	  else currentClient = blackClient;
	  
	  
  }

	//##################################################################
	/*  가장 최근에 놓여진 바둑알 정보를 기점으로 상하좌우대각선 방향으로
	  동일한 색의 바둑알이 5개 있는지 확인
	 */
     public  synchronized boolean isGame(Baduk curBaduk)
   {
		boolean bend = false;
		int row = curBaduk.getRow();
		int col = curBaduk.getCol();
		int color = curBaduk.getColor();
		int number = 0;
		winner = currentClient.getID();
		number += getSerialNumber(row, col, color, -1, 0 );//Up
		if ( number >= 5 )
			return true;
		number += getSerialNumber(row+1, col, color, 1, 0 );//Down 
		if ( number >= 5 )
			return true;
		number = getSerialNumber(row, col, color, 0, -1);//Left
		if ( number >= 5 )
			return true;
		number += getSerialNumber(row, col+1, color, 0, 1);//Right 
		if ( number >= 5 )
			return true;
		number = getSerialNumber(row, col, color, -1, -1 );//left , Up
		if ( number >= 5 )
			return true;
		number += getSerialNumber(row+1, col+1, color, 1, 1 );//right, down
		if ( number >= 5 )
			return true;
		number = getSerialNumber(row, col, color, -1, 1 );//right, up
		if ( number >= 5 )
			return true;
		number += getSerialNumber(row+1, col-1, color, 1, -1 );//left , Down
		if ( number >= 5 )
			return true;
	   winner = "무승부";
		return false;
   }

   public synchronized int getSerialNumber(int row, int col, int color, int rInc, int cInc){
   		int n = 0;
   		
   		while(true){
   			if ( row < 0 || row > 18 || col < 0 || col > 18 )
   				break;
   			if ( badukPan[row][col] != color )
   				break;
   			row += rInc;
   			col += cInc;
   			n++;
   		}
   		return n;
   }

   //------------------------------------------------------------
   /* 클라이언트에게 게임이 끝났음을 전송
	*/
   public synchronized void gameEnd(){
	    reStartInit(); 
  	   	BadukServerProtocol  p = new BadukServerProtocol();
		p.setState(BadukServerProtocol.END_GAME);
		p.setData(winner);
  		broadcast(p);
  }

  public synchronized void reStartInit(){
		currentClient	= null;
		whiteClient		= null;
		blackClient		= null;
		badukRock		= null;
		badukPan		= null;
  }


   public static void main(String[] arg) {
   		BadukServer cs = new BadukServer();
   }
}