package cse3040_hw2_20171702;
import java.util.Scanner;

interface IntSequenceStr {
	boolean hasNext();
	int next();
}
class BinarySequenceStr implements IntSequenceStr{
	public static int  i=0;
	private static int number;
	private static int[] arr=new int[100000];
	public BinarySequenceStr(int n) {
		number=n;
		while(number>0) {
			arr[i]=number%2;
			number=number/2;
			i++;
		}
	}
	public boolean hasNext() {
		return i!=0;
	}
	public int next() {
		i--;
		return arr[i];
	}
}
public class Problem07 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a positive integer: ");
		String str = in.nextLine();
		int num = Integer.parseInt(str);
		in.close();
		System.out.println("Integer: " + num);
		IntSequenceStr seq = new BinarySequenceStr(num);
		System.out.print("Binary number: ");
		while(seq.hasNext()) System.out.print(seq.next());
		System.out.println(" ");

	}

}
