package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import db.MonthGraphDB;
public class MonthGraphView extends JPanel implements ActionListener {
	MonthGraphDB db;
	
	JButton binitial;
	
	JTable tbl_cost;

	JComboBox cbyear;
	JComboBox cbmonth;//검색을 위한 콤보박스
	
	  int price[] = new int[12]; //총 매출
	  int buprice[] = new int[12];
	  int sprice[] = new int[12];
	  int iprice[] = new int[12];
	  int bprice[] = new int[12]; //카테고리별 매출
	  
	  String arrmonth[];
	
	  
	  final String sales = "Allsales";
	  final String Burger = "Burger";
	  final String Set = "Set";
	  final String Side = "Side";
	  final String Beverage = "Beverage"; //카테고리별 
	  
      DefaultCategoryDataset dataset;
      String month;
      
      int width;
      int height; // 그래프 넓이
      
      
      JLabel l_graph;//그래프 사진
      
      
      JFreeChart barChart; //chart
 	  
  	Font th = new Font("인터파크고딕 L",Font.BOLD, 24);
  	Font tf = new Font("인터파크고딕 L",Font.PLAIN,20);
  	Font plain = new Font("인터파크고딕 L",Font.PLAIN,15);
  	//font
   
      
      //그래프를 위한 변수
	MonthGraphView(){
		db = new MonthGraphDB();
	
		setUI();
		
		display(); //초기 그래프
		
		evtProc();
	
	}
	

	public void setUI() {

		binitial = new JButton("새로고침");
		l_graph = new JLabel();
		dataset =  new DefaultCategoryDataset(); 
		barChart = ChartFactory.createBarChart3D(
	 	         "BurgerKing Sales Statistics",             
	 	         "Month",             
	 	         "Category",             
	 	         dataset,            
	 	         PlotOrientation.VERTICAL,             
	 	         true, true, false);
		    
	   
		
	}
	
	public void display() {
		  arrmonth = new String[12];
		  arrmonth[0] = "Jan";              
		  arrmonth[1] = "Feb"; 
		  arrmonth[2] = "Mar"; 
		  arrmonth[3] = "Apr"; 
		  arrmonth[4] = "May"; 
		  arrmonth[5] = "Jun"; 
		  arrmonth[6] = "Jul"; 
		  arrmonth[7] = "Agu"; 
		  arrmonth[8] = "Sep"; 
		  arrmonth[9] = "Oct"; 
		  arrmonth[10] = "Nov"; 
		  arrmonth[11] = "Dec"; //달별 
		  
			setBackground(Color.WHITE);
			setLayout(new BorderLayout());
		
	      
	      for(int i=0;i<=11;i++) {
				if(i<10) {
					
					month = "0"+String.valueOf(i+1);
				
				}else {
					
					month = String.valueOf(i+1);
				}
				
				try {
					price[i] = db.input(month);
					buprice[i] = db.buinput(month);
					sprice[i] = db.sinput(month);
					iprice[i] = db.iinput(month);
					bprice[i] = db.binput(month);  //달별 매출량 가져오기
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
	      //총 매출량
	      for(int i=0;i<12;i++) {
	    	  dataset.addValue( price[i] , sales , arrmonth[i] );  
	      }
	                  
	      
	      
	      //set
	      for(int i=0;i<12;i++) {
	    	  dataset.addValue( buprice[i] , Burger ,arrmonth[i] );    
	      }
	               
	      
	      //버거 단품
	      
	      for(int i=0;i<12;i++) {
	    	  dataset.addValue( sprice[i] , Set , arrmonth[i] );          
	      }
	        
	      
	      //set
	      
	      for(int i=0;i<12;i++) {
	    	  dataset.addValue( iprice[i] , Side , arrmonth[i]  );  
	      }
	                 
	     
	      //side
	      for(int i=0;i<12;i++) {
	    	  dataset.addValue( bprice[i] , Beverage ,arrmonth[i]   );
	      }
	                    
	     
	      //각각의 데이터에 맞게 그래프에 값넣기  
	      //beverage
	      width = 640; /* Width of the image */              
	      height = 480; /* Height of the image */                              
	      barChart.getTitle().setFont(th); 
	      barChart.getCategoryPlot().getDomainAxis().setTickLabelFont(plain);
	      barChart.getCategoryPlot().getRangeAxis().setTickLabelFont(plain);
	      
	       
	      File barChart3D = new File( "barChart3D.jpeg" ); 
			try {
				ChartUtilities.saveChartAsJPEG( barChart3D, barChart, width, height);
			} catch (IOException e) {
		
				e.printStackTrace();
			}
			
			l_graph.setIcon(new ImageIcon("barChart3D.jpeg"));
			add(l_graph,BorderLayout.CENTER);
			add(binitial,BorderLayout.SOUTH);
			System.out.println(barChart3D.delete());
			
			dataset.clear();
			barChart.fireChartChanged();
			
			
		setVisible(true);
	}
	
	public void initial() {
	    for(int i=0;i<=11;i++) {
					if(i<10) {
						
						month = "0"+String.valueOf(i+1);
					
					}else {
						
						month = String.valueOf(i+1);
					}
					
					try {
						price[i] = db.input(month);
						buprice[i] = db.buinput(month);
						sprice[i] = db.sinput(month);
						iprice[i] = db.iinput(month);
						bprice[i] = db.binput(month);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		    
		      
	    //총 매출량
	      for(int i=0;i<12;i++) {
	    	  dataset.addValue( price[i] , sales , arrmonth[i] );  
	      }
	                  
	      
	      
	      //set
	      for(int i=0;i<12;i++) {
	    	  dataset.addValue( buprice[i] , Burger ,arrmonth[i] );    
	      }
	               
	      
	      //버거 단품
	      
	      for(int i=0;i<12;i++) {
	    	  dataset.addValue( sprice[i] , Set , arrmonth[i] );          
	      }
	        
	      
	      //set
	      
	      for(int i=0;i<12;i++) {
	    	  dataset.addValue( iprice[i] , Side , arrmonth[i]  );  
	      }
	                 
	     
	      //side
	      for(int i=0;i<12;i++) {
	    	  dataset.addValue( bprice[i] , Beverage ,arrmonth[i]   );
	      }  
		      
		     
		         
		     width = 640; /* Width of the image */              
		    height = 480; /* Height of the image */
		    barChart.getTitle().setFont(th); 
		    barChart.getCategoryPlot().getDomainAxis().setTickLabelFont(plain);
		      barChart.getCategoryPlot().getRangeAxis().setTickLabelFont(plain);
		    File barChart3D = new File( "barChart3D.jpeg" );                  
		    	barChart.fireChartChanged();
				try {
					ChartUtilities.saveChartAsJPEG( barChart3D, barChart, width, height);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				BufferedImage bufImg;
				try {
					bufImg = ImageIO.read(new File("barChart3D.jpeg"));
					l_graph.setIcon(new ImageIcon(bufImg));  //이미지 새로고침
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			
			
				l_graph.repaint();
			
				dataset.clear();
				
				
	}
	
	public void evtProc() {
		binitial.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if(evt == binitial) {
			initial();
		}
		
	}
	
	
}
