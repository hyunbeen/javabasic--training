package array;

import java.util.Calendar;

public class CalendarTest {
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);// 상수: 변하지 않는 변수
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DATE);
		int yoil = c.get(Calendar.DAY_OF_WEEK);
		String []yoilArr = {"토요일","월요일","화요일","수요일","목요일","금요일"};
		System.out.println(year+"년 "+month+"월 "+day+"일 "+yoilArr[yoil]);
	}
	
}
