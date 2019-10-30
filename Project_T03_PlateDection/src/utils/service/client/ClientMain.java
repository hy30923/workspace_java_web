package utils.service.client;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import utils.service.client.Client;

public class ClientMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String argv = br.readLine();
		Client client = new Client("127.0.0.1", Integer.parseInt(argv));
		//new Client(argv.split(":")[0], Integer.parseInt(argv.split(":")[1])).connect();
		client.connect();
		client.destroy();
	}
}
