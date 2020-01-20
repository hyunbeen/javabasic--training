package network.chat;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class ChatClient implements ActionListener,Runnable{
	
	int Userid;
	int set = 0;
	
	JFrame f;

	JTextField connTF, sendTF;
	JButton connB, sendB;
	JTextArea ta;
	
	Socket s;
	BufferedReader in;
	OutputStream out;

	// 추가0 : 대화명을 바꾸기
	JTextField changeNameTF;
	JButton    changeNameB;

	JTextField originalBallTF;
	JButton originalBallB;
	
	// 추가2 : 방인원의 대명 보여주기
	// 변수 선언
	JList  memberList;
	Vector list;
	
	public ChatClient() {
		f = new JFrame("Chat Client");
		

		connTF = new JTextField();
		sendTF = new JTextField();
		connB = new JButton("접 속");
		sendB = new JButton("확 인");
		ta = new JTextArea(15,40);
		
		// 추가0: 대화명 바꾸기
		changeNameTF	= new JTextField("guest", 10);
		changeNameB		= new JButton("입력");
		originalBallTF	= new JTextField("", 10);
		originalBallB		= new JButton("입력");
		JPanel p_changeName = new JPanel();
		p_changeName.setLayout(new GridLayout(2,3));
		p_changeName.add( new JLabel("참가자 : "));
		p_changeName.add(changeNameTF);
		p_changeName.add(changeNameB);
		p_changeName.add( new JLabel("초기공 입력 : "));
		p_changeName.add(originalBallTF);
		p_changeName.add(originalBallB);
		
		
		JPanel p_serverName = new JPanel();
		p_serverName.setLayout( new BorderLayout() );
		p_serverName.add( new JLabel("서버입력 : "),"West" );
		p_serverName.add(connTF, "Center");
		p_serverName.add(connB, "East");

		JPanel p_north = new JPanel();
		p_north.setLayout( new GridLayout(1, 2));
		p_north.add( p_changeName );
		p_north.add( p_serverName );

		JPanel p2 = new JPanel();
		p2.setLayout( new BorderLayout() );
		p2.add( new JLabel("공번호 입력 : "),"West" );
		p2.add(sendTF,"Center");
		p2.add(sendB, "East");
		
		// 추가2 : 방인원의 대명 보여주기
		memberList = new JList();
		list		= new Vector();
		JPanel  p_east = new JPanel();
		p_east.setLayout( new BorderLayout());
		p_east.add("North", new JLabel("   우 리 방 멤 버   "));
		p_east.add("Center", memberList );
		


		f.getContentPane().add("North", p_north);
		f.getContentPane().add("Center", new JScrollPane(ta));
		f.getContentPane().add("South", p2);
		f.getContentPane().add("East", p_east);
		
		//f.setSize(500, 300);
		f.pack();
		f.setVisible(true);
		


		connTF.addActionListener(this);
		connB.addActionListener(this);
		sendTF.addActionListener(this);
		sendB.addActionListener(this);

		//  추가0: 대화명 바꾸기
		changeNameTF.addActionListener(this);
		changeNameB.addActionListener(this);
		originalBallB.addActionListener(this);
		originalBallTF.addActionListener(this);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					if(s!=null) {
						out.write("/exit exit\n".getBytes());
						in.close();
						out.close();
						s.close();
					}
				}
				catch(IOException e1)
				{
					e1.printStackTrace();
				}
			}
		});
	}// 생성자 종료
	
	public void actionPerformed( ActionEvent e ) {
		Object o = e.getSource();

		if( o == connTF || o == connB ) {
			connProc();
		}
		
		else if( o == sendTF || o == sendB ) {
			sendProc();
		}

		//  추가0: 대화명 바꾸기
		else if( o == changeNameTF || o == changeNameB ) {
			changeNameProc();
		}
		else if(o == originalBallTF || o == originalBallB ) {
			originalBallSet();
			//초기 공번호 설정
		}
	} // actionPerformed ends
	
	//original ball 입력
	void originalBallSet() {
		String msg = "/ball "+originalBallTF.getText()+" "+String.valueOf(Userid)+"\n";
		System.out.println(msg);
		try {
			out.write(msg.getBytes());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	void changeNameProc(){
		JOptionPane.showMessageDialog(null,"변경");
		String msg = "/name "+changeNameTF.getText()+"\n";
		try {
			out.write(msg.getBytes());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	void connProc() {
		//JOptionPane.showMessageDialog(null,"접속");
		//1.소켓생성(서버아이피,서버포트)
		//2.입출력 스트림 얻어오기
		try {
		s= new Socket(connTF.getText(),1234);
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		out = s.getOutputStream();
		new Thread(this).start(); 
		enterRoom();	
		idSet(); //연결 id 설정
		
		}catch(Exception ex) {
			ta.append("서버에 접속하지 못함" + ex.getMessage());
		}
	} // connProc ends
	
	//id 설정 요청
	void idSet() {
		String msg = "/idset "+"\n";
		try {
			out.write(msg.getBytes());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}//id 설정을 위해 호출
	
	void enterRoom() {
		String msg = "/enter "+changeNameTF.getText()+"\n";
		try {
			out.write(msg.getBytes());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	void sendProc() {
		
		String msg = "/test "+sendTF.getText()+" "+Userid+"\n";
		if(sendTF.getText().equals("")) {
			return;
		}
		JOptionPane.showMessageDialog(null,"보내기");
		try {
			out.write(msg.getBytes());
			sendTF.setText(null);
			}
		catch(Exception ex) 
		{
			ta.append("메세지 전송시 오류 : "+ex.getMessage());
		};
	}// sendProc ends
	
	void result(int inputnum , int vsnum,String myname) {
		
		int basearr[] = new int[3];//최초 공넘버
		int basearri[] = new int[3];//test를 위한 공넘버
		int base = 0;
		
		basearr[0] = vsnum/100;
		basearr[1] = vsnum%100/10;
		basearr[2] = vsnum%10;//최초 공의 자리마다의 수
		
		int inum = 0;//입력 받은 공의 변수

		basearri[0] = inputnum/100;
		basearri[1] = inputnum%100/10;
		basearri[2] = inputnum%10;//테스트 공의 각 자리수

		//strike
		int strike = 0;
		for(int i=0;i<3;i++)
		{
			if(basearri[i]==basearr[i])
			{
				strike++;
				base++;
			}
		}

		//ball
		int ball=0;
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(basearr[i]==basearri[j])
				{
					ball++;
				}
			}
		}
		
		ball = ball - strike;
		ta.append(myname+" "+inputnum+ " "+"strike:"+strike+"ball:"+ball+"\n");

		
	}
	public static void main(String [] args ) {
		new ChatClient();
	}

	@Override
	public void run() {
		while(s!=null){
			try {
				String msg = in.readLine();
				StringTokenizer st = new StringTokenizer(msg);
				String temp = st.nextToken();
				if(temp.equals("/member")) {
					list.removeAllElements();
					
					while(st.hasMoreTokens()) {
						
						list.addElement(st.nextToken());
						memberList.setListData(list);
					}
					continue;                                 //list 저장
				}else if(temp.equals("/idset")) {
					if(set == 0)
					{
					Userid = Integer.parseInt(st.nextToken());
					System.out.println(Userid);
					set = 1;
					}
					continue;
																//user id 저장
				}else if(temp.equals("/set")) {
					System.out.println(st.nextToken());
					continue;
				}else if(temp.equals("/result")) {
					int inputnum = Integer.parseInt(st.nextToken());
					int vsnum = Integer.parseInt(st.nextToken());
					String myname = st.nextToken();
					result(inputnum,vsnum,myname);
					continue;									//strike ball 결과값 출력
				}
				ta.append(msg + "\n");
				
			} catch (Exception ex) {
				
				ta.append("메세지 읽기 실패 : "+ex.getMessage());
			}
			
		}
	}
	
}// ChatClient ends
			
			

	
		
