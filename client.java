
import java.io.*;
import java.net.*;

public class client {

	Socket mioSocket = null;
	int porta = 6789; // porta server

	DataInputStream in;
	DataOutputStream out;
	BufferedReader tastiera;

	public void comunica()
	{
		try {
			tastiera = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("[2] - Messaggio da inviare al server: ");
			String messaggio = tastiera.readLine();
			System.out.println("[3] - Invio: " + messaggio);
			out.writeBytes(messaggio + "\n");
			System.out.println("[4] - In attesa di una risposta...");
			String ricevuta = in.readLine();
			System.out.println("[5] - Risposta del server: " + ricevuta);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Socket connetti() {
		try {
			System.out.println("[0] - Provo a connettermi al server...");

			Socket mioSocket = new Socket(InetAddress.getLocalHost(), porta);
			System.out.println("[1] - Connesso!");

			in = new DataInputStream(mioSocket.getInputStream());
			out = new DataOutputStream(mioSocket.getOutputStream());

		} catch (UnknownHostException e) {
			System.err.println("Host sconosciuto");
		} catch (Exception e) {

			System.err.println("Impossibie stabilire la connessione");
		}
		return mioSocket;
	}

	public static void main(String[] args) {

		client c = new client();
		c.connetti();
		c.comunica();

	}

}
