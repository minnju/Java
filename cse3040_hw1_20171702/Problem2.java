package cse3040_hw1_20171702;
import java.util.Scanner;
import java.lang.Math;
public class Problem2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("Hello Java World");
		Scanner scan=new Scanner(System.in);
		int num=(int)(Math.random()*100)+1;
		int guess=0;
		int turn=0;
		int low=1;
		int high=100;
		while(guess!=num) {
			turn++;
			System.out.println("["+turn+"] Guess a number ("+low+"-"+high+"):");
			guess=scan.nextInt();
			if (guess==num) {
				System.out.println("Correct! Number of guesses: "+num);
				return;
			}
			else if(guess<num) {
				System.out.println("Too small!");
				low=guess+1;
			}
			else if(guess>num) {
				System.out.println("Too large!");
				high=guess-1;
			}
		}
		
		
	}
}
