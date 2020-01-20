package array;

public class StarTest {

	public static void main(String[] args) {
		char [][] star = new char[26][];
		for(int i=0;i<26;i++)
		{
			star[i] = new char[i+1];
		}
		char ch = 'A';
		for(int j=0;j<26;j++) {
			for(int i=0;i<=j;i++) {
				star[j][i] = ch;
				ch++;
			}
			ch ='A';
		}
		
		for(int j=0;j<26;j++) {
			for(int i=0;i<=j;i++) {
				System.out.print(star[j][i]);
			}
			System.out.println();
		}
	

	}

}
