//Tara Moses
//CP3 Assignment 12: Socket Game
//April 23, 2014

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class SocketGameServer {
	public static void main(String[] args) throws Exception {
		int port=5000;
		
		System.out.println("Player 1: SERVER waiting for a connection...");
		
		ServerSocket serverSocket=new ServerSocket(port);
		Socket acceptSS=serverSocket.accept();
		
		Scanner serverScan=new Scanner(System.in);
		PrintStream serverVoice=new PrintStream(
			acceptSS.getOutputStream());
		BufferedReader SSEyes=new BufferedReader(
			new InputStreamReader(acceptSS.getInputStream()));
		
		String feedback="";
		int targetNum=Integer.parseInt(SSEyes.readLine());
		
		System.out.print("Guess a number! ");
		int numGuessed=serverScan.nextInt();
		serverVoice.println(numGuessed);
		while (numGuessed!=targetNum) {
			feedback=SSEyes.readLine();
			System.out.println("\nPlayer 2 says: "+feedback);
			System.out.print("Guess again: ");
			numGuessed=serverScan.nextInt();
			serverVoice.println(numGuessed);
		}
		System.out.println("\nYou win! The number was "+targetNum+".");
	}
}
