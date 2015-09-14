//Tara Moses
//CP3 Assignment 13: Factoring Multi-computer Thing
//May 9, 2014

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.math.BigInteger;

public class FactoringServer {
	public static String time() {
		return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	}
	
	public static void main(String[] args) throws Exception {
		//initialize dumb variables
		int factor;
		int port=5000;
		Scanner serverScan=new Scanner(System.in);
		PrintWriter writer=new PrintWriter("logForrealYo.txt","UTF-8");
		
		//set up server
		ServerSocket serverSocket=new ServerSocket(port);
		
		//let's get some connections whoohoo
		System.out.println("SERVER waiting for Connection #1...");
		Socket acceptSS1=serverSocket.accept();
		PrintStream serverVoice1=new PrintStream(
			acceptSS1.getOutputStream());
		BufferedReader SSEyes1=new BufferedReader(
			new InputStreamReader(acceptSS1.getInputStream()));
		String ip1=SSEyes1.readLine();
		System.out.println("Connection #1: "+ip1);
		writer.write(time()+": Connection #1 ("+ip1+") received\n");
		
		System.out.println("SERVER waiting for Connection #2...");
		Socket acceptSS2=serverSocket.accept();
		PrintStream serverVoice2=new PrintStream(
			acceptSS2.getOutputStream());
		BufferedReader SSEyes2=new BufferedReader(
			new InputStreamReader(acceptSS2.getInputStream()));
		String ip2=SSEyes2.readLine();
		System.out.println("Connection #2: "+ip2);
		writer.write(time()+": Connection #2 ("+ip2+") received\n");
		
		System.out.println("SERVER waiting for Connection #3...");
		Socket acceptSS3=serverSocket.accept();
		PrintStream serverVoice3=new PrintStream(
			acceptSS3.getOutputStream());
		BufferedReader SSEyes3=new BufferedReader(
			new InputStreamReader(acceptSS3.getInputStream()));
		String ip3=SSEyes3.readLine();
		System.out.println("Connection #3: "+ip3);
		writer.write(time()+": Connection #3 ("+ip3+") received\n");
		
		System.out.println("SERVER waiting for Connection #4...");
		Socket acceptSS4=serverSocket.accept();
		PrintStream serverVoice4=new PrintStream(
			acceptSS4.getOutputStream());
		BufferedReader SSEyes4=new BufferedReader(
			new InputStreamReader(acceptSS4.getInputStream()));
		String ip4=SSEyes4.readLine();
		System.out.println("Connection #4: "+ip4);
		writer.write(time()+": Connection #4 ("+ip4+") received\n");
		
		System.out.println("SERVER waiting for Connection #5...");
		Socket acceptSS5=serverSocket.accept();
		PrintStream serverVoice5=new PrintStream(
			acceptSS5.getOutputStream());
		BufferedReader SSEyes5=new BufferedReader(
			new InputStreamReader(acceptSS5.getInputStream()));
		String ip5=SSEyes5.readLine();
		System.out.println("Connection #5: "+ip5);
		writer.write(time()+": Connection #5 ("+ip5+") received\n");
		
		System.out.println("SERVER waiting for Connection #6...");
		Socket acceptSS6=serverSocket.accept();
		PrintStream serverVoice6=new PrintStream(
			acceptSS6.getOutputStream());
		BufferedReader SSEyes6=new BufferedReader(
			new InputStreamReader(acceptSS6.getInputStream()));
		String ip6=SSEyes6.readLine();
		System.out.println("Connection #6: "+ip6);
		writer.write(time()+": Connection #6 ("+ip6+") received\n");
		
		System.out.println("SERVER waiting for Connection #7...");
		Socket acceptSS7=serverSocket.accept();
		PrintStream serverVoice7=new PrintStream(
			acceptSS7.getOutputStream());
		BufferedReader SSEyes7=new BufferedReader(
			new InputStreamReader(acceptSS7.getInputStream()));
		String ip7=SSEyes7.readLine();
		System.out.println("Connection #7: "+ip7);
		writer.write(time()+": Connection #7 ("+ip7+") received\n");
		
		System.out.println("SERVER waiting for Connection #8...");
		Socket acceptSS8=serverSocket.accept();
		PrintStream serverVoice8=new PrintStream(
			acceptSS8.getOutputStream());
		BufferedReader SSEyes8=new BufferedReader(
			new InputStreamReader(acceptSS8.getInputStream()));
		String ip8=SSEyes8.readLine();
		System.out.println("Connection #8: "+ip8);
		writer.write(time()+": Connection #8 ("+ip8+") received\n\n");
		
		System.out.print("Number to factor: ");
		BigInteger num=new BigInteger(serverScan.nextLine());
		serverVoice1.println(num);
		serverVoice2.println(num);
		serverVoice3.println(num);
		serverVoice4.println(num);
		serverVoice5.println(num);
		serverVoice6.println(num);
		serverVoice7.println(num);
		serverVoice8.println(num);
		
		//dole out ranges for all 8 computers >_>
		serverVoice1.println("10000000001");
		serverVoice1.println("20000000000");
		writer.write(time()+": Connection #1 starts checking factors 10000000001-20000000000\n");
		
		serverVoice2.println("20000000001");
		serverVoice2.println("30000000000");
		writer.write(time()+": Connection #2 starts checking factors 20000000001-30000000000\n");
		
		serverVoice3.println("30000000001");
		serverVoice3.println("40000000000");
		writer.write(time()+": Connection #3 starts checking factors 30000000001-40000000000\n");
		
		serverVoice4.println("40000000001");
		serverVoice4.println("50000000000");
		writer.write(time()+": Connection #4 starts checking factors 40000000001-50000000000\n");
		
		serverVoice5.println("50000000001");
		serverVoice5.println("60000000000");
		writer.write(time()+": Connection #5 starts checking factors 50000000001-60000000000\n");
		
		serverVoice6.println("60000000001");
		serverVoice6.println("70000000000");
		writer.write(time()+": Connection #6 starts checking factors 60000000001-70000000000\n");
		
		serverVoice7.println("70000000001");
		serverVoice7.println("80000000000");
		writer.write(time()+": Connection #7 starts checking factors 70000000001-80000000000\n");
		
		serverVoice8.println("80000000001");
		serverVoice8.println("99999999999");
		writer.write(time()+": Connection #8 starts checking factors 80000000001-99999999999\n");
		
		//dole out test ranges
		//serverVoice1.println("2");
		//serverVoice1.println("5");
		
		//serverVoice2.println("6");
		//serverVoice2.println("9");
		
		//serverVoice3.println("10");
		//serverVoice3.println("13");
		
		//serverVoice4.println("14");
		//serverVoice4.println("17");
		
		//serverVoice5.println("18");
		//serverVoice5.println("21");
		
		//serverVoice6.println("22");
		//serverVoice6.println("25");
		
		//serverVoice7.println("26");
		//serverVoice7.println("29");
		
		//serverVoice8.println("30");
		//serverVoice8.println("33");
		
		factor=Integer.parseInt(SSEyes1.readLine());
		if (factor!=0) writer.write(time()+": a factor found by Connection #1!! factor = "+factor+"\n");
		factor=Integer.parseInt(SSEyes1.readLine());
		if (factor!=0) writer.write(time()+": a factor found by Connection #1!! factor = "+factor+"\n");
		factor=Integer.parseInt(SSEyes2.readLine());
		if (factor!=0) writer.write(time()+": a factor found by Connection #2!! factor = "+factor+"\n");
		factor=Integer.parseInt(SSEyes2.readLine());
		if (factor!=0) writer.write(time()+": a factor found by Connection #2!! factor = "+factor+"\n");
		factor=Integer.parseInt(SSEyes3.readLine());
		if (factor!=0) writer.write(time()+": a factor found by Connection #3!! factor = "+factor+"\n");
		factor=Integer.parseInt(SSEyes3.readLine());		
		if (factor!=0) writer.write(time()+": a factor found by Connection #3!! factor = "+factor+"\n");
		factor=Integer.parseInt(SSEyes4.readLine());		
		if (factor!=0) writer.write(time()+": a factor found by Connection #4!! factor = "+factor+"\n");
		factor=Integer.parseInt(SSEyes4.readLine());		
		if (factor!=0) writer.write(time()+": a factor found by Connection #4!! factor = "+factor+"\n");
		factor=Integer.parseInt(SSEyes5.readLine());		
		if (factor!=0) writer.write(time()+": a factor found by Connection #5!! factor = "+factor+"\n");
		factor=Integer.parseInt(SSEyes5.readLine());		
		if (factor!=0) writer.write(time()+": a factor found by Connection #5!! factor = "+factor+"\n");
		factor=Integer.parseInt(SSEyes6.readLine());		
		if (factor!=0) writer.write(time()+": a factor found by Connection #6!! factor = "+factor+"\n");
		factor=Integer.parseInt(SSEyes6.readLine());		
		if (factor!=0) writer.write(time()+": a factor found by Connection #6!! factor = "+factor+"\n");
		factor=Integer.parseInt(SSEyes7.readLine());		
		if (factor!=0) writer.write(time()+": a factor found by Connection #7!! factor = "+factor+"\n");
		factor=Integer.parseInt(SSEyes7.readLine());		
		if (factor!=0) writer.write(time()+": a factor found by Connection #7!! factor = "+factor+"\n");
		factor=Integer.parseInt(SSEyes8.readLine());		
		if (factor!=0) writer.write(time()+": a factor found by Connection #8!! factor = "+factor+"\n");
		factor=Integer.parseInt(SSEyes8.readLine());		
		if (factor!=0) writer.write(time()+": a factor found by Connection #8!! factor = "+factor+"\n");
		
		serverVoice1.println(num);
		serverVoice2.println(num);
		serverVoice3.println(num);
		serverVoice4.println(num);
		serverVoice5.println(num);
		serverVoice6.println(num);
		serverVoice7.println(num);
		serverVoice8.println(num);		
		
		writer.close();
	}
}
