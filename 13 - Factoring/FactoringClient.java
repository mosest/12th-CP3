//Tara Moses
//CP3 Assignment 13: Factoring Multi-computer Thing
//May 9, 2014

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.lang.Math;
import java.math.BigInteger;

public class FactoringClient {
	public static void main(String[] args) throws Exception {
		//declare and initialize variables whoo
		BigInteger num;
		BigInteger startNum;
		BigInteger endNum;
		int port=5000;
		int factorCount=0;
		InetAddress ip=InetAddress.getLocalHost();
		
		Socket clientSocket=new Socket("CSE2-142",port);
		
		Scanner clientScan=new Scanner(System.in);
		PrintStream clientVoice=new PrintStream(
			clientSocket.getOutputStream());
		BufferedReader clientEyes=new BufferedReader(
			new InputStreamReader(clientSocket.getInputStream()));
		
		System.out.println("LET'S FACTOR A THING YEAH\n");
		
		//send ip address
		System.out.println("IP is: "+ip);
		clientVoice.println(ip);
		
		num=new BigInteger(clientEyes.readLine());
		System.out.println("The number to factor is: "+num);
		
		startNum=new BigInteger(clientEyes.readLine());
		System.out.println("Beginning factor: "+startNum);
		
		endNum=new BigInteger(clientEyes.readLine());
		System.out.println("Ending factor: "+endNum+"\n");
		
		for (BigInteger i=startNum;i.compareTo(endNum)!=1;i=i.add(new BigInteger("1"))) {
			if (i.mod(new BigInteger("10000000")).compareTo(new BigInteger("0"))==0) System.out.println("i = "+i);
			if (num.mod(i).compareTo(new BigInteger("0"))==0) {
				System.out.println(i+" IS A FACTOR");
				clientVoice.println(i);
				factorCount++;
			}	
		}
		
		for (int i=factorCount;i<2;i++) {
			clientVoice.println(0);
		}
		clientEyes.readLine();
	}
}
