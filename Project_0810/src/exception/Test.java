package exception;

public class Test {

	public static void main(String[] args) {
		try {
		method();
		}catch(MyException ex) {
			System.out.println("예외=>"+ex.getMessage());
		}

	}
	
	static void method() throws MyException {
		/*
		 * 예외가 발생한다면 -> 예외처리
		 * (1) try~catch 구문
		 * (2) 메소드 뒤에 throws 예외
		 */
		throw new MyException();
	}

}

class MyException extends Exception	{
	public String getMessage() {
		return "우리가 정의한 예외발생";
	}
}