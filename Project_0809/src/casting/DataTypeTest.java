package casting;

public class DataTypeTest {
	public static void main(String[] args) {
		Object[] data = method();
			for(int i=0;i<data.length;i++){
				System.out.println(data[i]);
			}
	}
	static Object[] method() {
		String a = "¾È³ç";
		int b=100;
		double c =33.3;
		Object[] data = new Object[3];
		data[0] = a;
		data[1] = new Integer(b);
		data[2] = new Double(c);
		return data;
	}
}
