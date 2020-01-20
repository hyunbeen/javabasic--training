package arraylisg;
import java.util.*;
public class TreeSetTest {

	public static void main(String[] args) {
		TreeSet<String> set = new TreeSet<String>();
		set.add("elephant");
		set.add("cat");
		set.add("dog");
		set.add("bee");
		set.add("tiger");
		set.add("okja");
		set.add("lion");
		set.add("eagle");
		set.add("bear");
		set.add("ant");
		System.out.println(set);
		System.out.println(set.subSet("b","o"));
		System.out.println(set.headSet("e"));
		System.out.println(set.tailSet("e"));
	}

}
