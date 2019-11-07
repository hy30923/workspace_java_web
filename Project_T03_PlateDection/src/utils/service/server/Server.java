package utils.service.server;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import utils.service.datacontrol.DataControl;


// Server module with thread implement
public class Server implements Runnable {

	private int count = 0;
	
	private int port = 0;
	private Socket socket = null;
	
	private BufferedReader reader = null;
	private PrintWriter writer = null;
	DataOutputStream dOut = null;
	private DataControl dataControl = null;
	
	private Object prevSendData = null;
	private byte prevPlaceInfo = 0x00;
	
	public Server(Socket socket, int port, DataControl dataControl) throws Exception {
		
		super();
		this.socket = socket;
		this.port = port;
		this.dataControl = dataControl;
		
		InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
		reader = new BufferedReader(isReader);
		writer = new PrintWriter(socket.getOutputStream());
		dOut = new DataOutputStream(socket.getOutputStream());
	}
	
	public void destroy() throws Exception {
		
		socket.close();
		reader.close();
		writer.close();
		dOut.close();
		socket = null;
		reader = null;
		writer = null;
		dOut = null;
	}
	
	// Thread start
	public void run() {

		try {

			dataControl.setPort(port);
			dataControl.setWriter(writer);
			dataControl.setdOut(dOut);
			
			String data = "";
			
			while(port == 5001) {
				
				if(dataControl.getData().getPlaceData().isEmpty())	
					continue;
				
				HashMap<String, String> sendData = dataControl.getData().getPlaceData();
				
				if(sendData.equals(prevSendData))	
					continue;
				
				dataControl.sendData(sendData);
				Thread.sleep(500);
				prevSendData = sendData;
				dataControl.getData().setPlaceData(new HashMap<String, String>());
				
				try {
					socket.sendUrgentData(0xFF);
					Thread.sleep(10);
				} catch (SocketException e) {
					// TODO: handle exception
					return;
				}
				/*
				HashMap<String, String> recvData = new HashMap<String, String>();
				recvData.put("3.jpg", "6V33I8");
				recvData.put("2.jpg", "RCJ9785");
				recvData.put("1.jpg", "9318VA");
				dataControl.sendData(recvData);
				Thread.sleep(2000);
				*/
			}
			
			while(port == 5002) {

				if(dataControl.getData().getPlaceData().isEmpty())	
					continue;
				
				HashMap<String, String> sendData = dataControl.getData().getPlaceData();
				
				if(sendData.equals(prevSendData))	
					continue;
				
				dataControl.sendData(sendData);
				Thread.sleep(500);
				prevSendData = sendData;
				dataControl.getData().setPlaceData(new HashMap<String, String>());
				
				try {
					socket.sendUrgentData(0xFF);
					Thread.sleep(10);
				} catch (SocketException e) {
					// TODO: handle exception
					return;
				}
				/*
				HashMap<String, String> recvData = new HashMap<String, String>();
				recvData.put("3.jpg", "6V33I8");
				recvData.put("2.jpg", "RCJ9785");
				recvData.put("1.jpg", "9318VA");
				dataControl.sendData(recvData);
				Thread.sleep(2000);
				*/
			}
			
			while(port == 5004) {

				if(dataControl.getData().getEnterPlateData().isEmpty())	
					continue;
				
				HashMap<String, String> sendData = dataControl.getData().getEnterPlateData();
				
				if(sendData.equals(prevSendData))	
					continue;
				
				dataControl.sendData(sendData);
				Thread.sleep(500);
				prevSendData = sendData;
				dataControl.getData().setEnterPlateData(new HashMap<String, String>());
				
				try {
					socket.sendUrgentData(0xFF);
					Thread.sleep(10);
				} catch (SocketException e) {
					// TODO: handle exception
					return;
				}
				/*
				HashMap<String, String> recvData = new HashMap<String, String>();
				recvData.put("3.jpg", "6V33I8");
				dataControl.sendData(recvData);
				Thread.sleep(2000);
				*/
			}
			
			while(port == 5006) {

				if(dataControl.getData().getExitPlateData().isEmpty())	
					continue;
				
				HashMap<String, String> sendData = dataControl.getData().getExitPlateData();
				
				if(sendData.equals(prevSendData))	
					continue;
				
				dataControl.sendData(sendData);
				Thread.sleep(500);
				prevSendData = sendData;
				dataControl.getData().setExitPlateData(new HashMap<String, String>());
				
				try {
					socket.sendUrgentData(0xFF);
					Thread.sleep(10);
				} catch (SocketException e) {
					// TODO: handle exception
					return;
				}
				/*
				HashMap<String, String> recvData = new HashMap<String, String>();
				recvData.put("3.jpg", "6V33I8");
				dataControl.sendData(recvData);
				Thread.sleep(2000);
				*/
			}
			
			while(port == 5008) {
				
				byte sendData = dataControl.getData().getPlaceInfo();	
				
				if(sendData == prevPlaceInfo)	
					continue;
				
				dataControl.sendHexData(sendData);
				Thread.sleep(500);
				prevPlaceInfo = sendData;
				dataControl.getData().setPlaceInfo((byte) 0x00);
				
				try {
					socket.sendUrgentData(0xFF);
					Thread.sleep(10);
				} catch (SocketException e) {
					// TODO: handle exception
					return;
				}
			}
			
			while(port == 5009) {

				byte sendData = dataControl.getData().getPlaceInfo();
				
				if(sendData == prevPlaceInfo)	
					continue;
				
				dataControl.sendHexData(sendData);
				Thread.sleep(500);
				prevPlaceInfo = sendData;
				dataControl.getData().setPlaceInfo((byte) 0x00);
				
				try {
					socket.sendUrgentData(0xFF);
					Thread.sleep(10);
				} catch (SocketException e) {
					// TODO: handle exception
					return;
				}
			}
			
			while((data = reader.readLine()) != null) {
				
				if(port == 5000) {
					
					System.out.println("Port " + port + " recv: " + data);
					dataControl.recvData(port, data);
					data = "";
				}
			
				if(port == 5003) {
					
					System.out.println("Port " + port + " recv: " + data);
					dataControl.recvData(port, data);
					data = "";
				}
				
				if(port == 5005) {
					
					System.out.println("Port " + port + " recv: " + data);
					dataControl.recvData(port, data);
					data = "";
				}
				
				if(port == 5007) {
					
					System.out.println("Port " + port + " recv: " + data);
					dataControl.recvData(port, data);
					data = "";
				}
				/*
				System.out.println("Port " + port + " recv: " + data);
				data = data.replace("\\", "");
				data = data.substring(1, data.length() - 1); 
				System.out.println("JSON format: " + data);
				dataControl.recvData(data);
				writer.println(data);
				writer.println("1.jpg=RCJ9785");
				writer.flush();
				data = "";
				*/
				
				try {
					socket.sendUrgentData(0xFF);
					Thread.sleep(10);
				} catch (SocketException e) {
					// TODO: handle exception
					return;
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
