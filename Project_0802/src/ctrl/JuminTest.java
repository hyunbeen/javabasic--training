package ctrl;

public class JuminTest {

	public static void main(String[] args) {
		String id = "000101-3234567";
		char sung = id.charAt(7);
		if(sung=='1'||sung=='3'||sung=='9')
		{
			System.out.println("�����Դϴ�");
		}
		else if(sung=='2'||sung=='4'||sung=='0')
		{
			System.out.println("�����Դϴ�");
		}
		int year = 0;
		if(sung=='1'||sung=='2')
		year = 1900;
		else if(sung=='3'||sung=='4')
		year = 2000;
		else if(sung=='9'||sung=='0')
		year = 1800;
		char home = id.charAt(8); 
		switch(home)
		{
			case  '0' :
				System.out.println("����");
				break;
		
			case  '1' :
				System.out.println("�λ�,��õ");
				break;
			case  '2' :
				System.out.println("���");
				break;
		
			case  '9' :
				System.out.println("����");
				break;
			
			default : 
				System.out.println("??");
				break;
		}
		String old = id.substring(0, 2); //�����ͱ����� ���´�
		System.out.println(old);
		
		int cyear = (int)(System.currentTimeMillis()/1000/60/60/24/365+1970);
		int age = cyear - (year + Integer.parseInt(old))+1;
		System.out.println(age);
	}

}
