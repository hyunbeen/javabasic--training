package op;

import java.util.Scanner;

public class SwitchTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("�й��� �Է��� �ּ��� : ");
		String str = sc.nextLine();
		int year = Integer.parseInt(str.substring(0, 4));
		int univ = Integer.parseInt(str.substring(4, 5));
		int major = Integer.parseInt(str.substring(5, 7));
		String univStr = "";
		String majorStr = "";
		if (univ == 1) {
			univStr = "����";
			switch (major) {
			case 11:
				majorStr = "��ǻ���а�";
				break;
			case 12:
				majorStr = "����Ʈ�����а�";
				break;
			case 13:
				majorStr = "������а�";
				break;
			case 14:
				majorStr = "�ڹ��а�";
				break;
			case 15:
				majorStr = "�����а�";
				break;

			}

		} else if (univ == 2) {
			majorStr = "��ȸ��";
			if (major == 11) {
				majorStr = "��ȸ�а�";
			} else if (major == 12) {
				majorStr = "�濵�а�";
			} else if (major == 13) {
				majorStr = "����";
			}
		}

		System.out.println("�⵵ : " + year);
		System.out.println("���� : " + univStr);
		System.out.println("�� : " + majorStr);

	}
}
