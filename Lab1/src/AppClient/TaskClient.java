package AppClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TaskClient {


    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        String instruction, response, task, getList = "L", register = "R", quit = "Q", ipAdress, port;;
        
        boolean condition = true;
        
        Socket clientSocket;
        BufferedReader in;
        PrintWriter out;

        System.out.print("Introduza o IP do servidor: ");
        ipAdress = sc.nextLine();
       
        
        System.out.print("Introduza o porto: ");
        port = sc.nextLine();
       // int portInt=Integer.parseInt(port);
        clientSocket = new Socket(ipAdress, Integer.parseInt(port));
        
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        response = in.readLine();
    	System.out.print(response);
        while (condition) {
        	
        	
	        System.out.print("\nInstrução (L -> Listar Tarefa; R -> Resgistar Tarefa; Q -> Sair): ");
	        instruction = sc.nextLine();
	       
        	if (instruction.equals(getList)){
        		out.println(instruction);
            	response = in.readLine();
        		System.out.println(response);
       	 
        	} else if (instruction.equals(register)){
        		out.println(instruction);
            	response = in.readLine();
        		System.out.print(response);
        		task = sc.nextLine();
        		out.println(task);
        		response = in.readLine();
        		System.out.println(response);
        	 
        	} else if (instruction.equals(quit)) {
        		out.println(instruction);
            	response = in.readLine();
        		System.out.println(response);
        		in.close();
        		out.close();
        		sc.close();
        		clientSocket.close();
        		condition = false;
    
    		} else {
    			System.out.println("Instrução inválida.");
    		}
	        
        }
    }
}