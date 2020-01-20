package op;

import java.util.Scanner;

public class SwitchTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("학번을 입력해 주세여 : ");
		String str = sc.nextLine();
		int year = Integer.parseInt(str.substring(0, 4));
		int univ = Integer.parseInt(str.substring(4, 5));
		int major = Integer.parseInt(str.substring(5, 7));
		String univStr = "";
		String majorStr = "";
		if (univ == 1) {
			univStr = "공대";
			switch (major) {
			case 11:
				majorStr = "컴퓨터학과";
				break;
			case 12:
				majorStr = "소프트웨어학과";
				break;
			case 13:
				majorStr = "모바일학과";
				break;
			case 14:
				majorStr = "자바학과";
				break;
			case 15:
				majorStr = "서버학과";
				break;

			}

		} else if (univ == 2) {
			majorStr = "사회대";
			if (major == 11) {
				majorStr = "사회학과";
			} else if (major == 12) {
				majorStr = "경영학과";
			} else if (major == 13) {
				majorStr = "경제";
			}
		}

		System.out.println("년도 : " + year);
		System.out.println("대학 : " + univStr);
		System.out.println("과 : " + majorStr);

	}
}
