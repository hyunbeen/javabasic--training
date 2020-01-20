package arraylisg;
import java.util.*;

public class HashMapTest {

	public static void main(String[] args) {
		HashMap<String,String> map = new HashMap<String,String>();//HashTable
		map.put("javassem","1111");
		map.put("김자바","9999");
		map.put("javababo","9999");
		map.put("javassem2","1234");
		map.put("이현빈", "5678");
		Scanner input = new Scanner(System.in);
		while(true) {
			System.out.println("아이디와 패스워드를 입력하세요");
			System.out.println("아이디->");
			String id = input.nextLine();
			
			System.out.println();
			System.out.println("패스워드->");
			String pass = input.nextLine();
			
			//입력받은 id가 맵에 키값이 해당 되는지
			if(map.containsKey(id)){
				if(pass.equals(map.get(id)) ) {
				System.out.println("로그인 성공");
				break;
			}else {
				System.out.println("패스워드가 틀렸습니다");
				continue;
			}
				}else {
				System.out.println("존재하지 않는 아이디 입니다.");
				continue;
			}
		
		}

	}
}
