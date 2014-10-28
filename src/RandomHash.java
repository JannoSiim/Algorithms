
public class RandomHash {

	private int p;
	private int arrayLen;
	private int a;

	private int b;
	
	public RandomHash(int p, int arrayLen) {
		this.p = p;
		this.arrayLen = arrayLen;
		//a = Misc.randomNrFromRange(1, p-1);
		//b = Misc.randomNrFromRange(0, p-1);
		a = 1000;
		b = 244224;
	}
	
	public int getHash(int value){
		return ((a*value + b) % p) % arrayLen;
	}
	
	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}
}
