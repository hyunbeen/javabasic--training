package arraylisg;
import java.util.*;

public class HashMapTest {

	public static void main(String[] args) {
		HashMap<String,String> map = new HashMap<String,String>();//HashTable
		map.put("javassem","1111");
		map.put("���ڹ�","9999");
		map.put("javababo","9999");
		map.put("javassem2","1234");
		map.put("������", "5678");
		Scanner input = new Scanner(System.in);
		while(true) {
			System.out.println("���̵�� �н����带 �Է��ϼ���");
			System.out.println("���̵�->");
			String id = input.nextLine();
			
			System.out.println();
			System.out.println("�н�����->");
			String pass = input.nextLine();
			
			//�Է¹��� id�� �ʿ� Ű���� �ش� �Ǵ���
			if(map.containsKey(id)){
				if(pass.equals(map.get(id)) ) {
				System.out.println("�α��� ����");
				break;
			}else {
				System.out.println("�н����尡 Ʋ�Ƚ��ϴ�");
				continue;
			}
				}else {
				System.out.println("�������� �ʴ� ���̵� �Դϴ�.");
				continue;
			}
		
		}

	}
}
