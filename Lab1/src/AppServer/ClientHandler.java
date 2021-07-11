package AppServer;

import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ClientHandler extends TaskServer implements Runnable {

	private Socket clientSocket;
	private TaskList listOfTasks;
	private PrintWriter out;
	private BufferedReader in;
	private String command, task, getList = "L", register = "R", quit = "Q";
	private String listTasks;

	boolean exit = false;	
	
	public ClientHandler(Socket clientSocket, TaskList listOfTasks) throws IOException {
		
		this.clientSocket = clientSocket;
		this.listOfTasks = listOfTasks;
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
	}

		@Override
		public void run() {
			try {
				out.println("Server Msg - Ligação estabelecida.");
				while (!exit) {
					
					System.out.println("Em espera por instrução de cliente.");
					command = in.readLine();

					if (command.equals(getList)) {

						System.out.println(String.format("Instrunção recebida: Listar - %s.", command));
					    listTasks = listOfTasks.getList();
					    out.println("Server Msg - Lista de tarefas: " + listTasks);
					    System.out.println("Resposta enviada.");
			        	
					} else if (command.equals(register)) {
						System.out.println("Instrunção recebida: Registar Tarefa ("+command+").");
						out.println("Server Msg - Introduza a tarefa a registar: ");
						System.out.println("Resposta enviada.");
		        	
						task = in.readLine();
							
						if (task.length() > 120 ) {
							out.println("Server Msg - Erro: Registo com mais de 120 caractéres.");
				
						} else {
							System.out.println("Tarefa recebida: " + task);
							listOfTasks.addToList(task);
							System.out.println("Tarefa registada.");
							out.println("Server Msg - Tarefa registada.");
						}
			
					} else if (command.equals(quit)) {

						System.out.println(String.format("\nInstrunção recebida: Sair - (%s).\n", command));
						out.println("Server Msg - Ligação terminada.");
						
						in.close();
						out.close();
						clientSocket.close();
						quit(clientSocket);
						exit = true;		        			
					}
				}

			} catch (IOException error) {
				error.printStackTrace();
			}
			
		}
}

