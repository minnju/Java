package cse3040_hw1_20171702;
import java.util.Scanner;

public class Problem3 {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter a text: ");
		String text="";
		text=scan.nextLine();
		System.out.println("Enter a letter: ");
		String letter=scan.next();
		int len=text.length();
		int answer=0;
		if(len>1) {
			System.out.println("You must enter a single letter.");
			return;
		}
		for(int i=0;i<len;i++) {
			if (text.toCharArray()[i]==letter.toCharArray()[0]) {
				answer++;
			}
		}
		System.out.println("There are "+answer+" "+letter+"'s in the text.");
		return;
	}
}
