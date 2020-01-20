package arraylisg;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListTest {
	public static void main(String[] args) {
		ArrayList list = new ArrayList(4);
		list.add("rabbit");
		list.add("mouse");
		list.add("dog");
		list.add("cat");
		list.add("tiger");
		
		//System.out.println(list);
		for(int i=0;i<list.size();i++) {
			String temp = (String)list.get(i);
			System.out.println(temp);
		}
		
//		for(String temp:list)
//		{
//		System.out.println("["+temp+"]");	
//		}
		
		Collections.sort(list);
		System.out.println(list);
		list.set(3,"cow");
		System.out.println(list);
		list.remove(1);
		System.out.println(list);
	}
}
