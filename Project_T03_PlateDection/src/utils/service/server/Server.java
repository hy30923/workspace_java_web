package utils.service.server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


// Server module with thread implement
public class Server implements Runnable {

	private int port = 0;
	private Socket socket = null;
	
	private BufferedReader reader = null;
	private PrintWriter writer = null;

	public Server(Socket socket, int port) throws Exception {
		
		super();
		this.socket = socket;
		this.port = port;
		
		InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
		reader = new BufferedReader(isReader);
		writer = new PrintWriter(socket.getOutputStream());
	}
	
	public void destroy() throws Exception {
		
		socket.close();
		reader.close();
		writer.close();
		socket = null;
		reader = null;
		writer = null;
	}
	
	// Thread start
	public void run() {

		try {

			String data = "";
			while((data = reader.readLine()) != null) {
					
				System.out.println("Port " + port + " recv: " + data);
				writer.println(data);
				writer.flush();
				
				if(data.equals("quit")) {
					
					break;
				}
			}
			
			System.out.println("Port " + port + " disconnect...");
		}
		
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	// End Thread
}
