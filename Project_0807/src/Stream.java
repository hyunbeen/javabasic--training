import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Stream {

	public static void main(String[] args) throws Exception{
		  // TODO Auto-generated method stub
		  //FileInputStream
		  FileInputStream fis = new FileInputStream("C:/webtest/3.java/readme.txt");
		  
		  //������ ���� ��� -> FileOutputStream
		  FileOutputStream fos = new FileOutputStream("C:/webtest/3.java/readme2.txt");
		  
		  int read = 0; //������ �о�鿩�� �ӽ÷� ����
		  //������ �� �а� scroll ���� ���� -1�� retrun �� �ش�.
		  
		  while(true) {
		   read = fis.read(); // file load
		   if(read == -1) {
		    break;
		   }
		   //System.out.write(read); // load�� �����͸� �޸𸮿� ���
		   fos.write(read); // ����� fos �� ���
		   
		  }
		  
		 }
		


}
