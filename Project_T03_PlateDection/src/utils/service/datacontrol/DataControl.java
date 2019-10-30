package utils.service.datacontrol;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;

public class DataControl {
	
	private Data data;
	
	private PrintWriter writer;
	private int port;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public DataControl(Data data) {
		// TODO Auto-generated constructor stub
		this.data = data;
	}

	public PrintWriter getWriter() {
		return writer;
	}

	public void setWriter(PrintWriter writer) {
		this.writer = writer;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void recvData(int port, String data) throws InterruptedException {
		
		if(port == 5000 || port == 5002) {
			
			if(data.equals("")) {
				
				return;
			}
			
			data = data.replace("\\", "");
			data = data.substring(2, data.length() - 2); 
			System.out.println("JSON format: " + data);
	
			String[] strs = data.split("[:,]");
	
			HashMap<String, String> inData = new HashMap<String, String>();
			for(int i = 0 ; i < strs.length ; i += 2) {
				
				inData.put(strs[i].replace("\"", "").trim(), strs[i+1].replace("\"", "").trim());
			}
		
			if(port == 5000) {
				
				this.data.setPlaceData(inData);
				return;
			}
			
			if(port == 5002) {
				
				this.data.setPlateData(inData);
				return;
			}
		}
		
		if(port == 5005) {
			
			//...
			return;
		}
		
		this.data.setTest(data);
	}
	
	public void sendData(Object data) throws InterruptedException {
		
		if(data == null)	
			return;
		
		System.out.println("[" + new Date(System.currentTimeMillis()).toString() + "]" + " Send to Port " + port + ": " + data.toString());
		writer.println(data);
		writer.flush();
	}
}
