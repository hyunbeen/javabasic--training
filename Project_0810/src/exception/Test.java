package exception;

public class Test {

	public static void main(String[] args) {
		try {
		method();
		}catch(MyException ex) {
			System.out.println("����=>"+ex.getMessage());
		}

	}
	
	static void method() throws MyException {
		/*
		 * ���ܰ� �߻��Ѵٸ� -> ����ó��
		 * (1) try~catch ����
		 * (2) �޼ҵ� �ڿ� throws ����
		 */
		throw new MyException();
	}

}

class MyException extends Exception	{
	public String getMessage() {
		return "�츮�� ������ ���ܹ߻�";
	}
}