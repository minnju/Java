package cse3040_mp1_20171702;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;

class Text{
	public static int[] alpha= new int[26];
	private static File fp;
	private static FileReader fr;
	private static BufferedReader br;
	private static String line;
	private static char[] str_arr;
	public int countChar(char a) {
		return alpha[(int)a-97];
	}
	public boolean readTextFromFile(String str) {
		try {
			fp=new File(str);
			fr=new FileReader(fp);
			br=new BufferedReader(fr);
			while(true) {
				line=br.readLine();
				if(line==null) {
					br.close();
					fr.close();
					break;
				}
				str_arr=line.toCharArray();
				//System.out.println(str_arr);
				for(char a:str_arr) {
					if((int)a>=65 &&(int)a<=90) {
						alpha[(int)a-65]++;
					}
					else if((int)a>=97 &&(int)a<=122) {
						alpha[(int)a-97]++;
					}
					
				}
			}
			
		}catch (FileNotFoundException e) {
			System.out.println("Input file not found.");
			return false;
		}catch (IOException e) {
			return false;
		}
	return true;
	}
}

public class Problem13 {
	public static void main(String[] args) {
		Text t = new Text();
		if(t.readTextFromFile("input_prob13.txt")) { 
			for(char c = 'a'; c <= 'z'; c++) { 
				System.out.println(c + ": " + t.countChar(c)); 
			} 
		} 
	}
}
