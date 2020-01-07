/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientTCP;

/*
 * ClientTesto.java e' il programma per connettersi ad un Server usando i socket
 * ed inviare il testo ricevuto dalla linea di comando.
 * Utilizza una "Thread" per l'ascolto dei messaggi provenienti dal Server.
 */

/**
 *
 * @author Guidetti
 */
import java.net.*;
import java.io.*;

public class Messaggio {
    
    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
		
	String hostName = "127.0.0.1";
	int portNumber = 1234;
	try {
            
            InetAddress address = InetAddress.getByName(hostName);
			
         
            Socket clientSocket = new Socket(address, portNumber);
		
           
            System.out.println("Client-Testo: usa Ctrl-C per terminare, ENTER per spedire la linea di testo.\n");
			
           
            Listener l;
            try {
                l = new Listener(clientSocket);
                Thread t = new Thread(l);
                t.start();
            } catch (Exception e) { System.out.println("Connessione NON riuscita con server: "); }
		
           
            PrintWriter out =  new PrintWriter(clientSocket.getOutputStream(), true);
			
            
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            
           
            System.out.print(">"); //visualizza il prompt
            while ((userInput = stdIn.readLine()) != null) {
             
		out.println(userInput);
                System.out.println("Messaggio spedito al server: " + userInput);
                System.out.print(">"); //visualizza il prompt
            }
           
            clientSocket.close();
            System.out.println("connessione terminata!");
	}
        catch (IOException e) { System.out.println("Connessione terminata dal server: "); e.printStackTrace(); }
    }
    
}