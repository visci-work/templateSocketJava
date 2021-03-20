
import java.io.*;
import java.net.*;

public class server {

	ServerSocket server = null;
	Socket socketClient = null;

	int porta = 6789; // porta server

	DataInputStream in;
	DataOutputStream out;

	public void comunica() {
		try {
			System.out.println("[3] - Aspetto un messaggio dal client...");
			String letto = in.readLine();
			System.out.println("[4] - Messaggio ricevuto: " + letto);
			String risposta = letto.toUpperCase();
			System.out.println("[5] - Rispondo con: " + risposta);
			out.writeBytes(risposta + "\n");
			socketClient.close(); // Chiudo la connessione con quel client
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Socket attendi() {

		try {

			System.out.println("[0] - Inizializzo il server...");
			// inizializziamo il servizio
			server = new ServerSocket(porta);

			System.out.println("[1] - Server pronto, in ascolto sulla porta: " + porta);
			// mi metto in scolto sulla porta che ho aperto
			socketClient = server.accept();

			System.out.println("[2] - Connessione stabilita con il client!");

			// evitiamo connessioni multiple
			server.close();
			in = new DataInputStream(socketClient.getInputStream());
			out = new DataOutputStream(socketClient.getOutputStream());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return socketClient;

	}

	public static void main(String[] args) {

		server s = new server();
		s.attendi();
		s.comunica();

	}

}
