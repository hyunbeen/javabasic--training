package array;

public class MainTest {
	//�ڹ� ������(java -> class) : javac MainTest.java
	//�ڹ� ���� : java MainTest
	public static void main(String[] str) {
	 try {
		for(int i=0;i<=10;i++)
		{
			System.out.println(str[i]);
		}
		return;
	 }catch(ArrayIndexOutOfBoundsException ex){
		 System.out.println("���ܹ߻�: " + ex.getMessage());
		 
	 }
	 catch(Exception ex) {
		 System.out.println("���ܹ߻�:" + ex.getMessage());
	 }finally {
		 System.out.println("�����Ǽ���");
	 }
		System.out.println("���α׷� ���� ����");

	}

}


