import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import utils.service.server.Server;

public class ServerMain {

	private int nPorts = 5;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		new ServerMain().startServer();
	}
	
	public void startServer() throws Exception {

		Thread[] threads = new Thread[nPorts];
			
		for(int i = 0 ; i < nPorts ; i++) {
			
			threads[i] = new Thread(new PortListeningThread(5000 + i));
			threads[i].start();
		}
		
		for(int i = 0 ; i < nPorts ; i++) {
			
			threads[i].join();
		}
	}
	
	public class PortListeningThread implements Runnable {
		
		private int port = 0;
		private ServerSocket serverSocket;
		
		public PortListeningThread(int port) {

			super();
			this.port = port;
		}
		
		public void run() {
			
			try {
				
				serverSocket = new ServerSocket(port);
				System.out.println("Port " + port + " listening... ");
			} 
			
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			while(true) {
				
				try {

					Socket socket = serverSocket.accept();
					Thread t = new Thread(new Server(socket, port));
					t.start();
					System.out.println("Port " + port + " got a connection");
				} 
				
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
