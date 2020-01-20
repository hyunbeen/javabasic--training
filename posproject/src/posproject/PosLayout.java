package posproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class PosLayout extends JFrame implements ActionListener {
   
   JLabel mrpizza; //Main Label
   JLabel[] pizzaLabel = new JLabel[9];//각각의 버튼라벨
   JLabel tv;//타이머 라벨
   
   
   String str;
   
   JPanel allPane;
   JPanel mainPane;
   JPanel psbPane;
   JPanel menuPane;
   JPanel movePane;
   JPanel payPane;
   JPanel selPane;
   JPanel payPane2; 
   JPanel[] selPizzaPane = new JPanel[9]; //pizza label 
   
   JButton bPizza, bSide, bBev; // 피자, 사이드, 음료 버튼
   JButton[] selPizza = new JButton[9]; // 피자 종류버튼
   JButton bPrev, bNext; // 이전, 다음 버튼
   JButton bPay;// 결제버튼
   JButton bRec;// 영수증 버튼

   JTable tbl; //주문 내역 테이블
   TModel model;
   JTextField tfPrice;
   
      Font font = new Font("한컴 윤고딕 240",Font.PLAIN,20);
      Font f2 = new Font("한컴 윤고딕 240",Font.PLAIN,15);

   // PosData pd;

   int select, select2;
//---------------------------현재시간 출력----------------------------------------------
   void TimeView() {

      new Thread(new Runnable() {
         public void run() {
            while(true) {
               Date d = new Date();
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분 ss초");

               str = sdf.format(d);

               try {
                  Thread.sleep(1000);
                  tv.setText(str);
                  //System.out.println("현재시간 : " + str);
               } catch (Exception ex) {

               }
            }
         }
      }).start();

      new Thread(new Runnable() {
         public void run() {

         }
      }).start();
   }
//----------------------pos기 객체 초기화------------------------------   
   PosLayout() {
      super("Mr.Pizza");

      select = 1;
      select2 = 1;

      mrpizza = new JLabel(new ImageIcon("src/imgs/logo.PNG"));
      tv = new JLabel();
      allPane = new JPanel();
      mainPane = new JPanel();
      payPane = new JPanel();
      selPane = new JPanel();
      psbPane = new JPanel();
      menuPane = new JPanel();
      movePane = new JPanel();
      payPane2 = new JPanel();

      for (int i = 0; i < 9; i++) {
         selPizzaPane[i] = new JPanel();
         selPizza[i] = new JButton();
         pizzaLabel[i] = new JLabel();
      }

      bPizza = new JButton("피자");
      bSide = new JButton("사이드");
      bBev = new JButton("음료");

      bPrev = new JButton("◀");
      bNext = new JButton("▶");
      bPay = new JButton("결제");
      bRec = new JButton("영수증");
      model = new TModel();
      tbl = new JTable(model);
      tfPrice = new JTextField(15);
   }
//-----------------------레이아웃--------------------------
   void setUI() {
      setLayout(new BorderLayout());
      
      
     /*
      * 
      * allpane 전체 패널
      * mainpane 상단 패널을 제외한 부분을 1:2 로 gridlayout 적용
      * payPane 왼쪽의 구매를 한 상품의 목록을 출력
      * sellPane 버튼들의 집합으로 구매할수있는 상품들의 목록이다
      * sellPane : movepane
      *                pabpane
      *                menupane
      *                
      */
      
      add(allPane);
      tv.setBounds(10, 85, 500, 100);
      
      allPane.add(tv);
      allPane.setLayout(new BorderLayout());
     
      allPane.add(mrpizza, BorderLayout.NORTH);
      allPane.add(mainPane, BorderLayout.CENTER);

      
      mainPane.setLayout(new GridLayout(1, 2));
      mainPane.add(payPane);
      mainPane.add(selPane);

      payPane.setLayout(new BorderLayout());
      payPane.add(new JScrollPane(tbl), BorderLayout.CENTER);
      payPane.add(payPane2, BorderLayout.SOUTH);
      
      
      payPane2.add(tfPrice);
      payPane2.add(bPay);
      payPane2.add(bRec);
      

      tfPrice.setHorizontalAlignment(SwingConstants.RIGHT); // 오른쪽정렬
      
      
     
      selPane.setLayout(new BorderLayout());
      selPane.add(psbPane, BorderLayout.NORTH);
      selPane.add(menuPane, BorderLayout.CENTER);
      selPane.add(movePane, BorderLayout.SOUTH);
  
     
      psbPane.setLayout(new GridLayout(1, 3));
      psbPane.add(bPizza);
      psbPane.add(bSide);
      psbPane.add(bBev);
      
      movePane.setLayout(new GridLayout(1, 3));
      movePane.add(bPrev);
      movePane.add(new JPanel());
      movePane.add(bNext);
      
      bPizza.setBackground(Color.WHITE);
      bSide.setBackground(Color.WHITE);
      bBev.setBackground(Color.WHITE);
      psbPane.setBackground(Color.WHITE);
      bPay.setBackground(Color.WHITE);
      bRec.setBackground(Color.WHITE);
      selPane.setBackground(Color.WHITE);
     
      
      tbl.getTableHeader().setFont(new Font("한컴 윤고딕 240",Font.PLAIN,23));

      
      // tbl width 조정
      tbl.getColumnModel().getColumn(0).setPreferredWidth(300);
      tbl.getColumnModel().getColumn(1).setPreferredWidth(70);
      tbl.getColumnModel().getColumn(2).setPreferredWidth(10);
      tbl.getColumnModel().getColumn(3).setPreferredWidth(10);

      tbl.setFont(font);
      tbl.setRowHeight(32);
      tfPrice.setFont(font);
      bPay.setFont(font);
      bPizza.setFont(font);
      bSide.setFont(font);
      bBev.setFont(font);
      bPrev.setFont(new Font("고딕",Font.PLAIN,24));
      bNext.setFont(new Font("고딕",Font.PLAIN,24));
      bRec.setFont(font);
      tv.setFont(font);
      
      
      
      
      
      
      menuPane.setLayout(new GridLayout(3, 3));
 
    

      bPrev.setEnabled(false);
      bNext.setEnabled(false);

      for (int i = 0; i < 9; i++) {
         menuPane.add(selPizzaPane[i]);
         selPizzaPane[i].setLayout(new BorderLayout());
         selPizzaPane[i].setBackground(Color.WHITE);
         selPizzaPane[i].add(selPizza[i], BorderLayout.CENTER);
         selPizzaPane[i].add(pizzaLabel[i], BorderLayout.SOUTH);
         selPizza[i].setBackground(Color.WHITE);
         pizzaLabel[i].setHorizontalAlignment(JLabel.CENTER);
         pizzaLabel[i].setFont(font);

      }
      
      //-------------------------------색깔설정----------------------------------------
      payPane2.setBackground(Color.WHITE);
      mainPane.setBackground(Color.WHITE);
      allPane.setBackground(Color.WHITE);
      menuPane.setBackground(Color.WHITE);
      movePane.setBackground(Color.WHITE);
      bPrev.setBackground(Color.WHITE);
      bNext.setBackground(Color.WHITE);
      
      DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
      tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
      TableColumnModel tcmSchedule = tbl.getColumnModel();
      for (int i = 0; i < tcmSchedule.getColumnCount(); i++) { 
    	  tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
    	  
      }
      
      //---------------------아이콘 이미지 및 텍스트 설정---------------------------
      selPizza[0].setIcon(new ImageIcon("src/imgs/고르곤졸라.png"));
      selPizza[1].setIcon(new ImageIcon("src/imgs/고르곤졸라.png"));
      selPizza[2].setIcon(new ImageIcon("src/imgs/따블퐈.png"));
      selPizza[3].setIcon(new ImageIcon("src/imgs/따블퐈.png"));
      selPizza[4].setIcon(new ImageIcon("src/imgs/불닭신화.png"));
      selPizza[5].setIcon(new ImageIcon("src/imgs/불닭신화.png"));
      selPizza[6].setIcon(new ImageIcon("src/imgs/쉬림프골드.png"));
      selPizza[7].setIcon(new ImageIcon("src/imgs/쉬림프골드.png"));
      selPizza[8].setIcon(new ImageIcon("src/imgs/홍크러쉬.png"));

      pizzaLabel[0].setText("고르곤졸라R 13900");
      pizzaLabel[1].setText("고르곤졸라L 20900");
      pizzaLabel[2].setText("따블퐈R 13900");
      pizzaLabel[3].setText("따블퐈L 20900");
      pizzaLabel[4].setText("불닭신화R 13900");
      pizzaLabel[5].setText("불닭신화L 20900");
      pizzaLabel[6].setText("쉬림프골드R 13900");
      pizzaLabel[7].setText("쉬림프골드L 20900");
      pizzaLabel[8].setText("홍크러쉬R 13900");

      bNext.setEnabled(true);
      bPrev.setEnabled(false);

      setSize(1200, 700);
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   void evtproc() {
      BtnHdlr bh = new BtnHdlr();
      for (int i = 0; i < 9; i++)
         selPizza[i].addActionListener(bh);

      bPay.addActionListener(bh);
      bPizza.addActionListener(bh);
      bSide.addActionListener(bh);
      bBev.addActionListener(bh);
      tbl.addMouseListener(bh);
      bNext.addActionListener(bh);
      bPrev.addActionListener(bh);
      bPizza.addActionListener(bh);
      bSide.addActionListener(bh);
      bBev.addActionListener(bh);
      bRec.addActionListener(bh);
   }

   class BtnHdlr implements ActionListener, MouseListener {
    
      
      private int arraynum = 0; //각각의 버튼페이지를 구별하기 위한 변수
      
      Vector data = new Vector(); //데이터를 저장하기 위한 공간
      
      int num[][] = { {1, 1, 1, 1, 1, 1, 1, 1, 1}, 
                    {1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1}};
      //페이별 수량을 저장하기위한 이차원 배열
    
      int rownum = 0;
      int select=1, select2=1;

      public void actionPerformed(ActionEvent ev) {
         JButton evt = (JButton) ev.getSource();
         int number = 0;
         int sum = 0;
         // selnum[100] 초기화 0으로 때린다
         // input index

         for (int i = 0; i < 9; i++) {
            if (evt == selPizza[i]) {

               Vector temp = new Vector();
               boolean condition = true;

               StringTokenizer st = new StringTokenizer(pizzaLabel[i].getText(), " ");
               
               String name = st.nextToken();
               
               /*
                * 누른 버튼의 이름과 table 데이터의 이름을 비교
                * 같을시에는 수량 이차원 배열의 값을 올리고 table의 수량을 올린다
                * 그후 합계가 변하기 때문에 합계를 다시 출력한다
                * 아닐시 에는 새로 table에 값을 추가하고 합계를 출력한다 
                */
               
               for (int j = 0; j < rownum; j++) {

                  if (name.equals((String) tbl.getValueAt(j, 0))) {

                     number = Integer.parseInt(String.valueOf(tbl.getValueAt(j, 2)));
                     num[arraynum][i]++;
                     number++;

                     tbl.setValueAt(number, j, 2);
                     condition = false;
                     sum = 0;

                     for (int k = 0; k < rownum; k++) {
                        sum = sum + Integer.parseInt(String.valueOf(tbl.getValueAt(k, 1)))
                              * Integer.parseInt(String.valueOf(tbl.getValueAt(k, 2)));
                     }
                     tfPrice.setText(String.valueOf(sum));
                     break;
                  }

               } 
               if (condition) {
                  temp.add(name);



                  temp.add(st.nextToken());
                  temp.add(num[arraynum][i]);
                  temp.add("-");
                  
                  data.add(temp);
                  
                 

                  model.data = data;
                  tbl.setModel(model);
                  model.fireTableDataChanged();

                  num[arraynum][i]++;
                  rownum++;

                  sum = 0;

                  for (int j = 0; j < rownum; j++) {
                     sum = sum + Integer.parseInt(String.valueOf(tbl.getValueAt(j, 1)))
                           * Integer.parseInt(String.valueOf(tbl.getValueAt(j, 2)));
                  }
                  tfPrice.setText(String.valueOf(sum));
               }
            }
         }
 //각각의 페이지를 구별하기위한 이벤트
         if (evt == bPizza) {
            arraynum = 0;
            this.select = 1;
            this.select2 = 1;
            mainPane.repaint();
         }

         if (evt == bSide) {
            arraynum = 3;
            this.select = 1;
            this.select2 = 2;
            mainPane.repaint();

         }

         if (evt == bBev) {
            arraynum = 4;
            this.select = 1;
            this.select2 = 3;
            mainPane.repaint();
         }
         //결제 이벤트
         if (evt == bPay) {
        	 
        	UIManager.put("OptionPane.messageFont", f2);
        	UIManager.put("OptionPane.buttonFont", f2);
            String[] pay = { "현금", "카드" };
            //choice 변수는 현금과 카드를 선택하기 위한 변수
            int choice = JOptionPane.showOptionDialog(new JFrame(), "결제방법", "결제수단", JOptionPane.DEFAULT_OPTION,
                  JOptionPane.DEFAULT_OPTION, null, pay, "");
            
            String allPay = tfPrice.getText();
            if (choice == 0) {
               String chg = JOptionPane.showInputDialog("받은돈");
               JOptionPane.showMessageDialog(new JFrame(),
                     "거스름돈 : " + String.valueOf(Integer.parseInt(chg) - Integer.parseInt(allPay)), "현금결제",
                     JOptionPane.INFORMATION_MESSAGE, null);
            } else if (choice == 1) {
            	//카드입력시 결제 완료를 띄우고 영수증 출력
               JOptionPane.showMessageDialog(null, "결제완료");
               try {
            	   String str1 = "";
            	   String str2 = "";
            	   String str3 = "";
            	   FileWriter fw =  new FileWriter("receipt.txt");
                 BufferedWriter out = new BufferedWriter(fw);
                 out.write("-------------------영수증-------------------\r\n");
                   for(int i=0;i<rownum;i++) {
                	   
                   str1 =String.valueOf(tbl.getValueAt(i, 0));
                   str2 = String.valueOf(tbl.getValueAt(i, 1));
                   str3 = String.valueOf(tbl.getValueAt(i, 2));
                  
                     out.write(str1);
                     out.write(" ");
                     out.write(str2);
                     out.write(" ");
                     out.write(str3);
                     out.write("\r\n");
                      
                   }
                   out.close();
                }
                catch(Exception ex) {
                   System.out.println("에러");
                   System.out.println(ex.getMessage());
                   ex.printStackTrace();
                }
                

                 
               
            }
            model.data.removeAllElements();
            tbl.setModel(model);
            model.fireTableDataChanged();
            tfPrice.setText("");
            rownum = 0;
                   

         }
         if (evt == bPrev) {
            if (this.select > 1) {
               this.select = this.select - 1;
               mainPane.repaint();
            }

         }

         if (evt == bNext) {
            if (this.select >= 1) {
               this.select = this.select + 1;
               mainPane.repaint();
            }
         }
         
         if(evt == bRec) {
            
           try {
        	   String str1 = "";
        	   String str2 = "";
        	   String str3 = "";
        	   FileWriter fw =  new FileWriter("reciept.txt");
             BufferedWriter out = new BufferedWriter(fw);
             out.write("-------------------영수증-------------------\r\n");
             //들어간 데이터 만큼 출력
               for(int i=0;i<rownum;i++) {
            	   
               str1 =String.valueOf(tbl.getValueAt(i, 0));
               str2 = String.valueOf(tbl.getValueAt(i, 1));
               str3 = String.valueOf(tbl.getValueAt(i, 2));
              
                 out.write(str1);
                 out.write(" ");
                 out.write(str2);
                 out.write(" ");
                 out.write(str3);
                 out.write("\r\n");
                  
               }
               out.close();
            }
            catch(Exception ex) {
               System.out.println("에러");
               System.out.println(ex.getMessage());
               ex.printStackTrace();
            }
            

             
           
       
     


         }
      //------------------이벤트가 발생하여 상품의 이미지를 변경시킬때---------------
         switch (select2) {
         case 1: // 피자메뉴 선택
            if (select == 1) {
               selPizza[0].setEnabled(true);
               selPizza[1].setEnabled(true);
               selPizza[2].setEnabled(true);
               selPizza[3].setEnabled(true);
               selPizza[4].setEnabled(true);
               selPizza[5].setEnabled(true);
               selPizza[6].setEnabled(true);
               selPizza[7].setEnabled(true);
               selPizza[8].setEnabled(true);
               arraynum = 0;
               selPizza[0].setIcon(new ImageIcon("src/imgs/고르곤졸라.png"));
               selPizza[1].setIcon(new ImageIcon("src/imgs/고르곤졸라.png"));
               selPizza[2].setIcon(new ImageIcon("src/imgs/따블퐈.png"));
               selPizza[3].setIcon(new ImageIcon("src/imgs/따블퐈.png"));
               selPizza[4].setIcon(new ImageIcon("src/imgs/불닭신화.png"));
               selPizza[5].setIcon(new ImageIcon("src/imgs/불닭신화.png"));
               selPizza[6].setIcon(new ImageIcon("src/imgs/쉬림프골드.png"));
               selPizza[7].setIcon(new ImageIcon("src/imgs/쉬림프골드.png"));
               selPizza[8].setIcon(new ImageIcon("src/imgs/홍크러쉬.png"));

               pizzaLabel[0].setText("고르곤졸라R 13900");
               pizzaLabel[1].setText("고르곤졸라L 20900");
               pizzaLabel[2].setText("따블퐈R 13900");
               pizzaLabel[3].setText("따블퐈L 20900");
               pizzaLabel[4].setText("불닭신화R 13900");
               pizzaLabel[5].setText("불닭신화L 20900");
               pizzaLabel[6].setText("쉬림프골드R 13900");
               pizzaLabel[7].setText("쉬림프골드L 20900");
               pizzaLabel[8].setText("홍크러쉬R 13900");

               bNext.setEnabled(true);
               bPrev.setEnabled(false);
            } else if (select == 2) {
               arraynum = 1;
                 selPizza[0].setEnabled(true);
                  selPizza[1].setEnabled(true);
                  selPizza[2].setEnabled(true);
                  selPizza[3].setEnabled(true);
                  selPizza[4].setEnabled(true);
                  selPizza[5].setEnabled(true);
                  selPizza[6].setEnabled(true);
                  selPizza[7].setEnabled(true);
                  selPizza[8].setEnabled(true);
               selPizza[0].setIcon(new ImageIcon("src/imgs/홍크러쉬.png"));
               selPizza[1].setIcon(new ImageIcon("src/imgs/스윗피스.png"));
               selPizza[2].setIcon(new ImageIcon("src/imgs/스윗피스.png"));
               selPizza[3].setIcon(new ImageIcon("src/imgs/로열홍새우.png"));
               selPizza[4].setIcon(new ImageIcon("src/imgs/로열홍새우.png"));
               selPizza[5].setIcon(new ImageIcon("src/imgs/트레비앙.png"));
               selPizza[6].setIcon(new ImageIcon("src/imgs/트레비앙.png"));
               selPizza[7].setIcon(new ImageIcon("src/imgs/페퍼로니.png"));
               selPizza[8].setIcon(new ImageIcon("src/imgs/페퍼로니.png"));

               pizzaLabel[0].setText("홍크러쉬L 20900");
               pizzaLabel[1].setText("스윗피스R 14900");
               pizzaLabel[2].setText("스윗피스L 21900");
               pizzaLabel[3].setText("로열홍새우R 14900");
               pizzaLabel[4].setText("로열홍새우L 21900");
               pizzaLabel[5].setText("트레비앙R 14900");
               pizzaLabel[6].setText("트레비앙L 21900");
               pizzaLabel[7].setText("페퍼로니R 14900");
               pizzaLabel[8].setText("페퍼로니L 21900");

               bNext.setEnabled(true);
               bPrev.setEnabled(true);
            } else if (select == 3) {
               
               arraynum = 2;
                 selPizza[0].setEnabled(true);
                  selPizza[1].setEnabled(true);
                  selPizza[2].setEnabled(false);
                  selPizza[3].setEnabled(false);
                  selPizza[4].setEnabled(false);
                  selPizza[5].setEnabled(false);
                  selPizza[6].setEnabled(false);
                  selPizza[7].setEnabled(false);
                  selPizza[8].setEnabled(false);
               selPizza[0].setIcon(new ImageIcon("src/imgs/로맨틱콤보.png"));
               selPizza[1].setIcon(new ImageIcon("src/imgs/로맨틱콤보.png"));
               selPizza[2].setIcon(null);
               selPizza[3].setIcon(null);
               selPizza[4].setIcon(null);
               selPizza[5].setIcon(null);
               selPizza[6].setIcon(null);
               selPizza[7].setIcon(null);
               selPizza[8].setIcon(null);

               pizzaLabel[0].setText("로맨틱콤보R 15900");
               pizzaLabel[1].setText("로맨틱콤보L 22900");
               pizzaLabel[2].setText(null);
               pizzaLabel[3].setText(null);
               pizzaLabel[4].setText(null);
               pizzaLabel[5].setText(null);
               pizzaLabel[6].setText(null);
               pizzaLabel[7].setText(null);
               pizzaLabel[8].setText(null);

               bNext.setEnabled(false);
               bPrev.setEnabled(true);
            }
            break;
         case 2: // 사이드메뉴 선택
            if (select == 1) {
               arraynum = 3;
               selPizza[0].setEnabled(true);
                selPizza[1].setEnabled(true);
                selPizza[2].setEnabled(true);
                selPizza[3].setEnabled(true);
                selPizza[4].setEnabled(true);
                selPizza[5].setEnabled(true);
                selPizza[6].setEnabled(true);
                selPizza[7].setEnabled(true);
                selPizza[8].setEnabled(true);
               selPizza[0].setIcon(new ImageIcon("src/imgs/베이크윙.jpg"));
               selPizza[1].setIcon(new ImageIcon("src/imgs/리코타치즈샐러드.jpg"));
               selPizza[2].setIcon(new ImageIcon("src/imgs/케이준치킨샐러드.jpg"));
               selPizza[3].setIcon(new ImageIcon("src/imgs/홈샐러드.jpg"));
               selPizza[4].setIcon(new ImageIcon("src/imgs/오븐치즈씨푸드김치리조또.jpg"));
               selPizza[5].setIcon(new ImageIcon("src/imgs/오븐치즈씨푸드김치스파게티.jpg"));
               selPizza[6].setIcon(new ImageIcon("src/imgs/오븐치즈베이컨크림스파게티.jpg"));
               selPizza[7].setIcon(new ImageIcon("src/imgs/오븐치즈미트스파게티.jpg"));
               selPizza[8].setIcon(new ImageIcon("src/imgs/BBQ갈비라이스.jpg"));

               pizzaLabel[0].setText("베이크윙 1390");
               pizzaLabel[1].setText("리코타치즈샐러드 1390");
               pizzaLabel[2].setText("케이준치킨샐러드 2390");
               pizzaLabel[3].setText("홈샐러드 2390");
               pizzaLabel[4].setText("오븐치즈씨푸드김치리조또 3900");
               pizzaLabel[5].setText("오븐치즈씨푸드김치스파게티 3900");
               pizzaLabel[6].setText("오븐치즈베이컨크림스파게티 3900");
               pizzaLabel[7].setText("오븐치즈미트스파게티 3900");
               pizzaLabel[8].setText("BBQ갈비라이스 4900");

               bNext.setEnabled(false);
               bPrev.setEnabled(false);
            }
            break;

         case 3: // 음료메뉴 선택
            if (select == 1) {
               arraynum = 4;
               selPizza[0].setEnabled(true);
                selPizza[1].setEnabled(true);
                selPizza[2].setEnabled(true);
                selPizza[3].setEnabled(true);
                selPizza[4].setEnabled(true);
                selPizza[5].setEnabled(true);
                selPizza[6].setEnabled(false);
                selPizza[7].setEnabled(false);
                selPizza[8].setEnabled(false);
               selPizza[0].setIcon(new ImageIcon("src/imgs/콜라.png"));
               selPizza[1].setIcon(new ImageIcon("src/imgs/사이다.png"));
               selPizza[2].setIcon(new ImageIcon("src/imgs/마운틴듀.png"));
               selPizza[3].setIcon(new ImageIcon("src/imgs/콜라.png"));
               selPizza[4].setIcon(new ImageIcon("src/imgs/사이다.png"));
               selPizza[5].setIcon(new ImageIcon("src/imgs/마운틴듀.png"));
               selPizza[6].setIcon(null);
               selPizza[7].setIcon(null);
               selPizza[8].setIcon(null);

               pizzaLabel[0].setText("콜라0.5L 1200");
               pizzaLabel[1].setText("사이다0.5L 900");
               pizzaLabel[2].setText("마운틴듀0.4L 900");
               pizzaLabel[3].setText("콜라1.5L 900");
               pizzaLabel[4].setText("사이다1.5L 1200");
               pizzaLabel[5].setText("마운틴듀1.5L 2200");
               pizzaLabel[6].setText(null);
               pizzaLabel[7].setText(null);
               pizzaLabel[8].setText(null);

               bNext.setEnabled(false);
               bPrev.setEnabled(false);
            }
         }
         
      }
//----------------------------삭제 이벤트--------------------------------
      @Override
      public void mouseClicked(MouseEvent e) {
         int number = 0;
        /*
         * 삭제시 수량을 체크하여 수량이 1이 아니면 수량 배열의 값이 줄어들고 테이블의 수량값이 줄어든다
         * 수량이 1이면 테이블의 이름을 비교하여 해당 상품의 수량 배열의 값을 1로 초기화 해준다 또한 데이터를 삭제해준다
         * 또한 합계가 변하기 때문에 합계를 다시 출력 해준다
         */
         if (tbl.getSelectedColumn() == 3) {
           if(Integer.parseInt(String.valueOf(tbl.getValueAt(tbl.getSelectedRow(), 2))) == 1)
            {
              String str = String.valueOf(tbl.getValueAt(tbl.getSelectedRow(), 0));
              System.out.println(str);
              
               switch(str) {
               case "고르곤졸라R" : num[0][0] = 1; break;
               case "고르곤졸라L" : num[0][1] = 1; break;
               case "따블퐈R" : num[0][2] = 1; break;
               case "따블퐈L" : num[0][3] = 1; break;
               case "불닭신화R" : num[0][4] = 1; break;
               case "불닭신화L" : num[0][5] = 1; break;
               case "쉬림프골드R" : num[0][6] = 1; break;
               case "쉬림프골드L" : num[0][7] = 1; break;
               case "홍크러쉬R" : num[0][8] = 1; break;
               case "홍크러쉬L" : num[1][0] = 1; break;
               case "스윗피스R" : num[1][1] = 1; break;
               case "스윗피스L" : num[1][2] = 1; break;
               case "로열홍새우R" : num[1][3] = 1; break;
               case "로열홍새우L" : num[1][4] = 1; break;
               case "트레비앙R" : num[1][5] = 1; break;
               case "트레비앙L" : num[1][6] = 1; break;
               case "페퍼로니R" : num[1][7] = 1; break;
               case "페퍼로니L" : num[1][8] = 1; break;
               case "로맨틱콤보R" : num[2][0] = 1; break;
               case "로맨틱콤보L" : num[2][1] = 1; break;
               case "베이크윙" : num[3][0] = 1; break;
               case "리코타치즈샐러드" : num[3][1] = 1; break;
               case "케이준치킨샐러드" : num[3][2] = 1; break;
               case "홈샐러드" : num[3][3] = 1; break;
               case "오븐치즈씨푸드김치리조또" : num[3][4] = 1; break;
               case "오븐치즈씨푸드김치스파게티" : num[3][5] = 1; break;
               case "오븐치즈베이컨크림스파게티" : num[3][6] = 1; break;
               case "오븐치즈미트스파게티" : num[3][7] = 1; break;
               case "BBQ갈비라이스" : num[3][8] = 1; break;
               case "콜라0.5L" : num[4][0] = 1; break;
               case "사이다0.5L" : num[4][1] = 1; break;
               case "마운틴듀0.4L" : num[4][2] = 1; break;
               case "콜라1.5L" : num[4][3] = 1; break;
               case "사이다1.5L" : num[4][4] = 1; break;
               case "마운틴듀1.5L" : num[4][5] = 1; break;
              
               
               }
            model.data.remove(tbl.getSelectedRow());
            tbl.setModel(model);
            model.fireTableDataChanged();
            rownum--;
            
           

            
            int sum = 0;
            for (int j = 0; j < rownum; j++) {
               sum = sum + Integer.parseInt(String.valueOf(tbl.getValueAt(j, 1)))
                     * Integer.parseInt(String.valueOf(tbl.getValueAt(j, 2)));
            }
            tfPrice.setText(String.valueOf(sum));
            
            
            }else {
               number = Integer.parseInt(String.valueOf(tbl.getValueAt(tbl.getSelectedRow(), 2)));
               number--;
               tbl.setValueAt(String.valueOf(number), tbl.getSelectedRow(),2);
               int sum = 0;
               for (int j = 0; j < rownum; j++) {
                  sum = sum + Integer.parseInt(String.valueOf(tbl.getValueAt(j, 1)))
                        * Integer.parseInt(String.valueOf(tbl.getValueAt(j, 2)));
               }
               tfPrice.setText(String.valueOf(sum));
            }

         }

      }

      @Override
      public void mousePressed(MouseEvent e) {
         // TODO Auto-generated method stub

      }

      @Override
      public void mouseReleased(MouseEvent e) {
         // TODO Auto-generated method stub

      }

      @Override
      public void mouseEntered(MouseEvent e) {
         // TODO Auto-generated method stub

      }

      @Override
      public void mouseExited(MouseEvent e) {
         // TODO Auto-generated method stub

      }
   }

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}

  

}

