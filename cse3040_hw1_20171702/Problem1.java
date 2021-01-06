package cse3040_hw1_20171702;
import java.util.Scanner;
public class Problem1 {
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			//System.out.println("Hello Java World");
			Scanner scan=new Scanner(System.in);
			int len=0;
			String alphabet="";
			System.out.println("ASCII code teller. Enter a letter: ");
			alphabet=scan.nextLine();
			len=alphabet.length();
			if (len==1){
				int num=(int)alphabet.toCharArray()[0];
				if ((num>=65 && num<=90)||(num>=97 && num<=122)) {
				System.out.println("The ASCII code of "+alphabet+" is "+num+"." );
				return;
				}
				else {
					System.out.println("You must input a single uppercase or lowercase alphabet.");
					return;
				}
			}
			else {
				System.out.println("You must input a single uppercase or lowercase alphabet.");
				return;
			}
			//cSystem.out.println("입력받은 문자: "+ alphabet+"문자열 길이: "+len);
			//System.out.println("The ASCII code of "+alphabet+" is "+num );
		}
}
