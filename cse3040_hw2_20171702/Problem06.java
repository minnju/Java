package cse3040_hw2_20171702;
interface IntSequence {
	boolean hasNext();
	int next();
}
class FibonacciSequence implements IntSequence{
	private static int  i1=0;
	private static int  i2=1;
	private static int num=0;
	private int i3=0;
	public boolean hasNext() {
		return true;
	}
	public int next() {
		if (num==0) {
			num++;
			return i1;
		}
		if (num==1) {
			num++;
			return i2;
		}
		num++;
		int temp1=i1;
		int temp2=i2;
		i3=temp1+temp2;
		i1=i2;
		i2=i3;
		return i3;
	}
}
public class Problem06 {
	public static void main(String[] args) { 
		IntSequence seq = new FibonacciSequence();
		for(int i=0; i<20; i++) {
			if(seq.hasNext() == false) break;
			System.out.print(seq.next() + " ");
		} 
		System.out.println(" "); 
	}

}
