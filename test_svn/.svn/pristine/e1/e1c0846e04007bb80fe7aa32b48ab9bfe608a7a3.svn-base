package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

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
import org.jfree.data.category.DefaultCategoryDataset;

import db.MonthGraphDB;
public class MonthGraphView extends JPanel implements ActionListener {
	MonthGraphDB db;
	
	JButton bcost;
	JButton bmonth;
	JButton bsearch; //월별 매출 그래프를 보여주는 버튼과 검색버튼
	
	JTable tbl_cost;

	JComboBox cbyear;
	JComboBox cbmonth;//검색을 위한 콤보박스
	
	MonthGraphView(){
		db = new MonthGraphDB();
	
		setUI();
		
		display();
	
	}
	

	public void setUI() {

		

		
	}
	public void display() {
		  int price[] = new int[12];
		  int buprice[] = new int[12];
		  int sprice[] = new int[12];
		  int iprice[] = new int[12];
		  int bprice[] = new int[12];
		  final String month_1 = "Jan";              
		  final String month_2 = "Feb"; 
		  final String month_3 = "Mar"; 
		  final String month_4 = "Apr"; 
		  final String month_5 = "May"; 
		  final String month_6 = "Jun"; 
		  final String month_7 = "Jul"; 
		  final String month_8 = "Agu"; 
		  final String month_9 = "Sep"; 
		  final String month_10 = "Oct"; 
		  final String month_11 = "Nov"; 
		  final String month_12 = "Dec"; 
		  final String sales = "Allsales";
		  final String Burger = "Burger";
		  final String Set = "Set";
		  final String Side = "Side";
		  final String Beverage = "Beverage";
	      final DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 
	      String month;
	      
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
	    
	      
	      dataset.addValue( price[0] , sales , month_1  );              
	      dataset.addValue( price[1] , sales , month_2  );   
	      dataset.addValue( price[2] , sales , month_3  );   
	      dataset.addValue( price[3] , sales , month_4  );   
	      dataset.addValue( price[4] , sales , month_5  );   
	      dataset.addValue( price[5] , sales , month_6  );   
	      dataset.addValue( price[6] , sales , month_7  );   
	      dataset.addValue( price[7] , sales , month_8  );   
	      dataset.addValue( price[8] , sales , month_9  );   
	      dataset.addValue( price[9] , sales , month_10  );   
	      dataset.addValue( price[10] , sales , month_11  );   
	      dataset.addValue( price[11] , sales , month_12  );   
	      dataset.addValue( buprice[0] , Burger , month_1  );              
	      dataset.addValue( buprice[1] , Burger , month_2  );   
	      dataset.addValue( buprice[2] , Burger , month_3  );   
	      dataset.addValue( buprice[3] , Burger , month_4  );   
	      dataset.addValue( buprice[4] , Burger , month_5  );   
	      dataset.addValue( buprice[5] , Burger , month_6  );   
	      dataset.addValue( buprice[6] , Burger , month_7  );   
	      dataset.addValue( buprice[7] , Burger , month_8  );   
	      dataset.addValue( buprice[8] , Burger , month_9  );   
	      dataset.addValue( buprice[9] , Burger , month_10  );   
	      dataset.addValue( buprice[10] , Burger , month_11  );   
	      dataset.addValue( buprice[11] , Burger , month_12  );   
	      dataset.addValue( sprice[0] , Set , month_1  );              
	      dataset.addValue( sprice[1] , Set , month_2  );   
	      dataset.addValue( sprice[2] , Set , month_3  );   
	      dataset.addValue( sprice[3] , Set , month_4  );   
	      dataset.addValue( sprice[4] , Set , month_5  );   
	      dataset.addValue( sprice[5] , Set , month_6  );   
	      dataset.addValue( sprice[6] , Set , month_7  );   
	      dataset.addValue( sprice[7] , Set , month_8  );   
	      dataset.addValue( sprice[8] , Set , month_9  );   
	      dataset.addValue( sprice[9] , Set , month_10  );   
	      dataset.addValue( sprice[10] , Set , month_11  );   
	      dataset.addValue( sprice[11] , Set , month_12  );   
	      dataset.addValue( iprice[0] , Side , month_1  );              
	      dataset.addValue( iprice[1] , Side , month_2  );   
	      dataset.addValue( iprice[2] , Side , month_3  );   
	      dataset.addValue( iprice[3] , Side , month_4  );   
	      dataset.addValue( iprice[4] , Side , month_5  );   
	      dataset.addValue( iprice[5] , Side , month_6  );   
	      dataset.addValue( iprice[6] , Side , month_7  );   
	      dataset.addValue( iprice[7] , Side , month_8  );   
	      dataset.addValue( iprice[8] , Side , month_9  );   
	      dataset.addValue( iprice[9] , Side , month_10  );   
	      dataset.addValue( iprice[10] , Side , month_11  );   
	      dataset.addValue( iprice[11] , Side , month_12  );   
	      dataset.addValue( bprice[0] , Beverage , month_1  );              
	      dataset.addValue( bprice[1] , Beverage , month_2  );   
	      dataset.addValue( bprice[2] , Beverage , month_3  );   
	      dataset.addValue( bprice[3] , Beverage , month_4  );   
	      dataset.addValue( bprice[4] , Beverage , month_5  );   
	      dataset.addValue( bprice[5] , Beverage , month_6  );   
	      dataset.addValue( bprice[6] , Beverage , month_7  );   
	      dataset.addValue( bprice[7] , Beverage , month_8  );   
	      dataset.addValue( bprice[8] , Beverage , month_9  );   
	      dataset.addValue( bprice[9] , Beverage , month_10  );   
	      dataset.addValue( bprice[10] , Beverage , month_11  );   
	      dataset.addValue( bprice[11] , Beverage , month_12  );   
	      JFreeChart barChart = ChartFactory.createBarChart3D(
	         "BurgerKing Sales Statistics",             
	         "Month",             
	         "Category",             
	         dataset,            
	         PlotOrientation.VERTICAL,             
	         true, true, false);
	         
	      int width = 640; /* Width of the image */              
	      int height = 480; /* Height of the image */                              
	      File barChart3D = new File( "barChart3D.jpeg" );                            
	      try {
			ChartUtilities.saveChartAsJPEG( barChart3D, barChart, width, height);
			add(new JLabel(new ImageIcon("barChart3D.jpeg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setVisible(true);
	}
	
	public void evtProc() {
	
	}
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	
}
