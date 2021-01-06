package cse3040_mp2_20171702;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


class ElementReader{
	private static File fp;
	private static FileReader fr;
	private static BufferedReader br;
	private static String fruit_n;
	private static double fruit_p;
	private static String line;
	private static String[] fruit_arr;
	private static ArrayList<Element> list =new ArrayList<>();
	public static ArrayList<Element> readElements(String file_n){
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
				fruit_arr=line.split("\s");
				fruit_n=fruit_arr[0];
				fruit_p=Double.parseDouble(fruit_arr[1]);
				list.add(new Element(fruit_n,fruit_p));
				//System.out.println("Fruit_name: "+fruit_n+" Fruit_price: "+fruit_p);
			}
		}catch(FileNotFoundException e) {
			//System.out.println("Input file not found.");
			return null;
		}catch(IOException e) {
			return null;
		}
		return list;
	}
}
class Element implements Comparable<Element>{
	private String Item;
	private double num;
	public Element(String word,double cnt){
		this.Item=word;
		this.num=cnt;
	}
	public int compareTo(Element elem) {
		if (this.num==elem.num) {
			return(this.Item.compareTo(elem.Item));	
		}else if(this.num<elem.num) {
			return -1;
		}else {
			return 1; 
		}
	}
	public String toString() {
		return Item+" "+num;
	}
	
}
public class Problem16 {
	public static void main(String args[]) {
		ArrayList<Element> list = ElementReader.readElements("input.txt");
		if(list == null) { 
			System.out.println("Input file not found.");
			return;
			} 
			Collections.sort(list);
			Iterator<Element> it = list.iterator();
			while(it.hasNext()) System.out.println(it.next());
			}

}
