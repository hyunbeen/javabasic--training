package array;

import java.util.Calendar;

public class CalendarTest {
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);// ���: ������ �ʴ� ����
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DATE);
		int yoil = c.get(Calendar.DAY_OF_WEEK);
		String []yoilArr = {"�����","������","ȭ����","������","�����","�ݿ���"};
		System.out.println(year+"�� "+month+"�� "+day+"�� "+yoilArr[yoil]);
	}
	
}
