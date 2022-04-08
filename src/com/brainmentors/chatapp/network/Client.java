package com.brainmentors.chatapp.network;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.brainmentors.chatapp.utils.ConfigReader;

public class Client {
	Socket socket;
	public Client() throws UnknownHostException, IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		socket = new Socket(ConfigReader.getValue("SERVER_IP"),PORT);
		System.out.println("Client comes");
		System.out.println("Enter the message Send to the Server....");
		Scanner scanner = new Scanner(System.in);
		String message = scanner.nextLine();
		OutputStream out = socket.getOutputStream(); // Write Bytes on Network
		out.write(message.getBytes());
		System.out.println("Message Send to the server");
		scanner.close();
		out.close();
		socket.close();
	}
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		Client client = new Client();
	}

}
