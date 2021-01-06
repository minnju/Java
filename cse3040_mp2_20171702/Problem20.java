package cse3040_mp2_20171702;

import java.util.ArrayList;
import java.util.Collections;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import java.io.IOException;



class BookReader{
	private static ArrayList<BookInfo> list=new ArrayList<>();
	private static Document doc=null;
	private static Elements bestsellers;
	private static Elements author;
	private static Elements money;
	public static ArrayList<BookInfo> readBooksJsoup(String link){
		try {
			doc=Jsoup.connect(link).get();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		bestsellers=doc.select("div.product-shelf-title a");	
		author=doc.select("div.product-shelf-author a:first-child");	
		money=doc.select("span.current a");
		for(int i=0;i<20;i++) {
			//System.out.println("#"+(i+1)+" "+bestsellers.eq(i).text()+", "+author.eq(i).text()+", "+money.eq(i).text());
			list.add(new BookInfo(i+1,bestsellers.eq(i).text(),author.eq(i).text(),money.eq(i).text()));
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
public class Problem20 {
	public static void main(String[] args) { 
		ArrayList<BookInfo> books;
		books = BookReader.readBooksJsoup("https://www.barnesandnoble.com/b/books/_/N-1fZ29Z8q8"); 
		Collections.sort(books);
		for(int i=0; i<books.size(); i++) { 
			BookInfo book = books.get(i);
			System.out.println(book); 
			}
	}

}
