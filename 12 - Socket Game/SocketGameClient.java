//Tara Moses
//CP3 Assignment 12: Socket Game
//April 23, 2014

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class SocketGameClient {
	public static void main(String[] args) throws Exception {
		int port=5000;
		
		System.out.println("Player 2: CLIENT is thinking of a number...");
		
		Socket clientSocket=new Socket("localhost",port);
		
		Scanner clientScan=new Scanner(System.in);
		PrintStream clientVoice=new PrintStream(
			clientSocket.getOutputStream());
		BufferedReader clientEyes=new BufferedReader(
			new InputStreamReader(clientSocket.getInputStream()));
		
		System.out.print("What number do you want Player 1 to guess? ");
		int targetNum=clientScan.nextInt();
		clientScan.nextLine();
		
		clientVoice.println(targetNum); //send targetnum over to server
			
		boolean gameOver=false;
		String feedback="feedback";
		String guess=clientEyes.readLine();
		while (targetNum!=Integer.parseInt(guess)) {
			System.out.print("Player 1 guessed "+guess+". Any feedback? ");
			feedback=clientScan.nextLine();
			clientVoice.println(feedback);
			guess=clientEyes.readLine();
		}
		
		System.out.println("Player 1 guessed the number "+targetNum+"!");
	}
}
