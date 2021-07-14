package AppServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TaskServer extends TaskList{
	
	static ServerSocket serverSocket;
	public TaskServer() {
		
	}
	
	private static boolean exit = false;
	
	public static void main (String[]args) throws Exception {

		TaskList listOfTasks = new TaskList();
		Socket clientSocket;
		

		try {
			serverSocket = new ServerSocket(4444);
			serverSocket.setReuseAddress(true);
			System.out.println("Servidor iniciado.");

			while (!exit) {
				
				System.out.println("Em espera por ligação de clientes.");
				clientSocket = serverSocket.accept();

				ClientHandler client = new ClientHandler(clientSocket, listOfTasks);
				new Thread(client).start();

				System.out.println("Cliente ligado.");
			}

		} catch (IOException error) {
			error.printStackTrace();
		}
	}

	public void quit(Socket clientSocket) {
		try {
			serverSocket.close();
			System.out.println("O cliente " + clientSocket + " enviou a intrução Q - Ligação terminada\n");
			exit = true;
		} catch (IOException error) {
		
			error.printStackTrace();
		}
	}
	
}

