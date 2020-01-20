package process;

import java.io.IOException;

public class ProcessTest {
	public static void main(String[] args) {
		
		try {
		Runtime rt = Runtime.getRuntime();
		rt.exec("C:\\Program Files\\Internet Explorer\\iexplore.exe");
		}
		catch(IOException ex) {
			System.out.println("예외발생");
		}
	}
}
