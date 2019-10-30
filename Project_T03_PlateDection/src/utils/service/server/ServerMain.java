package utils.service.server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import utils.service.datacontrol.DataControl;

public class ServerMain {
	
	public void startServer(DataControl[] dataControls, int nPorts) throws Exception {

		Thread[] threads = new Thread[nPorts];
			
		for(int i = 0 ; i < nPorts ; i++) {
			
			threads[i] = new Thread(new PortListeningThread(5000 + i, dataControls[i]));
			threads[i].start();
		}
		
		for(int i = 0 ; i < nPorts ; i++) {
			
			threads[i].join();
		}
	}
	
	public class PortListeningThread implements Runnable {
		
		private int port = 0;
		private ServerSocket serverSocket = null;
		private DataControl dataControl = null;
		
		public PortListeningThread(int port, DataControl dataControl) {

			super();
			this.port = port;
			this.dataControl = dataControl;
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
					Thread t = new Thread(new Server(socket, port, dataControl));
					t.start();
					System.out.println("[" + new Date(System.currentTimeMillis()).toString() + "]"+ " Port " + port + " got a connection");
				} 
				
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
