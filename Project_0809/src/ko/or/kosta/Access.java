package ko.or.kosta;
/*
 * ����������
 * private : �� Ŭ������ ����
 * (default) : �� ��Ű������ ����
 * protected : �ٸ� ��Ű������ ��Ӱ����� ��쿡 ����
 * 				(���� ��Ű����� ����)
 * public : ��� ���� ���
 */
public class Access {
	private String a = "�����̺�";
	String b = "����Ʈ";
	protected String c = "������Ƽ��";
	public String d = "�ۺ�";
	public void output() {
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
	}
}

