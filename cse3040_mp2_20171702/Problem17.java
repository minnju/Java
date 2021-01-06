package cse3040_mp2_20171702;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


class MapManager{
	private static File fp;
	private static FileReader fr;
	private static BufferedReader br;
	private static String fruit_n;
	private static double fruit_p;
	private static String line;
	private static String[] fruit_arr;
	private static Map<String,Double> maps=new HashMap<>();
	private static minjuMap elem;
	public static Map<String,Double> readData(String file_n){
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
				maps.put(fruit_n,fruit_p);
				//System.out.println("Fruit_name: "+fruit_n+" Fruit_price: "+fruit_p);
			}
		}catch(FileNotFoundException e) {
			return null;
		}catch(IOException e) {
			return null;
		}
		elem=new minjuMap(maps);
		//System.out.print(elem);
		return elem;
	}
	
}

class minjuMap implements Map<String,Double>{
	private TreeMap<String,Double> order_m=new TreeMap<>();
	private Map<String,Double> maps=new HashMap<>();
	private  Iterator<String> keys;
	private  String str="";
	private  String key_str;
	minjuMap(Map<String, Double> map){
		maps=map;
	}
	public String toString() {
		order_m=new TreeMap(maps);
		keys=order_m.keySet().iterator();
		while(keys.hasNext()) {
			key_str=keys.next();
			str+=key_str+" "+order_m.get(key_str)+"\n";
		}
		//System.out.print(str);
		return str;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return maps.size();
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return maps.isEmpty();
	}
	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return maps.containsKey(key);
	}
	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return maps.containsValue(value);
	}
	@Override
	public Double get(Object key) {
		// TODO Auto-generated method stub
		return maps.get(key);
	}
	@Override
	public Double put(String key, Double value) {
		// TODO Auto-generated method stub
		return maps.put(key, value);
	}
	@Override
	public Double remove(Object key) {
		// TODO Auto-generated method stub
		return maps.remove(key);
	}
	@Override
	public void putAll(Map<? extends String, ? extends Double> m) {
		// TODO Auto-generated method stub
		maps.putAll(m);
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		maps.clear();
	}
	@Override
	public Set<String> keySet() {
		// TODO Auto-generated method stub
		return maps.keySet();
	}
	@Override
	public Collection<Double> values() {
		// TODO Auto-generated method stub
		return maps.values();
	}
	@Override
	public Set<Entry<String, Double>> entrySet() {
		// TODO Auto-generated method stub
		return maps.entrySet();
	}
}


public class Problem17 {
	public static void main(String args[]) { 
		Map<String, Double> map = MapManager.readData("input.txt");
		if(map == null) { 
			System.out.println("Input file not found.");
			return; } 
		System.out.println(map); 
		}
}


