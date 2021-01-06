package cse3040_hw1_20171702;
import java.util.Scanner;

public class Problem4 {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter a text: ");
		String text="";
		text=scan.nextLine();
		System.out.println("Enter a String: ");
		String string=scan.next();
		int len=text.length();
		int l_len=string.length();
		int answer=0;
		for(int i=0;i<len-l_len;i++) {
			String str1=text.substring(i,i+l_len);
			if(str1.equals(string)==true) {
				answer++;
			}
		}
		System.out.println("There are "+answer+" instances of "+string+".");
		return;
	}
}
