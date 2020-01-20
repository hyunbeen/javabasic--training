package uml;

import java.util.ArrayList;
import java.util.Iterator;

public class Test {

	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add(new String("점심"));
		list.add(new String("뭐"));
		list.add(new String("드실"));
		list.add(new String("?????"));
		//출력 1
		for(int i=0;i<list.size();i++)
		{
			String temp = (String)list.get(i);
			System.out.println(temp);
		}
		//출력 2 속도가 빠르고 목록만 가지고 온다
		Iterator it = list.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		//
		
		

	}

}
