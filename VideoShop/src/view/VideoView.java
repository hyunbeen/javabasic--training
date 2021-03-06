package	 view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import model.VideoModel;
import model.vo.Video;


public class VideoView extends JPanel implements ActionListener 
{	
	//	member field
	JTextField	tfVideoNum, tfVideoTitle, tfVideoDirector, tfVideoActor;
	JComboBox	comVideoJanre;
	JTextArea	taVideoContent;

	JCheckBox	cbMultiInsert;
	JTextField	tfInsertCount;

	JButton		bVideoInsert, bVideoModify, bVideoDelete;

	JComboBox	comVideoSearch;
	JTextField	tfVideoSearch;
	JTable		tableVideo;
	
	VideoTableModel tbModelVideo;
	VideoModel db;
	

	//##############################################
	//	constructor method
	public VideoView(){
		addLayout(); 	// 화면설계
		initial();
		eventProc();
		connectDB();	// DB연결
	}
	
	public void initial() {
		tfInsertCount.setEditable(false);
		tfVideoNum.setEditable(false);
	}
	
	public void connectDB(){	// DB연결
		try {
			db = new VideoModel();
		} catch (Exception e) {
			System.out.println("연결실패");
		}
	}
	
	public void eventProc(){
		bVideoInsert.addActionListener(this);
		bVideoModify.addActionListener(this);
		bVideoDelete.addActionListener(this);
		tfVideoSearch.addActionListener(this);
		cbMultiInsert.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				tfInsertCount.setEditable(cbMultiInsert.isSelected());
				
			}
			
		});
		tableVideo.addMouseListener(new MouseAdapter() {

		
			public void mouseClicked(MouseEvent e) {
				int row = tableVideo.getSelectedRow();
				int vNum = Integer.parseInt(String.valueOf(tableVideo.getValueAt(row,0)));
				System.out.println(vNum);
				searchByPk(vNum);
				
			}

		});
	}	
	void searchByPk(int vNum) {
		Video v;
		try {
			v = db.searchByPk(vNum);
			int idx = 0;
			tfVideoNum.setText(String.valueOf(v.getVideoNo()));
			tfVideoTitle.setText(String.valueOf(v.getVideoName()));
			tfVideoActor.setText(v.getActor());
			tfVideoDirector.setText(v.getDirector());
			taVideoContent.setText(v.getExp());
			switch(v.getGenre()) {
			case "멜로" : idx = 0;break;
			case "엑션" : idx = 1;break;
			case "스릴" : idx = 2;break;
			case "코미디" : idx = 3;break;
			}
			comVideoJanre.setSelectedIndex(idx);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void actionPerformed(ActionEvent ev) {
		Object evt = ev.getSource();
		if(evt == bVideoInsert) {
			insertVideo();
			searchVideo();
			clear();
		}else if(evt == bVideoModify) {
			modifyVideo();
			searchVideo();
			clear();
		}else if(evt == bVideoDelete) {
			deleteVideo();
			searchVideo();
			clear();
		}else if(evt == tfVideoSearch) {
			searchVideo();
			clear();
		}
	}
	public void deleteVideo() {
		int id = Integer.parseInt(tfVideoNum.getText());
		try {
			db.deleteVideo(id);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void clear(){
	      comVideoJanre.setSelectedItem(null);
	      tfVideoNum.setText(null);
	      tfVideoTitle.setText(null);
	      tfVideoDirector.setText(null);
	      tfVideoActor.setText(null);
	      taVideoContent.setText(null);
	      tfInsertCount.setText("1");
	      
	      cbMultiInsert.setSelected(false);
	      tfInsertCount.setEditable(false);
	   }
	public void modifyVideo(){
		int id = Integer.parseInt(tfVideoNum.getText());
		Video v = new Video();
		v.setActor(tfVideoActor.getText());
		v.setDirector(tfVideoDirector.getText());
		v.setExp(taVideoContent.getText());
		v.setGenre(String.valueOf(comVideoJanre.getSelectedItem()));
		v.setVideoName(tfVideoTitle.getText());
		
		try {
			db.modifyVideo(id,v);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void searchVideo() {
		int idx = comVideoSearch.getSelectedIndex();
		String word = tfVideoSearch.getText();
		ArrayList list;
		try {
			list = db.searchVideo(idx,word);
			tbModelVideo.data = list;
			tableVideo.setModel(tbModelVideo);
			tbModelVideo.fireTableDataChanged();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	void insertVideo() {
		Video v = new Video();
		v.setGenre(String.valueOf(comVideoJanre.getSelectedItem()));
		v.setActor(tfVideoActor.getText());
		v.setDirector(tfVideoActor.getText());
		v.setExp(taVideoContent.getText());
		v.setVideoName(tfVideoTitle.getText());
		
		int count = Integer.parseInt(tfInsertCount.getText());
		try {
			db.insertVideo(v, count);
		} catch (Exception e) {
			System.out.println("입고안됨");
			e.printStackTrace();
		}
	}
	//  화면설계 메소드
	public void addLayout(){
		//멤버변수의 객체 생성
		tfVideoNum = new JTextField();
		tfVideoTitle = new JTextField();
		tfVideoDirector = new JTextField();
		tfVideoActor = new JTextField();
		
		String []cbJanreStr = {"멜로","엑션","스릴","코미디"};
		comVideoJanre = new JComboBox(cbJanreStr);
		taVideoContent = new JTextArea();
		
		cbMultiInsert = new JCheckBox("다중입고");
		tfInsertCount = new JTextField("1",5);
	
		bVideoInsert = new JButton("입고");
		bVideoModify = new JButton("수정");
		bVideoDelete = new JButton("삭제");
		
		String []cbVideoSearch = {"비디오번호","제목","장르","감독","배우"};
		comVideoSearch = new JComboBox(cbVideoSearch);
		tfVideoSearch = new JTextField(15);
		
		tbModelVideo = new VideoTableModel();
		tableVideo = new JTable(tbModelVideo);
		// tableVideo.setModel(tbModelVideo);
		
		
		
		//************화면구성************
		//왼쪽영역
		JPanel p_west = new JPanel();
		p_west.setLayout(new BorderLayout());
		// 왼쪽 가운데
		JPanel p_west_center = new JPanel();	
		p_west_center.setLayout(new BorderLayout());
		// 왼쪽 가운데의 윗쪽
		JPanel p_west_center_north = new JPanel();
		p_west_center_north.setLayout(new GridLayout(5,2));
		p_west_center_north.add(new JLabel("비디오번호"));
		p_west_center_north.add(tfVideoNum);
		p_west_center_north.add(new JLabel("장르"));
		p_west_center_north.add(comVideoJanre);
		p_west_center_north.add(new JLabel("제목"));
		p_west_center_north.add(tfVideoTitle);
		p_west_center_north.add(new JLabel("감독"));
		p_west_center_north.add(tfVideoDirector);
		p_west_center_north.add(new JLabel("배우"));
		p_west_center_north.add(tfVideoActor);
		
		// 왼쪽 가운데의 가운데
		JPanel p_west_center_center = new JPanel();
		p_west_center_center.setLayout(new BorderLayout());
		// BorderLayout은 영역 설정도 해야함
		p_west_center_center.add(new JLabel("설명"),BorderLayout.WEST);
		p_west_center_center.add(taVideoContent,BorderLayout.CENTER);
		
		// 왼쪽 화면에 붙이기
		p_west_center.add(p_west_center_north,BorderLayout.NORTH);
		p_west_center.add(p_west_center_center,BorderLayout.CENTER);
		p_west_center.setBorder(new TitledBorder("비디오 정보입력"));
		
		// 왼쪽 아래
		JPanel p_west_south = new JPanel();		
		p_west_south.setLayout(new GridLayout(2,1));
		
		JPanel p_west_south_1 = new JPanel();
		p_west_south_1.setLayout(new FlowLayout());
		p_west_south_1.add(cbMultiInsert);
		p_west_south_1.add(tfInsertCount);
		p_west_south_1.add(new JLabel("개"));
		p_west_south_1.setBorder(new TitledBorder("다중입력시 선택하시오"));
		// 입력 수정 삭제 버튼 붙이기
		JPanel p_west_south_2 = new JPanel();
		p_west_south_2.setLayout(new GridLayout(1,3));
		p_west_south_2.add(bVideoInsert);
		p_west_south_2.add(bVideoModify);
		p_west_south_2.add(bVideoDelete);
		
		p_west_south.add(p_west_south_1);
		p_west_south.add(p_west_south_2);
		
		p_west.add(p_west_center,BorderLayout.CENTER);
		p_west.add(p_west_south, BorderLayout.SOUTH);   // 왼쪽부분완성
		
		// 화면구성 - 오른쪽영역
		JPanel p_east = new JPanel();
		p_east.setLayout(new BorderLayout());
		
		JPanel p_east_north = new JPanel();
		p_east_north.add(comVideoSearch);
		p_east_north.add(tfVideoSearch);
		p_east_north.setBorder(new TitledBorder("비디오 검색"));
		
		p_east.add(p_east_north,BorderLayout.NORTH);
		p_east.add(new JScrollPane(tableVideo),BorderLayout.CENTER);
		// 테이블을 붙일때에는 반드시 JScrollPane() 이렇게 해야함 
		
		
		// 전체 화면에 왼쪽 오른쪽 붙이기
		setLayout(new GridLayout(1,2));
		
		add(p_west);
		add(p_east);
		
	}
	
	//화면에 테이블 붙이는 메소드 
	class VideoTableModel extends AbstractTableModel { 
		  
		ArrayList data = new ArrayList();
		String [] columnNames = {"비디오번호","제목","장르","감독","배우"};

		//=============================================================
		// 1. 기본적인 TabelModel  만들기
		// 아래 세 함수는 TabelModel 인터페이스의 추상함수인데
		// AbstractTabelModel에서 구현되지 않았기에...
		// 반드시 사용자 구현 필수!!!!

		    public int getColumnCount() { 
		        return columnNames.length; 
		    } 
		     
		    public int getRowCount() { 
		        return data.size(); 
		    } 

		    public Object getValueAt(int row, int col) { 
				ArrayList temp = (ArrayList)data.get( row );
		        return temp.get( col ); 
		    }
		    
		    public String getColumnName(int col){
		    	return columnNames[col];
		    }
	}
}


