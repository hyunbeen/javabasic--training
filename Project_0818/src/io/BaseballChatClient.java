package io;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import java.util.*;

class BaseballChatClient implements ActionListener, Runnable{
	JFrame f;

	JTextField connTF, sendTF;
	JButton connB, sendB;
	JTextArea ta;

	Socket s;
	BufferedReader in;
	OutputStream out;

	// 추가0 : 대화명을 바꾸기
	JTextField changeNameTF;
	JTextField setBallNoTF;
	JButton    changeNameB;
	JButton	   setBallNoB;

	// 추가2 : 방인원의 대명 보여주기
	// 변수 선언
	JList  memberList;
	Vector list;

	public BaseballChatClient() {
		f = new JFrame("Chat Client");


		connTF = new JTextField();
		sendTF = new JTextField();
		connB = new JButton("접 속");
		sendB = new JButton("확 인");
		ta = new JTextArea(15,40);

		// 추가0: 대화명 바꾸기
		changeNameTF	= new JTextField("guest", 10);
		changeNameB		= new JButton("바꾸기");
		setBallNoTF		= new JTextField("", 10);
		setBallNoB		= new JButton("입력");

		JPanel inputSet = new JPanel();
		inputSet.setLayout(new GridLayout(2, 3));
		inputSet.add(new JLabel("대화명 : "));
		inputSet.add(changeNameTF);
		inputSet.add(changeNameB);
		inputSet.add(new JLabel("볼번호 : "));
		inputSet.add(setBallNoTF);
		inputSet.add(setBallNoB);
		
		JPanel p_serverName = new JPanel();
		p_serverName.setLayout( new BorderLayout() );
		p_serverName.add( new JLabel("서버입력 : "),"West" );
		p_serverName.add(connTF, "Center");
		p_serverName.add(connB, "East");

		JPanel p_north = new JPanel();
		p_north.setLayout( new GridLayout(1, 2));
		p_north.add( inputSet );
		p_north.add( p_serverName );

		JPanel p2 = new JPanel();
		p2.setLayout( new BorderLayout() );
		p2.add( new JLabel("메세지입력 : "),"West" );
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
		setBallNoTF.addActionListener(this);
		setBallNoB.addActionListener(this);

		f.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				try {
					if (s != null) {
						out.write("/exit xxxxxx\n".getBytes());

						in.close();
						out.close();
						s.close();
					}
				}
				catch(Exception ex) {

				}			}


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
		} else if( o == setBallNoTF || o == setBallNoB ) {
			setBallNoProc();
		}
	} // actionPerformed ends


	void setBallNoProc() {
		String setball = "/ball " + setBallNoTF.getText() + "\n";
		try {
			out.write(setball.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void changeNameProc(){
		//JOptionPane.showMessageDialog(null,"변경");
		String msg = "/name " + changeNameTF.getText() + "\n";
		try {
			out.write(msg.getBytes());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	void connProc() {
		//JOptionPane.showMessageDialog(null,"접속");
		// 1. 소켓생성 (서버아이피, 서버포트)
		// 2. 입출력스트림 얻어오기
		try {
			s = new Socket(connTF.getText(), 1234);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));	// 형변환
			out = s.getOutputStream();

			new Thread(this).start();

			enterRoom();

		} catch(Exception ex) {
			System.out.println("서버에 접속하지 못함 : " + ex.getMessage());
		}
	} // connProc ends

	void enterRoom() {
		String msg = "/enter " + changeNameTF.getText() + "\n";
		try {
			out.write(msg.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while(s != null) {
			try {
				String msg = in.readLine();	// member 바보 천재 지니어스 푸울
				StringTokenizer st = new StringTokenizer(msg);
				String temp = st.nextToken();
				if(temp.equals("/member")) {
					list.removeAllElements();
					while(st.hasMoreTokens()) {
						list.addElement(st.nextToken());
						memberList.setListData(list);
					}
					continue;
				}

				ta.append(msg + "\n");
			} catch (Exception ex) {
				//System.out.println("메시지 읽기 실패 : " + ex.getMessage());
			}
		}
	}

	void sendProc() {
		//JOptionPane.showMessageDialog(null,"보내기");
		String msg = "/game " + sendTF.getText() + "\n";
		try {
			if(sendTF.getText().equals("")) return;	// 폭탄메세지막기
			out.write(msg.getBytes());
			sendTF.setText(null);
		} catch (IOException ex) {
			System.out.println("메세지 전송시 오류 : " + ex.getMessage());
		}
	}// sendProc ends


	public static void main(String [] args ) {
		new BaseballChatClient();
	}

}// ChatClient ends





