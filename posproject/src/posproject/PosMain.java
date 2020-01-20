package posproject;

public class PosMain {
/*
 * pos기기의 main
 * 수고하신 분들
 * 이아름 : 디자인 및 함수 이벤트 추가
 * 이현빈 : 데이터 관리 및 함수 이벤트추가
 * 정성현 : UI 및 데이터 입력
 */
	public static void main(String[] args) {
		PosLayout pm = new PosLayout();
		pm.TimeView();
		pm.setUI();
		pm.evtproc();
		
		
		

	}

}
