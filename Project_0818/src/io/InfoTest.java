package io;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.RandomAccessFile;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class InfoTest implements ActionListener{

	JFrame frame;
	JTextField tf_name, tf_id, tf_tel, tf_grade, tf_email;
	JButton b_add, b_show, b_detail, b_modify, b_delete, b_exit;
	JTextArea ta;	
	
	/*
	 * <info>
	 * <person>
	 * <tel>010-9999</tel>
	 * <name>홍길동</name>
	 * <jumin>.....</jumin>
	 * </person>
	 * </info>
	 */

	public InfoTest() {

		frame		= new JFrame("DBTest");


		
		
		tf_id		= new JTextField(15);
		tf_id.setDocument((new JTextFieldLimit(3)));
		tf_name	= new JTextField(15);
		tf_tel		= new JTextField(15);
		tf_grade		= new JTextField(15);
		tf_email	= new JTextField(15);
		

		b_add		= new JButton("Add", new ImageIcon("img/add.gif"));
		b_add.setVerticalTextPosition(SwingConstants.BOTTOM);
		b_add.setHorizontalTextPosition(SwingConstants.CENTER);		
		b_add.setBorder(new BevelBorder(BevelBorder.RAISED));
		b_add.setToolTipText("추가");


		b_show		= new JButton("Show");
		b_modify	= new JButton("Modify");
		b_delete	= new JButton("Delete");
		b_exit		= new JButton("Exit");
		b_detail    = new JButton("Detail");
		ta			= new JTextArea(20, 50);


	}

	void setup(){

		JPanel p_center  = new JPanel();
		JPanel p_west	 = new JPanel();
		JPanel p_south 	 = new JPanel();

		// west영역 붙이기
		// 화면출력만 하는 라벨 생성 및 붙이기 

		/************************
		 * 이미지 라벨 
		 */
	
		JLabel ll_id   = new JLabel("직원번호",   new ImageIcon("img/cute/2.gif"),SwingConstants.CENTER);
		JLabel ll_name = new JLabel("이름", new ImageIcon("img/cute/1.gif"),JLabel.CENTER);		
		JLabel ll_tel  = new JLabel("전화번호",  new ImageIcon("img/cute/3.gif"),SwingConstants.CENTER);
		JLabel ll_grade  = new JLabel("직급",  new ImageIcon("img/cute/4.gif"),SwingConstants.CENTER);
		JLabel ll_email  = new JLabel("이메일주소",  new ImageIcon("img/cute/5.gif"),SwingConstants.CENTER);
		


		p_west.setLayout( new GridLayout(5,2));
		p_west.add( ll_id);
		p_west.add( tf_id );
		p_west.add( ll_name);
		p_west.add( tf_name );
		p_west.add( ll_tel);
		p_west.add( tf_tel );
		p_west.add( ll_grade);
		p_west.add( tf_grade );
		p_west.add( ll_email);
		p_west.add( tf_email );
		


		// center 영역
		p_center.setLayout(new BorderLayout());
		p_center.add("Center", ta );

		// south 영역
		p_south.setLayout( new GridLayout(1,6));
		p_south.add( b_add );
		p_south.add( b_show );
		p_south.add( b_detail );
		p_south.add( b_modify );
		p_south.add( b_delete );
		p_south.add( b_exit );

		frame.getContentPane().setLayout( new BorderLayout() );
		frame.getContentPane().add("West", p_west );
		frame.getContentPane().add("Center", p_center );
		frame.getContentPane().add("South", p_south );

		frame.pack();
		frame.setTitle("InfoTest");
		frame.setVisible(true);	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void eventProc(){


		// 버튼 이벤트 등록
		b_add.addActionListener( this );
		b_show.addActionListener( this );
		b_detail.addActionListener( this );
		b_modify.addActionListener( this );
		b_delete.addActionListener( this );
		b_exit.addActionListener( this );
		
		tf_tel.addActionListener(this);

	}

	public void actionPerformed( ActionEvent ev ){

		Object evt = ev.getSource();
		if( evt== b_add ){
			saveData();
			alignment();
		}	else if( evt == b_show ){
			alignment();
			readDatashow();
			
		} 	else if( evt == tf_tel ){
			search();
		}	else if( evt == b_delete ){
			removeData();
			alignment();
		}	else if( evt == b_detail) {
			alignment();
			readData();
		} else if(evt == b_exit)
		{
			System.exit(0);
		}else if(evt == b_modify) {
			modify();
			alignment();
		}

	}
	void modify() {
	try {
			
			File f = new File("info.xml");
			DocumentBuilderFactory docFac = DocumentBuilderFactory.newInstance().newInstance();
			DocumentBuilder docBuild = docFac.newDocumentBuilder();
			Document doc = docBuild.parse(f);
			doc.getDocumentElement().normalize();
			
			NodeList personlist = doc.getElementsByTagName("person");
			for(int i=0;i<personlist.getLength();i++) {
				
				Node person = personlist.item(i);
				NodeList children = person.getChildNodes();
				
				
					if(tf_tel.getText().equals(children.item(2).getTextContent())) {
						
						
						children.item(0).setTextContent(tf_id.getText());
						children.item(1).setTextContent(tf_name.getText());
						children.item(2).setTextContent(tf_tel.getText());
						children.item(3).setTextContent(tf_grade.getText());
						children.item(4).setTextContent(tf_email.getText());
						
						Transformer trans = TransformerFactory.newInstance().newTransformer();
						Result output = new StreamResult(f);
						Source input = new DOMSource(doc);
						trans.transform(input, output);
					}
					
				
			}
			
			
		}catch(Exception ex) {
			System.out.println("삭제 실행시 오류 : "+ex.getMessage());
		}
	}
	void removeData(){
try {
			
			File f = new File("info.xml");
			DocumentBuilderFactory docFac = DocumentBuilderFactory.newInstance().newInstance();
			DocumentBuilder docBuild = docFac.newDocumentBuilder();
			Document doc = docBuild.parse(f);
			doc.getDocumentElement().normalize();
			
			NodeList personlist = doc.getElementsByTagName("person");
			for(int i=0;i<personlist.getLength();i++) {
				
				Node person = personlist.item(i);
				NodeList children = person.getChildNodes();
				
				for(int j=0;j<children.getLength();j++) {
					Node child = children.item(j);
					
				if((child.getNodeName()).equals("tel")) {
					if((tf_tel.getText()).equals(child.getTextContent())) {
						Node parent = person.getParentNode();
						parent.removeChild(person);
						
						//System.out.println("결과:"+personlist.getLength());
						//Document (객체)를 파일을 저장
						
						Transformer trans = TransformerFactory.newInstance().newTransformer();
						Result output = new StreamResult(f);
						Source input = new DOMSource(doc);
						trans.transform(input, output);
					}
				}
				}
			}
			
		}catch(Exception ex) {
			System.out.println("삭제 실행시 오류 : "+ex.getMessage());
		}
	}
	void search(){
			try {
			
			File f = new File("info.xml");
			DocumentBuilderFactory docFac = DocumentBuilderFactory.newInstance().newInstance();
			DocumentBuilder docBuild = docFac.newDocumentBuilder();
			Document doc = docBuild.parse(f);
			doc.getDocumentElement().normalize();
			
			NodeList personlist = doc.getElementsByTagName("person");
			for(int i=0;i<personlist.getLength();i++) {
				
				Node person = personlist.item(i);
				NodeList children = person.getChildNodes();
				
				
					if(tf_tel.getText().equals(children.item(2).getTextContent())) {
			
						
						tf_id.setText(children.item(0).getTextContent());
						tf_name.setText(children.item(1).getTextContent());
						
						tf_grade.setText(children.item(3).getTextContent());
						tf_email.setText(children.item(4).getTextContent());
						
					}
					
				
			}
			
			
		}catch(Exception ex) {
			System.out.println("삭제 실행시 오류 : "+ex.getMessage());
		}
	}
	
	void alignment(){
		try {
		
		File f = new File("info.xml");
		DocumentBuilderFactory docFac = DocumentBuilderFactory.newInstance().newInstance();
		DocumentBuilder docBuild = docFac.newDocumentBuilder();
		Document doc = docBuild.parse(f);
		doc.getDocumentElement().normalize();
		int num1;
		int num2;
		String temp = "";
		NodeList personlist = doc.getElementsByTagName("person");
		 System.out.println(personlist.item(0).getChildNodes().item(0).getTextContent());
		 System.out.println(personlist.item(1).getChildNodes().item(0).getTextContent());
		 System.out.println(personlist.item(2).getChildNodes().item(0).getTextContent());
		 System.out.println(personlist.item(3).getChildNodes().item(0).getTextContent());
		System.out.println(personlist.item(4).getChildNodes().item(0).getTextContent());
		System.out.println();

		for(int i=personlist.getLength()-1;i>0;i--) {
			for(int j = 0;j<i;j++) {
				
					num1 = Integer.parseInt(personlist.item(j).getChildNodes().item(0).getTextContent());
					num2 = Integer.parseInt(personlist.item(j+1).getChildNodes().item(0).getTextContent());
				
					if(num1>num2) {
					
						for(int k=0;k<5;k++)
						{
						temp = personlist.item(j+1).getChildNodes().item(k).getTextContent();
						personlist.item(j+1).getChildNodes().item(k).setTextContent(personlist.item(j).getChildNodes().item(k).getTextContent());
						personlist.item(j).getChildNodes().item(k).setTextContent(temp);
						
						
						}
					}
					
					
					
				
			}
			
		}
		
		Transformer trans = TransformerFactory.newInstance().newTransformer();
		Result output = new StreamResult(f);
		Source input = new DOMSource(doc);
		trans.transform(input, output);
		
		
	}catch(Exception ex) {
		System.out.println("삭제 실행시 오류 : "+ex.getMessage());
	}
}
	
	void readData(){
		try {
			
			File f = new File("info.xml");
			DocumentBuilderFactory docFac = DocumentBuilderFactory.newInstance().newInstance();
			DocumentBuilder docBuild = docFac.newDocumentBuilder();
			Document doc = docBuild.parse(f);
			doc.getDocumentElement().normalize();
			
			NodeList personlist = doc.getElementsByTagName("person");
			System.out.println(personlist.getLength() + "명 저장됨");
			
			ta.setText("");
			ta.setText("직원번호\t이름\t전화번호\t직급\t이메일주소\n");
			for(int i=0;i<personlist.getLength();i++) {
				Node person = personlist.item(i);
				NodeList psChild = person.getChildNodes();
				for(int j = 0;j<psChild.getLength();j++) {
					Node temp = psChild.item(j);
					ta.append(temp.getTextContent() + "\t");
				}
				ta.append("\n");
			}
		}catch(Exception ex) {
			System.out.println("전체보기 실행시 오류 : "+ex.getMessage());
		}
	}
	void readDatashow(){
		try {
			
			File f = new File("info.xml");
			DocumentBuilderFactory docFac = DocumentBuilderFactory.newInstance().newInstance();
			DocumentBuilder docBuild = docFac.newDocumentBuilder();
			Document doc = docBuild.parse(f);
			doc.getDocumentElement().normalize();
			
			NodeList personlist = doc.getElementsByTagName("person");
			System.out.println(personlist.getLength() + "명 저장됨");
			
			ta.setText("");
			ta.setText("직원번호\t이름\t전화번호\n");
			for(int i=0;i<personlist.getLength();i++) {
				Node person = personlist.item(i);
				NodeList psChild = person.getChildNodes();
				for(int j = 0;j<3;j++) {
					Node temp = psChild.item(j);
					ta.append(temp.getTextContent() + "\t");
				}
				ta.append("\n");
			}
		}catch(Exception ex) {
			System.out.println("전체보기 실행시 오류 : "+ex.getMessage());
		}
	}
	
	void saveData(){
		
		try {
			File f = new File("info.xml");
			RandomAccessFile raf = new RandomAccessFile(f,"rw");
			
			long pos = raf.length()-7;
			if(pos < 0) { //첫 데이타 삽입
				String tag = "<info>" + makeTags() + "</info>";
				raf.write(tag.getBytes());
			}else {
				raf.seek(pos); // </info> 바로 앞
				String tag = makeTags() + "</info>";
				raf.write(tag.getBytes());
			}
			raf.close();
			clearTextFields();
		} catch (Exception e) {
			System.out.println("저장시 오류 : "+e.getMessage());
			e.printStackTrace();
		}
		
	}

	String makeTags(){
		StringBuffer tag = new StringBuffer();
		tag.append("<person>");
		tag.append("<id>" +  tf_id.getText() + "</id>");
		tag.append("<name>" + tf_name.getText() + "</name>");
		tag.append("<tel>" +  tf_tel.getText() + "</tel>");
		tag.append("<grade>" +  tf_grade.getText() + "</grade>");
		tag.append("<email>" +  tf_email.getText() + "</email>");
		tag.append("</person>");
		return tag.toString();
	}

	void clearTextFields(){
		tf_tel.setText("");
		tf_name.setText("");
		tf_id.setText("");
		tf_grade.setText("");
		tf_email.setText("");
		
	}
	public static void main(String args[]) {		
		InfoTest mainFrame = new InfoTest();
		mainFrame.setup();
		mainFrame.eventProc();
	}
}

class JTextFieldLimit extends PlainDocument {
	   private int limit;
	   private boolean toUppercase = false;
	    JTextFieldLimit(int limit) {
	      super();
	      this.limit = limit;
	   }
	    JTextFieldLimit(int limit, boolean upper) {
	      super();
	      this.limit = limit;
	      this.toUppercase = upper;
	   }
	 
	   public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
	      if (str == null) {
	         return;
	      }
	      if ( (getLength() + str.length()) <= limit) {
	         if (toUppercase) {
	            str = str.toUpperCase();
	         }
	         super.insertString(offset, str, attr);
	      }
	   }
	}