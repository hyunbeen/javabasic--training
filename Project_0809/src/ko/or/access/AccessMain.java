package ko.or.access;

import ko.or.kosta.Access;

public class AccessMain extends Access {

	public static void main(String[] args) {
		AccessMain acc = new AccessMain();
		//acc.a = "프라이빗 변경";
		//acc.b = "디폴트 변경";
		acc.c = "프로텍티드 변경";
		acc.d = "퍼블릭변경";
		acc.output();


	}

}
