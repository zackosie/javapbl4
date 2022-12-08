package pbl4demo2;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.Vector;


public class server {
	final static int port = 9999;
//	DataOutputStream dos;
//	DataInputStream dis
	private String date;
	
//	
	public server()
	{
		
		ServerSocket serversocket;
		Socket Connection;
		try {
			serversocket = new ServerSocket(port);
			while(true)
			{
//				Connection = serversocket.accept();
//				dis = new DataInputStream(Connection.getInputStream());
//				dos = new DataOutputStream(Connection.getOutputStream());
//				String fromClient = dis.readUTF();
//				dos.writeUTF(fromClient);
//				Connection.close();
				Connection = serversocket.accept();
				System.out.println("New client connected: "
                         + Connection.getInetAddress()
                               .getHostAddress());
				new XuLyClient(this,Connection).start();
			}
		} catch (Exception e) {
			System.err.println(e);
			System.out.println("error o server");
		}     
	}

	public static void main(String[] args) throws ParseException {
		new server();
	}
}
class XuLyClient extends Thread{
	Socket socket;
	server server;
	static String s1 = "";
	public DataInputStream is;
	public DataOutputStream os;
	public XuLyClient(server server, Socket socket) {
		this.server = server;
		this.socket = socket;
		try {
		this.is = new DataInputStream(socket.getInputStream());
		this.os = new DataOutputStream(socket.getOutputStream());
	} catch (IOException e) {
		System.out.println("error o threadhandler");
	}
}
//	public static String printMonth(int numberOfDays, int startDay) {
//	      int weekdayIndex = 0;
//	      s1 +=  "Su  Mo  Tu  We  Th  Fr  Sa";
//	 
//	      for (int day = 1; day < startDay; day++) {
//	         s1 += ("    ");
//	         weekdayIndex++;
//	      }
//	 
//	      for (int day = 1; day <= numberOfDays; day++) {
//	         s1 += ( day);
//	         weekdayIndex++;
//	         if (weekdayIndex == 7) {
//	            weekdayIndex = 0;
//	            s1 += ("\n");
//	         } else { 
//	            s1 += "  ";
//	         }
//	      }
//	      s1 += "/n";
//	      return s1;
//	}

//	public static String printCalendar(String date) {
//		Calendar cal = new GregorianCalendar();
//		int startDay;
//		int numberOfDays;
//		int datetemp = Integer.parseInt(date);
//		for (int i = 0; i<12; i++) {
//			cal.set(datetemp, i, 1);
//			startDay = cal.get(Calendar.DAY_OF_WEEK);
//			numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
//			s1 += (cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US));
//			s1 += ( " " + date);
//			printMonth(numberOfDays,startDay);
//			s1 += "\n";
//		}
//		return s1;
//	}
	public static String printYear(String date) {
		String s = "";
		Calendar cal = new GregorianCalendar();
		int startDay;
		int numberOfDays;
		int datetemp = Integer.parseInt(date); 
		for (int i = 0; i < 12; i++) {
			cal.set(datetemp, i, 1);
			startDay = cal.get(Calendar.DAY_OF_WEEK);
			numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
//			System.out.print(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US));
			s+=cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
//			System.out.println(" " + year);
			s+=" "+ date + "\n";
			s+=printMonth(numberOfDays, startDay);
//			System.out.println();
			s+="\n";
		}
		return s;
	}

	public static String printMonth(int numberOfDays, int startDay) {
		String s = "";
		int weekdayIndex = 0;
//		System.out.println("Su  Mo  Tu  We  Th  Fr  Sa");
		s += "Su  Mo  Tu  We  Th  Fr  Sa\n";

		for (int day = 1; day < startDay; day++) {
//			System.out.print("    ");
			s+="    ";
			weekdayIndex++;
		}

		for (int day = 1; day <= numberOfDays; day++) {
//			System.out.printf("%1$2d", day);
			s = s+String.format("%1$2d", day);
			weekdayIndex++;
			if (weekdayIndex == 7) {
				weekdayIndex = 0;
//				System.out.println();
				s+="\n";
			} else {
//				System.out.print("  ");
				s+="  ";
			}
		}
//		System.out.println();
		s+="\n";
		return s;
	}
	
	public void run() {
		try {
//			DataInputStream in = new DataInputStream(socket.getInputStream());
//			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			while (true) {
				String ch = is.readUTF();
				if (ch.equals("q")) {
					break;
				}
				String date = is.readUTF();
				String s1 = printYear(date);
//				int date1 = Integer.parseInt(date);
				//tra du lieu ve phia client hien thi
				os.writeUTF(s1);
				os.flush();
//				out.writeObject(printCalendar(date1));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
