package basic;

public class StringTest {

	public static void main(String[] args) {
		//���ڿ� ���� Ŭ���� : String StringBuffer StringBuilder
		StringBuffer sb = new StringBuffer("�ȳ��ϼ���");
		StringBuffer sb2 = new StringBuffer("�ȳ��ϼ���");
		
		if(sb.equals(sb2))
		{
			//������ �´ٸ�
			System.out.println("����");
		}else {
			//������ �ƴϸ� 	
		}
		
		String sb3 = "�ȳ��ϼ���";
		String sb4 = "�ȳ��ϼ���";
		
		sb3 = sb3 + "����";
		if(sb3.equals(sb4))
		{
			System.out.println("����");
		}
		// == : �ּҺ�
	}

}
