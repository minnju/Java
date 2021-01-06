package cse3040_mp1_20171702;

class PalindromeChecker{
	private static String[] txt_arr;
	private static String int_num;
	private static int str_len;
	public static void check(String txt) {	
		txt_arr=txt.split("");
		str_len=txt_arr.length;
		for(int i=0;i<str_len/2;i++) {
			if(!txt_arr[i].equals(txt_arr[str_len-i-1])) {
				System.out.println(txt+" is not a palindrome.");
				return;
			}
		}
		System.out.println(txt+" is a palindrome.");
	}
	public static void check(int a) {	
		int_num=Integer.toString(a);
		txt_arr=int_num.split("");
		str_len=txt_arr.length;
		for(int i=0;i<str_len/2;i++) {
			if(!txt_arr[i].equals(txt_arr[str_len-i-1])) {
				System.out.println(a+" is not a palindrome.");
				return;
			}
		}
		System.out.println(a+" is a palindrome.");
		
	}
}
public class Problem11 {
	public static void main(String[] args) { 
		PalindromeChecker.check("abcde"); 
		PalindromeChecker.check("abcba"); 
		PalindromeChecker.check(1234);
		PalindromeChecker.check(12321); 
		}

}
