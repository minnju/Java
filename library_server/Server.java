package cse3040fp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Server {
	HashMap<String,DataOutputStream> clients;
	static ArrayList<BookInfo> list = ElementReader.readElements("books.txt");
	static int port_num=0;
	Server(){
		clients=new HashMap<>();
		Collections.synchronizedMap(clients);
	}
	public static void main(String args[]) {
		if (args.length==0) {
			System.out.print("Please give the port number as an argument.");
			return;
		}
		port_num=Integer.parseInt(args[0]);
		Collections.sort(list);
		new Server().start();
	}
	public void start() {
		ServerSocket serverSocket=null;
		Socket socket=null;
		
			try {
				serverSocket= new ServerSocket(port_num);
				while(true) {
					socket=serverSocket.accept();
					System.out.println("a new connection from [" + socket.getInetAddress() + ":" + socket.getPort() + "]");
					ServerReceiver thread = new ServerReceiver(socket);
					thread.start();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		
	}
	class ServerReceiver extends Thread {
		Socket socket;
		DataInputStream in;
		DataOutputStream out;
		ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch(IOException e) {}
		}
		public void run() {
			String name="";
			String command="";
			String msg="";
			String param1="";
			String param2="";
			boolean cmdflag=true;
			try {
					name = in.readUTF();
					//sendToAll("#"+name+" has joined.");
					clients.put(name, out);
					System.out.println("Current number of users: " + clients.size());
					//cout.writeUTF("Hello "+name+"!");
					while (in != null) {
						//cout.writeUTF(name+">> ");
						if(cmdflag==true) {
							cmdflag=false;
							command=in.readUTF();
						}
						//System.out.println(in.readUTF());
						switch(command) {
						case "add":
							param1=in.readUTF();
							param1=param1.trim();
							if(param1.equals("")) {
								cmdflag=true;
								break;
							}
							param2=in.readUTF();
							param2=param2.trim();
							if(param2.equals("")) {
								cmdflag=true;
								break;
							}
							//System.out.println(param1+" "+param2);
							add(name,param1,param2);
							cmdflag=true;
							//sendMsg("success",name,true);
							break;
						case "borrow":
							param1=in.readUTF();
							param1=param1.trim();
							if(param1.equals("")) {
								cmdflag=true;
								break;
							}
							borrow(name,param1);
							cmdflag=true;
							break;
						case "return":
							param1=in.readUTF();
							param1=param1.trim();
							if(param1.equals("")) {
								cmdflag=true;
								break;
							}
							return_book(name,param1);
							cmdflag=true;
							break;
						case "search":
							param1=in.readUTF();
							//param1=param1.trim();
							if(param1.equals("")) {
								//System.out.println("Enter");
								cmdflag=true;
								break;
							}
							/*if(param1.length()<3) {
								break;
							}*/
							search(name,param1);
							cmdflag=true;
							break;
						case "info":
							info(name);
							cmdflag=true;
							break;
						default:
							msg="[available commands]\n" + 
									"add: add a new book to the list of books.\r\n" + 
									"borrow: borrow a book from the library.\r\n" + 
									"return: return a book to the library.\r\n" + 
									"info: show list of books I am currently borrowing.\r\n" + 
									"search: search for books.";
							sendMsg(msg,name,true);
							cmdflag=true;
							break;
						}
						
					//sendToAll(in.readUTF());
					}
				} catch(IOException e) {
				// ignore
				} finally {
					//sendToAll("#"+name+" has left.");
					clients.remove(name);
					System.out.println("["+socket.getInetAddress()+":"+socket.getPort()+"]"+" has disconnected.");
					System.out.println("Current number of users: " + clients.size());
				}
		}
	}
	synchronized void add(String name,String title,String author) {
		String temp="";
		String find_title=title.toLowerCase();
		for (BookInfo elem: list) {
			temp=elem.title().toLowerCase();
			if(find_title.equals(temp)) {
				//System.out.println(find_title+" "+temp);
				sendMsg("The book already exists in the list.",name,true);
				return;
			}
		}
		list.add(new BookInfo(title,author,"-"));
		addfile(title+"\t"+author+"\t"+"-");
		Collections.sort(list);
		sendMsg("A new book added to the list.",name,true);
	}
	synchronized void borrow(String name, String title) {
		int idx=0;
		String temp="";
		String find_title=title.toLowerCase();
		String original="";
		String replace="";
		for (BookInfo elem: list) {
			temp=elem.title().toLowerCase();
			if(find_title.equals(temp)) {
				//System.out.println(find_title+" "+temp);
				if(!elem.borrower().equals("-")) {
					sendMsg("The book is not available.",name,true);
					return;
				}
				sendMsg("You borrowd a book. - "+elem.title(),name,true);
				original=elem.title()+"\t"+elem.author()+"\t"+elem.borrower();
				replace=elem.title()+"\t"+elem.author()+"\t"+name;
				Updatefile(original,replace);
				list.set(idx, new BookInfo(elem.title(),elem.author(),name));
				Collections.sort(list);
				return;
			}
			idx+=1;
		}
		sendMsg("The book is not available.",name,true);
		return;
	}
	synchronized void return_book(String name, String title) {
		int idx=0;
		String temp="";
		String find_title=title.toLowerCase();
		String original="";
		String replace="";
		for (BookInfo elem: list) {
			temp=elem.title().toLowerCase();
			if(find_title.equals(temp)) {
				//System.out.println(find_title+" "+temp);
				if(!elem.borrower().equals(name)) {
					sendMsg("You did not borrow the book.",name,true);
					return;
				}
				sendMsg("You returned a book. - "+elem.title(),name,true);
				list.set(idx, new BookInfo(elem.title(),elem.author(),"-"));
				Collections.sort(list);
				original=elem.title()+"\t"+elem.author()+"\t"+elem.borrower();
				replace=elem.title()+"\t"+elem.author()+"\t-";
				Updatefile(original,replace);
				return;
			}
			idx+=1;
		}
		sendMsg("You did not borrow the book",name,true);
		return;
	}
	synchronized void Updatefile(String original,String replace) {
		String result="";
		try {
			File fp=new File("books.txt");
			File tp=new File("temp.txt");
			FileReader fr=new FileReader(fp);
			FileWriter fw=new FileWriter(tp);
			BufferedReader br=new BufferedReader(fr);
			BufferedWriter bw=new BufferedWriter(fw);
			while((result=br.readLine())!=null) {
				result=result.replaceAll(original, replace);
				bw.write(result,0,result.length());
				bw.newLine();
			}
			bw.close();
			br.close();
			fp.delete();
			tp.renameTo(new File("books.txt"));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	synchronized void addfile(String newbook) {
		try {
			File fp=new File("books.txt");
			FileWriter fw=new FileWriter(fp,true);
			BufferedWriter bw=new BufferedWriter(fw);
			PrintWriter pw=new PrintWriter(bw,true);
			pw.write("\n"+newbook);
			pw.flush();
			pw.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	void info(String name) {
		int cnt=0;
		String msg="";
		String book="";
		for (BookInfo elem: list) {
			if(elem.borrower().equals(name)) {
				cnt++;
				book+=cnt+". "+elem.title()+", "+elem.author()+"\r\n";
			}
		}
		msg="You are currently borrowing "+cnt+" books:\r\n";
		msg+=book;
		msg=msg.trim();
		sendMsg(msg,name,true);
	}
	void search(String name, String str) {
		int cnt=0;
		String msg="";
		String book="";
		String temp1="";
		String temp2="";
		str=str.toLowerCase();
		for (BookInfo elem: list) {
			temp1=elem.title().toLowerCase();
			temp2=elem.author().toLowerCase();
			if(temp1.contains(str)) {
				cnt+=1;
				book+=cnt+". "+elem.title()+", "+elem.author()+"\r\n";
				continue;
			}
			if(temp2.contains(str)) {
				cnt+=1;
				book+=cnt+". "+elem.title()+", "+elem.author()+"\r\n";
				continue;
			}
		}
		msg="Your search matched  "+cnt+" results.\r\n";
		msg+=book;
		msg=msg.trim();
		sendMsg(msg,name,true);
	}
	void sendMsg(String msg,String name,Boolean val) {
		try {
			DataOutputStream out = (DataOutputStream)clients.get(name);
			out.writeUTF(msg);
			out.writeBoolean(val);
		}catch(IOException e) {}
	}
	
}
class ElementReader{
	private static ArrayList<BookInfo> list =new ArrayList<>();
	static ArrayList<BookInfo> readElements(String file_n){
		String book_title;
		String book_author;
		String book_borrower;
		String line;
		String[] book_arr;
		try {
			File fp=new File(file_n);
			FileReader fr=new FileReader(fp);
			BufferedReader br=new BufferedReader(fr);
			while(true) {
				line=br.readLine();
				if(line==null) {
					br.close();
					fr.close();
					break;
				}
				book_arr=line.split("\t");
				book_title=book_arr[0];
				book_author=book_arr[1];
				book_borrower=book_arr[2];
				list.add(new BookInfo(book_title,book_author,book_borrower));
				//System.out.println("title: "+book_title+" author: "+book_author+"borrower: "+book_borrower);
			}
		}catch(FileNotFoundException e) {
			System.out.println("Input file not found.");
			return list;
		}catch(IOException e) {
			return null;
		}
		return list;
	}
}
class BookInfo implements Comparable<BookInfo>{
	private String title;
	private String author;
	private String borrower;
	BookInfo(String book_title,String book_author,String book_borrower){
		title=book_title;
		author=book_author;
		borrower=book_borrower;
	}
	@Override
	public int compareTo(BookInfo o) {
		String this_title=this.title.toLowerCase();
		String o_title=o.title.toLowerCase();
		//System.out.println(this_title+" "+o_title);
		//System.out.println(this_title.compareTo(o_title));
		// TODO Auto-generated method stub
		if(this_title.compareTo(o_title)<0) {
			return -1;
		}else if(this_title.compareTo(o_title)>0) {
			return 1;
		}
		return 0;
	}
	public String borrower() {
		return borrower;
	}
	public String title() {
		return title;
	}
	public String author() {
		return author;
	}
	public String toString() {
		return title+" "+author+" "+borrower;
	}
}
