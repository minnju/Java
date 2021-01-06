package cse3040fp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ConnectException;
import java.util.Scanner;

public class Client {
	static boolean msgflag=true;
	public static void main(String args[]) {
		if (args.length<2) {
			System.out.print("Please give the port number as an argument.");
			return;
		}
		String userID;
		String[] tocken;
		char[] chararr;
		Scanner scan=new Scanner(System.in);
		String serverIp=args[0];
		int port_num=Integer.parseInt(args[1]);
		boolean flag=true;
		try {
			Socket socket= new Socket(serverIp,port_num);
			while(true) {
			flag=true;
			System.out.print("Enter userID>> ");
			userID=scan.nextLine();
			userID=userID.trim();
			//System.out.println("ID "+userID);
			tocken=userID.split(" ");
			//System.out.println(tocken.length);
			if (tocken.length!=1 || userID.equals("")) {
				System.out.println("UserID must be a single word with lowercase alphabets and numbers.");
				continue;
			}
			chararr=userID.toCharArray();
			for(int i=0;i<tocken.length;i++) {
				if (!(48<=chararr[i] && chararr[i]<=57)&&!(97<=chararr[i]&&chararr[i]<=122)) {
					System.out.println("UserID must be a single word with lowercase and numbers.");
					flag=false;
				}
			}
			if(flag==false) {
				continue;
			}
				break;
			}
			System.out.println("Hello "+userID+"!");
			Thread sender=new Thread(new ClientSender(socket,userID));
			Thread receiver=new Thread(new ClientReceiver(socket));
			sender.start();
			receiver.start();
		}catch(ConnectException ce){
			System.out.print("Connection establishment failed.");
			return;
		}catch(IOException ie) {
			ie.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	static class ClientSender extends Thread{
		Socket socket;
		DataOutputStream out;
		String name;
		ClientSender(Socket socket, String name){
			this.socket=socket;
			try {
				out=new DataOutputStream(socket.getOutputStream());
				this.name=name;
			}catch(IOException e) {}
		}
		public void run() {
			String command="";
			String param1="";
			String param2="";
			Scanner scan= new Scanner(System.in);
			try {
				if(out!=null) {
					out.writeUTF(name);
				}
				while(out!=null) {
					if(msgflag==true) {
						msgflag=false;
						System.out.print(name+">> ");
						command=scan.nextLine();
						command=command.trim();
						out.writeUTF(command);
						switch(command) {
						case "add":
							System.out.print("add-title> ");
							param1=scan.nextLine();
							//out.writeUTF(scan.nextLine());
							param1=param1.trim();
							if(param1.equals("")) {
								//System.out.println("enter");
								out.writeUTF(param1);
								msgflag=true;
								break;
							}
							System.out.print("add-author> ");
							param2=scan.nextLine();
							param2=param2.trim();
							if(param2.equals("")) {
								//System.out.println("enter");
								out.writeUTF(param2);
								msgflag=true;
								break;
							}
							//System.out.println(param1+" "+param2);
							out.writeUTF(param1);
							out.writeUTF(param2);
							continue;	
						case "borrow":
							System.out.print("borrow-title> ");
							param1=scan.nextLine();
							param1=param1.trim();
							if(param1.equals("")) {
								out.writeUTF(param1);
								msgflag=true;
								break;
							}
							out.writeUTF(param1);
							continue;
						case "return":
							System.out.print("return-title> ");
							param1=scan.nextLine();
							param1=param1.trim();
							if(param1.equals("")) {
								out.writeUTF(param1);
								msgflag=true;
								break;
							}
							out.writeUTF(param1);
							continue;
						case "search":
							while(true) {
								System.out.print("search-string> ");
								param1=scan.nextLine();
								//param1=param1.trim();
								if(param1.equals("")) {
									//System.out.println("Enter");
									out.writeUTF(param1);
									msgflag=true;
									break;
								}
								if(param1.length()<3) {
									System.out.println("Search string must be longer than 2 characters.");
									//out.writeUTF(param1);
									continue;
								}
								out.writeUTF(param1);
								break;
							}
							continue;
						default:
							//msgflag=true;
							continue;
						}
					}
				}
			}catch(IOException e) {}
		}
		
	}
	static class ClientReceiver extends Thread{
		Socket socket;
		DataInputStream in;
		ClientReceiver(Socket socket){
			this.socket=socket;
			try {
				in=new DataInputStream(socket.getInputStream());
			}catch(IOException e) {}
		}
		public void run() {
			while(in!=null) {
				try {
					System.out.println(in.readUTF());
					msgflag=in.readBoolean();
				}catch(IOException e) {}
			}
		}
	}
}
