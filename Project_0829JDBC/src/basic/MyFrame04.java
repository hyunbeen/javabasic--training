package basic;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

class GraphicObject {
 BufferedImage img = null;
 int x = 0, y = 0;

 public GraphicObject(String fileName) {
  try {
   img = ImageIO.read(new File(fileName));
  } catch (IOException e) {
   System.out.println(e.getMessage()); // 예외 에러메시지 출력
   System.exit(0); // 프로그램 종료
  } // try catch
 }// 생성자 오버로딩

 public void draw(Graphics g) {
  g.drawImage(img, x, y, null);
 }// draw() 이미지를 그리고자

 public void keyPressed(KeyEvent e) {
 } // 키 이벤트 처리

 public void update() {
 } // 위치변경
}// GraphicObject class
 // 미사일 클래스

class Missile extends GraphicObject {
 boolean launched = false;

 public Missile(String name) {
  super(name); // 자손클래스에서 조상의 오버로딩된 생성자 호출
  y = -200;
 }// 생성자 오버로딩

 public void keyPressed(KeyEvent event, int x, int y) {
  if (event.getKeyCode() == KeyEvent.VK_SPACE) { // VK_SPACE는 스페이스바. 를 누르면
   // 미사일 발사.
   launched = true;
   this.x = x;
   this.y = y;
  } // if
 }// 키 이벤트

 @Override
 public void update() {
  if (launched)
   y -= 50;
  if (y < -100)
   launched = false;
 }
}// Missile class
 // 비행접시 클래스

class Enemy extends GraphicObject {
 int dx = -10;

 public Enemy(String name) {
  super(name);
  x = 500;
  y = 0;
 }// Enemy

 @Override
 public void update() {
  x += dx;
  if (x < 0)
   dx = +10;
  if (x > 500)
   dx = -10;
 }// update() 스레드에 의해서 비행접시 좌우 위치변경

}// Enemy class

// 우주선클래스
class SpaceShip extends GraphicObject {
 public SpaceShip(String name) {
  super(name);
  x = 150;
  y = 350;
 }// 생성자 오버로딩

 @Override
 public void keyPressed(KeyEvent event) {
  if (event.getKeyCode() == KeyEvent.VK_LEFT) {
   x -= 10;
  }
  if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
   x += 10;
  }
  if (event.getKeyCode() == KeyEvent.VK_UP) {
   y -= 10;
  }
  if (event.getKeyCode() == KeyEvent.VK_DOWN) {
   y += 10;
  }
 }// 키보드를 아래로 눌렀을때 이벤트 처리

}// SpaceShip class 스레드와 관련없고, 방향키에 의해서 상하좌우 이동

// Mypanel12 클래스
class MyPanel12 extends JPanel implements KeyListener {

 Enemy enemy;
 SpaceShip spaceship;
 Missile missile;

 public MyPanel12() {
  super();
  this.addKeyListener(this);
  this.requestFocus();
  setFocusable(true);

  enemy = new Enemy("enemy.png");
  spaceship = new SpaceShip("spaceship.png");
  missile = new Missile("missile.png");

  class MyThread extends Thread {

   @Override
   public void run() {
    while (true) {
     enemy.update();
     spaceship.update();
     missile.update();
     repaint();
     try {
      Thread.sleep(50);
     } catch (InterruptedException e) {

     } // try catch
    } // while
   }// run()
  }// MyThread class
  Thread t = new MyThread();
  t.start();
 } // 생성자

 public void paint(Graphics g) {
  super.paint(g);
  enemy.draw(g);
  spaceship.draw(g);
  missile.draw(g);
 }

 @Override
 public void keyPressed(KeyEvent event) {
  spaceship.keyPressed(event);
  missile.keyPressed(event, spaceship.x, spaceship.y);
 }

 @Override
 public void keyReleased(KeyEvent e) {
 }

 @Override
 public void keyTyped(KeyEvent e) {
 }

}// Mypanel12

public class MyFrame04 extends JFrame {
 public MyFrame04() {
  setTitle("My Game");
  add(new MyPanel12());
  setSize(500, 500);
  setVisible(true);
 }// 생성자

 public static void main(String[] args) {
  new MyFrame04();
 }// main
}