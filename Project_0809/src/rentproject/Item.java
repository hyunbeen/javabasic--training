package rentproject;

abstract public class Item {
	
	protected String num; 
	protected String title; //���������� �����۵鿡 ���� ����
	public Item() {
		this("�˼�����","�˼�����");
		
		System.out.println("Item �⺻����");
	}
	public Item(String num,String title) {
		this.num = num;
		this.title = title;
		System.out.println("Item ���ڻ���");
	}
	public abstract void output() ;
	
	
}

