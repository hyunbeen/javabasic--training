package arraylisg;
import java.util.*;
public class HashSetTest {
	public static void main(String[] args) {
		

		
		
		
		HashSet<Integer> set = new HashSet<Integer>();
		
		for(int i=0;set.size()!=6;i++) 
		set.add((int) (Math.random() * 45) + 1);
		
		List list = new ArrayList(set);
		Collections.sort(list);
		System.out.println(list);
	}
}
