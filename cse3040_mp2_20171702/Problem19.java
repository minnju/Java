package cse3040_mp2_20171702;
import java.util.ArrayList;
import java.util.Collections;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;


class BookReader{
	private static ArrayList<BookInfo> list=new ArrayList<>();
	private static ArrayList<String> lines=new ArrayList<>();
	private static URL url=null;
	private static BufferedReader input=null;
	private static String line="";
	private static int rank=1;
	private static int status;
	private static String l="";
	private static int begin;
	private static int end;
	private static String author;
	private static String title;
	private static String money;
	private static String[] token;
	public static ArrayList<BookInfo> readBooks(String link){
		try {
			url=new URL(link);
			input = new BufferedReader(new InputStreamReader(url.openStream()));
			while((line=input.readLine())!=null) {
				if(line.trim().length()>0)lines.add(line);
			}
			input.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		for(int i=0;i<lines.size();i++) {
			l=lines.get(i);
			if(status==0) {
				if((l.contains("div class=\"col-lg-9 product-info-listView\""))) status=1;
			}else if(status ==1) {
				if(l.contains("div class=\"product-shelf-title pr-m\"")) status=2;
			}else if(status==2) {
				if(l.contains("a title")) {
					begin=l.indexOf("\">")+"\">".length();
					end=l.indexOf("</a>");
					title=l.substring(begin,end);
				}
				if(l.contains("div class=\"product-shelf-author contributors\"")) {
					if(l.contains("a href")) {
						token=l.split(">");
						begin=0;
						end=token[2].indexOf("<");
						author=token[2].substring(begin,end);
						status=3;
					}
				}
				
			}else if(status==3) {
				if(l.contains("span class=\"current\"")) status=4;
			}else if(status==4) {
				if(l.contains("a title")) {
					begin=l.indexOf("\">")+"\">".length();
					end=l.indexOf("</a>");
					money=l.substring(begin,end);
					//System.out.println("#"+rank+" "+title+", "+author+", "+money);
					list.add(new BookInfo(rank,title,author,money));
					status=0;
					rank++;
					if (rank>20)break;
				}
			}
		}
		
		return list;
	}
}
class BookInfo implements Comparable<BookInfo>{
	private int rank;
	private String title;
	private String author;
	private String dollars;
	BookInfo(int book_rank,String book_name,String book_author,String money){
		rank=book_rank;
		title=book_name;
		author=book_author;
		dollars=money;
	}
	@Override
	public int compareTo(BookInfo o) {
		// TODO Auto-generated method stub
		if (this.rank>o.rank) {
			return -1;
		}else if (this.rank<o.rank){
			return 1;
		}
		return 0;
	}
	public String toString() {
		return "#"+rank+" "+title+", "+author+", "+dollars;
	}
	
}

public class Problem19 {
	public static void main(String[] args) {
		ArrayList<BookInfo> books;
		books = BookReader.readBooks("https://www.barnesandnoble.com/b/books/_/N-1fZ29Z8q8");
		Collections.sort(books);
		for(int i=0; i<books.size(); i++) {
			BookInfo book = books.get(i);
			System.out.println(book); 
			}
	}
}
