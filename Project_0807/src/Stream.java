import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Stream {

	public static void main(String[] args) throws Exception{
		  // TODO Auto-generated method stub
		  //FileInputStream
		  FileInputStream fis = new FileInputStream("C:/webtest/3.java/readme.txt");
		  
		  //파일을 만들어서 출력 -> FileOutputStream
		  FileOutputStream fos = new FileOutputStream("C:/webtest/3.java/readme2.txt");
		  
		  int read = 0; //파일을 읽어들여서 임시로 저장
		  //파일을 다 읽고 scroll 끝에 가면 -1을 retrun 해 준다.
		  
		  while(true) {
		   read = fis.read(); // file load
		   if(read == -1) {
		    break;
		   }
		   //System.out.write(read); // load한 데이터를 메모리에 출력
		   fos.write(read); // 출력을 fos 에 출력
		   
		  }
		  
		 }
		


}
