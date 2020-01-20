package op;

public class ForTest {

	public static void main(String[] args) {
		int osum = 0;
		int esum = 0;
		for(int i = 1;i<11;i+=2) {
		osum = osum+ i;
		esum = esum+ (i + 1);
		}
		System.out.println("È¦¼ö ÇÕ : "+osum+" Â¦¼ö ÇÕ : "+esum);
	}
}
