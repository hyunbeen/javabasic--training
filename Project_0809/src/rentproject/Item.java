package rentproject;

abstract public class Item {
	
	protected String num; 
	protected String title; //공통적으로 아이템들에 들어가는 값들
	public Item() {
		this("알수없음","알수없음");
		
		System.out.println("Item 기본생성");
	}
	public Item(String num,String title) {
		this.num = num;
		this.title = title;
		System.out.println("Item 인자생성");
	}
	public abstract void output() ;
	
	
}

