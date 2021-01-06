package cse3040_mp1_20171702;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.util.*

;class MyFileReader{
	private static File fp;
	private static FileReader fr;
	private static BufferedReader br;
	private static String line;
	private static String[] buff;
	public static Integer freq;
	public static String small_word;
	public static String key;
	public static ArrayList<String> set=new ArrayList<>();
	private static Map<String,Integer> words=new HashMap<String,Integer>();
	public static boolean readDataFromFile(String file_n,ArrayList<Item> list) {
		try {
			fp=new File(file_n);
			fr=new FileReader(fp);
			br=new BufferedReader(fr);
			while(true) {
				line=br.readLine();
				if(line==null) {
					br.close();
					fr.close();
					break;
				}
				buff=line.split("\s");
				for(String elem:buff) {
					small_word=elem.toLowerCase();
					if(!set.contains(small_word))
						set.add(small_word);
					freq=words.get(small_word);
					if(freq==null)words.put(small_word,1);
					else words.put(small_word,freq+1);
				}
			}
			
			//System.out.println("set: "+set);
			for(String elem: set) {
				//System.out.println("key: "+key+" cnt: "+words.get(key));
				list.add(new Item(elem,words.get(elem)));
			}
			
		}catch(FileNotFoundException e) {
			return false;
		}catch(IOException e) {
			return false;
		}
		return true;
		
	}
}
class Item{
	private String Item;
	private Integer num;
	Item(String word,Integer cnt){
		Item=word;
		num=cnt;
	}
	public String toString() {
		return Item+" "+num;
	}
}

public class Problem15 {
	public static void main(String[] args) {
		ArrayList<Item> list = new ArrayList<>();
		boolean rv = MyFileReader.readDataFromFile("input_prob15.txt", list);
		if(rv == false) {
			System.out.println("Input file not found.");
			return; 
		} 
		for(Item it: list) System.out.println(it); 
		}
}
