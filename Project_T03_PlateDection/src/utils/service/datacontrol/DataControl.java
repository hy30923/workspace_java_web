package utils.service.datacontrol;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;

public class DataControl {
	
	private Data data = null;
	
	private PrintWriter writer = null;
	private DataOutputStream dOut = null;
	private int port = 0;

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

	public DataOutputStream getdOut() {
		return dOut;
	}

	public void setdOut(DataOutputStream dOut) {
		this.dOut = dOut;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void recvData(int port, String data) throws InterruptedException {
		
		if(port == 5000 || port == 5003 || port == 5005) {
			
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
			
			if(port == 5003) {
				
				this.data.setEnterPlateData(inData);
				return;
			}
			
			if(port == 5005) {
				
				this.data.setExitPlateData(inData);
				return;
			}
		}
		
		if(port == 5007) {
			
			byte inData = 0x00;
			if(data.contains("1"))	inData |= 0x01;
			if(data.contains("2"))	inData |= 0x02;
			if(data.contains("3"))	inData |= 0x04;
			
			this.data.setPlaceInfo(inData);
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
	
	public void sendHexData(Object data) throws IOException {
		
		if(data == null)
			return;
		
		System.out.println("[" + new Date(System.currentTimeMillis()).toString() + "]" + " Send to Port " + port + ": 0x" + byteToHex((byte) data));
		dOut.write((byte) data);
		dOut.flush();
	}
	
	private String byteToHex(byte b) {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%02x", b).toUpperCase());

        return sb.toString();
    }
}
