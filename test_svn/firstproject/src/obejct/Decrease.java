package obejct;

public class Decrease {
	String decName;	//할인명
	int decRate;			//할인율
	
	public Decrease() {
	}
	
	public Decrease(String decName, int decRate) {
		this.decName = decName;
		this.decRate = decRate;
	}
	public String getDecName() {
		return decName;
	}

	public void setDecName(String decName) {
		this.decName = decName;
	}

	public int getDecRate() {
		return decRate;
	}

	public void setDecRate(int decRate) {
		this.decRate = decRate;
	}


}	
