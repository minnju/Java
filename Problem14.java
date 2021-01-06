package cse3040_mp1_20171702;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;

class ItemReader{
	private static File fp;
	private static FileReader fr;
	private static BufferedReader br;
	private static String fruit_n;
	private static double fruit_p;
	private static String line;
	private static String[] fruit_arr;
	public static boolean fileToBox(String file_n, FruitBox<Fruit> box){
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
				box.add(new Fruit(fruit_n,fruit_p));
				//System.out.println("Fruit_name: "+fruit_n+" Fruit_price: "+fruit_p);
			}
		}catch(FileNotFoundException e) {
			System.out.println("Input file not found.");
			return false;
		}catch(IOException e) {
			return false;
		}
		
		return true;
	}
}

class FruitBox<T extends Fruit> {
	private static int num=0;
	private double max=0;
	private double min=0;
	private static String max_i="";
	private static String min_i="";
	private static double avg=0;
	private String fruit_name;
	private double fruit_price;
	private static double sum=0;
	private static ArrayList<Fruit> list =new ArrayList<>();
	//private static FruitBox<Fruit> list = new FruitBox<>();
	void add(Fruit one) {
		fruit_name=one.fr_n();
		fruit_price=one.fr_p();
		System.out.println(fruit_name+" "+fruit_price);
		list.add(one);
		num++;
	}
	public int getNumItems() {
		return list.size();
	}
	public String getMaxItem() {
		//for(Fruit it: list) System.out.println(it.fr_n()); 
		max=list.get(0).fr_p();
		for(int i=0;i<list.size();i++) {
			if(max<list.get(i).fr_p()) {
				max=list.get(i).fr_p();
				max_i=list.get(i).fr_n();
			}
		}
		return max_i;
	}
	public double getMaxPrice() {
		
		return max;
	}
	public String getMinItem() {
		min=list.get(0).fr_p();
		for(int i=0;i<list.size();i++) {
			if(min>list.get(i).fr_p()) {
				min=list.get(i).fr_p();
				min_i=list.get(i).fr_n();
			}
		}
		return min_i;
		
	}
	public double getMinPrice() {
		return min;
	}
	public double getAvgPrice() {
		
		for(Fruit i: list) {
			sum+=i.fr_p();
		}
		avg=sum/list.size();
		return avg;
	}
	
}



class Fruit{
	private String fr_name="";
	private double fr_price=0;
	Fruit(String str, double price) {
		fr_name=str;
		fr_price=price;
	}
	public String fr_n() {
		return fr_name;
	}
	public double fr_p() {
		return fr_price;
	}
}
public class Problem14 {
	public static void main(String[] args) { 
		FruitBox<Fruit> box = new FruitBox<>();
		boolean rv = ItemReader.fileToBox("input_prob14.txt", box); 
		if(rv == false) return; 
		box.add(new Fruit("orange", 9.99)); 
		System.out.println("----------------");
		System.out.println(" Summary");
		System.out.println("----------------");
		System.out.println("number of items: " + box.getNumItems());
		System.out.println("most expensive item: " + box.getMaxItem() + " (" +
							box.getMaxPrice() + ")");
		System.out.println("cheapest item: " + box.getMinItem() + " (" +
							box.getMinPrice() + ")"); 
		System.out.printf("average price of items: %.2f", box.getAvgPrice());
		}
}
