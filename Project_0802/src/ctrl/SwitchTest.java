package ctrl;

public class SwitchTest {

	public static void main(String[] args) {
		int i = 1;
		int j = 0;
		
		switch(i) {
		
		case 2 : j += 8;
		break;
		case 4 : j += 1;
		break;
		default : j += 5;
		case 6 : j += 3;
		break;
		
		
		}//12
		System.out.println("J = " + j);
	}

}
