package project;

import java.util.Scanner;

public class project1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("������ �Է� �Ͻÿ� : ");
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		int num3 = sc.nextInt();
		System.out.println(((num1>num2)?(num1>num3)?num1:num3:(num2>num3)?num2:num3));
		
	}

}
