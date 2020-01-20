package ko.or.kosta;
/*
 * 접근지정자
 * private : 그 클래스만 접근
 * (default) : 그 패키지에만 접근
 * protected : 다른 패키지에서 상속관계인 경우에 접근
 * 				(동일 패키지라면 접근)
 * public : 모든 접근 허용
 */
public class Access {
	private String a = "프라이빗";
	String b = "디폴트";
	protected String c = "프로텍티드";
	public String d = "퍼블릭";
	public void output() {
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
	}
}

