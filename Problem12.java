package cse3040_mp1_20171702;

class SubsequenceChecker{
	private static String[] array_txt;
	private static String[] array_word;
	private static int[] location;
	private static int num=0;
	public static void check(String txt,String word) {
		array_txt=txt.split("");
		array_word=word.split("");
		location=new int[array_word.length];
		num=0;
		for (int i=0;i<array_txt.length;i++) {
			if(num>=array_word.length) {
				break;
			}
			//System.out.println("arraytxt: "+array_txt[i]+" word: "+array_word[num]);
			if(array_txt[i].equals(array_word[num])) {
				//System.out.println("Enter");
				location[num]=i;
				num=num+1;
			}
		}
		if (num==array_word.length) {
			//System.out.println("num"+num);
			System.out.println(word+" is a subsequence of "+txt);
			for (int i=0;i<array_word.length;i++) {
				System.out.print(location[i]+" ");
			}
			System.out.print("\n");
		}
		else {
			//System.out.println("num"+num);
			System.out.println(word+" is not a subsequence of "+txt);
		}
	}
}
public class Problem12 {
	public static void main(String[] args) {
		SubsequenceChecker.check("supercalifragilisticexpialidocious", "pads");
		SubsequenceChecker.check("supercalifragilisticexpialidocious", "padsx");
		}
}
