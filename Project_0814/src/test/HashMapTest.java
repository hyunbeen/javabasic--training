package test;
import java.util.*;

class HashMapTest 
{
	// ��ȭ��ȣ�� ������ �ɺ���
	HashMap<String,HashMap<String,String>> phoneBook = new HashMap<String,HashMap<String,String>>();


	/*
		��ȭ��ȣ ������ �Է�
	*/
	void insertPhoneNo()
	{
		addPhoneNo("����", "�ƺ�", "010-111-1111");
		addPhoneNo("����", "����", "010-111-1112");
		addPhoneNo("ģ��", "������", "010-333-0303");
		addPhoneNo("����", "�����", "010-999-9999");
		addPhoneNo("ģ��", "õ����", "010-333-3030");
		addPhoneNo("��Ÿ", "���۸���", "032-333-0000");
		addPhoneNo("����", "����", "010-111-1113");
		addPhoneNo("��Ÿ", "ġŲ��", "032-777-9292");
		addPhoneNo("ģ��", "�����", "010-888-8080");
	}
	
	/*
		��ȭ��ȣ�ο� ����
	*/
	void addPhoneNo( String groupName, String name, String tel )
	{
		//--------------------------------------------
		// 1. ��ȭ��ȣ Map�� �ش� �׷��̸��� ������ �߰�
		// 2. �Էµ� �׷��̸����� ����� HashMap�� ����
		// 3. �� HashMap�� ��ȭ��ȣ�� �̸��� ����
		//
		if(!phoneBook.containsKey(groupName)) {
			phoneBook.put(groupName,new HashMap<String,String>());
		}
		HashMap<String,String> group = (HashMap<String,String>)phoneBook.get(groupName);//group name�� hashmap�� �����´�
		group.put(name, tel);//group�� �ڷḦ �ִ´�
	}

	/*
		��ȭ��ȣ�� ���	
	*/
	void printPhoneNo()
	{
		Set set = phoneBook.entrySet();
		Iterator it = set.iterator();

		while( it.hasNext() )
		{
			Map.Entry e = (Map.Entry) it.next();
			
			Set subSet = ( (HashMap)e.getValue() ).entrySet() ;
			Iterator subIt = subSet.iterator();

			System.out.println(" # " + e.getKey() + " [" + subSet.size() +"]");

			while( subIt.hasNext() )
			{
				Map.Entry  subE = (Map.Entry)subIt.next();
				System.out.println( subE.getValue() + "  " + subE.getKey() );
			}
			System.out.println();
		}
	}

	public static void main(String[] args) 
	{
		HashMapTest  map = new HashMapTest();
		map.insertPhoneNo();
		map.printPhoneNo();
	}

}
