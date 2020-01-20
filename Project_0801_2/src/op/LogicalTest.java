package op;

public class LogicalTest {
	public static void main(String[] args){
		int 성적 = 86;
		char 태도 = 'A';
		
		if(성적>=90 || 태도=='A') {
			System.out.println("우수상 대상");
		}//둘중 하나만
		if(성적>=90 && 태도=='A') {
			System.out.println("우등상 대상");
		}//둘다
	}
	
}