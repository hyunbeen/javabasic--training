package io;

import java.io.File;

public class FileTest {

	public static void main(String[] args) {
		File f = new File("c:\\temp");
		if(f.exists()) {
//			File temp = new File("c:\\\\temp\\imsi");
//			temp.mkdir();
			File temp = new File("c:\\temp\\a.txt");
			temp.delete();
		}
	}

}
