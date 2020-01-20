package array;

public class MainTest {
	//자바 컴파일(java -> class) : javac MainTest.java
	//자바 실행 : java MainTest
	public static void main(String[] str) {
	 try {
		for(int i=0;i<=10;i++)
		{
			System.out.println(str[i]);
		}
		return;
	 }catch(ArrayIndexOutOfBoundsException ex){
		 System.out.println("예외발생: " + ex.getMessage());
		 
	 }
	 catch(Exception ex) {
		 System.out.println("예외발생:" + ex.getMessage());
	 }finally {
		 System.out.println("무조건수행");
	 }
		System.out.println("프로그램 정상 종료");

	}

}


