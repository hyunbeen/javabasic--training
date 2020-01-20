package network.chat;


import java.io.*;
import java.util.*;
import java.net.*;

public class ChatServer implements Runnable {
	ArrayList vc = new ArrayList();
	int id = 0;
	int set = 0;
	private int ball[] = {0,0,0};
	
	public void run() {
		ServerSocket ss = null;
		try{
			ss = new ServerSocket(9000);
		}catch( Exception e ) {
			System.out.println(e);
		}
		
		while(vc.size()<=2) {
			try{
				Socket s = ss.accept();
				System.out.println("Client 가 접속시도 :" + s );
				ChatService cs = new ChatService(s);
				cs.start();
				vc.add(cs);
				System.out.println(vc.size());
			
			} catch( Exception e ) {
				
			}
		}
	}  // run ends
	
	public static void main( String [] arg ) {
		ChatServer cs = new ChatServer();
		new Thread(cs).start();
	}



class ChatService extends Thread {
		
		String myname = "quest";
		BufferedReader in;
		OutputStream out;
		ChatService( Socket s ) {
			try{
				in = new BufferedReader( new InputStreamReader(s.getInputStream()));
				out = s.getOutputStream();
			}catch( Exception e ) { }
		}// 생성자 종료
		
	

	public void run() {
		while(true) {
			try{
				String msg = in.readLine();
				if( msg == null ) return;
				StringTokenizer st = new StringTokenizer(msg);
				
					String temp = st.nextToken();
					
					if( temp.equalsIgnoreCase("/name" )) {
						temp = st.nextToken();
						putMessageAll(myname + "님의 이름이 " + temp + "으로 바뀌었습니다.");
						
						myname = temp;
						continue;
					}
					
					else if( temp.equalsIgnoreCase("/enter" )) {
						myname = st.nextToken();
						putMessageAll(myname + "님이 입장하였습니다.");
						changeList();
						continue;
					}
					else if(temp.equalsIgnoreCase("/exit")) {
						
						putMessageAll(myname + "님이 퇴장하였습니다.");
						vc.remove(this);
						changeList();
						continue;
					}
					else if(temp.equalsIgnoreCase("/idset")) {
						
						putMessageAll("/idset "+String.valueOf(id));
						id++;
						
						
						continue;
					}
					else if(temp.equalsIgnoreCase("/ball")) {              //original 공의 값을 저장
						int ballnum = Integer.parseInt(st.nextToken());
						int arr = Integer.parseInt(st.nextToken());
						ball[arr] = ballnum;
						putMessageAll("/set "+String.valueOf(ballnum));
						continue;
					}else if(temp.equalsIgnoreCase("/test")) {          //test공을 입력하면 서버에서 상대방의 공과 함께 보낸다
						int inputnum = Integer.parseInt(st.nextToken());
						int Userid = Integer.parseInt(st.nextToken());
						int vsnum = 0;
						if(Userid == 0) {
							vsnum = ball[1];
						}else if(Userid == 1) {
							vsnum = ball[2];
						}else if(Userid == 2) {
							vsnum = ball[0];
						}
						putMessageAll("/result "+String.valueOf(inputnum)+" "+String.valueOf(vsnum)+" "+myname);
						continue;
					}
					

			
				
				putMessageAll( myname + ">" + msg );
			
			}catch( Exception ex ) { return; }
			
		}
	}// run ends
	
	void changeList() {
		// "/member 대화명 \n"
		
		String msg = "/member ";
		System.out.println(msg);
		for(int i=0;i<vc.size();i++) {
			ChatService cs = (ChatService)vc.get(i);
			msg += cs.myname + " ";
		}
		putMessageAll(msg);
	}

	void putMessageAll( String msg ) {
		for( int i =0 ; i<vc.size() ; i++ ) {
			ChatService cs = ( ChatService ) vc.get(i);
			
			try {
				cs.putMessage(msg);
			}catch( Exception e ) {
				vc.remove(i--);
			}
		}
	} // putMessageAll ends
	
	
	void putMessage( String msg )
		throws Exception {
			out.write( (msg+"\r\n").getBytes() );
			
		}

 } // ChatService class ends
	
	
}// ChatServer class ends
