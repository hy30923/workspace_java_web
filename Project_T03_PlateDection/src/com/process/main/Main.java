package com.process.main;
import java.io.BufferedReader;
import java.io.PrintWriter;
import utils.service.datacontrol.Data;
import utils.service.datacontrol.DataControl;
import utils.service.server.ServerMain;

public class Main {
	
	public static int[] ports = new int[Config.N_PORTS];
	public static PrintWriter[] writers = new PrintWriter[Config.N_PORTS];
	public static BufferedReader[] readers = new BufferedReader[Config.N_PORTS];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int nPorts = Config.N_PORTS;
		Data data = new Data();

		DataControl[] dataControls = new DataControl[nPorts];
		for(int i = 0 ; i < nPorts ; i++) {
			
			dataControls[i] = new DataControl(data);
		}
		
		new ServerMain().startServer(dataControls, nPorts);
	}
}